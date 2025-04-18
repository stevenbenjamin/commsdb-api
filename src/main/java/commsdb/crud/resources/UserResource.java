
package commsdb.crud.resources;

import java.net.URI;
import java.util.List;

import commsdb.crud.entities.User;
import io.quarkus.logging.Log;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @GET
    public List<User> list() {
        return User.listAll();
    }

    @GET
    @Path("/{id}")

    public User get(Long id, @Context SecurityContext securityContext) {
        var v = securityContext;
        var p = v.getUserPrincipal();
        System.out.printf("\n\n\nReceived from %s: %s\n\n", p.getName(), securityContext.isUserInRole("admin"));

        Log.infof("Received from %s: %s\n\n", p.getName(), securityContext.isUserInRole("admin"));
        return User.findById(id);
    }

    @POST
    @Transactional
    public Response create(User User) {
        User.persist();
        return Response.created(URI.create("/Users/" + User.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public User update(Long id, User user) {
        User entity = User.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the User parameter to the existing entity
        entity.firstName = user.firstName ;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        User entity = User.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return User.count();
     }
}
