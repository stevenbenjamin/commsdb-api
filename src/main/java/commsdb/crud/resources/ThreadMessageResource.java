
package commsdb.crud.resources;

import java.net.URI;
import java.util.List;

import commsdb.crud.entities.ThreadMessage;
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

@Path("/threadMessage")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ThreadMessageResource {

    @GET
    public List<ThreadMessage> list() {
        return ThreadMessage.listAll();
    }

    @GET
    @Path("/{id}")
    public ThreadMessage get(Long id) {
        return ThreadMessage.findById(id);
    }

    @POST
    @Transactional
    public Response create(ThreadMessage ThreadMessage) {
        ThreadMessage.persist();
        return Response.created(URI.create("/ThreadMessages/" + ThreadMessage.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public ThreadMessage update(Long id, ThreadMessage threadMessage) {
        ThreadMessage entity = ThreadMessage.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the ThreadMessage parameter to the existing entity
        entity.content = threadMessage.content;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        ThreadMessage entity = ThreadMessage.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return ThreadMessage.count();
     }
}
