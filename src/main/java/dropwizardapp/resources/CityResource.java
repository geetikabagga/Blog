package dropwizardapp.resources;

import com.codahale.metrics.annotation.Timed;
import dropwizardapp.api.CityRequest;
import dropwizardapp.api.CityResponse;
import dropwizardapp.dao.DbInterface;
import dropwizardapp.dao.SqlConnector;
import dropwizardapp.dao.models.City;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class CityResource {
    private int defaultId;
    private DbInterface dataConnector;

    public CityResource(int defaultId) {
        this.defaultId = defaultId;
        dataConnector = new SqlConnector();
    }

    @Path("/get-city")
    @Timed
    @GET
    public Response getCity(@QueryParam("id") Optional<Integer> id) throws IOException {
        dropwizardapp.dao.models.City city1 = dataConnector.fetchCityById(id.orElse(defaultId));
        //yet to map  city to the city api class
//        CityResponse cityResponse = new CityResponse(city1.getId(), city1.getName(), city1.getDescription());
        if(city1 == null) {
            Response response = Response.status(Response.Status.PARTIAL_CONTENT).entity("no city found").build();
            throw new WebApplicationException(response);
        }
        CityResponse cityResponse = new CityResponse(city1.getId(), city1.getName(), city1.getDescription());
        return Response.status(Response.Status.OK).entity(cityResponse).build();
    }

    @Path("/add-city")
    @Timed
    @POST
    public Response addCity(CityRequest cityRequest) {
        dataConnector.addCity(cityRequest);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Timed
    @Path("/list-cities")
    public Response listCities(@QueryParam("bookmarkId") Optional<Integer> bookmarkId) {
        List<City> cities = dataConnector.listCities(bookmarkId.orElse(defaultId));
        List<CityResponse> CitiesResponse = new ArrayList<>();
        for(int i=0; i< cities.size(); i++){
            City city = cities.get(i);
            CityResponse cityResponse = new CityResponse(city.getId(), city.getName(), city.getDescription());
            CitiesResponse.add(cityResponse);
        }
        return Response.status(Response.Status.OK).entity(CitiesResponse).build();
    }
}
