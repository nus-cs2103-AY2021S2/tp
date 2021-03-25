package seedu.address.model.session;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class that handles RecurringSession that extend Session.
 */
public class RecurringSession extends Session {
    public static final String MESSAGE_CONSTRAINTS = "End date/time does not match starting date and interval.";
    private Interval interval;
    private SessionDate endDateTime;

    /**
     * Constructs a {@code RecurringSession}.
     *
     * Requires that all fields entered to be non null.
     * @param sessionDate
     * @param duration
     * @param subject
     * @param fee
     * @param interval
     * @param endDateTime must match sessionDate and interval.
     */
    public RecurringSession(SessionDate sessionDate, Duration duration, Subject subject, Fee fee,
                            Interval interval, SessionDate endDateTime) {
        super(sessionDate, duration, subject, fee);
        requireAllNonNull(interval, endDateTime);
        this.interval = interval;
        checkArgument(isValidEnd(sessionDate, endDateTime, interval), MESSAGE_CONSTRAINTS);
        this.endDateTime = endDateTime;
    }

    // Checks that endDate can be on given sessionDate.
    private static boolean isValidEnd(SessionDate startDateTime, SessionDate endDateTime, Interval interval) {
        boolean occursAtTime = startDateTime.isSameTime(endDateTime);
        boolean occursAtDate = occursAtDate(startDateTime, endDateTime, interval);
        return occursAtTime && occursAtDate;
    }

    // Checks that s1 occurs on the date of s2, upon recurring 0 or more times.
    private static boolean occursAtDate(SessionDate sessionDate1, SessionDate sessionDate2, Interval interval) {
        requireAllNonNull(sessionDate1, sessionDate2, interval);
        int daysBetween = sessionDate1.numOfDayTo(sessionDate2);
        return daysBetween >= 0 && daysBetween % interval.value == 0;
    }

    public Interval getInterval() {
        return interval;
    }

    public SessionDate getEndDateTime() {
        return endDateTime;
    }

    private boolean endBefore(SessionDate sessionDate) {
        requireAllNonNull(sessionDate);
        return endDateTime.getDate().isBefore(sessionDate.getDate());
    }

    private boolean startAfter(SessionDate sessionDate) {
        requireAllNonNull(sessionDate);
        return getSessionDate().getDate().isAfter(sessionDate.getDate());
    }

    // THIS METHOD IS FOR SCHEDULE REMINDER TO CHECK IF THERE'S SESSION ON THE DAY.
    /**
     * Returns if session exists on given sessionDate's date.
     * @param sessionDate
     * @return true if recurring session falls on given sessionDate.
     */
    public boolean hasSessionOnDate(SessionDate sessionDate) {
        requireAllNonNull(sessionDate);
        return !endBefore(sessionDate) && occursAtDate(getSessionDate(), sessionDate, getInterval());
    }

    // THIS METHOD IS FOR SCHEDULE REMINDER TO RETRIEVE INFO ABOUT SESSION HAPPENING ON GIVEN DATE.
    /**
     * Returns a single non-recurring session on the sessionDate.
     * @param sessionDate A valid sessionDate.
     * @return
     */
    private Session onSessionDate(SessionDate sessionDate) {
        requireAllNonNull(sessionDate);
        assert(hasSessionOnDate(sessionDate));
        return new Session(sessionDate, getDuration(), getSubject(), getFee());
    }


    /**
     * Returns a non-recurring, single session that occurred before a given SessionDate.
     * @param sessionDate A valid date, where a session exists on or before the date.
     * @return A non-recurring single session, most recently past sessionDate.
     */
    public Session lastSessionOnOrBefore(SessionDate sessionDate) {
        requireAllNonNull(sessionDate);
        checkArgument(!startAfter(sessionDate), "There exists no session before.");
        int numOfDaysBetween = getSessionDate().numOfDayTo(sessionDate);
        LocalDate lastLocalDate = sessionDate.getDateTime().minusDays(numOfDaysBetween % interval.value).toLocalDate();
        SessionDate lastSessionDate = new SessionDate(
                LocalDateTime.of(lastLocalDate, getSessionDate().getDateTime().toLocalTime())
                        .toString());
        return this.onSessionDate(lastSessionDate);
    }

    // THIS METHOD IS EXPECTED TO BE USED IN FEE CALCULATION.
    /**
     * Returns the number of session in the span.
     * @param inclusiveStart start of the span
     * @param inclusiveEnd end of the span
     * @return number of sessions occuring
     */
    public int numOfSessionBetween(SessionDate inclusiveStart, SessionDate inclusiveEnd) {
        requireAllNonNull(inclusiveStart, inclusiveEnd);

        SessionDate lastInSpan;
        if (endBefore(inclusiveEnd)) {
            lastInSpan = endDateTime;
        } else {
            lastInSpan = lastSessionOnOrBefore(inclusiveEnd).getSessionDate();
        }

        SessionDate firstInSpan;
        if (startAfter(inclusiveStart)) {
            firstInSpan = getSessionDate();
        } else {
            int daysDiff = getSessionDate().numOfDayTo(inclusiveStart) % interval.value;
            int plusDays = daysDiff == 0
                    ? 0
                    : interval.value - daysDiff;
            LocalDateTime firstInSpanLdt = inclusiveStart.getDateTime().plusDays(plusDays);
            firstInSpan = new SessionDate(firstInSpanLdt.toString());
        }

        return firstInSpan.numOfDayTo(lastInSpan) / interval.value + 1;
    }
}
