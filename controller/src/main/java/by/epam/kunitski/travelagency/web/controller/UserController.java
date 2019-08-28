package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.User;
import by.epam.kunitski.travelagency.service.ReviewService;
import by.epam.kunitski.travelagency.service.TourService;
import by.epam.kunitski.travelagency.service.UserService;
import by.epam.kunitski.travelagency.web.webDto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Locale;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TourService tourService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MessageSource messageSource;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String logIn(ModelMap model) {
        return "login";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/registration")
    public String registrationView(@ModelAttribute("userDto") UserDto userDto, ModelMap model) {

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("userDto") UserDto userDto,
                               BindingResult result, HttpServletRequest request, Locale locale) throws ServletException {

        User existing = userService.findByUserName(userDto.getLogin());

        if (existing != null) {
            String loginErrorMsg = messageSource.getMessage("msg.register_error_login", new Object[]{}, locale);
            result.rejectValue("login", null, loginErrorMsg);

            return "registration";
        }

        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            String passErrorMsg = messageSource.getMessage("msg.wrongConfirmPass", new Object[]{}, locale);
            result.rejectValue("confirmPassword", null, passErrorMsg);
            return "registration";
        }

        if (result.hasErrors()){
            return "registration";
        }

        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setRole(User.UserRole.MEMBER);

        userService.add(user);
        request.login(userDto.getLogin(), request.getParameter("password"));

        return "redirect:/search_tours?register_success";
    }

    @Secured("ROLE_MEMBER")
    @GetMapping("/profile_member")
    public String memberProfile(Principal principal, ModelMap model) {

        User user = userService.findByUserName(principal.getName());

        model.addAttribute("tours", tourService.findAllByUserId(user.getId()));
        model.addAttribute("reviews", reviewService.findAllByUserId(user.getId()));

        return "userProfile";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/profile_admin")
    public String adminProfile(Principal principal, ModelMap model) {

        User user = userService.findByUserName(principal.getName());

        model.addAttribute("tours", tourService.findAllByUserId(user.getId()));
        model.addAttribute("reviews", reviewService.findAllByUserId(user.getId()));

        return "userProfile";
    }

}
