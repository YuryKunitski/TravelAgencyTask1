package by.epam.kunitski.travelagency.web.controller;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Optional;

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
                .param("user_name", "Saundra")
                .param("textReview", "aaa"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/profile_member?left_review"))
                .andExpect(model().attribute("tour_id", 1));

        verify(reviewService, times(1)).add(review);
        verifyNoMoreInteractions(reviewService);
    }

    @Test
    public void removeReview() throws Exception {

        when(reviewService.delete(1)).thenReturn(true);

        mockMvc.perform((RequestBuilder) post("/remove_review")
                .param("review_id", "1"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/profile_member?removed_review"))
                .andExpect(model().attributeDoesNotExist());

        verify(reviewService, times(1)).delete(1);
        verifyNoMoreInteractions(reviewService);
    }

    @Test
    public void updateReviewView() throws Exception {

        review.setId(1);

        when(reviewService.findById(1)).thenReturn(Optional.of(review));

        mockMvc.perform((RequestBuilder) get("/update_review_view")
                .param("review_id", "1")
                .param("tour_id", "1")
                .param("textReview", "aaa"))
                .andExpect(status().isOk())
                .andExpect(view().name("review"))
                .andExpect(model().attribute("tour_id", 1))
                .andExpect(model().attribute("currentText", "aaa"))
                .andExpect(model().attribute("review_id", 1));

        verify(reviewService, times(1)).findById(1);
        verifyNoMoreInteractions(reviewService);
    }

    @Test
    public void updateReview() throws Exception {

        User user = WebInitEntity.initUser();
        user.setId(1);

        Tour tour = WebInitEntity.initTour();
        tour.setId(1);

        review.setId(1);

        when(userService.findByUserName("Saundra")).thenReturn(user);
        when(tourService.findById(1)).thenReturn(Optional.of(tour));
        when(reviewService.update(review)).thenReturn(new HashSet<>());

        mockMvc.perform((RequestBuilder) post("/update_review")
                .param("tour_id", "1")
                .param("review_id", "1")
                .param("user_name", "Saundra")
                .param("textReview", "aaa"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/profile_member?updated_review"))
                .andExpect(model().attributeDoesNotExist());

        verify(reviewService, times(1)).update(review);
        verifyNoMoreInteractions(reviewService);
    }
}