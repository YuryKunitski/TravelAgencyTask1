package by.epam.kunitski.travelagency.dao.specification.impl;

import by.epam.kunitski.travelagency.dao.entity.Review;
import by.epam.kunitski.travelagency.dao.specification.AbstractSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ReviewSpecification extends AbstractSpecification<Review> {

    @Override
    public Predicate toPredicate(Root<Review> root, CriteriaBuilder cb) {
        return cb.isNotNull(root);
    }

}
