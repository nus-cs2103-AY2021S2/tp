package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.session.Duration;
import seedu.address.model.session.Fee;
import seedu.address.model.session.Interval;
import seedu.address.model.session.RecurringSession;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.session.Subject;

public class JsonAdaptedRecurringSession extends JsonAdaptedSession {
    private String interval;
    private String lastSessionDate;

    /**
     * JsonCreator for Recurring Session
     * @param sessionDate
     * @param duration
     * @param subject
     * @param fee
     * @param interval
     * @param lastSessionDate
     */
    @JsonCreator
    public JsonAdaptedRecurringSession(@JsonProperty("sessionDate") String sessionDate,
                                       @JsonProperty("duration")String duration,
                                       @JsonProperty("subject") String subject,
                                       @JsonProperty("fee") String fee,
                                       @JsonProperty("interval") String interval,
                                       @JsonProperty("lastSessionDate") String lastSessionDate) {
        super(sessionDate, duration, subject, fee);
        this.interval = interval;
        this.lastSessionDate = lastSessionDate;
    }

    /**
     * Converts a given {@code RecurringSession} into this class for Jackson use.
     */
    public JsonAdaptedRecurringSession(RecurringSession source) {
        super(source.getSessionDate().getDateTime().toString(),
                source.getDuration().toString(),
                source.getSubject().getValue(),
                "" + source.getFee().getFee());
        interval = source.getInterval().toString();
        lastSessionDate = source.getLastSessionDate().getDateTime().toString();
    }

    @Override
    public Session toModelType() throws IllegalValueException {
        return new RecurringSession(new SessionDate(super.getSessionDate()), new Duration(super.getDuration()),
                new Subject(super.getSubject()), new Fee(super.getFee()),
                new Interval(interval), new SessionDate(lastSessionDate));
    }
}
