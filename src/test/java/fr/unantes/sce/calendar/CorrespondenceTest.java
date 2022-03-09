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
        assert(corresp1.getStartTime().getHour()==1);
        assert(corresp1.getStartTime().getMinute()==20);
        assert(corresp1.getArrivalTime().getHour()==8);
        assert(corresp1.getArrivalTime().getMinute()==45);
        assert(corresp1.getStartTime().getHour()*60+corresp1.getStartTime().getMinute()==80);
        assert(corresp1.getArrivalTime().getHour()*60+corresp1.getArrivalTime().getMinute()==525);

        LocalTime time1 = LocalTime.of(12,59);
        LocalTime time2 = LocalTime.of(18,25);
        Correspondence corresp2 = new Correspondence(null, null, null, time1, time2);
        assert(corresp2.getStartTime().getHour()==12);
        assert(corresp2.getStartTime().getMinute()==59);
        assert(corresp2.getArrivalTime().getHour()==18);
        assert(corresp2.getArrivalTime().getMinute()==25);
    }

    @AfterEach
    void tearDown() {
    }
}
