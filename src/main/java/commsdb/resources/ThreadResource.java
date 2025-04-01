
package commsdb.resources;

import java.net.URI;
import java.util.List;

import commsdb.entities.Thread;
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

@Path("/thread")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ThreadResource {

    @GET
    public List<Thread> list() {
        return Thread.listAll();
    }

    @GET
    @Path("/{id}")
    public Thread get(Long id) {
        return Thread.findById(id);
    }

    @POST
    @Transactional
    public Response create(Thread Thread) {
        Thread.persist();
        return Response.created(URI.create("/Threads/" + Thread.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Thread update(Long id, Thread thread) {
        Thread entity = Thread.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the Thread parameter to the existing entity
        entity.submissionId = thread.submissionId;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Thread entity = Thread.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return Thread.count();
     }
}
