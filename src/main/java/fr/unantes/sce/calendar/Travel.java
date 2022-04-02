package fr.unantes.sce.calendar;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * A Travel goes from one place to another, with a departure date and an arrival date
 */
public class Travel {
    private final TreeSet<Correspondence> steps;
    private Calendar parent;

    /**
     * Class constructor
     * @param parent the calendar the travel belongs to
     */
    public Travel(Calendar parent) {
        this.setParent(parent);
        steps = new TreeSet<>(Comparator.comparing(Correspondence::getStartTime));
    }

    /**
     * steps getter
     * @return the different steps of the travel
     */
    public TreeSet<Correspondence> getSteps() {
        return steps;
    }

    /**
     * parent getter
     * @return the calendar the travel belongs to
     */
    public Calendar getParent() {
        return parent;
    }

    /**
     * parent setter
     * @param parent the Calendar the travel belongs to
     */
    public void setParent(Calendar parent) {
        this.parent = parent;
        parent.addTravel(this);
    }

    /**
     * first step getter
     * @return the first step of the travel
     */
    public Correspondence getFirstStep() {
        return steps.first();
    }

    /**
     * last step getter
     * @return the last step of the travel
     */
    public Correspondence getLastStep() {
        return steps.last();
    }

    /**
     * Adds a correspondance to the travel and sets the travel as the correspondence's parent travel
     * @param correspondence the correspondence to be added
     * @return True if the correspondance was successfully added, False otherwise
     */
    public boolean addCorrespondence(Correspondence correspondence) {
        correspondence.basicSetTravel(this);
        return steps.add(correspondence);
    }

    /**
     * Adds a correspondence to the travel
     * @param correspondence the correspondence to be added
     * @return True if the correspondance was successfully added, False otherwise
     */
    protected boolean basicAddCorrespondance(Correspondence correspondence) {
        return steps.add(correspondence);
    }

    /**
     * Removes a correspondence from the travel
     * @param step the correspondence to be removed
     * @return True if the correspondance was successfully removed, False otherwise
     */
    public boolean removeCorrespondence(Correspondence step) {
        //TODO : créer une instance null d'un trajet
        step.basicSetTravel(null);
        return steps.remove(step);
    }
}
