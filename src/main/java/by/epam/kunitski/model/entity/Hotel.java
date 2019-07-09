package by.epam.kunitski.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private int id;
    private String name;
    private int stars;
    private String website;
    private double latitude;
    private double longitude;
    private FeatureType features;

   public enum FeatureType{
        SWIMMING_POOL, FREE_WIFI, PARKING, CHILDREN_AREA, RESTAURANT
    }

}
