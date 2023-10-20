package de.huepattl.playground;

import java.util.List;

public record SqlTable(
        List<String> columnNames,
        List<List<String>> rows
) { }
