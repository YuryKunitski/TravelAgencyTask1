package by.epam.kunitski.travelagency.dao.specification;

import javax.persistence.criteria.*;
import java.util.Optional;

public abstract class AbstractSpecification<T> implements Specification<T> {

    protected <X extends Comparable<? super X>> Optional<Predicate> getRangePredicate(X min, X max, Path<X> path, CriteriaBuilder cb) {

        Predicate predicate = null;

        if (min != null) {
            predicate = cb.greaterThanOrEqualTo(path, min);
        }

        if (max != null && min != null) {
            predicate = cb.and(
                    cb.lessThanOrEqualTo(path, max),
                    predicate);

        } else if (max != null) {
            predicate = cb.lessThanOrEqualTo(path, max);
        }

        return Optional.ofNullable(predicate);
    }
}