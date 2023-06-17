package dropwizardapp.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;
import dropwizardapp.dao.DbInterface;
import dropwizardapp.dao.SqlConnector;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Optional;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class CityResource {
    private int defaultId;
    private DbInterface dataConnector;

    public CityResource(int defaultId) {
        this.defaultId = defaultId;
//        dataConnector = new YamlConnector();
        dataConnector = new SqlConnector();
    }

    @Path("/get-city")
    @Timed
    @GET
    public Response getCity(@QueryParam("id") Optional<Integer> id) throws IOException {
        dropwizardapp.dao.models.City city = dataConnector.fetchCityById(id.orElse(defaultId));
        if(city == null) {
            Response response = Response.status(Response.Status.PARTIAL_CONTENT).entity("no city found").build();
            throw new WebApplicationException(response);
        }
        return Response.status(Response.Status.OK).entity(city).build();//does the code come here for an invalid id?
    }

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public class BlahResponse {

        @JsonProperty
        private String message;
    }
}
