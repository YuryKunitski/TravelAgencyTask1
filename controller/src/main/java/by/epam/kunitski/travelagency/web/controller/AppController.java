package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.dto.TourDto;
import by.epam.kunitski.travelagency.dao.specification.impl.TourSpecification;
import by.epam.kunitski.travelagency.service.CountryService;
import by.epam.kunitski.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    private TourService tourService;

    @Autowired
    private CountryService countryService;

    //  @GetMapping("/search_tours")
//  public String searchTours(Model model,
//                            @RequestParam(value = "minCost", required = false) Double minCost,
//                            @RequestParam(value = "maxCost", required = false) Double maxCost,
//                            @RequestParam(value = "minDuration", required = false) Integer minDuration,
//                            @RequestParam(value = "maxDuration", required = false) Integer maxDuration,
//                            @RequestParam(value = "minStars", required = false) Integer minStars,
//                            @RequestParam(value = "maxStars", required = false) Integer maxStars,
//                            @RequestParam(value = "minDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
//                            @RequestParam(value = "maxDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate,
//                            @RequestParam(value = "tourType", required = false) String tourType,
//                            @RequestParam(value = "country", required = false) List<String> countries) {
//
//    TourSpecification tourSpecification = new TourSpecification();
//    tourSpecification.setMinCost(minCost);
//    tourSpecification.setMaxCost(maxCost);
//    tourSpecification.setMinDuration(minDuration);
//    tourSpecification.setMaxDuration(maxDuration);
//    tourSpecification.setMinStars(minStars);
//    tourSpecification.setMaxStars(maxStars);
//    tourSpecification.setMinDate(minDate);
//    tourSpecification.setMaxDate(maxDate);
//
//    if (countries != null) {
//      tourSpecification.setCountryNames(countries);
//    }
//
//    if (tourType != null) {
//      tourSpecification.setTourType(Tour.TourType.valueOf(tourType));
//    }
//
//    model.addAttribute("tours", tourService.findAllByCriteria(tourSpecification));
//    model.addAttribute("countries", countryService.findAll());
//
//    return "home";
//  }

    @RequestMapping(value = "/search_tours", method = RequestMethod.GET)
    public String searchTours(@ModelAttribute("tourDto") TourDto tourDto,
                              BindingResult result, ModelMap model) {

        System.out.println("country names - "+tourDto.getCountryNames());

        TourSpecification tourSpecification = new TourSpecification();

        model.addAttribute("tours", tourService.findAllByCriteria(tourSpecification));
        model.addAttribute("countries", countryService.findAll());

        return "home";
    }

}
