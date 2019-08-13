package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.specification.impl.TourSpecification;
import by.epam.kunitski.travelagency.entity.Tour;
import by.epam.kunitski.travelagency.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @Autowired
    @Qualifier("tourServiceImpl")
    private EntityService<Tour> tourService;

    @RequestMapping("/tours")
    public String allTours(Model model) {

        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setMaxCost(600.0);
        tourSpecification.setMaxDuration(5);

        model.addAttribute("tours", tourService.findAll(tourSpecification));

        return "index";
    }

    @RequestMapping(value = "/tours", method = RequestMethod.GET)
    public String serchTours(Model model,
                             @RequestParam("minCost") Double minCost,
                             @RequestParam("maxCost") Double maxCost) {

        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setMinCost(minCost);
        tourSpecification.setMaxCost(maxCost);

        model.addAttribute("tours", tourService.findAll(tourSpecification));

        return "index";
    }

}
