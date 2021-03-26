package seedu.address.model.scheduler;


import java.time.LocalDateTime;

/**
 * Represents objects that can be scheduled by the scheduler. A schedulable object has
 * to have a start date and terminate date.
 */

public interface Schedulable {

    /**
     * get name of this object.
     */
    public String getName();


    /**
     * Get the start date time.
     * @return
     */
    public LocalDateTime getStartLocalDateTime();

    /**
     * Get the termination date time.
     * @return
     */
    public LocalDateTime getTerminateLocalDateTime();

    /**
     * Given another schedulable object, check if there are any conflicts.
     * @param schedulable
     * @return
     */
    public boolean isConflict(Schedulable schedulable);


}
