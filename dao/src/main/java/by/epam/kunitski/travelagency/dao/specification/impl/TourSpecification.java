package by.epam.kunitski.travelagency.dao.specification.impl;

import by.epam.kunitski.travelagency.dao.dto.TourDto;
import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.dao.entity.Hotel;
import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.dao.specification.AbstractSpecification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourSpecification extends AbstractSpecification<Tour> {

    private TourDto tourDto;

    public TourSpecification(TourDto tourDto) {
        this.tourDto = tourDto;
    }

    @Override
    public Predicate toPredicate(Root<Tour> root, CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();

        //filter for countries name
        if (tourDto.getCountryNames().size() != 0) {
            Join<Tour, Country> joinCountry = root.join("country_id");
            Predicate predicateCountryName = cb.and(joinCountry.get("name").in(tourDto.getCountryNames()));
            predicates.add(predicateCountryName);
        }

        //filter for tour type
        if (tourDto.getTourType() != null) {
            Predicate predicateTourType = cb.equal(root.get("tour_type"), tourDto.getTourType());
            predicates.add(predicateTourType);
        }

        //filter for hotel stars
        Join<Tour, Hotel> joinHotel = root.join("hotel_id");
        Path<Integer> intStarsPath = joinHotel.get("stars");
        Optional<Predicate> predicateHotelStars = getRangePredicate(tourDto.getMinStars(), tourDto.getMaxStars(), intStarsPath, cb);
        predicateHotelStars.ifPresent(predicates::add);

        //filter for tour date
        Path<LocalDate> localDatePath = root.get("date");
        Optional<Predicate> predicateTourDate = getRangePredicate(tourDto.getMinDate(), tourDto.getMaxDate(), localDatePath, cb);
        predicateTourDate.ifPresent(predicates::add);

        //filter for tour duration
        Path<Integer> intDurationPath = root.get("duration");
        Optional<Predicate> predicateTourDuration = getRangePredicate(tourDto.getMinDuration(), tourDto.getMaxDuration(), intDurationPath, cb);
        predicateTourDuration.ifPresent(predicates::add);

        //filter for tour cost
        Path<Double> dblCostPath = root.get("cost");
        Optional<Predicate> predicateTourCost = getRangePredicate(tourDto.getMinCost(), tourDto.getMaxCost(), dblCostPath, cb);
        predicateTourCost.ifPresent(predicates::add);

        Predicate result = null;
        result = cb.and(predicates.toArray(new Predicate[0]));

        return result;

    }
}
