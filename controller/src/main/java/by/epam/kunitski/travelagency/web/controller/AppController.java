package by.epam.kunitski.travelagency.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalTime;

@Controller
//@RequestMapping("/")
public class AppController {

    @RequestMapping("/time/{indexId}")
    public String handleRequest(Model model, @PathVariable String indexId) {

        indexId = "id";

        model.addAttribute("msg", "A message from the controller");
        model.addAttribute("time", LocalTime.now());
        return "index";
    }

    @RequestMapping("/example")
    public String example(Model model) {
        return "example";
    }

}
