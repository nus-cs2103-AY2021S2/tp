package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.DateTimeUtil;
import seedu.address.model.group.Group;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Priority;


/**
 * Jackson-friendly version of {@link seedu.address.model.meeting.Meeting}.
 */

public class JsonAdaptedMeeting {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

    private final String name;
    private final String startDateTime;
    private final String endDateTime;
    private final String description;
    private final String priority;

    private final List<JsonAdaptedGroup> group = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdoptedMeeting} with the given meeting details.
     */

    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("name") String name,
                              @JsonProperty("startDateTime") String startDateTime,
                              @JsonProperty("endDateTime") String endDateTime,
                              @JsonProperty("description") String description,
                              @JsonProperty("priority") String priority,
                              @JsonProperty("group") List<JsonAdaptedGroup> group) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
        this.priority = priority;
        if (group != null) {
            this.group.addAll(group);
        }
    }


    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        name = source.getName().fullName;
        startDateTime = DateTimeUtil.formatDateTime(source.getStart().value);
        endDateTime = DateTimeUtil.formatDateTime(source.getTerminate().value);
        description = source.getDescription().fullDescription;
        priority = source.getPriority().toString();
        group.addAll(source.getGroups().stream()
                .map(JsonAdaptedGroup::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */

    public Meeting toModelType() throws IllegalValueException {
        final List<Group> meetingGroups = new ArrayList<>();
        for (JsonAdaptedGroup g: group) {
            meetingGroups.add(g.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MeetingName.class.getSimpleName()));
        }
        if (!MeetingName.isValidName(name)) {
            throw new IllegalValueException(MeetingName.MESSAGE_CONSTRAINTS);
        }

        final MeetingName modelMeetingName = new MeetingName(name);

        if (startDateTime == null || endDateTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName()));
        }
        if (!DateTime.isValidDateTime(startDateTime) || !DateTime.isValidDateTime(endDateTime)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        final DateTime modelStart = new DateTime(startDateTime);
        final DateTime modelTerminate = new DateTime(endDateTime);

        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }

        final Description modelDescription = new Description(description);


        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Priority.class.getSimpleName()));
        }
        if (!Priority.isValidPriority(priority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }

        final Priority modelPriority = new Priority(priority);
        final Set<Group> modelTags = new HashSet<>(meetingGroups);
        try {
            return new Meeting(modelMeetingName, modelStart, modelTerminate,
                    modelPriority, modelDescription, modelTags);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }
    }

}

