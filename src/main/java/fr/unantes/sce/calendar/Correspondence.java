package fr.unantes.sce.calendar;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A correspondance belongs to a travel, and has a starting city and a destination city, each having a corresponding start and arrival time.
 */
public class Correspondence {
    private Travel travel;
    private City startCity;
    private City destinationCity;

    private LocalDateTime startTime;
    private LocalDateTime arrivalTime;

    /**
     * Class constructor
     * @param travel the travel which the correspondence is a step of
     * @param startCity the starting city of the correspondence
     * @param destinationCity the destination city of the correspondence
     * @param startTime the time of departure of the correspondence
     * @param arrivalTime the time of arrival of the correspondence
     */
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

    /**
     * travel getter
     * @return travel
     */
    public Travel getTravel() {
        return travel;
    }

    /**
     * Sets the travel of the correspondence and adds the correspondence to the travel
     * @param travel the travel the correspondence is a part of
     */
    public void setTravel(Travel travel) {
        this.travel = travel;
        this.travel.basicAddCorrespondance(this);
    }

    /**
     * Sets the travel of the correspondence
     * @param travel
     */
    public void basicSetTravel(Travel travel) {
        this.travel = travel;
    }

    /**
     * start city getter
     * @return the start city of the correspondence
     */
    public City getStartCity() {
        return startCity;
    }

    /**
     * Sets the start city
     * @param startCity the start city of the correspondence
     */
    public void setStartCity(City startCity) {
        if (startCity != null && !startCity.equals(destinationCity)) {
            this.startCity = startCity;
        } else {
            throw new RuntimeException("startCity and destinationCity must be different");
        }
    }

    /**
     * destination city getter
     * @return the destination city of the correspondence
     */
    public City getDestinationCity() {
        return destinationCity;
    }

    /**
     * Sets the destination city
     * @param destinationCity the start city of the correspondence
     */
    public void setDestinationCity(City destinationCity) {
        if (destinationCity != null && !destinationCity.equals(startCity)) {
            this.destinationCity = destinationCity;
        } else {
            throw new RuntimeException("startCity and destinationCity must be different");
        }
    }

    /**
     * start time getter
     * @return the start time of the correspondence
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time
     * @param startTime the start time of the correspondence
     */
    public void setStartTime(LocalDateTime startTime) {
        if (startTime != null) {
            if (this.arrivalTime == null || startTime.isBefore(this.arrivalTime)) {
                this.startTime = startTime;
            } else {
                throw new RuntimeException("start time must be before arrival time");
            }
        }
    }

    /**
     * arrival time getter
     * @return the arrival time of the correspondence
     */
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Sets the arrival time
     * @param arrivalTime the arrival time of the correspondence
     */
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
