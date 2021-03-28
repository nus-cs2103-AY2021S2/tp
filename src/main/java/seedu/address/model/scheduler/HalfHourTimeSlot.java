package seedu.address.model.scheduler;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *  This represents a half hour timeslot in a timetable. It allows this slot to be booked.
 */
public class HalfHourTimeSlot {

    public static final int MINUTES_IN_HALF_PERIOD = 30;
    private final TimeInterval timeInterval;
    private final BooleanProperty isBooked = new SimpleBooleanProperty(false);

    /**
     * Constructs a 30 minute period in the timetable starting at a specified time, which is not booked.
     * Note startTime must be 00:00 <= 23:30
     * @param startTime
     */
    public HalfHourTimeSlot(LocalTime startTime) {
        assert startTime.compareTo(LocalTime.of(11, 30)) <= 0;
        LocalTime endTime;
        // The case when local time is 11:30, then it is max.
        if (startTime.getMinute() == 30 && startTime.getHour() ==23) {
            endTime = LocalTime.MAX;
        } else {
            endTime = startTime.plusMinutes(MINUTES_IN_HALF_PERIOD);
        }
        this.timeInterval = new TimeInterval(startTime, endTime);
    }

    /**
     * sets the slot to be booked or not.
     *
     * @param isBooked
     */

    public void setIsBooked(boolean isBooked) {
        this.isBooked.set(isBooked);
    }

    /**
     * Retrieves the status booking of this timeslot.
     *
     * @return
     */
    public boolean isBooked() {
        return this.isBooked.get();
    }

    /**
     * Returns the property containing the boolean value to indicate if the slot is booked.
     * @return
     */
    public ReadOnlyBooleanProperty getisBookedProperty() {
        return isBooked;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "](" + timeInterval.toString() + ")";
    }

    public String getStatusIcon() {
        return (isBooked() ? "\u2718" : "\u2713"); // return tick or X symbols
    }

}
