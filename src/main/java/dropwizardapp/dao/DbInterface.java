package dropwizardapp.dao;

import dropwizardapp.api.CityRequest;
import dropwizardapp.dao.models.City;
import jakarta.inject.Singleton;

import java.io.IOException;

@Singleton
public interface DbInterface {
    public City fetchCityById(int id) throws IOException;

    public void addCity(CityRequest cityRequest);
}
