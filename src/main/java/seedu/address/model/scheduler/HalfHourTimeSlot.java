package seedu.address.model.scheduler;

import seedu.address.logic.parser.DateTimeUtil;

import java.time.LocalTime;

/**
 *  This represents a half hour timeslot in a timetable. It allows this slot to be booked.
 */
public class HalfHourTimeSlot {

    public static final int MINUTES_IN_HALF_PERIOD = 30;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private boolean isBooked;

    /**
     * Constructs a 30 minute period in the timetable starting at a specified time, which is not booked.
     *
     * @param startTime
     */
    public HalfHourTimeSlot(LocalTime startTime) {
        this.startTime = startTime;
        this.endTime = startTime.plusMinutes(MINUTES_IN_HALF_PERIOD);
        this.isBooked = false;
    }

    /**
     * sets the slot to be booked or not.
     *
     * @param isBooked
     */

    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    /**
     * Retrieves the status booking of this timeslot.
     *
     * @return
     */
    public boolean getBooked() {
        return isBooked;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "](" + DateTimeUtil.isoFormatTime(startTime) + " - "
                + DateTimeUtil.isoFormatTime(endTime) + ")";
    }

    public String getStatusIcon() {
        return (isBooked ? "\u2718" : "\u2713"); // return tick or X symbols
    }

}
