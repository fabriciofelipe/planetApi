package planet.application.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import planet.domain.Swapi;


@FeignClient(name="swapi", url = "${integration.swapi.url}")
public interface SwapiClient {

    @RequestMapping(value = "/planets", method = RequestMethod.GET)
    Swapi SwapifindAll();
}
