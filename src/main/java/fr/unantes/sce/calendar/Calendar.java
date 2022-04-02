package fr.unantes.sce.calendar;

import fr.unantes.sce.people.Person;

import java.util.ArrayList;

/**
 * A Calendar stores a list of travels for an agent
 */
public class Calendar {
    private final ArrayList<Travel> travels;
    private Person owner;

    /**
     * Class constructor
     * @param owner the person whose calendar it is
     */
    public Calendar(Person owner) {
        this.owner = owner;
        travels = new ArrayList<>();
    }

    /**
     * owner getter
     * @return owner of the calendar
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * owner setter
     * @param owner the new owner of the calendar
     */
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    /**
     * Adds travel to the calendar
     * @param travel the travel to be added
     * @return True if the travel was successfully added, False otherwise
     * @throws IndexOutOfBoundsException
     */
    public boolean addTravel(Travel travel) throws IndexOutOfBoundsException {
        if (!this.travels.contains(travel)) return travels.add(travel);
        else if (this.travels.size() > 10) throw new IndexOutOfBoundsException();
        else return false;
    }

    /**
     * Removes travel from the calendar
     * @param travel the travel to be removed
     * @return True if the travel was successfully removed, False otherwise.
     */
    public boolean removeTravel(Travel travel) {
        return travels.remove(travel);
    }

    /**
     * travels getter
     * @return travels
     */
    public ArrayList<Travel> travels() {
        return this.travels;
    }
}
