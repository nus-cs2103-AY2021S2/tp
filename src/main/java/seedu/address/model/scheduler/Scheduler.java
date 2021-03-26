package seedu.address.model.scheduler;

/**
 * This is a class that will manage the scheduling of a set of Schedulable objects.
 */

public interface Scheduler {

    /**
     * adds a schedulable object to the schedule.
     * @param schedulable
     */
    public void addToSchedule(Schedulable schedulable) throws BookingException;

    /**
     * deletes a schedulable object from the schedule.
     * @param schedulable
     * @throws
     */
    public void freeSchedule(Schedulable schedulable) throws TimetableAccessException;

}
