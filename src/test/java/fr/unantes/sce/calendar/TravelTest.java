package fr.unantes.sce.calendar;

import fr.unantes.sce.people.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
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
    }

    @Test
    void addAndDeleteCorrespondance() {
        Correspondence step1 = new Correspondence(this.travel, new City("France", "Nantes"), new City("France", "Paris"), 12, 16);
        Correspondence step2 = new Correspondence(this.travel, new City("France", "Paris"), new City("France", "Lyon"), 17, 20);
        Correspondence step3 = new Correspondence(this.travel, new City("France", "Lyon"), new City("France", "Toulouse"), 8, 10);
        Correspondence step4 = new Correspondence(this.travel, new City("France", "Toulouse"), new City("France", "Marseille"), 16, 18);
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
    void stepsConsistencyBasicTest(){
        City city1 = new City("France","Paris");
        City city2 = new City("Belgique","Bruxelles");
        City city3 = new City("Irlande","Dublin");
        City city4 = new City("Espagne","Madrid");
        Correspondence corresp1 = new Correspondence(travel, city1, city2, LocalTime.of(8,50), LocalTime.of(12,25));
        Correspondence corresp2 = new Correspondence(travel, city2, city3, LocalTime.of(15,50), LocalTime.of(19,35));
        Correspondence corresp3 = new Correspondence(travel, city3, city4, LocalTime.of(23,0), LocalTime.of(1,40));
        travel.addCorrespondence(corresp2);
        travel.addCorrespondence(corresp3);
        travel.addCorrespondence(corresp1);

        TreeSet<Correspondence> steps = travel.getSteps();
        for(Correspondence each : steps){
            assertNotEquals(null, each.getDestinationCity());
        }
        // city of arrival of a correspondence must be equal to the city of departure of the next
        Iterator<Correspondence> iterator = steps.iterator();
        Correspondence cinit = iterator.next();
        for(int i=0 ; i<steps.size()-1 ; i++){
            //(cinit = iterator.next()) affecte iterator.next() a cinit avant de faire le getStartCity()
            assertEquals(cinit.getDestinationCity(), ((cinit = iterator.next()).getStartCity()));
        }
    }

}
