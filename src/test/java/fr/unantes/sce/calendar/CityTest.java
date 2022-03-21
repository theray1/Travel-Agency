package fr.unantes.sce.calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CityTest {
   private City city1;

   @BeforeEach
   public void setUp(){
      city1 = new City("France","Arnac-la-poste");
   }

   @Test
   void constructor_with_null_values_must_throw_exception(){
      assertThrows(IllegalArgumentException.class, () -> new City(null,"Mouais"));
      assertThrows(IllegalArgumentException.class, () -> new City("Espagne",null));
      assertThrows(IllegalArgumentException.class, () -> new City(null,null));
   }

   @Test
   void constructor_with_correct_values_must_works(){
      assertDoesNotThrow( () -> new City("Espagne","Madrid"));
      assertDoesNotThrow( () -> new City("France","Arnac-la-poste"));
   }

   @Test
   void get_and_setName_test() {
      assertEquals("Arnac-la-poste", city1.getName());
      city1.setName("Mouais");
      assertEquals("Mouais", city1.getName());
   }

   @Test
   void getCountry_test(){
      assertEquals("France", city1.getCountry());
   }

   @Test
   void setName_with_null_values_must_throw_exception(){
      assertThrows(IllegalArgumentException.class, () -> city1.setName(null));
   }

   @Test
   void equals_and_hash_code_must_works(){
      City city2 = new City("Espagne","Madrid");
      assertNotEquals(city1, city2);
      City city3 = new City("France","Arnac-la-poste");
      assertEquals(city1,city3);

      assertEquals(city1.hashCode(), city3.hashCode());
      assertFalse(city2.hashCode()==city3.hashCode());
   }

}
