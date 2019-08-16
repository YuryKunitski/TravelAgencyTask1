package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.dto.TourDto;
import by.epam.kunitski.travelagency.dao.specification.impl.TourSpecification;
import by.epam.kunitski.travelagency.service.CountryService;
import by.epam.kunitski.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class HomeController {

    @Autowired
    private TourService tourService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/search_tours")
    public String searchTours(@Valid @ModelAttribute("tourDto") TourDto tourDto,
                              BindingResult result, ModelMap model, Locale locale) {

        TourSpecification tourSpecification = new TourSpecification(tourDto);

        if (tourDto.getMaxDate()!= null && tourDto.getMinDate() != null && tourDto.getMinDate().isAfter(tourDto.getMaxDate())) {
            String dateErrorMsg = messageSource.getMessage("msg.wrongDate", new Object[] {}, locale);
            result.rejectValue("minDate", "error.tourDto", dateErrorMsg);
        }

        model.addAttribute("tours", tourService.findAllByCriteria(tourSpecification));
        model.addAttribute("countries", countryService.findAll());

        return "home";
    }

}
