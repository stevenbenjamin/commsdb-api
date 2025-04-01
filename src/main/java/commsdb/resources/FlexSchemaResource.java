
package commsdb.resources;

import java.net.URI;
import java.util.List;

import commsdb.entities.FlexSchema;
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

@Path("/flexSchema")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlexSchemaResource {

    @GET
    public List<FlexSchema> list() {
        return FlexSchema.listAll();
    }

    @GET
    @Path("/{id}")
    public FlexSchema get(Long id) {
        return FlexSchema.findById(id);
    }

    @POST
    @Transactional
    public Response create(FlexSchema FlexSchema) {
        FlexSchema.persist();
        return Response.created(URI.create("/FlexSchemas/" + FlexSchema.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public FlexSchema update(Long id, FlexSchema flexSchema) {
        FlexSchema entity = FlexSchema.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the FlexSchema parameter to the existing entity
        entity.name = flexSchema.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        FlexSchema entity = FlexSchema.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return FlexSchema.count();
     }
}
