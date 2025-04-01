
package commsdb.resources;

import java.net.URI;
import java.util.List;

import commsdb.entities.DenialReason;
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

@Path("/denialReason")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DenialReasonResource {

    @GET
    public List<DenialReason> list() {
        return DenialReason.listAll();
    }

    @GET
    @Path("/{id}")
    public DenialReason get(Long id) {
        return DenialReason.findById(id);
    }

    @POST
    @Transactional
    public Response create(DenialReason DenialReason) {
        DenialReason.persist();
        return Response.created(URI.create("/DenialReasons/" + DenialReason.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public DenialReason update(Long id, DenialReason denialReason) {
        DenialReason entity = DenialReason.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the DenialReason parameter to the existing entity
        entity.name = denialReason.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        DenialReason entity = DenialReason.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return DenialReason.count();
     }
}
