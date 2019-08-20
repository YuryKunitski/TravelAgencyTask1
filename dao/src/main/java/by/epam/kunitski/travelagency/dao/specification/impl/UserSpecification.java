package by.epam.kunitski.travelagency.dao.specification.impl;

import by.epam.kunitski.travelagency.dao.specification.AbstractSpecification;
import by.epam.kunitski.travelagency.dao.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification extends AbstractSpecification<User> {

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaBuilder cb) {
        return cb.isNotNull(root);
    }
}
