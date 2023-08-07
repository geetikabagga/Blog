package dropwizardapp;

import org.junit.Assert;
import org.junit.Test;

public class CityConfigurationTest {

    @Test
    public void testGetDefaultId() {
        CityConfiguration cityConfiguration = new CityConfiguration();
        Assert.assertEquals("Check if getter returns right value",
                1, cityConfiguration.getDefaultId());
    }

    @Test
    public void testSetDefaultId() {
        CityConfiguration cityConfiguration = new CityConfiguration();
        cityConfiguration.setDefaultId(2);
        Assert.assertEquals("Check if setter successfully set the value",
                2, cityConfiguration.getDefaultId());
    }
}
