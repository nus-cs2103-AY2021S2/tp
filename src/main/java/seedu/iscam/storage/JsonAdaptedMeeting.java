package seedu.iscam.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.iscam.commons.exceptions.IllegalValueException;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.client.Location;
import seedu.iscam.model.client.Name;
import seedu.iscam.model.meeting.DateTime;
import seedu.iscam.model.meeting.Description;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

    private final String clientName;
    private final String dateTime;
    private final String location;
    private final String description;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final String isDone;

    /**
     * Constructs a {@code JsonAdaptedClient} with the given client details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("client") String clientName, @JsonProperty("dateTime") String dateTime,
                              @JsonProperty("location") String location,
                              @JsonProperty("description") String description,
                              @JsonProperty("tags") List<JsonAdaptedTag> tags, @JsonProperty("isDone") String isDone) {
        this.clientName = clientName;
        this.dateTime = dateTime;
        this.location = location;
        this.description = description;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.isDone = isDone;
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
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        isDone = source.getIsDone() ? "true" : "false";
    }

    /**
     * Converts this Jackson-friendly adapted client object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException {
        final List<Tag> meetingTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            meetingTags.add(tag.toModelType());
        }

        if (clientName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Client.class.getSimpleName()));
        }
        if (!Name.isValidName(clientName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelClient = new Name(clientName);

        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }

        //        if (!Phone.isValidPhone(phone)) {
        //            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        //        }
        final DateTime modelDateTime = new DateTime(dateTime);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        final Set<Tag> modelTags = new HashSet<>(meetingTags);

        return new Meeting(modelClient, modelDateTime, modelLocation, modelDescription, modelTags);
    }

}
