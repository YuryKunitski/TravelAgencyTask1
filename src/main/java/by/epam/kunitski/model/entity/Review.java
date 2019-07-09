package by.epam.kunitski.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    private int id;
    private Date date;
    private String text;
    private int userID;
    private int tourID;
}
