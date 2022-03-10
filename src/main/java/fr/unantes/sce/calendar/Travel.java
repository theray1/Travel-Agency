package fr.unantes.sce.calendar;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * A Travel goes from one place to another, with a departure date and an arrival date
 */
public class Travel {
    private TreeSet<Correspondence> steps;
    private Calendar parent;

    public Travel(Calendar parent) {
        this.parent = parent;
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
    }

    public Correspondence getFirstStep() {
        return steps.iterator().next();
    }

    public Correspondence getLastStep() {
        Iterator<Correspondence> iterator = steps.iterator();
        Correspondence c = null;
        while(iterator.hasNext()){
            c=iterator.next();
        }
        return c;
    }

    public boolean addCorrespondence(Correspondence step) {
        return steps.add(step);
    }

    public boolean removeCorrespondence(Correspondence step) {
        return steps.remove(step);
    }
}
