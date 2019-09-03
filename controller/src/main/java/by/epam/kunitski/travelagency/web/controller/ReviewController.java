package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Review;
import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.dao.entity.User;
import by.epam.kunitski.travelagency.service.ReviewService;
import by.epam.kunitski.travelagency.service.TourService;
import by.epam.kunitski.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;

@Controller
public class ReviewController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private TourService tourService;

    @GetMapping("/create_review_view")
    public String addReviewView(Principal principal, Model model,
                                @RequestParam(value = "tour_id", required = false) Integer tour_id,
                                @RequestParam(value = "textReview", required = false) String textReview) {

        model.addAttribute("tour_id", tour_id);

        return "review";
    }

    @PostMapping("/create_review")
    public String addReview(Principal principal, Model model,
                            @RequestParam(value = "tour_id", required = false) Integer tour_id,
                            @RequestParam(value = "textReview", required = false) String textReview) {

        model.addAttribute("tour_id", tour_id);

        if (textReview != null) {

            User user = userService.findByUserName(principal.getName());
            Tour tour = new Tour();
            if (tourService.findById(tour_id).isPresent()) {
                tour = tourService.findById(tour_id).get();
            }

            Review review = new Review();
            review.setDate(LocalDate.now());
            review.setText(textReview);
            review.setUserID(user);
            review.setTourID(tour);

            reviewService.add(review);

            return "redirect:/profile_member?left_review";
        }

        return "review";
    }


    @PostMapping("/remove_review")
    public String removeReview(Model model,
                               @RequestParam(value = "review_id", required = false) Integer review_id) {

        reviewService.delete(review_id);

        return "redirect:/profile_member?removed_review";
    }

    @GetMapping("/update_review_view")
    public String updateReviewView(Model model, Principal principal,
                                   @RequestParam(value = "tour_id", required = false) Integer tour_id,
                                   @RequestParam(value = "review_id", required = false) Integer review_id,
                                   @RequestParam(value = "textReview", required = false) String textReview) {

        String currentText = reviewService.findById(review_id).get().getText();

        model.addAttribute("tour_id", tour_id);
        model.addAttribute("review_id", review_id);
        model.addAttribute("currentText", currentText);

        return "review";

    }

    @PostMapping("/update_review")
    public String updateReview(Model model, Principal principal,
                               @RequestParam(value = "tour_id", required = false) Integer tour_id,
                               @RequestParam(value = "review_id", required = false) Integer review_id,
                               @RequestParam(value = "textReview", required = false) String textReview) {

        if (textReview != null) {

            User user = userService.findByUserName(principal.getName());
            Tour tour = new Tour();
            if (tourService.findById(tour_id).isPresent()) {
                tour = tourService.findById(tour_id).get();
            }

            Review review = new Review();
            review.setId(review_id);
            review.setDate(LocalDate.now());
            review.setText(textReview);
            review.setUserID(user);
            review.setTourID(tour);

            reviewService.update(review);

            return "redirect:/profile_member?updated_review";
        }

        return "review";
    }

}
