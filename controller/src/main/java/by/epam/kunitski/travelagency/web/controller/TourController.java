package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.specification.impl.TourSpecification;
import by.epam.kunitski.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping("/tours")
    public String allTours(Model model) {

        TourSpecification tourSpecification = new TourSpecification();
        model.addAttribute("tours", tourService.findAllByCriteria(tourSpecification));

        return "tour";
    }
}
