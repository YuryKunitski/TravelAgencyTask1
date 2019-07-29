package by.epam.kunitski.travelagency.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

abstract public class AbstractEntityDao<T> {

    @PersistenceContext
    private EntityManager em;

    public List<T> getAll(Class<T> typeClass) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(typeClass);
        Root<T> root = criteriaQuery.from(typeClass);
        criteriaQuery.select(root);

        return em.createQuery(criteriaQuery).getResultList();
    }
}
