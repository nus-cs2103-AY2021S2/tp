package seedu.address.model.session;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an individual tuition session at one time slot for one student
 */
public class Session {

    private SessionDate sessionDate;
    private Duration duration;
    private Subject subject;
    private Fee fee;

    /**
     * Constructs a {@code Session}.
     *
     * Requires that all fields entered to be non null
     */
    public Session(SessionDate sessionDate, Duration duration, Subject subject, Fee fee) {
        requireAllNonNull(sessionDate, duration, subject, fee);
        this.sessionDate = sessionDate;
        this.duration = duration;
        this.subject = subject;
        this.fee = fee;
    }

    public SessionDate getSessionDate() {
        return sessionDate;
    }

    public Duration getDuration() {
        return duration;
    }

    public Subject getSubject() {
        return subject;
    }

    public Fee getFee() {
        return fee;
    }

    /**
     * Checks if the {@code Session} slot overlaps with another session.
     * @param otherSession the other session that is compared to.
     */
    public boolean isOverlapping(Session otherSession) {
        SessionDate otherSessionStartDate = otherSession.getSessionDate();
        SessionDate otherSessionEndDate = otherSessionStartDate.getEndSessionDate(otherSession.getDuration());
        SessionDate sessionStartDate = sessionDate;
        SessionDate sessionEndDate = sessionStartDate.getEndSessionDate(duration);
        return isDateTimeOverlapping(sessionStartDate, sessionEndDate, otherSessionStartDate, otherSessionEndDate);
    }

    /**
     * Checks if the {@code RecurringSession} slot overlaps with another session.
     * @param otherSession the other recurring session that is compared to.
     */
    public boolean isOverlapping(RecurringSession otherSession) {
        if (!otherSession.hasSessionOnDate(getSessionDate())) {
            return false;
        }
        SessionDate sessionStartDate = sessionDate;
        SessionDate sessionEndDate = sessionStartDate.getEndSessionDate(duration);
        SessionDate otherSessionStartDate = otherSession.getSessionDate();
        SessionDate otherSessionEndDate = otherSessionStartDate.getEndSessionDate(otherSession.getDuration());
        return isTimeOverlapping(sessionStartDate, sessionEndDate, otherSessionStartDate, otherSessionEndDate);
    }

    /**
     * Checks if the two session overlaps each other through {@code LocalDateTime} comparison.
     */
    public boolean isDateTimeOverlapping(SessionDate sessionStartDate, SessionDate sessionEndDate,
                                         SessionDate otherSessionStartDate, SessionDate otherSessionEndDate) {
        boolean otherSessionOverlapsAfterSessionStarts = (
                otherSessionStartDate.getDateTime().isEqual(sessionStartDate.getDateTime())
                        || otherSessionStartDate.getDateTime().isAfter(sessionStartDate.getDateTime()))
                && otherSessionStartDate.getDateTime().isBefore(sessionEndDate.getDateTime());
        boolean sessionOverlapsAfterOtherSessionStarts = (
                sessionStartDate.getDateTime().isEqual(otherSessionStartDate.getDateTime())
                        || sessionStartDate.getDateTime().isAfter(otherSessionStartDate.getDateTime()))
                && sessionStartDate.getDateTime().isBefore(otherSessionEndDate.getDateTime());
        return otherSessionOverlapsAfterSessionStarts || sessionOverlapsAfterOtherSessionStarts;
    }

    /**
     * Checks if the two sessions overlaps each other through {@code LocalTime} comparison.
     * A softer check compared to {@link #isDateTimeOverlapping(SessionDate, SessionDate, SessionDate, SessionDate)}.
     */
    public boolean isTimeOverlapping(SessionDate sessionStartDate, SessionDate sessionEndDate,
                                     SessionDate otherSessionStartDate, SessionDate otherSessionEndDate) {
        boolean otherSessionOverlapsAfterSessionStarts = (
                otherSessionStartDate.getTime().equals(sessionStartDate.getTime())
                        || otherSessionStartDate.getTime().isAfter(sessionStartDate.getTime()))
                && otherSessionStartDate.getTime().isBefore(sessionEndDate.getTime());
        boolean sessionOverlapsAfterOtherSessionStarts = (
                sessionStartDate.getTime().equals(otherSessionStartDate.getTime())
                        || sessionStartDate.getTime().isAfter(otherSessionStartDate.getTime()))
                && sessionStartDate.getTime().isBefore(otherSessionEndDate.getTime());
        return otherSessionOverlapsAfterSessionStarts || sessionOverlapsAfterOtherSessionStarts;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(sessionDate, duration, subject, fee);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Date: ")
                .append(getSessionDate().getDate())
                .append("; Time: ")
                .append(getSessionDate().getTime())
                .append("; Duration: ")
                .append(getDuration())
                .append("; Subject: ")
                .append(getSubject())
                .append("; Fee: ")
                .append(getFee());

        return builder.toString();
    }

    /**
     * Returns true if both sessions have the same data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Session)) {
            return false;
        }

        Session otherSession = (Session) other;
        return otherSession.getSessionDate().equals(getSessionDate())
                && otherSession.getDuration().equals(getDuration())
                && otherSession.getSubject().equals(getSubject())
                && otherSession.getFee().equals(getFee());
    }
}
