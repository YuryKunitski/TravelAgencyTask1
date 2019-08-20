package by.epam.kunitski.travelagency.dao.dto;

import by.epam.kunitski.travelagency.dao.entity.Tour;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDto {

    private List<String> countryNames = new ArrayList<>();

    private Tour.TourType tourType;

    @Range(min = 1, max = 5)
    private Integer maxStars;

    @Range(min = 1, max = 5)
    private Integer minStars;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate minDate;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate maxDate;

    @Range(min = 1, max = 99)
    private Integer minDuration;

    @Range(min = 1, max = 99)
    private Integer maxDuration;

    @Positive
    private Double minCost;

    @Positive
    private Double maxCost;

}
