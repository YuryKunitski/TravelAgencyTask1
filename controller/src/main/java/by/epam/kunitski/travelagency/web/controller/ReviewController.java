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

    @GetMapping("/review")
    public String leaveReviewForm(Principal principal, Model model,
                                  @RequestParam(value = "tour_id", required = false) Integer tour_id,
                                  @RequestParam(value = "textReview", required = false) String textReview) {


        System.out.println("tour_id - " + tour_id);
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


    @GetMapping("/remove_review")
    public String removeReview(Model model,
                               @RequestParam(value = "review_id", required = false) Integer review_id) {

        reviewService.delete(review_id);

        return "redirect:/profile_member";
    }

    @GetMapping("/update_review")
    public String updateReview(Model model,
                               @RequestParam(value = "review_id", required = false) Integer review_id,
                               @RequestParam(value = "tour_id", required = false) Integer tour_id,
                               @RequestParam(value = "textReview", required = false) String textReview) {

        System.out.println("tour_id" + tour_id);
        System.out.println("review_id" + review_id);

        model.addAttribute("tour_id", tour_id);
        model.addAttribute("review_id", review_id);

        if (textReview != null) {

            Review review = new Review();
            review.setId(review_id);
            review.setDate(LocalDate.now());
            review.setText(textReview);

            reviewService.update(review);

            return "redirect:/profile_member";
        }

        return "redirect:/review?update";
    }

}
