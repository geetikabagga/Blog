package dropwizardapp;

import dropwizardapp.resources.CityResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class CityApplicationStart extends Application<CityConfiguration> {//is this a standard way of passing parameters?
    public static void main(String[] args) throws Exception {
//        Application<CityConfiguration> obj = new CityApplicationStart(); possible uses of this?
        new CityApplicationStart().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-app";
    }

    @Override
    public void initialize(Bootstrap<CityConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(CityConfiguration configuration, Environment environment) {
        CityResource resource = new CityResource(
                configuration.getDefaultId()
        );
        environment.jersey().register(new CityResource(configuration.getDefaultId()));
    }
}
