package de.huepattl.playground;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class PersonResource {

    @Inject
    PersonRepository personRepository;

    @GET
    @Path("{id}")
    public Person get(@PathParam("id") long id) {
        Log.info("Received /person for ID " + id);
        var entity = personRepository.findById(id);
        return PersonMapper.fromEntityToDto(entity);
    }

    @POST
    public Response persist(Person person) {
        Log.info("Received " + person);
        var entity = PersonMapper.fromDtoToEntity(person);
        personRepository.persist(entity);
        return Response.ok().build();
    }
}
