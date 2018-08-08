package planet.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import planet.Infrastructure.repository.PlanetRepository;
import planet.application.rest.PlanetsApiController;
import planet.application.service.PlanetsService;
import planet.domain.Planet;
import planet.domain.Results;
import planet.domain.Swapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PlanetsApiController.class})
@WebMvcTest(PlanetsApiController.class)
public class PlanetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanetsService planetsService;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PlanetRepository planetRepository;


    @Test
    public void addPlanet_thenStatus200Ok() throws Exception {

        //given
        Optional<Planet> planet = Optional.ofNullable(Planet.builder()
                .climate("temperate")
                .name("Alderan")
                .terrain("terrain")
                .build());

        String filmes = "Alderam";
        List<String> films = Arrays.asList(filmes);
        Results resultsFilmes = Results.builder().climate("temperate").films(films).name("Alderan").build();

        final String json = objectMapper.writeValueAsString(planet);

        List<Results> results = Arrays.asList(resultsFilmes);
        when(planetsService.findAllPlanetsSW()).thenReturn(Swapi.builder().results(results).build());
        when(planetRepository.save(planet.get())).thenReturn(planet.get());
        when(planetsService.addPlanet(planet.get())).thenReturn(planet);

        this.mockMvc.perform(post("/planet")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void addPlanet_thenStatus400BadRequest() throws Exception {

        //given
        Optional<Planet> planet = Optional.ofNullable(Planet.builder().build());

        String filmes = "Alderam";
        List<String> films = Arrays.asList(filmes);
        Results resultsFilmes = Results.builder().climate("temperate").films(films).name("Alderan").build();

        final String json = objectMapper.writeValueAsString(planet);

        //then
        this.mockMvc.perform(post("/planet")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addPlanet_thenStatus404NotFound() throws Exception {

        //given
        Optional<Planet> planet = Optional.ofNullable(Planet.builder()
                .climate("temperate")
                .name("Alderan")
                .terrain("terrain")
                .build());

        String filmes = "Alderam";
        List<String> films = Arrays.asList(filmes);
        Results resultsFilmes = Results.builder().climate("temperate").films(films).name("Alderan").build();

        final String json = objectMapper.writeValueAsString(planet);

        List<Results> results = Arrays.asList(resultsFilmes);
        when(planetsService.findAllPlanetsSW()).thenReturn(Swapi.builder().results(results).build());
        when(planetRepository.save(planet.get())).thenReturn(planet.get());
        when(planetsService.addPlanet(planet.get())).thenReturn(Optional.empty());

        //then
        this.mockMvc.perform(post("/planet")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void removePlanet_thenStatus200Ok() throws Exception {

        //given
        Optional<Planet> planet = Optional.ofNullable(Planet.builder().name("Alderan").build());

        when(planetRepository.findByName(planet.get().getName())).thenReturn(planet);
        when(planetsService.removePlanet(planet.get().getName())).thenReturn(planet);

        this.mockMvc.perform(delete("/planet/" + planet.get().getName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void removePlanet_thenStatus404NotFound() throws Exception {

        //given
        Optional<Planet> planet = Optional.ofNullable(Planet.builder().name("Alderan").build());

        when(planetRepository.findByName(planet.get().getName())).thenReturn(planet);
        when(planetsService.removePlanet(planet.get().getName())).thenReturn(Optional.empty());

        this.mockMvc.perform(delete("/planet/".concat(planet.get().getName()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getPlanets_thenStatus200Ok() throws Exception {

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
        when(planetsService.findAllPlanets()).thenReturn(Optional.ofNullable(planets));


        this.mockMvc.perform(get("/planets")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getPlanets_thenStatus404NotFound() throws Exception {

        //given
        List<Planet> planets = new ArrayList<>();
        Optional<Planet> planet = Optional.ofNullable(Planet.builder().build());
        planets.add(planet.get());
        //when
        when(planetRepository.findAll()).thenReturn(Optional.ofNullable(planets).get());
        when(planetsService.findAllPlanets()).thenReturn(Optional.empty());


        this.mockMvc.perform(get("/planets")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void getPlanetById_thenStatus200Ok() throws Exception {

        //given
        Optional<Planet> planet = Optional.ofNullable(Planet.builder()
                .id("5b648f94ef73da00077d4359")
                .climate("temperate")
                .filmsOccurs(1L)
                .name("Alderan")
                .build());

        //when
        when(planetRepository.findById(planet.get().getId())).thenReturn(Optional.ofNullable(planet).get());
        when(planetsService.findPlanetById(planet.get().getId())).thenReturn(planet);


        this.mockMvc.perform(get("/planet/id/".concat(planet.get().getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getPlanetById_thenStatus404NotFound() throws Exception {

        //given
        Optional<Planet> planet = Optional.ofNullable(Planet.builder().id("5b648f94ef73da00077d4359").build());

        //when
        when(planetRepository.findById(planet.get().getId())).thenReturn(Optional.ofNullable(planet).get());
        when(planetsService.findPlanetById(planet.get().getId())).thenReturn(Optional.empty());


        this.mockMvc.perform(get("/planet/id/".concat(planet.get().getId()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getPlanetByName_thenStatus200Ok() throws Exception {

        //given
        Optional<Planet> planet = Optional.ofNullable(Planet.builder()
                .id("5b648f94ef73da00077d4359")
                .climate("temperate")
                .filmsOccurs(1L)
                .name("Alderan")
                .build());

        //when
        when(planetRepository.findByName(planet.get().getName())).thenReturn(Optional.ofNullable(planet).get());
        when(planetsService.findPlanetByName(planet.get().getName())).thenReturn(planet);


        this.mockMvc.perform(get("/planet/name/".concat(planet.get().getName()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getPlanetByName_thenStatus404NotFound() throws Exception {

        //given
        Optional<Planet> planet = Optional.ofNullable(Planet.builder().name("Alderan").build());

        //when
        when(planetRepository.findByName(planet.get().getName())).thenReturn(Optional.ofNullable(planet).get());
        when(planetsService.findPlanetByName(planet.get().getName())).thenReturn(Optional.empty());


        this.mockMvc.perform(get("/planet/name/".concat(planet.get().getName()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
