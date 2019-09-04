package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Hotel;
import by.epam.kunitski.travelagency.service.HotelService;
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

import static by.epam.kunitski.travelagency.dao.entity.Hotel.FeatureType.CHILDREN_AREA;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppInitializer.class})
public class HotelControllerTest {

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private HotelController hotelController;

    private MockMvc mockMvc;
    private Hotel hotel = new Hotel();

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(hotelController)
                .setViewResolvers(new StandaloneMvcTestViewResolver())
                .build();

        hotel = WebInitEntity.initHotel();
    }

    @Test
    public void viewHotel() throws Exception {

        hotel.setId(1);

        when(hotelService.findById(1)).thenReturn(Optional.of(hotel));

        mockMvc.perform((RequestBuilder) get("/hotel")
                .param("hotel_id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("hotel"))
                .andExpect(model().attribute("hotel", hotel));

        verify(hotelService, times(2)).findById(1);
        verifyNoMoreInteractions(hotelService);
    }

    @Test
    public void addHotelView() throws Exception {

        mockMvc.perform((RequestBuilder) get("/add_hotel_view"))
                .andExpect(status().isOk())
                .andExpect(view().name("addHotel"))
                .andExpect(model().attribute("features", Hotel.FeatureType.values()));
    }

    @Test
    public void addHotelValid() throws Exception {

        when(hotelService.add(hotel)).thenReturn(new HashSet<>());

        mockMvc.perform((RequestBuilder) post("/add_hotel")
                .param("name", "Choloepus hoffmani")
                .param("stars", "2")
                .param("website", "http://kvassman0@wikimedia.org")
                .param("latitude", "8.2673715")
                .param("longitude", "48.9086571")
                .param("features", "CHILDREN_AREA"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/profile_admin?hotel_added"))
                .andExpect(model().attributeDoesNotExist());

        verify(hotelService, times(1)).add(hotel);
        verifyNoMoreInteractions(hotelService);
    }

    @Test
    public void addHotelNotValid() throws Exception {

        mockMvc.perform((RequestBuilder) post("/add_hotel")
                .param("name", "Choloepus hoffmani")
                .param("stars", "2")
                .param("website", "www") //not valid
                .param("latitude", "8.2673715")
                .param("longitude", "1000")  //not valid
                .param("features", "CHILDREN_AREA"))
                .andExpect(status().isOk())
                .andExpect(view().name("addHotel"))
                .andExpect(model().attribute("features", Hotel.FeatureType.values()));
    }

    @Test
    public void viewAllHotels() throws Exception {

        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(new Hotel());
        hotelList.add(new Hotel());

        when(hotelService.findAll()).thenReturn(hotelList);

        mockMvc.perform((RequestBuilder) get("/view_all_hotels"))
                .andExpect(status().isOk())
                .andExpect(view().name("allHotels"))
                .andExpect(model().attribute("hotels", hasSize(2)));

        verify(hotelService, times(1)).findAll();
        verifyNoMoreInteractions(hotelService);
    }

    @Test
    public void updateHotelView() throws Exception {

        hotel.setId(1);

        when(hotelService.findById(1)).thenReturn(Optional.of(hotel));

        mockMvc.perform((RequestBuilder) get("/update_hotel_view")
                .param("hotel_id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("addHotel"))
                .andExpect(model().attribute("features", Hotel.FeatureType.values()))
                .andExpect(model().attribute("hotel_id", 1));

        verify(hotelService, times(1)).findById(1);
        verifyNoMoreInteractions(hotelService);
    }

    @Test
    public void updateHotelValid() throws Exception {

        hotel.setId(1);

        when(hotelService.update(hotel)).thenReturn(new HashSet<>());

        mockMvc.perform((RequestBuilder) post("/update_hotel")
                .param("hotel_id", "1")
                .param("name", "Choloepus hoffmani")
                .param("stars", "2")
                .param("website", "http://kvassman0@wikimedia.org")
                .param("latitude", "8.2673715")
                .param("longitude", "48.9086571")
                .param("features", "CHILDREN_AREA"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/profile_admin?hotel_updated"))
                .andExpect(model().attributeDoesNotExist());

        verify(hotelService, times(1)).update(hotel);
        verifyNoMoreInteractions(hotelService);
    }

    @Test
    public void updateHotelNotValid() throws Exception {

        mockMvc.perform((RequestBuilder) post("/update_hotel")
                .param("hotel_id", "1")
                .param("name", "Choloepus hoffmani")
                .param("stars", "2")
                .param("website", "www")  //not valid
                .param("latitude", "1000")  //not valid
                .param("longitude", "48.9086571")
                .param("features", "CHILDREN_AREA"))
                .andExpect(status().isOk())
                .andExpect(view().name("addHotel"))
                .andExpect(model().attribute("features", Hotel.FeatureType.values()))
                .andExpect(model().attribute("hotel_id", 1));

    }

    @Test
    public void removeHotel() throws Exception {

        when(hotelService.delete(1)).thenReturn(true);

        mockMvc.perform((RequestBuilder) post("/remove_hotel")
                .param("hotel_id", "1"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/profile_admin?hotel_removed"))
                .andExpect(model().attributeDoesNotExist());

        verify(hotelService, times(1)).delete(1);
        verifyNoMoreInteractions(hotelService);
    }
}