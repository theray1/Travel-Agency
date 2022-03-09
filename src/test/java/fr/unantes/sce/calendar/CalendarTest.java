package fr.unantes.sce.calendar;


import fr.unantes.sce.people.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InvalidClassException;
import static org.junit.jupiter.api.Assertions.*;

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
    void SetOwner_HasNoOwner_HasOwner2() throws InvalidClassException {
        c.setOwner(null);
        Person p2 = new Person("TestName2", "agent");
        c.setOwner(p2);
        assert c.getOwner().equals(p2);
    }

    @Test
    void addTravel_HandShake() {
        Travel t = new Travel(null);
        c.addTravel(t);

        assert t.getParent() != null;
        assert c.travels().contains(t);
    }

    @Test
    void addTravel_NotMoreThan10Travels() {
        for(int i = 0; i < 10; i++){
            c.addTravel(new Travel(c));
        }

        Travel t = new Travel(c);
        c.addTravel(t);

        assert !c.travels().contains(t);
    }

    @Test
    void addTravel_NoDuplicates(){
        Travel t = new Travel(c);
        c.addTravel(t);
        c.addTravel(t);

        c.removeTravel(t);
        assert !c.travels().contains(t);
    }
}
