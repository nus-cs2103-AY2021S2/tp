package seedu.address.model.scheduler;

import java.util.List;

/**
 * Represents objects that can be scheduled in the scheduler. It must have be able to get the timeframe of the object
 */

public interface Schedulable {


    /**
     * Gets the list of timeframes in which this schedulable is scheduled on.
     * @return the list of timeframes.
     */
    public List<TimeInterval> getTimeFrame();

    /**
     * Given another schedulable object, check if there are any conflicts.
     * @param schedulable
     * @return
     */
    public boolean isConflict(Schedulable schedulable);


}
