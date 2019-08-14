package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.specification.impl.TourSpecification;
import by.epam.kunitski.travelagency.entity.Tour;
import by.epam.kunitski.travelagency.service.CountryService;
import by.epam.kunitski.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private TourService tourService;

    @Autowired
    private CountryService countryService;

    @RequestMapping("/tours")
    public String allTours(Model model) {

        TourSpecification tourSpecification = new TourSpecification();
        model.addAttribute("tours", tourService.findAllByCriteria(tourSpecification));
        model.addAttribute("countries", countryService.findAll());

        return "home";
    }

    @GetMapping("/search_tours")
    public String serchTours(Model model,
                             @RequestParam("minCost") Double minCost,
                             @RequestParam("maxCost") Double maxCost,
                             @RequestParam("minDuration") Integer minDuration,
                             @RequestParam("maxDuration") Integer maxDuration,
                             @RequestParam("minStars") Integer minStars,
                             @RequestParam("maxStars") Integer maxStars,
                             @RequestParam("minDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
                             @RequestParam("maxDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate,
                             @RequestParam(value = "tourType", required = false) String tourType,
                             @RequestParam("countries") List<String> countries) {

        TourSpecification tourSpecification = new TourSpecification();
        tourSpecification.setMinCost(minCost);
        tourSpecification.setMaxCost(maxCost);
        tourSpecification.setMinDuration(minDuration);
        tourSpecification.setMaxDuration(maxDuration);
        tourSpecification.setMinStars(minStars);
        tourSpecification.setMaxStars(maxStars);
        tourSpecification.setMinDate(minDate);
        tourSpecification.setMaxDate(maxDate);
        tourSpecification.setCountryNames(countries);

        if (tourType != null) {
            tourSpecification.setTourType(Tour.TourType.valueOf(tourType));
        }

        model.addAttribute("tours", tourService.findAllByCriteria(tourSpecification));
        model.addAttribute("countries", countryService.findAll());

        return "home";
    }

}
