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

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(sessionDate, duration, subject, fee);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getSessionDate())
                .append("; Duration: ")
                .append(getDuration())
                .append("; Subject: ")
                .append(getSubject())
                .append("; Fee: ")
                .append(getFee());

        return builder.toString();
    }
}
