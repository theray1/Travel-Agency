package fr.unantes.sce.calendar;


import fr.unantes.sce.people.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InvalidClassException;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalendarTest {

    Calendar c;

    @BeforeEach
    public void setUp() throws Exception {
        Person p1 = new Person("TestName", "agent");
        c = new Calendar(p1);

    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    void SetOwner_sets_owner_properly() throws InvalidClassException {
        c.setOwner(null);
        Person p2 = new Person("TestName2", "agent");
        c.setOwner(p2);
        assertEquals(c.getOwner(), p2);
    }

    @Test
    void addTravel_HandShake() throws InvalidClassException {
        Person p = new Person("TestName2", "agent");
        Travel t = new Travel(new Calendar(p));
        c.addTravel(t);

        assertNotNull(t.getParent());
        assertTrue(c.travels().contains(t));
    }

    @Test
    void addTravel_NotMoreThan10Travels() {
        for(int i = 0; i < 10; i++){
            c.addTravel(new Travel(c));
        }

        Travel t = new Travel(c);

        assertThrows(IndexOutOfBoundsException.class, () -> c.addTravel(t));
    }

    @Test
    void addTravel_NoDuplicates(){
        Travel t = new Travel(c);
        c.addTravel(t);
        c.addTravel(t);

        c.removeTravel(t);
        assertFalse(c.travels().contains(t));
    }
}
