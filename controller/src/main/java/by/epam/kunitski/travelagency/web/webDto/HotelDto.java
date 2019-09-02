package by.epam.kunitski.travelagency.web.webDto;

import by.epam.kunitski.travelagency.dao.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {

    private String name;

    @Range(min = 1, max = 5)
    private Integer stars;

    @URL(message = "{msg.valid_url}")
    private String website;

    @Range(min = -90, max = 90)
    private Double latitude;

    @Range(min = -180, max = 180)
    private Double longitude;

    private Hotel.FeatureType features;
}
