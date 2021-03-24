package seedu.address.model.session;

import java.time.temporal.ChronoUnit;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class RecurringSession extends Session{
    private Interval interval;
    private SessionDate endDateTime;
    public static final String MESSAGE_CONSTRAINTS = "End date/time does not match starting date and interval.";
    /**
     * Constructs a {@code RecurringSession}.
     * <p>
     * Requires that all fields entered to be non null
     *
     * @param sessionDate
     * @param duration
     * @param subject
     * @param fee
     * @param interval
     * @param endDateTime
     */
    public RecurringSession(SessionDate sessionDate, Duration duration, Subject subject, Fee fee,
                            Interval interval, SessionDate endDateTime) {
        super(sessionDate, duration, subject, fee);
        requireAllNonNull(interval, endDateTime);
        this.interval = interval;
        checkArgument(isValidEnd(sessionDate, interval), MESSAGE_CONSTRAINTS);
        this.endDateTime = endDateTime;
    }

    // Checks endDateTime is a multiple of interval (days) from sessionDate (start).
    private boolean isValidEnd(SessionDate sessionDate, Interval interval) {
        boolean matchTime = sessionDate.getDateTime().toLocalTime().equals(endDateTime.getDateTime().toLocalTime());
        int startToEndDays = (int) ChronoUnit.DAYS.between(sessionDate.getDateTime(), endDateTime.getDateTime());
        if (startToEndDays < 0) {
            return false;
        }
        boolean matchDate = (startToEndDays % interval.value) == 0;
        return  matchTime && matchDate;
    }

    public Interval getInterval() {
        return interval;
    }

    public SessionDate getEndDateTime() {
        return endDateTime;
    }

    /**
     * Returns whether Recurring Session ends on days before given sessionDate.
     * @param sessionDate
     * @return true if last session falls on days before given sessionDate.
     */
    public boolean endBefore(SessionDate sessionDate) {
        return endDateTime.getDateTime().toLocalDate().isBefore(sessionDate.getDateTime().toLocalDate());
    }

    /**
     * Returns if session exists on given sessionDate
     * @param sessionDate
     * @return true if recurring session falls on given sessionDate.
     */
    public boolean hasSessionOn(SessionDate sessionDate) {
        int numOfDays = (int) ChronoUnit.DAYS.between(super.getSessionDate().getDateTime(), sessionDate.getDateTime());
        boolean matchDate = (numOfDays % interval.value) == 0;
        return !endBefore(sessionDate) && matchDate;
    }

    public int numOfSessionBetween(SessionDate inclusiveStart, SessionDate inclusiveEnd) {
        int dayToStart = (int) ChronoUnit.DAYS.between(super.getSessionDate().getDateTime(), inclusiveStart.getDateTime());
        assert (dayToStart >= 0);
        int numOfDays = (int) ChronoUnit.DAYS.between(inclusiveStart.getDateTime(), inclusiveEnd.getDateTime());
        return (numOfDays - (dayToStart % interval.value)) % interval.value;
    }
}
