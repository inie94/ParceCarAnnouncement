package ca;

import java.util.Objects;

public class CarAd {
    private String title;
    private String url;
    private String seller;
    private String location;
    private String price;
    private String siteName;
    private Car car;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarAd carAd = (CarAd) o;
        return Objects.equals(title, carAd.title) && Objects.equals(url, carAd.url) && Objects.equals(seller, carAd.seller) && Objects.equals(location, carAd.location) && Objects.equals(price, carAd.price) && Objects.equals(siteName, carAd.siteName) && Objects.equals(car, carAd.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url, seller, location, price, siteName, car);
    }

    @Override
    public String toString() {
        return "CarAd{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", seller='" + seller + '\'' +
                ", location='" + location + '\'' +
                ", price='" + price + '\'' +
                ", siteName='" + siteName + '\'' +
                ", car=" + car +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
