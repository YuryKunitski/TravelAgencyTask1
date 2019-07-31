package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.EntityDAO;
import by.epam.kunitski.travelagency.dao.specification.Specification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
abstract public class AbstractEntityDao<T> implements EntityDAO<T> {

    private final Logger LOGGER = LoggerFactory.getLogger(AbstractEntityDao.class);

    private Class<T> type;

    public AbstractEntityDao(Class<T> type) {
        this.type = type;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<T> getAll(Specification<T> specification) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);

        Predicate predicate = specification.toPredicate(root, criteriaBuilder);
        criteriaQuery.where(criteriaBuilder.and(predicate));
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();

    }

    @Override
    public Optional<T> getById(int id) {
        return Optional.ofNullable(entityManager.find(type, id));
    }

    @Override
    public boolean delete(int id) {

        boolean result = false;

        try {
            T entity = getById(id).get();
            entityManager.remove(entity);
            result = true;
        } catch (NoSuchElementException e) {
            LOGGER.error("Couldn't delete " + type.getSimpleName() + " with id " + id);
        }
        return result;
    }

    @Override
    public T create(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }
}
