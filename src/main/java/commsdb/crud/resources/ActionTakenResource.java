
package commsdb.crud.resources;

import commsdb.crud.entities.Action;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/actionTaken")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ActionTakenResource {

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
    public Response create(Action actionTaken) {
        actionTaken.persist();
        return Response.created(URI.create("/actionTaken/" + actionTaken.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Action update(Long id, Action flexSchema) {
        Action entity = Action.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the FlexSchema parameter to the existing entity
        //entity.name = flexSchema.name;

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
