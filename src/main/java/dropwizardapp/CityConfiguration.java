package dropwizardapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.core.Configuration;
import jakarta.validation.constraints.NotNull;

public class CityConfiguration extends Configuration {
    @NotNull
    private int defaultId = 1;

    @JsonProperty
    public int getDefaultId() {
        return defaultId;
    }

    @JsonProperty
    public void setDefaultId(int id) {
        this.defaultId = id;
    }
}
