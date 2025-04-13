
package commsdb.crud.resources;

import java.net.URI;
import java.util.List;

import commsdb.crud.entities.RiskLevel;
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

@Path("/riskLevel")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RiskLevelResource {

    @GET
    public List<RiskLevel> list() {
        return RiskLevel.listAll();
    }

    @GET
    @Path("/{id}")
    public RiskLevel get(Long id) {
        return RiskLevel.findById(id);
    }

    @POST
    @Transactional
    public Response create(RiskLevel RiskLevel) {
        RiskLevel.persist();
        return Response.created(URI.create("/RiskLevels/" + RiskLevel.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public RiskLevel update(Long id, RiskLevel riskLevel) {
        RiskLevel entity = RiskLevel.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the RiskLevel parameter to the existing entity
        entity.name = riskLevel.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        RiskLevel entity = RiskLevel.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return RiskLevel.count();
     }
}
