package seedu.address.model.task;

public interface Recurrable {

    /**
     * Returns the recurrence interval.
     * @return Recurrence interval.
     */
    Recurrence getRecurrence();

    /**
     * Sets the Recurrence interval to specified level.
     * @param recurrence Level of Recurrence.
     */
    void setRecurrence(Recurrence recurrence);
}
