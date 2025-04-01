
package commsdb.resources;

import java.net.URI;
import java.util.List;

import commsdb.entities.Status;
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

@Path("/status")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StatusResource {

    @GET
    public List<Status> list() {
        return Status.listAll();
    }

    @GET
    @Path("/{id}")
    public Status get(Long id) {
        return Status.findById(id);
    }

    @POST
    @Transactional
    public Response create(Status Status) {
        Status.persist();
        return Response.created(URI.create("/Statuss/" + Status.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Status update(Long id, Status status) {
        Status entity = Status.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the Status parameter to the existing entity
        entity.name = status.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Status entity = Status.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return Status.count();
     }
}
