package planet.Infrastructure.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import planet.domain.Planet;

import java.util.Optional;

public interface PlanetRepository extends MongoRepository<Planet, String>{

     Optional<Planet> findByName(String name);
}
