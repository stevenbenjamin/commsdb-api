
package commsdb.crud.resources;

import commsdb.crud.entities.Event;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/Event")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {

    @GET
    public List<Event> list() {
        return Event.listAll();
    }

    @GET
    @Path("/{id}")
    public Event get(Long id) {
        return Event.findById(id);
    }

    @POST
    @Transactional
    public Response create(Event Event) {
        Event.persist();
        return Response.created(URI.create("/Event/" + Event.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Event update(Long id, Event flexSchema) {
        Event entity = Event.findById(id);
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
        Event entity = Event.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return Event.count();
     }
}
