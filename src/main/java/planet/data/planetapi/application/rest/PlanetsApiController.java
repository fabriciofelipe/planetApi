package planet.data.planetapi.application.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import planet.data.planetapi.application.service.PlanetsService;
import planet.data.planetapi.domain.Planet;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
@Slf4j
@RequiredArgsConstructor
public class PlanetsApiController {

    private final PlanetsService planetsService;


    @PostMapping(value = "/planet",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Planet> addPlanet(@RequestBody @Valid Planet planet) {
        return planetsService.addPlanet(planet)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/planet/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Void> removePlanet(@PathVariable String name) {

        if(planetsService.removePlanet(name).isPresent()){
            return new ResponseEntity<>( HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/planets",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Planet>> findAllPlanets() {
        return planetsService.findAllPlanets()
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    @GetMapping(value = "/planet/name/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Planet> findPlanetByName(@PathVariable String name) {
        return planetsService.findPlanetByName(name)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/planet/id/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Planet> findPlanetById(@PathVariable String id) {
        return planetsService.findPlanetById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
