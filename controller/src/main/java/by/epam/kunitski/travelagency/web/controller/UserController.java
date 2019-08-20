package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.User;
import by.epam.kunitski.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/perform_login")
    public String logIn(@ModelAttribute("user") User user, ModelMap model, Principal principal) {
        model.addAttribute("message", "You are logged in as " + principal.getName());
        return "login";
    }

//    @PostMapping("/perform_login")
//    public String logInForm(@ModelAttribute("user") User user, ModelMap model) {
//
//        System.out.println("user login - "+user.getLogin());
//        System.out.println("user password - "+user.getPassword());
//
//        return "login";
//    }

}
