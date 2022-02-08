package fr.unantes.sce.calendar;

import java.util.Objects;

/**
 * A city
 */
public class City {
    public String country;
    public String name;

    public City(String country, String name) {
        this.country = country;
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return getCountry().equals(city.getCountry()) &&
                getName().equals(city.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountry(), getName());
    }
}
