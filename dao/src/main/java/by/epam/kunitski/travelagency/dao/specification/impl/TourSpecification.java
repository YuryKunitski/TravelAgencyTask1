package by.epam.kunitski.travelagency.dao.specification.impl;

import by.epam.kunitski.travelagency.dao.daoDto.TourDto;
import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.dao.entity.Hotel;
import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.dao.specification.AbstractSpecification;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class TourSpecification extends AbstractSpecification<Tour> {

    private List<String> countryNames = new ArrayList<>();
    private Tour.TourType tourType;
    private Integer maxStars;
    private Integer minStars;
    private LocalDate minDate;
    private LocalDate maxDate;
    private Integer minDuration;
    private Integer maxDuration;
    private Double minCost;
    private Double maxCost;

    public TourSpecification(TourDto tourDto) {
        this.countryNames = tourDto.getCountryNames();
        this.tourType = tourDto.getTourType();
        this.maxStars = tourDto.getMaxStars();
        this.minStars = tourDto.getMinStars();
        this.minDate = tourDto.getMinDate();
        this.maxDate = tourDto.getMaxDate();
        this.minDuration = tourDto.getMinDuration();
        this.maxDuration = tourDto.getMaxDuration();
        this.minCost = tourDto.getMinCost();
        this.maxCost = tourDto.getMaxCost();
    }

    @Override
    public Predicate toPredicate(Root<Tour> root, CriteriaBuilder cb) {

        List<Predicate> predicates = new ArrayList<>();

        //filter for countries name
        if (countryNames.size() != 0) {
            Join<Tour, Country> joinCountry = root.join("country_id");
            Predicate predicateCountryName = cb.and(joinCountry.get("name").in(countryNames));
            predicates.add(predicateCountryName);
        }

        //filter for tour type
        if (tourType != null) {
            Predicate predicateTourType = cb.equal(root.get("tour_type"), tourType);
            predicates.add(predicateTourType);
        }

        //filter for hotel stars
        Join<Tour, Hotel> joinHotel = root.join("hotel_id");
        Path<Integer> intStarsPath = joinHotel.get("stars");
        Optional<Predicate> predicateHotelStars = getRangePredicate(minStars, maxStars, intStarsPath, cb);
        predicateHotelStars.ifPresent(predicates::add);

        //filter for tour date
        Path<LocalDate> localDatePath = root.get("date");
        Optional<Predicate> predicateTourDate = getRangePredicate(minDate, maxDate, localDatePath, cb);
        predicateTourDate.ifPresent(predicates::add);

        //filter for tour duration
        Path<Integer> intDurationPath = root.get("duration");
        Optional<Predicate> predicateTourDuration = getRangePredicate(minDuration, maxDuration, intDurationPath, cb);
        predicateTourDuration.ifPresent(predicates::add);

        //filter for tour cost
        Path<Double> dblCostPath = root.get("cost");
        Optional<Predicate> predicateTourCost = getRangePredicate(minCost, maxCost, dblCostPath, cb);
        predicateTourCost.ifPresent(predicates::add);

        Predicate result = null;
        result = cb.and(predicates.toArray(new Predicate[0]));

        return result;

    }
}
