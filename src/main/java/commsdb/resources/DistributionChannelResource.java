
package commsdb.resources;

import java.net.URI;
import java.util.List;

import commsdb.entities.DistributionChannel;
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

@Path("/distributionChannel")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DistributionChannelResource {

    @GET
    public List<DistributionChannel> list() {
        return DistributionChannel.listAll();
    }

    @GET
    @Path("/{id}")
    public DistributionChannel get(Long id) {
        return DistributionChannel.findById(id);
    }

    @POST
    @Transactional
    public Response create(DistributionChannel DistributionChannel) {
        DistributionChannel.persist();
        return Response.created(URI.create("/DistributionChannels/" + DistributionChannel.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public DistributionChannel update(Long id, DistributionChannel distributionChannel) {
        DistributionChannel entity = DistributionChannel.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the DistributionChannel parameter to the existing entity
        entity.name = distributionChannel.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        DistributionChannel entity = DistributionChannel.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

     @GET
     @Path("/count")
     public Long count() {
         return DistributionChannel.count();
     }
}
