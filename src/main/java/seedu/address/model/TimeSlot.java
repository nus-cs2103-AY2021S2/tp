package seedu.address.model;

import java.time.LocalDateTime;

/**
 * Objects implement TimeSlot so that clashes between it and other TimeSLots can be prevented in RemindMe.
 */
public interface TimeSlot {

    /**
     * Returns the start time of a time slot in LocalDateTime.
     * @return start time of a timeslot.
     */
    LocalDateTime getStartDateTime();

    /**
     * Returns the end time of a time slot in LocalDateTime.
     * @return end time of a time slot.
     */
    LocalDateTime getEndDateTime();
}
