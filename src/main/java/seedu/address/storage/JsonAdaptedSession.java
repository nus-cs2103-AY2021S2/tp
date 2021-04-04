package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.session.Duration;
import seedu.address.model.session.Fee;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionDate;
import seedu.address.model.session.Subject;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes(
        @JsonSubTypes.Type(value = JsonAdaptedRecurringSession.class))
public class JsonAdaptedSession {
    private final String sessionDate;
    private final String duration;
    private final String subject;
    private final String fee;

    /**
     * Test
     * @param sessionDate
     * @param duration
     * @param subject
     * @param fee
     */
    @JsonCreator
    public JsonAdaptedSession(@JsonProperty("sessionDate") String sessionDate, @JsonProperty("duration")String duration,
                              @JsonProperty("subject") String subject, @JsonProperty("fee") String fee) {
        this.sessionDate = sessionDate;
        this.duration = duration;
        this.subject = subject;
        this.fee = fee;
    }

    /**
     * test
     * @param source
     */
    public JsonAdaptedSession(Session source) {
        sessionDate = source.getSessionDate().getDateTime().toString();
        duration = source.getDuration().toString();
        subject = source.getSubject().getValue();
        fee = "" + source.getFee().getFee();
    }

    /**
     * Creates a session model based on the json values given.
     * @return New session instance based on the given information.
     * @throws IllegalValueException If the duration + start time is invalid.
     */
    public Session toModelType() throws IllegalValueException {
        Session.checkPossibleEndTime(new SessionDate(sessionDate), new Duration(duration));
        return new Session(new SessionDate(sessionDate), new Duration(duration), new Subject(subject), new Fee(fee));
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public String getDuration() {
        return duration;
    }

    public String getSubject() {
        return subject;
    }

    public String getFee() {
        return fee;
    }
}
