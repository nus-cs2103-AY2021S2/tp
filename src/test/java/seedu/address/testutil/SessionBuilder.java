package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.session.Day;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionId;
import seedu.address.model.session.Subject;
import seedu.address.model.session.Timeslot;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Session objects.
 */
public class SessionBuilder {
    public static final String DEFAULT_SESSION_ID = "c/1";
    public static final String DEFAULT_DAY = "MONDAY";
    public static final String DEFAULT_SUBJECT = "BIOLOGY";
    public static final String DEFAULT_TIMESLOT = "12:00 to 13:00";


    private SessionId sessionId;
    private Day day;
    private Subject subject;
    private Timeslot timeslot;
    private Set<Tag> tags;

    /**
     * Creates a {@code SessionBuilder} with the default details.
     */
    public SessionBuilder() {
        sessionId = new SessionId(DEFAULT_SESSION_ID);
        day = new Day(DEFAULT_DAY);
        subject = new Subject(DEFAULT_SUBJECT);
        timeslot = new Timeslot(DEFAULT_TIMESLOT);
        tags = new HashSet<>();
    }

    /**
     * Initializes the SessionBuilder with the data of {@code sessionToCopy}.
     */
    public SessionBuilder(Session sessionToCopy) {
        sessionId = sessionToCopy.getClassId();
        day = sessionToCopy.getDay();
        subject = sessionToCopy.getSubject();
        timeslot = sessionToCopy.getTimeslot();
        tags = new HashSet<>(sessionToCopy.getTags());
    }

    /**
     * Sets the {@code SessionId} of the {@code Session} that we are building.
     */
    public SessionBuilder withSessionId(String sessionId) {
        this.sessionId = new SessionId(sessionId);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Session} that we are building.
     */
    public SessionBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Day} of the {@code Session} that we are building.
     */
    public SessionBuilder withDay(String day) {
        this.day = new Day(day);
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
     * Sets the {@code Timeslot} of the {@code Session} that we are building.
     */
    public SessionBuilder withTimeslot(String timeslot) {
        this.timeslot = new Timeslot(timeslot);
        return this;
    }

    /**
     * Build
     * @return Session
     */
    public Session build() {
        return new Session(sessionId, day, timeslot, subject, tags);
    }

}
