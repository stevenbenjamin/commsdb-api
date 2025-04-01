
package commsdb.resources;

import java.net.URI;
import java.util.List;

import commsdb.entities.Review;
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

@Path("/review")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewResource {

    @GET
    public List<Review> list() {
        return Review.listAll();
    }

    @GET
    @Path("/{id}")
    public Review get(Long id) {
        return Review.findById(id);
    }

    @POST
    @Transactional
    public Response create(Review Review) {
        Review.persist();
        return Response.created(URI.create("/Reviews/" + Review.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Review update(Long id, Review review) {
        Review entity = Review.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the Review parameter to the existing entity
        entity.sequenceNum = review.sequenceNum;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Review entity = Review.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return Review.count();
     }
}
