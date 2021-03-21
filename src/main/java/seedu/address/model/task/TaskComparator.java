package seedu.address.model.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import seedu.address.model.task.exceptions.InvalidTaskComparatorVariableException;

public class TaskComparator implements Comparator<Task> {
    private static ArrayList<String> acceptedVar =
            new ArrayList<>(Arrays.asList("name", "deadline", "priority", "completion"));
    private String comparingVar;

    /**
     * Initialises a {@code TaskComparator} object.
     *
     */
    public TaskComparator() {
        // default is sort by name
        this.comparingVar = "name";
    }

    private boolean checkVar(String var) {
        return TaskComparator.acceptedVar.contains(var);
    }

    public void setComparingVar(String comparingVar) {
        if (!checkVar(comparingVar)) {
            throw new InvalidTaskComparatorVariableException();
        }
        this.comparingVar = comparingVar;
    }

    /**
     * Compares between two {@code Task} objects.
     *
     * @param t1 First {@code Task} to compare.
     * @param t2 Second {@code Task} to compare.
     */
    public int compare(Task t1, Task t2) {
        switch (comparingVar) {
        case "name":
            return t1.getName().compareTo(t2.getName());

        case "deadline":
            return t1.getDeadline().compareTo(t2.getDeadline());

        case "priority":
            return t1.getPriority().compareTo(t2.getPriority());

        case "completion":
            return t1.getCompletionStatus().compareTo(t2.getCompletionStatus());

        default:
            return 0;
        }
    }
}

