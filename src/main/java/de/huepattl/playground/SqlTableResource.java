package de.huepattl.playground;

import io.agroal.api.AgroalDataSource;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Lists the contents of a table having no knowledge of JPA entities.
 * It takes a table name as argument and lists all the contents. Do not use
 * such code in production, since it allows for SQL injection and DoS since
 * we load the entire table into memory instead of streaming/using cursors
 * and thus may run into problems on huge tables.
 */
@Path("/table/{table}")
@Produces(MediaType.TEXT_HTML)
public class SqlTableResource {

    @Inject
    AgroalDataSource dataSource;

    @GET
    public String showTable(@PathParam("table") String table) throws SQLException {
        var html = new StringBuilder();

        var dataTable = getTable("select * from " + table);

        html.append("<h1>Table: " + table + "</h1>");
        html.append("<table border='1'>");

        html.append(renderHeader(dataTable.columnNames()));

        html.append("<tbody>");
        for (var row : dataTable.rows()) {
            html.append(renderRow(row));
        }
        html.append("</tbody>");

        html.append("</table>");

        return html.toString();
    }

    private SqlTable getTable(String sql) throws SQLException {
        var columnNames = new ArrayList<String>();
        var rows = new ArrayList<List<String>>();

        Connection connection = dataSource.getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();

        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(metaData.getColumnName(i));
        }

        while (resultSet.next()) {
            var row = new ArrayList<String>();
            for (var column : columnNames) {
                row.add(resultSet.getString(column));
            }
            rows.add(row);
        }

        return new SqlTable(columnNames, rows);
    }


    private String renderHeader(List<String> columnNames) {
        var html = new StringBuilder();

        for (var name : columnNames) {
            html.append("<th>");
            html.append(name);
            html.append("</th>");
        }
        return "<thead><tr>" + html + "</tr></thead";
    }

    private String renderRow(List<String> row) {
        var html = new StringBuilder();
        for (var columnValue : row) {
            html.append("<td>");
            html.append(columnValue);
            html.append("</td>");
        }
        return "<tr>" + html + "</tr>";
    }

}
