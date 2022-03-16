package fr.unantes.sce.calendar;

import fr.unantes.sce.people.Person;

import java.util.ArrayList;

/**
 * A Calendar stores a list of travels for an agent
 */
public class Calendar {
    private final ArrayList<Travel> travels;
    private Person owner;

    public Calendar(Person owner) {
        this.owner = owner;
        travels = new ArrayList<>();
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public boolean addTravel(Travel travel) throws IndexOutOfBoundsException {
        if (!this.travels.contains(travel)) return travels.add(travel);
        else if (this.travels.size() > 10) throw new IndexOutOfBoundsException();
        else return false;
    }

    public boolean removeTravel(Travel travel) {
        return travels.remove(travel);
    }

    public ArrayList<Travel> travels() {
        return this.travels;
    }
}
