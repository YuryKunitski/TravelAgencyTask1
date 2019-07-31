package by.epam.kunitski.travelagency.dao.specification.impl;

import by.epam.kunitski.travelagency.dao.specification.AbstractSpecification;
import by.epam.kunitski.travelagency.entity.Hotel;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class HotelSpecification extends AbstractSpecification<Hotel> {

    @Override
    public Predicate toPredicate(Root<Hotel> root, CriteriaBuilder cb) {
        return cb.isNotNull(root);
    }
}
