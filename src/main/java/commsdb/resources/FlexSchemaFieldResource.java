
package commsdb.resources;

import java.net.URI;
import java.util.List;

import commsdb.entities.FlexSchemaField;
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

@Path("/flexSchemaField")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlexSchemaFieldResource {

    @GET
    public List<FlexSchemaField> list() {
        return FlexSchemaField.listAll();
    }

    @GET
    @Path("/{id}")
    public FlexSchemaField get(Long id) {
        return FlexSchemaField.findById(id);
    }

    @POST
    @Transactional
    public Response create(FlexSchemaField FlexSchemaField) {
        FlexSchemaField.persist();
        return Response.created(URI.create("/FlexSchemaFields/" + FlexSchemaField.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public FlexSchemaField update(Long id, FlexSchemaField flexSchemaField) {
        FlexSchemaField entity = FlexSchemaField.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the FlexSchemaField parameter to the existing entity
        entity.name = flexSchemaField.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        FlexSchemaField entity = FlexSchemaField.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return FlexSchemaField.count();
     }
}
