package dropwizardapp.dao;

import dropwizardapp.dao.models.City;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class SqlConnector implements DbInterface{
    String url = "jdbc:mysql://localhost:3306/city_info";
    String username = "root";
    String password = "";
    Connection connection;

    public SqlConnector() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");

        } catch (SQLException e) {
//            throw new IllegalStateException("Cannot connect the database!", e);
            System.out.println(e);
        }
    }
    @Override
    public City fetchCityById(int id) throws IOException {
        List<City> cities = null;
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from cities");
            ResultSetHandler<List<City>> resultSetHandler = new BeanListHandler<City>(City.class);
            cities = resultSetHandler.handle(rs);
        } catch (SQLException e) {
//            throw new IllegalStateException("Cannot connect the database!", e);
            System.out.println(e);
        }

        if(id > cities.size() || id < 1) {
            return null;
        }
        return cities.get(id-1);
    }
}
