package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.dao.entity.Hotel;
import by.epam.kunitski.travelagency.service.CountryService;
import by.epam.kunitski.travelagency.web.webDto.HotelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class CountryController {

  @Autowired
  private CountryService countryService;

  @Secured("ROLE_ADMIN")
  @GetMapping("/add_country_view")
  public String addCountryView(@ModelAttribute("country") Country country, Model model) {

    return "addCountry";
  }

  @Secured("ROLE_ADMIN")
  @PostMapping("/add_country")
  public String addCountry(@Valid @ModelAttribute("country") Country country,
                           BindingResult result, ModelMap model) {

    if (result.hasErrors()) {
      return "addCountry";
    }

    countryService.add(country);

    return "redirect:/profile_admin?country_added";

  }

  @Secured("ROLE_ADMIN")
  @GetMapping("/view_all_countries")
  public String viewAllCountries(@Valid @ModelAttribute("country") Country country, ModelMap model) {

    model.addAttribute("countries", countryService.findAll());

    return "allContries";
  }

  @Secured("ROLE_ADMIN")
  @GetMapping("/update_country_view")
  public String updateCountryView(@Valid @ModelAttribute("country") Country country, Model model,
                                  @RequestParam(value = "country_id", required = false) Integer country_id) {

    model.addAttribute("country_id", country_id);

    country.setId(country_id);
    country.setName(countryService.findById(country_id).get().getName());

    return "addCountry";
  }

  @Secured("ROLE_ADMIN")
  @PostMapping("/update_country")
  public String updateCountry(@Valid @ModelAttribute("country") Country country, BindingResult result,
                              @RequestParam(value = "country_id", required = false) Integer country_id) {

    if (result.hasErrors()) {

      return "addCountry";
    }

    country.setId(country_id);

    countryService.update(country);

    return "redirect:/profile_admin?country_updated";
  }

  @Secured("ROLE_ADMIN")
  @PostMapping("/remove_country")
  public String removeCountry(@RequestParam(value = "country_id", required = false) Integer country_id) {

    countryService.delete(country_id);

    return "redirect:/profile_admin?country_removed";
  }
}
