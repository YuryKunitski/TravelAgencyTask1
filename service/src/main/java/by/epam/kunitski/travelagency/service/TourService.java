package by.epam.kunitski.travelagency.service;


import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.dao.specification.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TourService extends EntityService<Tour> {

    List<Tour> findAllByUserId(int userId);
    Page<Tour> findPaginated(Pageable pageable, Specification<Tour> tourSpecification);

}
