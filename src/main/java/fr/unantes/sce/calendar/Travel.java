package fr.unantes.sce.calendar;

import java.nio.charset.CoderResult;
import java.util.ArrayList;

/**
 * A Travel goes from one place to another, with a departure date and an arrival date
 */
public class Travel {
    private ArrayList<Correspondence> steps;
    private Calendar parent;

    public Travel(Calendar parent) {
        this.parent = parent;
        steps = new ArrayList<>();
    }

    public Calendar getParent() {
        return parent;
    }

    public void setParent(Calendar parent) {
        this.parent = parent;
    }

    public Correspondence getFirstStep() {
        return (Correspondence) steps.get(0);
    }

    public Correspondence getLastStep() {
        return (Correspondence) steps.get(steps.size() - 1);
    }

    public boolean addCorrespondence(Correspondence step) {
        return steps.add(step);
    }

    public boolean removeCorrespondence(Correspondence step) {
        return steps.remove(step);
    }
}
