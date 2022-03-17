package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;

import java.io.InvalidClassException;

public abstract class Role {

   public Role(){
   }

   public abstract Calendar getCalendar() throws InvalidClassException;

   public abstract void setCalendar(Calendar calendar) throws InvalidClassException;

   public abstract boolean addTravelTo(Travel travel, Person agent) throws InvalidClassException;
}
