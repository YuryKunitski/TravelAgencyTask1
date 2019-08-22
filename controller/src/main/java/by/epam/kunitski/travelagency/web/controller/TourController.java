package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.service.ReviewService;
import by.epam.kunitski.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TourController {

    @Autowired
    private TourService tourService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/tour")
    public String allTours(@RequestParam(value = "id", required = false) Integer id, Model model) {

        Tour tour = new Tour();

        if (tourService.findById(id).isPresent()) {
            tour = tourService.findById(id).get();
        }

        model.addAttribute("tour", tour);
        model.addAttribute("reviews", reviewService.findAllByTourId(tour.getId()));

        return "tour";
    }
}
