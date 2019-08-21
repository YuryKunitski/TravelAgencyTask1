package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.User;
import by.epam.kunitski.travelagency.service.TourService;
import by.epam.kunitski.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TourService tourService;

    @GetMapping("/login")
    public String logIn(ModelMap model) {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationView(@ModelAttribute("user") User user, ModelMap model) {

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user,
                               BindingResult result, ModelMap model) {

        User existing = userService.findByUserName(user.getLogin());

        if (existing != null) {
            result.rejectValue("login", null, "There is already an account registered with that login");
        }

        if (result.hasErrors()) {
            return "registration";
        }

        user.setRole(User.UserRole.MEMBER);
        userService.add(user);
        return "redirect:/registration?success";
    }

    @GetMapping("/profile")
    public String userProfile(ModelMap model) {
//        model.addAttribute("tours", tourService.findById());
        return "userProfile";
    }

}
