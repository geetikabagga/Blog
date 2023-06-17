package dropwizardapp.dao;

import dropwizardapp.dao.models.City;

import java.io.IOException;

public interface DbInterface {
    public City fetchCityById(int id) throws IOException;

}
