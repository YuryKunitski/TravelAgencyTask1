package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping("/tours")
    public String allTours(Model model) {
        model.addAttribute("tours", tourService.findAll());

        return "tour";
    }
}
