package fr.unantes.sce.calendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class CorrespondenceTest {
    private LocalDateTime time1;
    private LocalDateTime time2;
    private Correspondence correspEmpty;

    @BeforeEach
    void setUp() {
        time1 = LocalDateTime.of(2012,1,12,15,5);
        time2 = LocalDateTime.of(2012,1,13,3,0);
        correspEmpty = new Correspondence(null,null,null,null,null);
    }

    @Test
    void set_start_and_end_time_test(){
        correspEmpty.setStartTime(time1);
        correspEmpty.setArrivalTime(time2);

        assertEquals(time1,correspEmpty.getStartTime());
        assertEquals(time2,correspEmpty.getArrivalTime());
    }

    @Test
    void setStartTime_with_startTime_after_arrival_time_must_throw_exception(){
        correspEmpty.setArrivalTime(time1);

        assertThrows(RuntimeException.class, () -> correspEmpty.setStartTime(time2));
    }

    @Test
    void setArrivalTime_with_arrivalTime_before_start_time_must_throw_exception(){
        correspEmpty.setStartTime(time2);

        assertThrows(RuntimeException.class, () -> correspEmpty.setArrivalTime(time1));
    }

    @Test
    void set_start_and_destination_city_test(){
        City city1 = new City("France","Paris");
        City city2 = new City("Belgique","Bruxelles");

        correspEmpty.setStartCity(city1);
        correspEmpty.setDestinationCity(city2);

        assertEquals(city1, correspEmpty.getStartCity());
        assertEquals(city2, correspEmpty.getDestinationCity());
    }

    @Test
    void correspondence_constructor_with_same_cities_must_throw_exception(){
        City city1 = new City("France","Paris");

        assertThrows(RuntimeException.class, () -> new Correspondence(null, city1, city1,null,null));
    }

    @Test
    void setStartCity_with_same_cities_must_throw_exception(){
        City city1 = new City("France","Paris");

        correspEmpty.setStartCity(city1);

        assertThrows(RuntimeException.class, () -> correspEmpty.setDestinationCity(city1));
    }

    @Test
    void setDestinationCity_with_same_cities_must_throw_exception(){
        City city1 = new City("France","Paris");

        correspEmpty.setDestinationCity(city1);

        assertThrows(RuntimeException.class, () -> correspEmpty.setStartCity(city1));
    }

    @AfterEach
    void tearDown() {
    }
}
