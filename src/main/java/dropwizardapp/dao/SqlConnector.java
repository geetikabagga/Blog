package dropwizardapp.dao;

import dropwizardapp.dao.models.City;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class SqlConnector implements DbInterface{
    @Override
    public City fetchCityById(int id) throws IOException {
        String url = "jdbc:mysql://localhost:3306/city_info";
        String username = "root";
        String password = "";

        System.out.println("Connecting database...");
        List<City> cities;
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from cities");

            ResultSetHandler<List<City>> resultSetHandler = new BeanListHandler<City>(City.class);
            cities = resultSetHandler.handle(rs);
//            while(rs.next())
//                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        if(id > cities.size() || id < 1) {
            return null;
        }
        return cities.get(id-1);
    }
}
