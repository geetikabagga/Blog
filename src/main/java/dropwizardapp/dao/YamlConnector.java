package dropwizardapp.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import dropwizardapp.dao.models.City;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class YamlConnector implements DbInterface {
    @Override
    public City fetchCityById(int id) throws IOException {
        // Loading the YAML file from the /resources folder
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ObjectMapper om = new ObjectMapper(new YAMLFactory());

        InputStream inputStream = classLoader.getResourceAsStream("cities.yaml");

        City[] cities = new City[0];
        try {
            cities = om.readValue(inputStream, City[].class);
            System.out.println("array print karo" + Arrays.toString(cities));
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        if(id > cities.length || id < 1) {
            return null;
        }
        else return cities[id-1];
    }
}
