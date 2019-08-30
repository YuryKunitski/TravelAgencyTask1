package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Hotel;
import by.epam.kunitski.travelagency.service.HotelService;
import by.epam.kunitski.travelagency.web.webDto.HotelDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/add_hotel_view")
    public String addHotelView(@Valid @ModelAttribute("hotelDto") HotelDto hotelDto, Model model) {

        model.addAttribute("features", Hotel.FeatureType.values());

        return "addHotel";
    }

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
        return "redirect:/profile_admin";
    }

}
