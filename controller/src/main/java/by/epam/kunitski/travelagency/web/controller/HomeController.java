package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.dto.TourDto;
import by.epam.kunitski.travelagency.dao.specification.impl.TourSpecification;
import by.epam.kunitski.travelagency.service.CountryService;
import by.epam.kunitski.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {

    @Autowired
    private TourService tourService;

    @Autowired
    private CountryService countryService;

    @GetMapping("/search_tours")
    public String searchTours(@ModelAttribute("tourDto") TourDto tourDto,
                              BindingResult result, ModelMap model) {

        TourSpecification tourSpecification = new TourSpecification(tourDto);

        model.addAttribute("tours", tourService.findAllByCriteria(tourSpecification));
        model.addAttribute("countries", countryService.findAll());

        return "home";
    }

}
