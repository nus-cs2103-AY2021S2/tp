package seedu.address.model.scheduler;


import java.time.LocalDateTime;

/**
 * Represents objects that can be scheduled by a Scheduler. A schedulable object has
 * to have a start time  and end time, and a date it is scheduled on. Schedulable
 * objects can be scheduled into a timetable object.
 */

public interface Schedulable {

    /**
     * get name of this object.
     */
    public String getNameString();


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
