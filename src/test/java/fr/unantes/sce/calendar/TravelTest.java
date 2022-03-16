package fr.unantes.sce.calendar;

import fr.unantes.sce.people.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InvalidClassException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class TravelTest {
    private Travel travel;
    private Calendar parent;
    private Person owner;
    private City city1;
    private City city2;
    private City city3;
    private City city4;
    private LocalDateTime date1;
    private LocalDateTime date2;
    private LocalDateTime date3;
    private LocalDateTime date4;
    private LocalDateTime date5;
    private LocalDateTime date6;
    private Correspondence corresp1;
    private Correspondence corresp2;
    private Correspondence corresp3;

    @BeforeEach
    public void setUp() throws Exception {
        this.owner = new Person("Test", "admin");
        this.parent = new Calendar(this.owner);
        this.travel = new Travel(this.parent);

        city1 = new City("France", "Paris");
        city2 = new City("Belgique", "Bruxelles");
        city3 = new City("Irlande", "Dublin");
        city4 = new City("Espagne", "Madrid");
        date1 = LocalDateTime.of(2018, 2, 19, 8, 55);
        date2 = LocalDateTime.of(2018, 3, 20, 18, 50);
        date3 = LocalDateTime.of(2018, 4, 20, 9, 35);
        date4 = LocalDateTime.of(2018, 5, 1, 12, 12);
        date5 = LocalDateTime.of(2018, 6, 3, 3, 45);
        date6 = LocalDateTime.of(2018, 6, 5, 4, 5);

        corresp1 = new Correspondence(travel, city1, city2, date1, date2);
        corresp2 = new Correspondence(travel, city2, city3, date3, date4);
        corresp3 = new Correspondence(travel, city3, city4, date5, date6);
    }

    @Test
    void addCorrespondance_Handshake() {

        assertEquals(this.travel, corresp1.getTravel());

        assertTrue(this.travel.removeCorrespondence(corresp3));
        assertFalse(this.travel.removeCorrespondence(corresp3));

        assertNull(corresp3.getTravel());
    }

    @Test
    void setParent_Handshake() {
        assertTrue(parent.travels().contains(travel));
        travel.setParent(parent);
        assertTrue(parent.travels().contains(travel));
        assertEquals(1, parent.travels().size());
        assertEquals(parent, travel.getParent());
    }

    @Test
    void getFirst_and_getLast_step_test() {
        travel.addCorrespondence(this.corresp2);
        travel.addCorrespondence(this.corresp3);
        travel.addCorrespondence(this.corresp1);

        assertEquals(this.corresp1,travel.getFirstStep());
        assertEquals(this.corresp3,travel.getLastStep());
    }

    @Test
    void the_end_cities_of_the_steps_must_be_the_beginning_of_the_next() {
        travel.addCorrespondence(this.corresp2);
        travel.addCorrespondence(this.corresp3);
        travel.addCorrespondence(this.corresp1);

        TreeSet<Correspondence> actualSteps = travel.getSteps();

        // city of arrival of a correspondence must be equal to the city of departure of the next
        Iterator<Correspondence> iterator = actualSteps.iterator();
        Correspondence cnext = iterator.next();
        for (int i = 0; i < actualSteps.size() - 1; i++) {
            //(cnext = iterator.next()) affecte iterator.next() a cnext avant de faire le getStartCity()
            assertEquals(cnext.getDestinationCity(), ((cnext = iterator.next()).getStartCity()));
        }
    }

    @Test
    void the_arrival_time_of_the_steps_must_be_before_the_start_time_of_the_next() {
        travel.addCorrespondence(this.corresp2);
        travel.addCorrespondence(this.corresp3);
        travel.addCorrespondence(this.corresp1);

        TreeSet<Correspondence> actualSteps = travel.getSteps();

        // city of arrival of a correspondence must be equal to the city of departure of the next
        Iterator<Correspondence> iterator = actualSteps.iterator();
        Correspondence cnext = iterator.next();
        for (int i = 0; i < actualSteps.size() - 1; i++) {
            //(cnext = iterator.next()) affecte iterator.next() a cnext avant de faire le getStartTime()
            assertTrue(cnext.getArrivalTime().isBefore((cnext = iterator.next()).getStartTime()));
        }
    }

}
