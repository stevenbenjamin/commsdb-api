
package commsdb.crud.resources;

import java.net.URI;
import java.util.List;

import commsdb.crud.entities.Industry;
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

@Path("/industry")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IndustryResource {

    @GET
    public List<Industry> list() {
        return Industry.listAll();
    }

    @GET
    @Path("/{id}")
    public Industry get(Long id) {
        return Industry.findById(id);
    }

    @POST
    @Transactional
    public Response create(Industry Industry) {
        Industry.persist();
        return Response.created(URI.create("/Industrys/" + Industry.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Industry update(Long id, Industry industry) {
        Industry entity = Industry.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the Industry parameter to the existing entity
        entity.name = industry.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Industry entity = Industry.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return Industry.count();
     }
}
