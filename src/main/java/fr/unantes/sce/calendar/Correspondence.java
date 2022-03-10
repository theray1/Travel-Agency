package fr.unantes.sce.calendar;

import java.util.Objects;
import java.time.LocalTime;

public class Correspondence {
    private Travel travel;
    private City startCity;
    private City destinationCity;
    //TODO : voir pour avoir les jour mois année en plus de l'heure et les minutes
    private LocalTime startTime;
    private LocalTime arrivalTime;


    public Correspondence(Travel travel, City startCity, City destinationCity, int startTime, int arrivalTime) {
        this(travel, startCity, destinationCity, LocalTime.of(startTime/60,startTime%60), LocalTime.of(arrivalTime/60,arrivalTime%60));
    }
    public Correspondence(Travel travel, City startCity, City destinationCity, LocalTime startTime, LocalTime arrivalTime) {
        if(startCity!=null && startCity.equals(destinationCity)){
            throw new RuntimeException("startCity and destinationCity must be different");
        }
        this.travel = travel;
        this.startCity = startCity;
        this.destinationCity = destinationCity;
        this.startTime = startTime;
        this.arrivalTime = arrivalTime;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public City getStartCity() {
        return startCity;
    }

    public void setStartCity(City startCity) {
        if(startCity!=null && !startCity.equals(destinationCity)){
            this.startCity = startCity;
        }
    }

    public City getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(City destinationCity) {
        if(destinationCity!=null && !destinationCity.equals(startCity)) {
            this.destinationCity = destinationCity;
        }
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Correspondence that = (Correspondence) o;
        return getStartCity().equals(that.getStartCity()) &&
                getDestinationCity().equals(that.getDestinationCity()) &&
                getStartTime() == that.getStartTime() &&
                getArrivalTime() == that.getArrivalTime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTravel(), getStartCity(), getDestinationCity(), getStartTime(), getArrivalTime());
    }
}
