package by.epam.kunitski.travelagency.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalTime;

@Controller
@RequestMapping("/")
public class AppController {

    @RequestMapping
    public String handleRequest (Model model) {
        model.addAttribute("msg", "A message from the controller");
        model.addAttribute("time", LocalTime.now());
        return "my-page";
    }

}
