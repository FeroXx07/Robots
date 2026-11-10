package Robots;

import java.util.Objects;

public abstract class Robot {
    private final String name;
    private final String manufacturer;
    private final int yearManufactur;

    private final int yearRegisterCompetition;

    public Robot(String name, String manufacturer, int yearManufactur, int yearRegistration) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.yearManufactur = yearManufactur;
        this.yearRegisterCompetition = yearRegistration;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getYearManufactur() {
        return yearManufactur;
    }

    public int getYearRegisterCompetition() {
        return yearRegisterCompetition;
    }

    public abstract String getTechnicalDescription();

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Robot) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.manufacturer, that.manufacturer) &&
                this.yearManufactur == that.yearManufactur &&
                this.yearRegisterCompetition == that.yearRegisterCompetition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manufacturer, yearManufactur, yearRegisterCompetition);
    }

    @Override
    public String toString() {
        return "Robot[" +
                "name=" + name + ", " +
                "manufacturer=" + manufacturer + ", " +
                "yearManufactur=" + yearManufactur + ", " +
                "yearRegistration=" + yearRegisterCompetition + ']';
    }

}
