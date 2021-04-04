package seedu.iscam.storage.meeting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.iscam.commons.exceptions.IllegalValueException;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.commons.Location;
import seedu.iscam.model.commons.Name;
import seedu.iscam.model.commons.Tag;
import seedu.iscam.model.meeting.CompletionStatus;
import seedu.iscam.model.meeting.DateTime;
import seedu.iscam.model.meeting.Description;
import seedu.iscam.model.meeting.Meeting;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

    private final String clientName;
    private final String dateTime;
    private final String location;
    private final String description;
    private final List<JsonAdaptedMeetingTag> tags = new ArrayList<>();
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedClient} with the given client details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("client") String clientName, @JsonProperty("dateTime") String dateTime,
                              @JsonProperty("location") String location,
                              @JsonProperty("description") String description,
                              @JsonProperty("tags") List<JsonAdaptedMeetingTag> tags,
                              @JsonProperty("status") String status) {
        this.clientName = clientName;
        this.dateTime = dateTime;
        this.location = location;
        this.description = description;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.status = status;
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        clientName = source.getClientName().toString();
        dateTime = source.getDateTime().toString();
        location = source.getLocation().value;
        description = source.getDescription().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedMeetingTag::new)
                .collect(Collectors.toList()));
        status = source.getStatus().toString();
    }

    /**
     * Converts this Jackson-friendly adapted meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException {
        // Name
        if (clientName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Client.class.getSimpleName()));
        }
        if (!Name.isValidName(clientName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelClient = new Name(clientName);

        // Date and time
        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateTime.class.getSimpleName()));
        }
        if (!DateTime.isStringValidFormat(dateTime)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        final DateTime modelDateTime = new DateTime(dateTime);

        // Location
        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        // Description
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        // Tags
        final List<Tag> meetingTags = new ArrayList<>();
        for (JsonAdaptedMeetingTag tag : tags) {
            meetingTags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(meetingTags);

        final CompletionStatus status = new CompletionStatus(this.status);

        return new Meeting(modelClient, modelDateTime, modelLocation, modelDescription, modelTags, status);
    }

}
