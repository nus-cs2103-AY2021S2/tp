package seedu.address.testutil;

import seedu.address.model.session.Duration;
import seedu.address.model.session.Fee;
import seedu.address.model.session.Interval;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.session.Subject;

public class RecurringSessionBuilder extends SessionBuilder {
    public static final String DEFAULT_INTERVAL = "7";
    public static final String DEFAULT_END_DATE = "2021-01-15";

    private Interval interval;
    private SessionDate lastSessionDate;

    /**
     * Creates a {@code RecurringSessionBuilder} with the default details.
     */
    public RecurringSessionBuilder() {
        super();
        interval = new Interval(DEFAULT_INTERVAL);
        lastSessionDate = new SessionDate(DEFAULT_END_DATE, DEFAULT_TIME);
    }

    /**
     * Initializes the RecurringSessionBuilder with the data of {@code recSessionToCopy}.
     */
    public RecurringSessionBuilder(RecurringSession recSessionToCopy) {
        setSessionDate(recSessionToCopy.getSessionDate());
        setDuration(recSessionToCopy.getDuration());
        setSubject(recSessionToCopy.getSubject());
        setFee(recSessionToCopy.getFee());
        interval = recSessionToCopy.getInterval();
        lastSessionDate = recSessionToCopy.getLastSessionDate();
    }

    /**
     * Sets the {@code SessionDate} of the {@code RecurringSession} that we are building.
     */
    public RecurringSessionBuilder withSessionDate(String dateValue, String timeValue) {
        super.setSessionDate(new SessionDate(dateValue, timeValue));
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code RecurringSession} that we are building.
     */
    public RecurringSessionBuilder withDuration(String value) {
        super.setDuration(new Duration(value));
        return this;
    }

    /**
     * Sets the {@code Subject} of the {@code RecurringSession} that we are building.
     */
    public RecurringSessionBuilder withSubject(String value) {
        super.setSubject(new Subject(value));
        return this;
    }

    /**
     * Sets the {@code Fee} of the {@code RecurringSession} that we are building.
     */
    public RecurringSessionBuilder withFee(String value) {
        super.setFee(new Fee(value));
        return this;
    }

    /**
     * Sets the {@code Interval} of the {@code RecurringSession} that we are building.
     */
    public RecurringSessionBuilder withInterval(String value) {
        this.interval = new Interval(value);
        return this;
    }

    /**
     * Sets the {@code LastSessionDate} of the {@code RecurringSession} that we are building.
     */
    public RecurringSessionBuilder withLastSessionDate(String value) {
        this.lastSessionDate = new SessionDate(value, DEFAULT_TIME);
        return this;
    }

    /**
     * Builds the recurring session object.
     * @return the recurring session built
     */
    public RecurringSession build() {
        Session parent = super.build();
        return new RecurringSession(parent.getSessionDate(), parent.getDuration(), parent.getSubject(),
                parent.getFee(), interval, lastSessionDate);
    }

}
