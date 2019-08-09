package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.specification.impl.TourSpecification;
import by.epam.kunitski.travelagency.entity.Tour;
import by.epam.kunitski.travelagency.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    @Autowired
    @Qualifier("tourServiceImpl")
    private EntityService<Tour> tourService;

    @RequestMapping("/tours")
    public String handleRequest(Model model) {

        TourSpecification tourSpecification = new TourSpecification();

        model.addAttribute("tours", tourService.findAll(tourSpecification));

        return "index";
    }


    @RequestMapping("/example")
    public String example(Model model) {
        return "example";
    }

}
