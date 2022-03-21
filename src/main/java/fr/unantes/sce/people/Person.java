package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;

import java.io.InvalidClassException;
import java.util.Objects;

/**
 * A Generic person, which can be an agent or an administrator
 */
public class Person {
    private String name;
    private final Role role;

    public Person(String name, String role) throws InvalidClassException {
        if (!role.equals("agent") && !role.equals("admin")) {
            throw new InvalidClassException("Invalid role supplied. A person can only be an agent or an admin");
        }
        this.name = name;
        if(role.equals("agent")){
            this.role = new Agent(new Calendar(this));
        } else {
            this.role = new Admin();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role.toString();
    }

    public Calendar getCalendar() throws InvalidClassException {
        return role.getCalendar();
    }

    public void setCalendar(Calendar calendar) throws InvalidClassException {
        role.setCalendar(calendar);
    }

    public boolean addTravelTo(Travel travel, Person agent) throws InvalidClassException {
        return role.addTravelTo(travel,agent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(role, person.role);
    }

    @Override
    public int hashCode() {
        try {
            return Objects.hash(getName(), getRole(), getCalendar());
        } catch (InvalidClassException e) {
            return Objects.hash(getName(), getRole());
        }
    }
}
