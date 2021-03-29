package seedu.address.model.schedule;


import java.time.LocalDateTime;

/**
 * Represents objects that can be scheduled by a Scheduler. A schedulable object has
 * to have a start time  and end time, and a day it is scheduled on. Schedulable
 * objects can be scheduled into a timetable.
 *
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



}
