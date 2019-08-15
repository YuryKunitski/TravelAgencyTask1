package by.epam.kunitski.travelagency.dao.dto;

import by.epam.kunitski.travelagency.dao.entity.Tour;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDto {

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

}
