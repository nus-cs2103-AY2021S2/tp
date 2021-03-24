package seedu.address.model.scheduler;

import seedu.address.logic.parser.DateTimeUtil;

import java.time.LocalDateTime;

/**
 * Represents a continuous time interval. Must have explicit start time and end time, in the form of localDate.
 */

public class Timeframe {

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public Timeframe(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }


    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other instanceof Timeframe) {
            Timeframe otherTimeframe = (Timeframe) other;
            return otherTimeframe.getEndDateTime() == this.getEndDateTime() &&
                    otherTimeframe.getStartDateTime() == this.getStartDateTime();
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return DateTimeUtil.prettyPrintFormatDateTime(startDateTime) + " to "
                + DateTimeUtil.prettyPrintFormatDateTime(endDateTime);
    }

    public boolean isValidTimeFrame() {
        return this.endDateTime.compareTo(this.startDateTime) == 1;
    }


}
