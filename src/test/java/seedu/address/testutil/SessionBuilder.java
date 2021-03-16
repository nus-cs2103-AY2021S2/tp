package seedu.address.testutil;

import seedu.address.model.session.Duration;
import seedu.address.model.session.Fee;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.session.Subject;

public class SessionBuilder {
    public static final String DEFAULT_DATE = "2021-01-01";
    public static final String DEFAULT_TIME = "00:00";
    public static final String DEFAULT_DURATION = "100";
    public static final String DEFAULT_SUBJECT = "COMPUTING";
    public static final String DEFAULT_FEE = "40";

    private SessionDate sessionDate;
    private Duration duration;
    private Subject subject;
    private Fee fee;

    /**
     * Creates a {@code SessionBuilder} with the default details.
     */
    public SessionBuilder() {
        sessionDate = new SessionDate(DEFAULT_DATE, DEFAULT_TIME);
        duration = new Duration(DEFAULT_DURATION);
        subject = new Subject(DEFAULT_SUBJECT);
        fee = new Fee(DEFAULT_FEE);
    }

    /**
     * Initializes the SessionBuilder with the data of {@code sessionToCopy}.
     */
    public SessionBuilder(Session sessionToCopy) {
        sessionDate = sessionToCopy.getSessionDate();
        duration = sessionToCopy.getDuration();
        subject = sessionToCopy.getSubject();
        fee = sessionToCopy.getFee();
    }

    /**
     * Sets the {@code SessionDate} of the {@code Session} that we are building.
     */
    public SessionBuilder withSessionDate(String dateValue, String timeValue) {
        this.sessionDate = new SessionDate(dateValue, timeValue);
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code Session} that we are building.
     */
    public SessionBuilder withDuration(String duration) {
        this.duration = new Duration(duration);
        return this;
    }

    /**
     * Sets the {@code Subject} of the {@code Session} that we are building.
     */
    public SessionBuilder withSubject(String subject) {
        this.subject = new Subject(subject);
        return this;
    }

    /**
     * Sets the {@code Fee} of the {@code Session} that we are building.
     */
    public SessionBuilder withFee(String fee) {
        this.fee = new Fee(fee);
        return this;
    }

    public Session build() {
        return new Session(sessionDate, duration, subject, fee);
    }

}
