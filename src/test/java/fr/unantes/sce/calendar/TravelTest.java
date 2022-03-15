package fr.unantes.sce.calendar;

import fr.unantes.sce.people.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class TravelTest {
    private Travel travel;
    private Calendar parent;
    private Person owner;

    @BeforeEach
    public void setUp() throws Exception {
        this.owner = new Person("Test", "admin");
        this.parent = new Calendar(this.owner);
        this.travel = new Travel(this.parent);
        City city1 = new City("France","Paris");
        City city2 = new City("Belgique","Bruxelles");
        City city3 = new City("Irlande","Dublin");
        City city4 = new City("Espagne","Madrid");
        LocalDateTime date1 = LocalDateTime.of(2018,10,19,8,55);
        LocalDateTime date2 = LocalDateTime.of(2018,10,20,18,50);
        LocalDateTime date3 = LocalDateTime.of(2018,10,20,9,35);
        LocalDateTime date4 = LocalDateTime.of(2018,11,1,12,12);
        LocalDateTime date5 = LocalDateTime.of(2018,11,3,3,45);
        Correspondence corresp1 = new Correspondence(travel, city1, city2, date1, date2);
        Correspondence corresp2 = new Correspondence(travel, city2, city3, date3, date4);
        Correspondence corresp3 = new Correspondence(travel, city3, city4, date4, date5);
        travel.addCorrespondence(corresp2);
        travel.addCorrespondence(corresp3);
        travel.addCorrespondence(corresp1);
    }

    @Test
    void addAndDeleteCorrespondance() {
        Correspondence step1 = new Correspondence(this.travel, new City("France", "Nantes"), new City("France", "Paris"), null, null);
        Correspondence step2 = new Correspondence(this.travel, new City("France", "Paris"), new City("France", "Lyon"), null, null);
        Correspondence step3 = new Correspondence(this.travel, new City("France", "Lyon"), new City("France", "Toulouse"), null, null);
        Correspondence step4 = new Correspondence(this.travel, new City("France", "Toulouse"), new City("France", "Marseille"), null, null);
        try {
            this.travel.addCorrespondence(step1);
            this.travel.addCorrespondence(step2);
            this.travel.addCorrespondence(step3);
            this.travel.addCorrespondence(step4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(step1, this.travel.getFirstStep());
        assertEquals(step4, this.travel.getLastStep());

        assertEquals(this.travel, step1.getTravel());

        assertTrue(this.travel.removeCorrespondence(step4));
        assertFalse(this.travel.removeCorrespondence(step4));

        assertEquals(step3, this.travel.getLastStep());


        assertNull(step4.getTravel());

        //Check 10 steps max
        assertThrows(IndexOutOfBoundsException.class, () -> {
            for (int i = 0; i < 8; i++) {
                this.travel.addCorrespondence(step1);
            }
        });

    }

    @Test
    void setParent_Handshake(){
        travel.setParent(parent);

        assertTrue(parent.travels().contains(travel));
        assertEquals(travel.getParent(), parent);
    }

    @Test
    void the_end_cities_of_the_steps_must_be_the_beginning_of_the_next(){
        TreeSet<Correspondence> actualSteps = travel.getSteps();

        // city of arrival of a correspondence must be equal to the city of departure of the next
        Iterator<Correspondence> iterator = actualSteps.iterator();
        Correspondence cinit = iterator.next();
        for(int i=0 ; i<actualSteps.size()-1 ; i++){
            //(cinit = iterator.next()) affecte iterator.next() a cinit avant de faire le getStartCity()
            assertEquals(cinit.getDestinationCity(), ((cinit = iterator.next()).getStartCity()));
        }
    }

}
