
package commsdb.crud.resources;

import java.net.URI;
import java.util.List;

import commsdb.crud.entities.ComplianceRisk;
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

@Path("/complianceRisk")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ComplianceRiskResource {

    @GET
    public List<ComplianceRisk> list() {
        return ComplianceRisk.listAll();
    }

    @GET
    @Path("/{id}")
    public ComplianceRisk get(Long id) {
        return ComplianceRisk.findById(id);
    }

    @POST
    @Transactional
    public Response create(ComplianceRisk ComplianceRisk) {
        ComplianceRisk.persist();
        return Response.created(URI.create("/ComplianceRisks/" + ComplianceRisk.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public ComplianceRisk update(Long id, ComplianceRisk complianceRisk) {
        ComplianceRisk entity = ComplianceRisk.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the ComplianceRisk parameter to the existing entity
        entity.name = complianceRisk.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        ComplianceRisk entity = ComplianceRisk.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return ComplianceRisk.count();
     }
}
