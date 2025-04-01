
package commsdb.resources;

import java.net.URI;
import java.util.List;

import commsdb.entities.Action;
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

@Path("/action")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActionResource {

    @GET
    public List<Action> list() {
        return Action.listAll();
    }

    @GET
    @Path("/{id}")
    public Action get(Long id) {
        return Action.findById(id);
    }

    @POST
    @Transactional
    public Response create(Action Action) {
        Action.persist();
        return Response.created(URI.create("/Actions/" + Action.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Action update(Long id, Action action) {
        Action entity = Action.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the Action parameter to the existing entity
        entity.name = action.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Action entity = Action.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return Action.count();
     }
}
