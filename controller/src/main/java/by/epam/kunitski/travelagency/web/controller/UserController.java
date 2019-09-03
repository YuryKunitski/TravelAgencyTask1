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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Locale;

@Controller
@Validated
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

    @PreAuthorize("hasRole('ADMIN') or isAnonymous()")
    @GetMapping("/registration")
    public String registrationView(@ModelAttribute("userDto") UserDto userDto, ModelMap model) {

        return "registration";
    }

    @PreAuthorize("hasRole('ADMIN') or isAnonymous()")
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result, Authentication authentication,
                               HttpServletRequest request, Locale locale) throws ServletException {


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

        if (result.hasErrors()) {
            return "registration";
        }

        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());

        if (authentication != null) {
            user.setRole(User.UserRole.ADMIN);

            userService.add(user);

            return "redirect:/profile_admin?admin_added";
        }

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

    @Secured("ROLE_ADMIN")
    @GetMapping("/view_all_users")
    public String viewAllUsers(ModelMap model) {

        model.addAttribute("users", userService.findAll());

        return "allUser";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/remove_user")
    public String removeUser(Model model,
                             @RequestParam(value = "user_id", required = false) Integer user_id) {

        userService.delete(user_id);

        return "redirect:/view_all_users?removed_user";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/update_user_view")
    public String updateUserView(Model model, @ModelAttribute("userDto") UserDto userDto,
                                 @RequestParam(value = "user_id", required = false) Integer user_id) {

        model.addAttribute("roles", User.UserRole.values());
        model.addAttribute("login", userService.findById(user_id).get().getLogin());
        model.addAttribute("role", userService.findById(user_id).get().getRole());
        model.addAttribute("user_id", user_id);

        return "update_user";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/update_user")
    public String updateUser(@Valid @ModelAttribute("userDto") UserDto userDto,      //this row for validation login input
                             BindingResult result, Model model,
                             @RequestParam(value = "user_id", required = false) Integer user_id,
                             @RequestParam(value = "role", required = false) String role,
                             @RequestParam(value = "login", required = false) String login) {

        if (result.hasErrors()) {

            model.addAttribute("roles", User.UserRole.values());
            model.addAttribute("login", userService.findById(user_id).get().getLogin());
            model.addAttribute("role", userService.findById(user_id).get().getRole());
            model.addAttribute("user_id", user_id);

            return "update_user";
        }

        User user = new User();
        user.setId(user_id);
        user.setLogin(login);
        user.setPassword(userService.findById(user_id).get().getPassword());

        if (role != null) {
            user.setRole(User.UserRole.valueOf(role));
        } else {
            user.setRole(userService.findById(user_id).get().getRole());
        }

        userService.update(user);

        return "redirect:/view_all_users?updated_user";
    }


}
