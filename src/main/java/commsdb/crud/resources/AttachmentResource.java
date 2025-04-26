
package commsdb.crud.resources;

import java.net.URI;
import java.util.List;

import commsdb.crud.entities.Attachment;
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

@Path("/attachement")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AttachmentResource {

    @GET
    public List<Attachment> list() {
        return Attachment.listAll();
    }

    @GET
    @Path("/{id}")
    public Attachment get(Long id) {
        return Attachment.findById(id);
    }

    @POST
    @Transactional
    public Response create(Attachment Attachement) {
        Attachement.persist();
        return Response.created(URI.create("/Attachements/" + Attachement.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Attachment update(Long id, Attachment attachement) {
        Attachment entity = Attachment.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the Attachement parameter to the existing entity
        entity.name = attachement.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Attachment entity = Attachment.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return Attachment.count();
     }
}
