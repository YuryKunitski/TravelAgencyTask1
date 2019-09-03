package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.service.CountryService;
import by.epam.kunitski.travelagency.web.config.AppInitializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppInitializer.class})
public class CountryControllerTest {

  @Mock
  private CountryService countryService;

  @InjectMocks
  private CountryController countryController;

  private MockMvc mockMvc;
  private Country country = new Country();

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
  }

  @Test
  public void addCountryView() {
  }

  @Test
  public void addCountry() {

  }

  @Test
  public void viewAllCountries() throws Exception {
    List<Country> countryList = new ArrayList<>();
    countryList.add(new Country());
    countryList.add(new Country());

    when(countryService.findAll()).thenReturn(countryList);

    mockMvc.perform((RequestBuilder) get("/view_all_countries"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("allContries"))
            .andExpect(model().attribute("countries", countryList));
  }

  @Test
  public void updateCountryView() throws Exception {

    country.setId(1);
    country.setName("China");

    when(countryService.findById(1)).thenReturn(Optional.of(country));

    mockMvc.perform(get("/update_country_view").param("country_id", "1"))
            .andExpect(status().isOk())
            .andExpect(view().name("addCountry"))
            .andExpect(model().attribute("country_id", 1));
  }

  @Test
  public void updateCountry() throws Exception {

    country.setId(1);
    country.setName("China");

    when(countryService.update(country)).thenReturn(new HashSet<>());

    mockMvc.perform(post("/update_country").param("country_id", "1"))
            .andExpect(status().isOk())
            .andExpect(view().name("redirect:/profile_admin?country_updated"));

  }

  @Test
  public void removeCountry() {
  }
}