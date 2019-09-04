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
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
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

        country = WebInitEntity.initCountry();
    }

    @Test
    public void addCountryView() throws Exception {
        mockMvc.perform((RequestBuilder) get("/add_country_view"))
                .andExpect(status().isOk())
                .andExpect(view().name("addCountry"))
                .andExpect(model().attributeDoesNotExist());
    }

    @Test
    public void addCountry() throws Exception {

        when(countryService.add(country)).thenReturn(new HashSet<>());

        mockMvc.perform((RequestBuilder) post("/add_country")
                .param("name", "China"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/profile_admin?country_added"))
                .andExpect(model().attributeDoesNotExist());

        verify(countryService, times(1)).add(country);
        verifyNoMoreInteractions(countryService);
    }

    @Test
    public void viewAllCountries() throws Exception {
        List<Country> countryList = new ArrayList<>();
        countryList.add(new Country());
        countryList.add(new Country());

        when(countryService.findAll()).thenReturn(countryList);

        mockMvc.perform((RequestBuilder) get("/view_all_countries"))
                .andExpect(status().isOk())
                .andExpect(view().name("allContries"))
                .andExpect(model().attribute("countries", hasSize(2)));

        verify(countryService, times(1)).findAll(); //Verify that the findAll() method of our mock object was called only once.
        verifyNoMoreInteractions(countryService); //Ensure that other methods of the mock object were not called during the test.
    }

    @Test
    public void updateCountryView() throws Exception {

        country.setId(1);

        when(countryService.findById(1)).thenReturn(Optional.of(country));

        mockMvc.perform(get("/update_country_view")
                .param("country_id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("addCountry"))
                .andExpect(model().attribute("country_id", 1));

        verify(countryService, times(1)).findById(1);
        verifyNoMoreInteractions(countryService);
    }

    @Test
    public void updateCountry() throws Exception {

        country.setId(1);

        when(countryService.update(country)).thenReturn(new HashSet<>());

        mockMvc.perform(post("/update_country")
                .param("country_id", "1")
                .param("name", "China"))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(model().attributeDoesNotExist())
                .andExpect(view().name("redirect:/profile_admin?country_updated"));

        verify(countryService, times(1)).update(country);
        verifyNoMoreInteractions(countryService);
    }

    @Test
    public void removeCountry() throws Exception {

        when(countryService.delete(1)).thenReturn(true);

        mockMvc.perform(post("/remove_country")
                .param("country_id", "1"))
                .andExpect(status().isFound())
                .andExpect(model().attributeDoesNotExist())
                .andExpect(view().name("redirect:/profile_admin?country_removed"));

        verify(countryService, times(1)).delete(1);
        verifyNoMoreInteractions(countryService);

    }
}