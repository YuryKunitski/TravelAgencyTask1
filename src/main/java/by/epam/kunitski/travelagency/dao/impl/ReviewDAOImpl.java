package by.epam.kunitski.travelagency.dao.impl;

import by.epam.kunitski.travelagency.dao.specification.Specification;
import by.epam.kunitski.travelagency.entity.Review;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository("reviewDAOImpl")
public class ReviewDAOImpl extends AbstractEntityDao<Review> {

    public ReviewDAOImpl() {
        super(Review.class);
    }

    @Override
    public List<Review> getAll(Specification<Review> reviewSpecification) {
        return super.getAll(reviewSpecification);
    }

    @Override
    public Optional<Review> getById(int id) {
        return super.getById(id);
    }

    @Override
    public boolean delete(int id) {
        return super.delete(id);
    }

    @Override
    public Review create(Review review) {
        return super.create(review);
    }

    @Override
    public Review update(Review review) {
        return super.update(review);
    }
}
