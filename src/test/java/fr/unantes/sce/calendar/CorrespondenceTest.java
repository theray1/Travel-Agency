package fr.unantes.sce.calendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class CorrespondenceTest {
    private Correspondence corresp1;

    @BeforeEach
    void setUp() {
        corresp1 = new Correspondence(null, null, null, 80, 525);
    }

    @Test
    void timeCalculateTest(){
        assertEquals(1,corresp1.getStartTime().getHour());
        assertEquals(20,corresp1.getStartTime().getMinute());
        assertEquals(8,corresp1.getArrivalTime().getHour());
        assertEquals(45,corresp1.getArrivalTime().getMinute());
        assertEquals(80,corresp1.getStartTime().getHour()*60+corresp1.getStartTime().getMinute());
        assertEquals(525,corresp1.getArrivalTime().getHour()*60+corresp1.getArrivalTime().getMinute());

        LocalTime time1 = LocalTime.of(12,59);
        LocalTime time2 = LocalTime.of(18,25);
        Correspondence corresp2 = new Correspondence(null, null, null, time1, time2);
        assertEquals(12,corresp2.getStartTime().getHour());
        assertEquals(59,corresp2.getStartTime().getMinute());
        assertEquals(18,corresp2.getArrivalTime().getHour());
        assertEquals(25,corresp2.getArrivalTime().getMinute());
    }

    @Test
    void correspCityTest(){
        City city1 = new City("France","Paris");
        City city2 = new City("Belgique","Bruxelles");
        corresp1.setStartCity(city1);
        corresp1.setDestinationCity(city2);
        assert(corresp1.getStartCity().equals(city1));
        assert(corresp1.getDestinationCity().equals(city2));

        //the startCity should not change if it's the same at destinationCity
        corresp1.setStartCity(city2);
        assert(corresp1.getStartCity().equals(city1));
        corresp1.setDestinationCity(city1);
        assert(corresp1.getDestinationCity().equals(city2));
        //TODO add test of contructor with same cities
    }

    @AfterEach
    void tearDown() {
    }
}
