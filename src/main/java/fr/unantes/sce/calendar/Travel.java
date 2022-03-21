package fr.unantes.sce.calendar;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * A Travel goes from one place to another, with a departure date and an arrival date
 */
public class Travel {
    private final TreeSet<Correspondence> steps;
    private Calendar parent;

    public Travel(Calendar parent) {
        this.setParent(parent);
        steps = new TreeSet<>(Comparator.comparing(Correspondence::getStartTime));
    }

    public TreeSet<Correspondence> getSteps() {
        return steps;
    }

    public Calendar getParent() {
        return parent;
    }

    public void setParent(Calendar parent) {
        this.parent = parent;
        parent.addTravel(this);
    }

    public Correspondence getFirstStep() {
        return steps.first();
    }

    public Correspondence getLastStep() {
        return steps.last();
    }

    public boolean addCorrespondence(Correspondence correspondence) {
        correspondence.basicSetTravel(this);
        return steps.add(correspondence);
    }

    protected boolean basicAddCorrespondance(Correspondence correspondence) {
        return steps.add(correspondence);
    }

    public boolean removeCorrespondence(Correspondence step) {
        //TODO : créer une instance null d'un trajet
        step.basicSetTravel(null);
        return steps.remove(step);
    }
}
