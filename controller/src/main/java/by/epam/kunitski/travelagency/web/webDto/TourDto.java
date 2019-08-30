package by.epam.kunitski.travelagency.web.webDto;

import by.epam.kunitski.travelagency.dao.entity.Tour;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDto {

    private String photo;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Range(min = 1, max = 99)
    private Integer duration;

    @Size(min = 1, max = 500)
    private String description;

    @Positive
    private Double cost;

    private Integer hotel_id;

    private Integer country_id;

    private Tour.TourType tour_type;
}
