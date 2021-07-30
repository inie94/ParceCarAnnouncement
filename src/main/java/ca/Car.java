package ca;

import java.util.Objects;

public class Car {
    private String yearOfIssue;
    private String body;
    private String engine;
    private String transmission;
    private String driveUnit;
    private String additionInfo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(yearOfIssue, car.yearOfIssue) && Objects.equals(body, car.body) && Objects.equals(engine, car.engine) && Objects.equals(transmission, car.transmission) && Objects.equals(driveUnit, car.driveUnit) && Objects.equals(additionInfo, car.additionInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yearOfIssue, body, engine, transmission, driveUnit, additionInfo);
    }

    @Override
    public String toString() {
        return "Car{" +
                "yearOfIssue='" + yearOfIssue + '\'' +
                ", body='" + body + '\'' +
                ", engine='" + engine + '\'' +
                ", transmission='" + transmission + '\'' +
                ", driveUnit='" + driveUnit + '\'' +
                ", additionInfo='" + additionInfo + '\'' +
                '}';
    }

    public String getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(String yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getDriveUnit() {
        return driveUnit;
    }

    public void setDriveUnit(String driveUnit) {
        this.driveUnit = driveUnit;
    }

    public String getAdditionInfo() {
        return additionInfo;
    }

    public void setAdditionInfo(String additionInfo) {
        this.additionInfo = additionInfo;
    }
}
