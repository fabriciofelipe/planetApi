package planet.data.planetapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PlanetapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanetapiApplication.class, args);
	}
}