package seedu.address.model.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import seedu.address.model.task.exceptions.InvalidEventComparatorVariableException;

public class EventComparator implements Comparator<Event> {
    public static final String MESSAGE_CONSTRAINTS =
            "Sort Variable must be: \"name\", \"start_time\", \"end_time\", \"start_date\", \"end_date\" ";
    public static final String VALIDATION_REGEX = "(name|start_time|end_time|start_date|end_date)";

    private static ArrayList<String> acceptedVar =
            new ArrayList<>(Arrays.asList("name", "start_time", "end_time", "start_date", "end_date"));

    private String comparingVar;

    /**
     * Initialises a {@code TaskComparator} object.
     *
     */
    public EventComparator() {
        // default is sort by name
        this.comparingVar = "name";
    }

    private boolean checkVar(String var) {
        return EventComparator.acceptedVar.contains(var);
    }

    public void setComparingVar(String comparingVar) {
        assert comparingVar != null;
        if (!checkVar(comparingVar)) {
            throw new InvalidEventComparatorVariableException();
        }
        this.comparingVar = comparingVar;
    }

    /**
     * Returns true if a given string is a valid comparing var.
     */
    public static boolean isValidComparingVar(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Compares between two {@code Event} objects.
     *
     * @param e1 First {@code Event} to compare.
     * @param e2 Second {@code Event} to compare.
     */
    public int compare(Event e1, Event e2) {
        switch (comparingVar) {
        case "name":
            return e1.getName().compareTo(e2.getName());

        case "start_time":
            return e1.getStartTime().compareTo(e1.getStartTime());

        case "end_time":
            return e1.getEndTime().compareTo(e2.getEndTime());

        case "start_date":
            return e1.getStartDate().compareTo(e2.getStartDate());

        case "end_date":
            return e1.getEndDate().compareTo(e2.getEndDate());

        default:
            return 0;
        }
    }
}
