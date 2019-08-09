package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.specification.Specification;
import by.epam.kunitski.travelagency.entity.Tour;
import by.epam.kunitski.travelagency.entity.User;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Repository("tourDAOImpl")
public class TourDAOImpl extends AbstractEntityDao<Tour> {

    public TourDAOImpl(){
        super(Tour.class);
    }

    @Override
    public List<Tour> getAll() {
        return super.getAll();
    }

    @Override
    public List<Tour> getAllByCriteria(Specification<Tour> tourSpecification) {
        return super.getAllByCriteria(tourSpecification);
    }

    public List<Tour> getAllByUserId(int userId){
        CriteriaBuilder cb = super.entityManager.getCriteriaBuilder();

        CriteriaQuery<Tour> criteriaQuery = cb.createQuery(Tour.class);
        Root<Tour> root = criteriaQuery.from(Tour.class);
        Join<Tour, User> joinUser = root.join("userList");
        Predicate predicateUser = cb.equal(joinUser.get("id"), userId);

        criteriaQuery.where(cb.and(predicateUser));
        TypedQuery<Tour> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public Optional<Tour> getById(int id) {
        return super.getById(id);
    }

    @Override
    public boolean delete(int id) {
        return super.delete(id);
    }

    @Override
    public Tour create(Tour tour) {
        return super.create(tour);
    }

    @Override
    public Tour update(Tour tour) {
        return super.update(tour);
    }
}
