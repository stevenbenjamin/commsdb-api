
package commsdb.crud.resources;

import java.net.URI;
import java.util.List;

import commsdb.crud.entities.Submission;
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

@Path("/submission")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubmissionResource {

    @GET
    public List<Submission> list() {
        return Submission.listAll();
    }

    @GET
    @Path("/{id}")
    public Submission get(Long id) {
        return Submission.findById(id);
    }

    @POST
    @Transactional
    public Response create(Submission Submission) {
        Submission.persist();
        return Response.created(URI.create("/Submissions/" + Submission.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Submission update(Long id, Submission submission) {
        Submission entity = Submission.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the Submission parameter to the existing entity
        entity.data = submission.data;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Submission entity = Submission.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return Submission.count();
     }
}
