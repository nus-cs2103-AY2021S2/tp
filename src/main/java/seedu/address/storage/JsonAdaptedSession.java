package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.session.*;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Session}.
 */
class JsonAdaptedSession {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Session's %s field is missing!";

    private final String classId;
    private final String day;
    private final String timeslot;
    private final String subject;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedSession(@JsonProperty("classId") String classId, @JsonProperty("day") String day,
                             @JsonProperty("timeslot") String timeslot, @JsonProperty("subject") String subject,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.classId = classId;
        this.day = day;
        this.timeslot = timeslot;
        this.subject = subject;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedSession(Session source) {
        classId = source.getClassId().toString();
        day = source.getDay().toString();
        timeslot = source.getTimeslot().toString();
        subject = source.getSubject().subject;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Session} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted session.
     */
    public Session toModelType() throws IllegalValueException {
        final List<Tag> sessionTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            sessionTags.add(tag.toModelType());
        }

        if (classId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "class Id"));
        }
        final SessionId modelClassId = new SessionId(classId);

        if (day == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName()));
        }
        if (!Day.isValidDay(day)) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }
        final Day modelDay = new Day(day);
        if (timeslot == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Timeslot.class.getSimpleName()));
        }
        if (!Timeslot.isValidTimeslot(timeslot)) {
            throw new IllegalValueException(Timeslot.MESSAGE_CONSTRAINTS);
        }
        final Timeslot modelTimeslot = new Timeslot(timeslot);

        if (subject == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Subject.class.getSimpleName()));
        }
        if (!Subject.isValidSubject(subject)) {
            throw new IllegalValueException(Subject.MESSAGE_CONSTRAINTS);
        }
        final Subject modelSubject = new Subject(subject);
        final Set<Tag> modelTags = new HashSet<>(sessionTags);
        return new Session(modelClassId, modelDay, modelTimeslot, modelSubject, modelTags);
    }

}
