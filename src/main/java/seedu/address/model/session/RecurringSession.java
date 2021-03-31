package seedu.address.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class that handles RecurringSession that extend Session.
 */
public class RecurringSession extends Session {
    public static final String MESSAGE_CONSTRAINTS = "Last date does not match starting date and interval.";
    private Interval interval;
    private SessionDate lastSessionDate;

    /**
     * Constructs a {@code RecurringSession}.
     *
     * Requires that all fields entered to be non null.
     * @param sessionDate First sessionDate
     * @param duration Duration of recurring session
     * @param subject Subject of recurring session
     * @param fee Fee of recurring session
     * @param interval Interval by days between recurring sessions.
     * @param lastSessionDate must match sessionDate and interval.
     */
    public RecurringSession(SessionDate sessionDate, Duration duration, Subject subject, Fee fee,
                            Interval interval, SessionDate lastSessionDate) {
        super(sessionDate, duration, subject, fee);
        requireAllNonNull(interval, lastSessionDate);
        this.interval = interval;
        checkArgument(isValidEnd(sessionDate, lastSessionDate, interval), MESSAGE_CONSTRAINTS);
        this.lastSessionDate = lastSessionDate;
    }

    /**
     * Returns true if {@code lastSessionDate} is valid, having consistent Time and Date with
     * {@code firstSessionDate} and {@code interval}.
     * @param firstSessionDate the SessionDate value for first session
     * @param lastSessionDate the SessionDate value for last session
     * @param interval the interval of recurrence
     * @return true if the first session and last session is consistent, based on interval
     */
    public static boolean isValidEnd(SessionDate firstSessionDate, SessionDate lastSessionDate, Interval interval) {
        boolean occursAtTime = firstSessionDate.isSameTime(lastSessionDate);
        boolean occursAtDate = isConsistentDatesAndInterval(firstSessionDate, lastSessionDate, interval);
        return occursAtTime && occursAtDate;
    }

    /**
     * Returns true if Date of {@code lastSessionDate} is consistent with
     * {@code firstSessionDate} and {@code interval}.
     * @param sessionDate1 the SessionDate value for first session
     * @param sessionDate2 the SessionDate value for last session
     * @param interval the interval of recurrence
     * @return true if the first session and last session is consistent in terms of Date, based on interval
     */
    public static boolean isConsistentDatesAndInterval(
            SessionDate sessionDate1, SessionDate sessionDate2, Interval interval) {
        requireAllNonNull(sessionDate1, sessionDate2, interval);
        int daysBetween = sessionDate1.numOfDayTo(sessionDate2);
        return daysBetween > 0 && daysBetween % interval.getValue() == 0;
    }

    public Interval getInterval() {
        return interval;
    }

    public SessionDate getLastSessionDate() {
        return lastSessionDate;
    }

    /**
     * Returns true if recurring session date ends before {@code SessionDate} date.
     */
    public boolean endBefore(SessionDate sessionDate) {
        requireAllNonNull(sessionDate);
        return lastSessionDate.getDate().isBefore(sessionDate.getDate());
    }

    /**
     * Returns true if recurring session date starts after {@code SessionDate} date.
     */
    private boolean startAfter(SessionDate sessionDate) {
        requireAllNonNull(sessionDate);
        return getSessionDate().getDate().isAfter(sessionDate.getDate());
    }

    // THIS METHOD IS FOR SCHEDULE REMINDER TO CHECK IF THERE'S SESSION ON THE DAY.
    /**
     * Returns if session exists on given sessionDate's date.
     * @param sessionDate SessionDate to check if recurring session falls on.
     * @return true if recurring session falls on given sessionDate.
     */
    public boolean hasSessionOnDate(SessionDate sessionDate) {
        requireAllNonNull(sessionDate);
        return !startAfter(sessionDate)
                && !endBefore(sessionDate)
                && isConsistentDatesAndInterval(getSessionDate(), sessionDate, getInterval());
    }

    // THIS METHOD IS FOR SCHEDULE REMINDER TO RETRIEVE INFO ABOUT SESSION HAPPENING ON GIVEN DATE.
    /**
     * Returns a single non-recurring session on the sessionDate.
     * @param sessionDate A valid sessionDate.
     * @return Session of Recurring Session on particular sessionDate.
     */
    private Session buildSessionOnDate(SessionDate sessionDate) {
        requireAllNonNull(sessionDate);
        return new Session(sessionDate, getDuration(), getSubject(), getFee());
    }

    /**
     * Builds a session with the time of the recurring session the specified {@code LocalDate}.
     */
    public Session buildSessionOnDate(LocalDate date) {
        requireNonNull(date);
        SessionDate sessionDate = new SessionDate(date.toString(), getSessionDate().getTime().toString());
        return buildSessionOnDate(sessionDate);
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
        LocalDate lastLocalDate = sessionDate.getDateTime()
                .minusDays(numOfDaysBetween % interval.getValue()).toLocalDate();
        SessionDate lastSessionDate = new SessionDate(
                LocalDateTime.of(lastLocalDate, getSessionDate().getTime())
                        .toString());
        return this.buildSessionOnDate(lastSessionDate);
    }

    // THIS METHOD IS EXPECTED TO BE USED IN FEE CALCULATION.
    /**
     * Returns the number of session in the span.
     * @param inclusiveStart start of the span
     * @param inclusiveEnd end of the span
     * @return number of sessions occurring
     */
    public int numOfSessionBetween(SessionDate inclusiveStart, SessionDate inclusiveEnd) {
        requireAllNonNull(inclusiveStart, inclusiveEnd);

        if (endBefore(inclusiveStart) || startAfter(inclusiveEnd)) {
            return 0;
        }

        SessionDate lastInSpan;
        if (endBefore(inclusiveEnd)) {
            lastInSpan = lastSessionDate;
        } else {
            lastInSpan = lastSessionOnOrBefore(inclusiveEnd).getSessionDate();
        }

        SessionDate firstInSpan;
        if (startAfter(inclusiveStart)) {
            firstInSpan = getSessionDate();
        } else {
            int daysDiff = getSessionDate().numOfDayTo(inclusiveStart) % interval.getValue();
            int plusDays = daysDiff == 0
                    ? 0
                    : interval.getValue() - daysDiff;
            LocalDateTime firstInSpanLdt = inclusiveStart.getDateTime().plusDays(plusDays);
            firstInSpan = new SessionDate(firstInSpanLdt.toString());
        }

        return (firstInSpan.numOfDayTo(lastInSpan) / interval.getValue()) + 1;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("; Interval: ")
                .append(getInterval())
                .append("; Last Date: ")
                .append(getLastSessionDate().getDate());
        return super.toString() + builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RecurringSession)) {
            return false;
        }

        RecurringSession otherSession = (RecurringSession) other;
        return super.equals(other) // this is intended to extend the parent equals method.
                && otherSession.getInterval().equals(otherSession.getInterval())
                && otherSession.getLastSessionDate().equals(otherSession.getLastSessionDate());

    }
}
