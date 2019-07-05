package by.epam.kunitski.model.entity;

public class Hotel {

    private int id;
    private String name;
    private int stars;
    private String website;
    private double latitude;
    private double longitude;
    private FeatureType features;

    enum FeatureType{
        SWIMMING_POOL, FREE_WIFI, PARKING, CHILDREN_AREA, RESTAURANT
    }

    public Hotel() {
    }

    public Hotel(int id, String name, int stars, String website, double latitude, double longitude, FeatureType features) {
        this.id = id;
        this.name = name;
        this.stars = stars;
        this.website = website;
        this.latitude = latitude;
        this.longitude = longitude;
        this.features = features;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public FeatureType getFeatures() {
        return features;
    }

    public void setFeatures(FeatureType features) {
        this.features = features;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (id != hotel.id) return false;
        if (stars != hotel.stars) return false;
        if (Double.compare(hotel.latitude, latitude) != 0) return false;
        if (Double.compare(hotel.longitude, longitude) != 0) return false;
        if (name != null ? !name.equals(hotel.name) : hotel.name != null) return false;
        if (website != null ? !website.equals(hotel.website) : hotel.website != null) return false;
        return features != null ? features.equals(hotel.features) : hotel.features == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + stars;
        result = 31 * result + (website != null ? website.hashCode() : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (features != null ? features.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stars=" + stars +
                ", website='" + website + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", features='" + features + '\'' +
                '}';
    }
}
