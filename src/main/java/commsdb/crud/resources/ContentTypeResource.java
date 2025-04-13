
package commsdb.crud.resources;

import java.net.URI;
import java.util.List;

import commsdb.crud.entities.ContentType;
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

@Path("/contentType")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContentTypeResource {

    @GET
    public List<ContentType> list() {
        return ContentType.listAll();
    }

    @GET
    @Path("/{id}")
    public ContentType get(Long id) {
        return ContentType.findById(id);
    }

    @POST
    @Transactional
    public Response create(ContentType ContentType) {
        ContentType.persist();
        return Response.created(URI.create("/ContentTypes/" + ContentType.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public ContentType update(Long id, ContentType contentType) {
        ContentType entity = ContentType.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the ContentType parameter to the existing entity
        entity.name = contentType.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        ContentType entity = ContentType.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return ContentType.count();
     }
}
