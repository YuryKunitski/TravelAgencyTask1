package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Hotel;
import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
