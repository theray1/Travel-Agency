package fr.unantes.sce.calendar;

import java.util.Objects;

/**
 * A city belongs to a country and has a name.
 */
public class City {
    private final String country;
    private String name;

    /**
     * Class constructor
     * @param country the country the city is located in
     * @param name the name of the city
     */
    public City(String country, String name) {
        if (country==null || name==null){
            throw new IllegalArgumentException("arguments must not be null");
        }
        this.country = country;
        this.name = name;
    }

    /**
     * country getter
     * @return the country the city is located in
     */
    public String getCountry() {
        return country;
    }

    /**
     * name getter
     * @return the name of the city
     */
    public String getName() {
        return name;
    }

    /**
     * name setter
     * @param name the name of the city
     */
    public void setName(String name) {
        if (name!=null) {
            this.name = name;
        }
        else {
            throw new IllegalArgumentException("arguments must not be null");
        }
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
