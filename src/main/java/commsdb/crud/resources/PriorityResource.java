
package commsdb.crud.resources;

import java.net.URI;
import java.util.List;

import commsdb.crud.entities.Priority;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/priority")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PriorityResource {

    @GET
    public List<Priority> list() {
        return Priority.listAll();
    }

    @GET
    @Path("/{id}")
    public Priority get(Long id) {
        return Priority.findById(id);
    }

    @POST
    @Transactional
    public Response create(Priority Priority) {
        Priority.persist();
        return Response.created(URI.create("/Prioritys/" + Priority.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Priority update(Long id, Priority priority) {
        Priority entity = Priority.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the Priority parameter to the existing entity
        entity.name = priority.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Priority entity = Priority.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return Priority.count();
     }
}
