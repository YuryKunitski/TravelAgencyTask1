package by.epam.kunitski.travelagency.web.controller;

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
public class UserControllerTest {


    @Mock
    private UserService userService;

    @Mock
    private TourService tourService;

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private User user = new User();

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setViewResolvers(new StandaloneMvcTestViewResolver())
                .build();

        user = WebInitEntity.initUser();
    }

    @Test
    public void logIn() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeDoesNotExist());
    }

    @Test
    public void registrationView() throws Exception {

        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().attributeDoesNotExist());
    }

    @Test
    public void registrationValid() throws Exception {

        user.setRole(User.UserRole.ADMIN);

        when(userService.findByUserName("Saundra")).thenReturn(null);
        when(userService.add(user)).thenReturn(new HashSet<>());

        mockMvc.perform(post("/registration")
                .param("user_role", "[ROLE_ADMIN]")
                .param("login", "Saundra")
                .param("password", "CDHjDf5Tnr")
                .param("confirmPassword", "CDHjDf5Tnr"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/profile_admin?admin_added"))
                .andExpect(model().attributeDoesNotExist());

        verify(userService, times(1)).add(user);
        verify(userService, times(1)).findByUserName("Saundra");
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void registrationNotValid() throws Exception {

        when(userService.findByUserName("S")).thenReturn(null);

        mockMvc.perform(post("/registration")
                .param("user_role", "[ROLE_MEMBER]")
                .param("login", "S")    //Not valid
                .param("password", "C")    //Not valid
                .param("confirmPassword", "CD"))    //Not valid
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(model().attributeDoesNotExist());

        verify(userService, times(1)).findByUserName("S");
        verifyNoMoreInteractions(userService);
    }


    @Test
    public void memberProfile() throws Exception {

        user.setId(1);

        when(userService.findByUserName("Saundra")).thenReturn(user);
        when(tourService.findAllByUserId(1)).thenReturn(new ArrayList<>());
        when(reviewService.findAllByUserId(1)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/profile_member")
                .param("user_name", "Saundra"))
                .andExpect(status().isOk())
                .andExpect(view().name("userProfile"))
                .andExpect(model().attribute("tours", new ArrayList<>()))
                .andExpect(model().attribute("reviews", new ArrayList<>()));

        verify(userService, times(1)).findByUserName("Saundra");
        verifyNoMoreInteractions(userService);

    }

    @Test
    public void adminProfile() throws Exception {

        mockMvc.perform(get("/profile_admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("userProfile"))
                .andExpect(model().attributeDoesNotExist());
    }

    @Test
    public void viewAllUsers() throws Exception {

        when(userService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/view_all_users"))
                .andExpect(status().isOk())
                .andExpect(view().name("allUser"))
                .andExpect(model().attribute("users", new ArrayList<>()));

        verify(userService, times(1)).findAll();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void removeUser() throws Exception {

        when(userService.delete(1)).thenReturn(true);

        mockMvc.perform(post("/remove_user")
        .param("user_id", "1"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/view_all_users?removed_user"))
                .andExpect(model().attributeDoesNotExist());

        verify(userService, times(1)).delete(1);
        verifyNoMoreInteractions(userService);

    }

    @Test
    public void updateUserView() throws Exception {

        user.setId(1);

        when(userService.findById(1)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/update_user_view")
        .param("user_id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("update_user"))
                .andExpect(model().attribute("roles", User.UserRole.values()))
                .andExpect(model().attribute("login", "Saundra"))
                .andExpect(model().attribute("role", User.UserRole.MEMBER))
                .andExpect(model().attribute("user_id", 1));

        verify(userService, times(1)).findById(1);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void updateUserValid() throws Exception {

        user.setId(1);

        when(userService.findById(1)).thenReturn(Optional.of(user));
        when(userService.update(user)).thenReturn(new HashSet<>());

        mockMvc.perform(post("/update_user")
                .param("user_id", "1")
                .param("login", "Saundra")
                .param("role", "MEMBER"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/view_all_users?updated_user"))
                .andExpect(model().attributeDoesNotExist());

        verify(userService, times(1)).update(user);
        verify(userService, times(1)).findById(1);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void updateUserNotValid() throws Exception {

        user.setId(1);

        when(userService.findById(1)).thenReturn(Optional.of(user));

        mockMvc.perform(post("/update_user")
                .param("user_id", "1")
                .param("login", "S")   //Not valid
                .param("role", "MEMBER"))
                .andExpect(status().isOk())
                .andExpect(view().name("update_user"))
                .andExpect(model().attributeDoesNotExist());

        verify(userService, times(1)).findById(1);
        verifyNoMoreInteractions(userService);
    }
}