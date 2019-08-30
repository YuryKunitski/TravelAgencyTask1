package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.dao.entity.Hotel;
import by.epam.kunitski.travelagency.service.CountryService;
import by.epam.kunitski.travelagency.web.webDto.HotelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;

@Controller
public class CountryController {

  @Autowired
  private CountryService countryService;

  @GetMapping("/add_country_view")
  public String addCountryView(@Valid @ModelAttribute("country") Country country, Model model) {

    return "addCountry";
  }

  @GetMapping("/add_country")
  public String addCountry(@Valid @ModelAttribute("country") Country country,
                           BindingResult result, ModelMap model) {

    if (result.hasErrors()) {
      return "addCountry";
    }

      countryService.add(country);

      return "redirect:/profile_admin";

  }
}
