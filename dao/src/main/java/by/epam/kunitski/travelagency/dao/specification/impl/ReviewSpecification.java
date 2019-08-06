package by.epam.kunitski.travelagency.dao.specification.impl;

import by.epam.kunitski.travelagency.dao.specification.AbstractSpecification;
import by.epam.kunitski.travelagency.entity.Review;
import by.epam.kunitski.travelagency.entity.Tour;
import by.epam.kunitski.travelagency.entity.User;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Setter
public class ReviewSpecification extends AbstractSpecification<Review> {

    private Tour tourReview;
    private User userReview;

    @Override
    public Predicate toPredicate(Root<Review> root, CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();

        //filter for a specific tour
        if (tourReview != null) {
            Predicate predicateTour = cb.equal(root.get("tourID"), tourReview);
            predicates.add(predicateTour);
        }

        //filter for a specific user
        if (userReview != null) {
            Predicate predicateUser = cb.equal(root.get("userID"), userReview);
            predicates.add(predicateUser);
        }

        Predicate result = null;
        result = cb.and(predicates.toArray(new Predicate[0]));

        return result;
    }
}
