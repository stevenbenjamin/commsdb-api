
package commsdb.resources;

import java.net.URI;
import java.util.List;

import commsdb.entities.Form;
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

@Path("/form")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FormResource {

    @GET
    public List<Form> list() {
        return Form.listAll();
    }

    @GET
    @Path("/{id}")
    public Form get(Long id) {
        return Form.findById(id);
    }

    @POST
    @Transactional
    public Response create(Form Form) {
        Form.persist();
        return Response.created(URI.create("/Forms/" + Form.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Form update(Long id, Form form) {
        Form entity = Form.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the Form parameter to the existing entity
        entity.name = form.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Form entity = Form.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return Form.count();
     }
}
