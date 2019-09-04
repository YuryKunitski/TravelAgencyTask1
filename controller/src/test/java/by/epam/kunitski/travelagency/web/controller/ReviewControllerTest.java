package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Hotel;
import by.epam.kunitski.travelagency.dao.entity.Review;
import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.dao.entity.User;
import by.epam.kunitski.travelagency.service.ReviewService;
import by.epam.kunitski.travelagency.service.TourService;
import by.epam.kunitski.travelagency.service.UserService;
import by.epam.kunitski.travelagency.web.config.AppInitializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppInitializer.class})
public class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    @Mock
    private UserService userService;

    @Mock
    private TourService tourService;

    @InjectMocks
    private ReviewController reviewController;

    private MockMvc mockMvc;
    private Review review = new Review();

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(reviewController)
                .setViewResolvers(new StandaloneMvcTestViewResolver())
                .build();

        review = WebInitEntity.initReview();
    }

    @Test
    public void addReviewView() throws Exception {

        mockMvc.perform((RequestBuilder) get("/create_review_view")
                .param("tour_id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("review"))
                .andExpect(model().attribute("tour_id", 1));
    }

    @Test
    public void addReview() throws Exception {

        User user = WebInitEntity.initUser();
        user.setId(1);
        Tour tour = WebInitEntity.initTour();
        tour.setId(1);

        when(reviewService.add(review)).thenReturn(new HashSet<>());
        when(userService.findByUserName("Saundra")).thenReturn(user);
        when(tourService.findById(1)).thenReturn(Optional.of(tour));

        mockMvc.perform((RequestBuilder) post("/create_review")
                .param("tour_id", "1")
                .param("textReview", "aaa"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/profile_admin?review_added"))
                .andExpect(model().attribute("tour_id", 1));

        verify(reviewService, times(1)).add(review);
        verifyNoMoreInteractions(reviewService);
    }

    @Test
    public void removeReview() {
    }

    @Test
    public void updateReviewView() {
    }

    @Test
    public void updateReview() {
    }
}