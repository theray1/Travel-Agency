package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;

import java.io.InvalidClassException;

public class Admin extends Role{

   @Override
   public Calendar getCalendar() throws InvalidClassException {
      throw new InvalidClassException("Invalid operation. Only agent have a calendar");
   }

   @Override
   public void setCalendar(Calendar calendar) throws InvalidClassException {
      throw new InvalidClassException("Invalid operation. Only agent have a calendar");
   }

   @Override
   public boolean addTravelTo(Travel travel, Person agent) throws InvalidClassException {
      return agent.getCalendar().addTravel(travel);
   }

   @Override
   public String toString() {
      return "admin";
   }

}
