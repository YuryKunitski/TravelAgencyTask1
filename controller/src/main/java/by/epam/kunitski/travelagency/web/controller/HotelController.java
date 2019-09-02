package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Hotel;
import by.epam.kunitski.travelagency.service.HotelService;
import by.epam.kunitski.travelagency.web.webDto.HotelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/hotel")
    public String viewHotel(@RequestParam(value = "hotel_id", required = false) Integer hotel_id, Model model) {

        Hotel hotel = new Hotel();

        if (hotelService.findById(hotel_id).isPresent()) {
            hotel = hotelService.findById(hotel_id).get();
        }

        model.addAttribute("hotel", hotel);

        return "hotel";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/add_hotel_view")
    public String addHotelView(@Valid @ModelAttribute("hotelDto") HotelDto hotelDto, Model model) {

        model.addAttribute("features", Hotel.FeatureType.values());

        return "addHotel";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/add_hotel")
    public String addHotel(@Valid @ModelAttribute("hotelDto") HotelDto hotelDto,
                           BindingResult result, ModelMap model) {

        model.addAttribute("features", Hotel.FeatureType.values());

        if (result.hasErrors()) {
            return "addHotel";
        }

        if (hotelDto != null) {
            Hotel hotel = new Hotel();
            hotel.setName(hotelDto.getName());
            hotel.setStars(hotelDto.getStars());
            hotel.setWebsite(hotelDto.getWebsite());
            hotel.setLatitude(hotelDto.getLatitude());
            hotel.setLongitude(hotelDto.getLongitude());
            hotel.setFeatures(hotelDto.getFeatures());

            hotelService.add(hotel);
        }
        return "redirect:/profile_admin?hotel_added";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/view_all_hotels")
    public String viewAllHotels(@Valid @ModelAttribute("hotelDto") HotelDto hotelDto,
                           BindingResult result, ModelMap model) {

        model.addAttribute("hotels", hotelService.findAll());

        return "allHotels";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/update_hotel_view")
    public String updateHotelView(@Valid @ModelAttribute("hotelDto") HotelDto hotelDto,
                                  @RequestParam(value = "hotel_id", required = false) Integer hotel_id,
                                BindingResult result, ModelMap model) {

        model.addAttribute("hotels", hotelService.findAll());
        model.addAttribute("features", Hotel.FeatureType.values());

        Hotel hotel = hotelService.findById(hotel_id).get();

        hotelDto.setName(hotel.getName());
        hotelDto.setStars(hotel.getStars());
        hotelDto.setWebsite(hotel.getWebsite());
        hotelDto.setLatitude(hotel.getLatitude());
        hotelDto.setLongitude(hotel.getLongitude());

        return "addHotel";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/update_hotel")
    public String updateHotel(@Valid @ModelAttribute("hotelDto") HotelDto hotelDto,
                                  BindingResult result, ModelMap model) {

        model.addAttribute("hotels", hotelService.findAll());

        return "";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/remove_hotel")
    public String removeHotel(@Valid @ModelAttribute("hotelDto") HotelDto hotelDto,
                                  BindingResult result, ModelMap model) {

        System.out.println("remove hotel method");

        return "redirect:/profile_admin?hotel_removed";
    }

}
