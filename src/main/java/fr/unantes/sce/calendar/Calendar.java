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

    public boolean addTravel(Travel travel) {
        return travels.add(travel);
    }

    public boolean removeTravel(Travel travel) {
        return travels.remove(travel);
    }
}
