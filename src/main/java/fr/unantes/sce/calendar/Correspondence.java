package fr.unantes.sce.calendar;

import java.time.LocalDateTime;
import java.util.Objects;

public class Correspondence {
    private Travel travel;
    private City startCity;
    private City destinationCity;

    private LocalDateTime startTime;
    private LocalDateTime arrivalTime;


    public Correspondence(Travel travel, City startCity, City destinationCity, LocalDateTime startTime, LocalDateTime arrivalTime) {
        if (startCity != null && startCity.equals(destinationCity))
            throw new RuntimeException("startCity and destinationCity must be different");
        if (startTime != null && !startTime.isBefore(arrivalTime)) {
            throw new RuntimeException("ArrivalTime must be after StartTime");
        }
        this.travel = travel;
        this.startCity = startCity;
        this.destinationCity = destinationCity;
        this.startTime = startTime;
        this.arrivalTime = arrivalTime;
        if (travel != null)
            this.travel.addCorrespondence(this);
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
        this.travel.basicAddCorrespondance(this);
    }

    public void basicSetTravel(Travel travel) {
        this.travel = travel;
    }

    public City getStartCity() {
        return startCity;
    }

    public void setStartCity(City startCity) {
        if (startCity != null && !startCity.equals(destinationCity)) {
            this.startCity = startCity;
        } else {
            throw new RuntimeException("startCity and destinationCity must be different");
        }
    }

    public City getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(City destinationCity) {
        if (destinationCity != null && !destinationCity.equals(startCity)) {
            this.destinationCity = destinationCity;
        } else {
            throw new RuntimeException("startCity and destinationCity must be different");
        }
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        if (startTime != null) {
            if (this.arrivalTime == null || startTime.isBefore(this.arrivalTime)) {
                this.startTime = startTime;
            } else {
                throw new RuntimeException("start time must be before arrival time");
            }
        }
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        if (arrivalTime != null) {
            if (this.startTime == null || this.startTime.isBefore(arrivalTime)) {
                this.arrivalTime = arrivalTime;
            } else {
                throw new RuntimeException("start time must be before arrival time");
            }
        }
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
