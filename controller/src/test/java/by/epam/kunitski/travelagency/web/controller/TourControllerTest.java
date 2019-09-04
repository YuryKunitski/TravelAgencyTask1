package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.dao.entity.Hotel;
import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.service.*;
import by.epam.kunitski.travelagency.web.config.AppInitializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppInitializer.class})
public class TourControllerTest {

    @Mock
    private TourService tourService;

    @Mock
    private ReviewService reviewService;

    @Mock
    private CountryService countryService;

    @Mock
    private HotelService hotelService;

    @Mock
    private JsonParserTourService jsonParserTourService;

    @InjectMocks
    private TourController tourController;

    private MockMvc mockMvc;
    private Tour tour = new Tour();

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(tourController)
                .setViewResolvers(new StandaloneMvcTestViewResolver())
                .build();

        tour = WebInitEntity.initTour();
    }

    @Test
    public void viewTour() throws Exception {

        tour.setId(1);

        when(tourService.findById(1)).thenReturn(Optional.of(tour));
        when(reviewService.findAllByTourId(1)).thenReturn(new ArrayList<>());

        mockMvc.perform((RequestBuilder) get("/tour")
                .param("tour_id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("tour"))
                .andExpect(model().attribute("tour", tour))
                .andExpect(model().attribute("reviews", new ArrayList<>()));

        verify(tourService, times(2)).findById(1);
        verifyNoMoreInteractions(tourService);
    }

    @Test
    public void addTourView() throws Exception {

        when(countryService.findAll()).thenReturn(new ArrayList<>());
        when(hotelService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform((RequestBuilder) get("/add_tour_view"))
                .andExpect(status().isOk())
                .andExpect(view().name("addTour"))
                .andExpect(model().attribute("tour_types", Tour.TourType.values()))
                .andExpect(model().attribute("countries", new ArrayList<>()))
                .andExpect(model().attribute("hotels", new ArrayList<>()));
    }

    @Test
    public void addTourValid() throws Exception {
        Country country = WebInitEntity.initCountry();
        country.setId(1);
        Hotel hotel = WebInitEntity.initHotel();
        hotel.setId(1);

        when(tourService.add(tour)).thenReturn(new HashSet<>());
        when(countryService.findById(1)).thenReturn(Optional.of(country));
        when(hotelService.findById(1)).thenReturn(Optional.of(hotel));

        mockMvc.perform((RequestBuilder) post("/add_tour")
                .param("photo", "http://dummyimage.com/190x216.jpg/ff4444/ffffff")
                .param("date", "2019-12-14")
                .param("duration", "1")
                .param("description", "Integer non velit.")
                .param("cost", "1839.65")
                .param("hotel_id", "1")
                .param("country_id", "1")
                .param("tour_type", "ECONOM"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/profile_admin?tour_added"))
                .andExpect(model().attributeDoesNotExist());

        verify(tourService, times(1)).add(tour);
        verifyNoMoreInteractions(tourService);
    }

    @Test
    public void addTourNotValid() throws Exception {

        when(countryService.findAll()).thenReturn(new ArrayList<>());
        when(hotelService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform((RequestBuilder) post("/add_tour")
                .param("photo", "http://dummyimage.com/190x216.jpg/ff4444/ffffff")
                .param("date", "2019-12-14")
                .param("duration", "1")
                .param("description", "")
                .param("cost", "-100")      //Not valid
                .param("hotel_id", "1")
                .param("country_id", "1")
                .param("tour_type", "ECONOM"))
                .andExpect(status().isOk())
                .andExpect(view().name("addTour"))
                .andExpect(model().attribute("tour_types", Tour.TourType.values()))
                .andExpect(model().attribute("countries", new ArrayList<>()))
                .andExpect(model().attribute("hotels", new ArrayList<>()));
    }

    @Test
    public void updateTourView() throws Exception {

        tour.setId(1);

        when(tourService.findById(1)).thenReturn(Optional.of(tour));
        when(countryService.findAll()).thenReturn(new ArrayList<>());
        when(hotelService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform((RequestBuilder) get("/update_tour_view")
                .param("tour_id", "1")
                .param("photo", "http://dummyimage.com/190x216.jpg/ff4444/ffffff")
                .param("date", "2019-12-14")
                .param("duration", "1")
                .param("description", "Integer non velit.")
                .param("cost", "1839.65")
                .param("hotel_id", "1")
                .param("country_id", "1")
                .param("tour_type", "ECONOM"))
                .andExpect(status().isOk())
                .andExpect(view().name("addTour"))
                .andExpect(model().attribute("tour_types", Tour.TourType.values()))
                .andExpect(model().attribute("countries", new ArrayList<>()))
                .andExpect(model().attribute("hotels", new ArrayList<>()))
                .andExpect(model().attribute("tour_id", 1));

        verify(tourService, times(1)).findById(1);
        verifyNoMoreInteractions(tourService);
    }

    @Test
    public void updateTourValid() throws Exception {

        tour.setId(1);

        Country country = WebInitEntity.initCountry();
        country.setId(1);
        Hotel hotel = WebInitEntity.initHotel();
        hotel.setId(1);

        when(tourService.update(tour)).thenReturn(new HashSet<>());
        when(countryService.findById(1)).thenReturn(Optional.of(country));
        when(hotelService.findById(1)).thenReturn(Optional.of(hotel));

        mockMvc.perform((RequestBuilder) post("/update_tour")
                .param("tour_id", "1")
                .param("photo", "http://dummyimage.com/190x216.jpg/ff4444/ffffff")
                .param("date", "2019-12-14")
                .param("duration", "1")
                .param("description", "Integer non velit.")
                .param("cost", "1839.65")
                .param("hotel_id", "1")
                .param("country_id", "1")
                .param("tour_type", "ECONOM"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/search_tours?tour_updated"))
                .andExpect(model().attributeDoesNotExist());

        verify(tourService, times(1)).update(tour);
        verifyNoMoreInteractions(tourService);
    }

    @Test
    public void updateTourNotValid() throws Exception {

        when(countryService.findAll()).thenReturn(new ArrayList<>());
        when(hotelService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform((RequestBuilder) post("/update_tour")
                .param("tour_id", "1")
                .param("photo", "http://dummyimage.com/190x216.jpg/ff4444/ffffff")
                .param("date", "2019-12-14")
                .param("duration", "-11")   //Not valid
                .param("description", "")
                .param("cost", "-100")   //Not valid
                .param("hotel_id", "1")
                .param("country_id", "1")
                .param("tour_type", "ECONOM"))
                .andExpect(status().isOk())
                .andExpect(view().name("addTour"))
                .andExpect(model().attribute("tour_id", 1))
                .andExpect(model().attribute("tour_types", Tour.TourType.values()))
                .andExpect(model().attribute("countries", new ArrayList<>()))
                .andExpect(model().attribute("hotels", new ArrayList<>()));
    }

    @Test
    public void removeTour() throws Exception {

        when(tourService.delete(1)).thenReturn(true);

        mockMvc.perform((RequestBuilder) post("/remove_tour")
                .param("tour_id", "1"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/search_tours?tour_removed"))
                .andExpect(model().attributeDoesNotExist());

        verify(tourService, times(1)).delete(1);
        verifyNoMoreInteractions(tourService);
    }

    @Test
    public void uploadTourJsonFileLoaded() throws Exception {

        MockMultipartFile jsonFile = new MockMultipartFile("tourUpload", "batchTours.json",
                "application/json", "{\"json\": \"someValue\"}".getBytes());

        when(jsonParserTourService.parseJsonTours(jsonFile.getInputStream())).thenReturn(new Tour[3]);


        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload_tour")
                .file(jsonFile))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/profile_admin?tour_loaded"))
                .andExpect(model().attributeDoesNotExist());

        verify(tourService, times(1)).uploadTours(null);
        verifyNoMoreInteractions(tourService);
    }

    @Test
    public void uploadTourNothingLoaded() throws Exception {

        MockMultipartFile jsonFile = new MockMultipartFile("tourUpload", "",
                "application/octet-stream", "".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload_tour")
                .file(jsonFile))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/profile_admin?tour_loaded_fail"))
                .andExpect(model().attributeDoesNotExist());
    }


}