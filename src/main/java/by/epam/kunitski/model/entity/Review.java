package by.epam.kunitski.model.entity;

import java.sql.Date;

public class Review {

    private int id;
    private Date date;
    private String text;
    private int userID;
    private int tourID;

    public Review() {
    }

    public Review(int id, Date date, String text, int userID, int tourID) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.userID = userID;
        this.tourID = tourID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTourID() {
        return tourID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (id != review.id) return false;
        if (userID != review.userID) return false;
        if (tourID != review.tourID) return false;
        if (date != null ? !date.equals(review.date) : review.date != null) return false;
        return text != null ? text.equals(review.text) : review.text == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + userID;
        result = 31 * result + tourID;
        return result;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", userID=" + userID +
                ", tourID=" + tourID +
                '}';
    }
}
