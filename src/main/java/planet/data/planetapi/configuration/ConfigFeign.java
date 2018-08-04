package planet.data.planetapi.configuration;

import feign.Feign;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigFeign {
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
}
