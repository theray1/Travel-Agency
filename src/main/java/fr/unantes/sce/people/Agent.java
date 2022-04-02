package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;

import java.io.InvalidClassException;

/**
 * Agent represents one of the roles available. An agent has a calendar but cannot manage any calendar.
 */
public class Agent extends Role{
   protected Calendar calendar;

   public Agent(Calendar c){
      this.calendar = c;
   }

   @Override
   public Calendar getCalendar() {
      return this.calendar;
   }

   @Override
   public void setCalendar(Calendar calendar) {
      if(calendar!=null){
         this.calendar = calendar;
      }
      else {
         throw new IllegalArgumentException("calendar must not be null");
      }
   }

   @Override
   public boolean addTravelTo(Travel travel, Person agent) throws InvalidClassException {
      throw new InvalidClassException("Invalid operation. Only an administrator can add travel to an agent.");
   }

   @Override
   public String toString() {
      return "agent";
   }

}
