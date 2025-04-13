
package commsdb.crud.resources;

import java.net.URI;
import java.util.List;

import commsdb.crud.entities.FormField;
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

@Path("/formField")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FormFieldResource {

    @GET
    public List<FormField> list() {
        return FormField.listAll();
    }

    @GET
    @Path("/{id}")
    public FormField get(Long id) {
        return FormField.findById(id);
    }

    @POST
    @Transactional
    public Response create(FormField FlexSchemaField) {
        FlexSchemaField.persist();
        return Response.created(URI.create("/formField/" + FlexSchemaField.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public FormField update(Long id, FormField flexSchemaField) {
        FormField entity = FormField.findById(id);
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
        FormField entity = FormField.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return FormField.count();
     }
}
