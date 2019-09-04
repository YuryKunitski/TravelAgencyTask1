package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.service.*;
import by.epam.kunitski.travelagency.web.webDto.TourDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class TourController {

    @Autowired
    private TourService tourService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private JsonParserTourService jsonParserTourService;

    @GetMapping("/tour")
    public String viewTour(@RequestParam(value = "tour_id", required = false) Integer tour_id, Model model) {

        Tour tour = new Tour();

        if (tourService.findById(tour_id).isPresent()) {
            tour = tourService.findById(tour_id).get();
        }

        model.addAttribute("tour", tour);
        model.addAttribute("reviews", reviewService.findAllByTourId(tour.getId()));

        return "tour";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/add_tour_view")
    public String addTourView(@ModelAttribute("tourDto") TourDto tourDto, Model model) {

        model.addAttribute("tour_types", Tour.TourType.values());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("hotels", hotelService.findAll());


        return "addTour";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/add_tour")
    public String addTour(@Valid @ModelAttribute("tourDto") TourDto tourDto, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("tour_types", Tour.TourType.values());
            model.addAttribute("countries", countryService.findAll());
            model.addAttribute("hotels", hotelService.findAll());

            return "addTour";
        }

        Tour tour = new Tour();
        tour.setPhoto(tourDto.getPhoto());
        tour.setDate(tourDto.getDate());
        tour.setDuration(tourDto.getDuration());
        tour.setDescription(tourDto.getDescription());
        tour.setCost(tourDto.getCost());
        tour.setHotel_id(hotelService.findById(tourDto.getHotel_id()).get());
        tour.setCountry_id(countryService.findById(tourDto.getCountry_id()).get());
        tour.setTour_type(tourDto.getTour_type());

        tourService.add(tour);

        return "redirect:/profile_admin?tour_added";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/remove_tour")
    public String removeTour(@RequestParam(value = "tour_id", required = false) Integer tour_id) {

        tourService.delete(tour_id);

        return "redirect:/search_tours?tour_removed";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/update_tour_view")
    public String updateTourView(@Valid @ModelAttribute("tourDto") TourDto tourDto, BindingResult result,
                                 @RequestParam(value = "tour_id", required = false) Integer tour_id, Model model) {

        model.addAttribute("tour_id", tour_id);
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("hotels", hotelService.findAll());
        model.addAttribute("tour_types", Tour.TourType.values());

        Tour tour = tourService.findById(tour_id).get();

        tourDto.setPhoto(tour.getPhoto());
        tourDto.setDate(tour.getDate());
        tourDto.setDuration(tour.getDuration());
        tourDto.setDescription(tour.getDescription());
        tourDto.setCost(tour.getCost());
        tourDto.setHotel_id(tour.getHotel_id().getId());
        tourDto.setCountry_id(tour.getCountry_id().getId());
        tourDto.setTour_type(tour.getTour_type());

        return "addTour";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/update_tour")
    public String updateTour(@Valid @ModelAttribute("tourDto") TourDto tourDto, BindingResult result,
                             @RequestParam(value = "tour_id", required = false) Integer tour_id, Model model) {


        if (result.hasErrors()) {
            model.addAttribute("tour_id", tour_id);
            model.addAttribute("countries", countryService.findAll());
            model.addAttribute("hotels", hotelService.findAll());
            model.addAttribute("tour_types", Tour.TourType.values());

            return "addTour";
        }

        Tour tour = new Tour();
        tour.setId(tour_id);
        tour.setPhoto(tourDto.getPhoto());
        tour.setDate(tourDto.getDate());
        tour.setDuration(tourDto.getDuration());
        tour.setDescription(tourDto.getDescription());
        tour.setCost(tourDto.getCost());
        tour.setHotel_id(hotelService.findById(tourDto.getHotel_id()).get());
        tour.setCountry_id(countryService.findById(tourDto.getCountry_id()).get());
        tour.setTour_type(tourDto.getTour_type());

        tourService.update(tour);

        return "redirect:/search_tours?tour_updated";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/upload_tour")
    public String uploadTour(@RequestParam(value = "tourUpload", required = false) MultipartFile tourUpload) throws IOException {

        if (tourUpload.getSize() != 0) {

            InputStream inputStream = tourUpload.getInputStream();
            Tour[] tours = jsonParserTourService.parseJsonTours(inputStream);

            tourService.uploadTours(tours);

            return "redirect:/profile_admin?tour_loaded";
        }

        return "redirect:/profile_admin?tour_loaded_fail";

    }

}
