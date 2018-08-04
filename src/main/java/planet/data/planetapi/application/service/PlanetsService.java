package planet.data.planetapi.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import planet.data.planetapi.Infrastructure.repository.PlanetRepository;
import planet.data.planetapi.application.client.SwapiClient;
import planet.data.planetapi.domain.Planet;
import planet.data.planetapi.domain.Results;
import planet.data.planetapi.domain.Swapi;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PlanetsService {

    private final SwapiClient swapiClient;
    private final PlanetRepository planetRepository;

    public Swapi findAllPlanetsSW(){
        return swapiClient.SwapifindAll();
    }

    public Optional<Planet> addPlanet(Planet planetTransient){
        Stream<Results> resultsStream = Optional.ofNullable(findAllPlanetsSW()).get()
                .getResults().stream();

        resultsStream.forEach(results -> {
            if(results.getName().equalsIgnoreCase(planetTransient.getName())){
                planetTransient.setFilmsOccurs(results.getFilms().stream().count());
            }
        });

        Optional<Planet> planet = Optional.ofNullable(planetTransient)
                .map(planetRepository::save);

        return planet;
    }

    public Optional<Planet> removePlanet(String nome){
        Optional<Planet> planetRepo  =  planetRepository.findByName(nome);
        planetRepo.ifPresent(planetRepository::delete);
        return planetRepo;
    }

    public Optional<Planet> findPlanetByName(String nome){
       return planetRepository.findByName(nome);
    }


    public Optional<Planet> findPlanetById(String id){
        return planetRepository.findById(id);
    }

    public Optional<List<Planet>> findAllPlanets(){
        return Optional.ofNullable(planetRepository.findAll());
    }

}
