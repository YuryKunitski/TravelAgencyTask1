package by.epam.kunitski.travelagency.dao.specification.impl;

import by.epam.kunitski.travelagency.dao.specification.AbstractSpecification;
import by.epam.kunitski.travelagency.dao.entity.Country;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CountrySpecification extends AbstractSpecification<Country> {

    @Override
    public Predicate toPredicate(Root<Country> root, CriteriaBuilder cb) {
        return cb.isNotNull(root);
    }
}
