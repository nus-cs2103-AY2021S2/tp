package seedu.address.model.scheduler;

import seedu.address.logic.parser.DateTimeUtil;

import java.time.LocalTime;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a continuous time interval in a day. Must have explicit start time and end time.
 * Mathematically, it is [startTime,endTime]
 */

public class TimeInterval {

    private final LocalTime startTime;
    private final LocalTime endTime;


    /**
     * Constructs the time interval with given endpoints.
     * @param startTime
     * @param endTime
     */

    public TimeInterval(LocalTime startTime, LocalTime endTime) {
        assert isValidTimeFrame(startTime, endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other instanceof TimeInterval) {
            TimeInterval otherTimeframe = (TimeInterval) other;
            return otherTimeframe.getEndTime() == this.getEndTime() &&
                    otherTimeframe.getStartTime() == this.getStartTime();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return DateTimeUtil.isoFormatTime(startTime) + " - " + DateTimeUtil.isoFormatTime(endTime);
    }

    /**
     * Check if the endTime is greater than the startTime.
     * @return
     */
    public static boolean isValidTimeFrame(LocalTime startTime, LocalTime endTime) {
        return endTime.compareTo(startTime) == 1;
    }

    /**
     * Checks if the timeInterval overlaps with this interval. We do not say an interval overlap if they share
     * only one endpoint.
     * @param timeInterval
     * @return True if there is overlap
     */

    public boolean isOverlap(TimeInterval timeInterval) {
        return !(timeInterval.getStartTime().compareTo(this.getEndTime()) >= 0
                || timeInterval.getEndTime().compareTo(this.getStartTime()) <= 0);
    }

    public boolean containsTime(LocalTime localTime) {
        return localTime.compareTo(startTime) >= 0 && localTime.compareTo(endTime) <= 0;
    }

}
