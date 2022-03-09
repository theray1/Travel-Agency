package fr.unantes.sce.calendar;

import fr.unantes.sce.people.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        assert parent.travels().contains(travel);
        assert travel.getParent().equals(parent);
    }

}
