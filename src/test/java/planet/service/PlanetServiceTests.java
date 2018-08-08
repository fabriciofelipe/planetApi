package planet.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import planet.Infrastructure.repository.PlanetRepository;
import planet.application.client.SwapiClient;
import planet.application.service.PlanetsService;
import planet.domain.Planet;
import planet.domain.Results;
import planet.domain.Swapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanetServiceTests {

	@Autowired
	private PlanetsService planetsService;

	@MockBean
	private SwapiClient swapiClient;

	@MockBean
	private PlanetRepository planetRepository;

	@Test
	public void addPlanet() {

		//given

		Optional<Planet> planet = Optional.ofNullable(Planet.builder()
				                                            .climate("temperate")
				                                            .filmsOccurs(1L)
				                                            .name("Alderan")
				                                            .build());

		String filmes = "Alderam";
		List<String> films = Arrays.asList(filmes);
		Results resultsFilmes = Results.builder().climate("temperate").films(films).name("Alderan").build();

		List<Results> results = Arrays.asList(resultsFilmes);
		when(planetsService.findAllPlanetsSW()).thenReturn(Swapi.builder().results(results).build());
		when(planetRepository.save(planet.get())).thenReturn(planet.get());

		// then
		assertThat(planetsService.addPlanet(planet.get()))
			.isEqualTo(planet);
	}

	@Test
	public void removePlanet() {

		//given
		Optional<Planet> planet = Optional.ofNullable(Planet.builder()
				.climate("temperate")
				.filmsOccurs(1L)
				.name("Alderan")
				.build());
		//when
		when(planetRepository.findByName(planet.get().getName())).thenReturn(planet);

		// then
		assertThat(planetsService.removePlanet(planet.get().getName()))
				.isEqualTo(planet);
	}

	@Test
	public void findPlanetByName() {

		//given
		Optional<Planet> planet = Optional.ofNullable(Planet.builder()
				.climate("temperate")
				.filmsOccurs(1L)
				.name("Alderan")
				.build());
		//when
		when(planetRepository.findByName(planet.get().getName())).thenReturn(planet);

		// then
		assertThat(planetsService.findPlanetByName(planet.get().getName()))
				.isEqualTo(planet);
	}

	@Test
	public void findPlanetById() {

		//given
		Optional<Planet> planet = Optional.ofNullable(Planet.builder()
				.id("5b648f94ef73da00077d4359")
				.climate("temperate")
				.filmsOccurs(1L)
				.name("Alderan")
				.build());
		//when
		when(planetRepository.findById(planet.get().getId())).thenReturn(planet);

		// then
		assertThat(planetsService.findPlanetById(planet.get().getId()))
				.isEqualTo(planet);
	}


	@Test
	public void findPlanets() {


		//given
		List<Planet> planets = new ArrayList<>();
		Optional<Planet> planet = Optional.ofNullable(Planet.builder()
				.id("5b648f94ef73da00077d4359")
				.climate("temperate")
				.filmsOccurs(1L)
				.name("Alderan")
				.build());

		planets.add(planet.get());
		//when
		when(planetRepository.findAll()).thenReturn(Optional.ofNullable(planets).get());

		// then
		assertThat(planetsService.findAllPlanets())
				.isEqualTo(Optional.ofNullable(planets));
	}

}
