package fr.unantes.sce.people;

import fr.unantes.sce.calendar.Calendar;
import fr.unantes.sce.calendar.Travel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.InvalidClassException;


class PersonTest {
    private Person agent;
    private Person admin;
    private Calendar cal;
    private Travel tra;

    @BeforeEach
    void setUp() {
        try {
            agent = new Person("Alice", "agent");
            admin = new Person("Bob", "admin");
        }
        catch(InvalidClassException e){
            e.printStackTrace();
        }
        cal = new Calendar(agent);
        tra = new Travel(cal);
    }

    @Test
    void constructor_exception_test(){
        assertThrows(InvalidClassException.class, () -> new Person("alice","error"));
    }

    @Test
    void get_and_set_name_test(){
        assertEquals("Alice",agent.getName());
        assertEquals("Bob",admin.getName());

        admin.setName("Alice2");
        agent.setName("Bob2");

        assertEquals("Bob2",agent.getName());
        assertEquals("Alice2",admin.getName());
    }

    @Test
    void get_and_setRole_test(){
        assertEquals("agent",agent.getRole());
        assertEquals("admin",admin.getRole());
        try {
            admin.setRole("agent");
            agent.setRole("admin");
            assertEquals("agent",admin.getRole());
            assertEquals("admin",agent.getRole());
        }
        catch(InvalidClassException e){
            e.printStackTrace();
        }
    }

    @Test
    void setRole_test_with_wrong_role_must_throw_exception(){
        assertThrows(InvalidClassException.class, () -> admin.setRole("test"));
        assertThrows(InvalidClassException.class, () -> agent.setRole("test"));
    }

    @Test
    void get_and_setCalendar_agent_test(){
        try {
            agent.setCalendar(cal);
            assertEquals(cal,agent.getCalendar());
        }
        catch(InvalidClassException e){
            e.printStackTrace();
        }
    }

    @Test
    void get_and_setCalendar_admin_test(){
        assertThrows(InvalidClassException.class, () -> admin.setCalendar(cal));
        assertThrows(InvalidClassException.class, () -> admin.getCalendar());
    }

    @Test
    void admin_addTravelTo_test(){

        assertThrows(InvalidClassException.class, () -> admin.addTravelTo(tra, admin));
        try {
            assertTrue(admin.addTravelTo(tra, agent));
        }
        catch(InvalidClassException e){
            e.printStackTrace();
        }
    }

    @Test
    void agent_addTravelTo_test(){
        assertThrows(InvalidClassException.class, () -> agent.addTravelTo(tra, agent));
    }

    @Test
    void equals_method_test(){
        assertNotEquals(admin, agent);
        assertEquals(admin, admin);
    }

    @AfterEach
    void tearDown() {
    }
}
