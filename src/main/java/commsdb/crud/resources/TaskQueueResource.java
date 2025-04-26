
package commsdb.crud.resources;

import commsdb.crud.entities.TaskQueue;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/taskQueue")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskQueueResource {

    @GET
    public List<TaskQueue> list() {
        return TaskQueue.listAll();
    }

    @GET
    @Path("/{id}")
    public TaskQueue get(Long id) {
        return TaskQueue.findById(id);
    }

    @POST
    @Transactional
    public Response create(TaskQueue TaskQueue) {
        TaskQueue.persist();
        return Response.created(URI.create("/TaskQueue/" + TaskQueue.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public TaskQueue update(Long id, TaskQueue flexSchema) {
        TaskQueue entity = TaskQueue.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the FlexSchema parameter to the existing entity
       // entity.name = flexSchema.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        TaskQueue entity = TaskQueue.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return TaskQueue.count();
     }
}
