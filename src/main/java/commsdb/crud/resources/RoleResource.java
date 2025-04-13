
package commsdb.crud.resources;

import java.net.URI;
import java.util.List;

import commsdb.crud.entities.Role;
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

@Path("/role")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoleResource {

    @GET
    public List<Role> list() {
        return Role.listAll();
    }

    @GET
    @Path("/{id}")
    public Role get(Long id) {
        return Role.findById(id);
    }

    @POST
    @Transactional
    public Response create(Role Role) {
        Role.persist();
        return Response.created(URI.create("/Roles/" + Role.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Role update(Long id, Role role) {
        Role entity = Role.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the Role parameter to the existing entity
        entity.name = role.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Role entity = Role.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return Role.count();
     }
}
