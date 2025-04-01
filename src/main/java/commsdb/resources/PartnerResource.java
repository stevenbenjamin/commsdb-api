
package commsdb.resources;

import java.net.URI;
import java.util.List;

import commsdb.entities.Partner;
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

@Path("/partner")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PartnerResource {

    @GET
    public List<Partner> list() {
        return Partner.listAll();
    }

    @GET
    @Path("/{id}")
    public Partner get(Long id) {
        return Partner.findById(id);
    }

    @POST
    @Transactional
    public Response create(Partner Partner) {
        Partner.persist();
        return Response.created(URI.create("/Partners/" + Partner.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Partner update(Long id, Partner partner) {
        Partner entity = Partner.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the Partner parameter to the existing entity
        entity.name = partner.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Partner entity = Partner.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return Partner.count();
     }
}
