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


    // TODO : Overlap check for recurring session.
    /**
     * Checks if the {@code Session} slot overlaps with another session.
     * @param otherSession the other session that is compared to.
     */
    public boolean isOverlapping(Session otherSession) {
        SessionDate otherSessionDate = otherSession.getSessionDate();
        SessionDate sessionStartDate = sessionDate;
        SessionDate sessionEndDate = sessionStartDate.getEndSessionDate(duration);
        return (otherSessionDate.getDateTime().isEqual(sessionStartDate.getDateTime())
                || otherSessionDate.getDateTime().isAfter(sessionStartDate.getDateTime()))
                && otherSessionDate.getDateTime().isBefore(sessionEndDate.getDateTime());
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
