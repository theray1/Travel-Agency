package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;

import java.io.InvalidClassException;

/**
 * Role defines the methods used by a person to interact with calendars.
 */
public abstract class Role {

   /**
    * Class constructor
    */
   public Role(){
   }

   /**
    * Calendar getter
    * @return the calendar of the person
    * @throws InvalidClassException
    */
   public abstract Calendar getCalendar() throws InvalidClassException;

   /**
    * Sets the calendar of the person
    * @param calendar the calendar of the person
    * @throws InvalidClassException
    */
   public abstract void setCalendar(Calendar calendar) throws InvalidClassException;

   /**
    * Adds a travel to a person's calendar
    * @param travel the travel to be added
    * @param agent the person to receive the new travel
    * @return True if the travel was successfully added, False otherwise
    * @throws InvalidClassException
    */
   public abstract boolean addTravelTo(Travel travel, Person agent) throws InvalidClassException;
}
