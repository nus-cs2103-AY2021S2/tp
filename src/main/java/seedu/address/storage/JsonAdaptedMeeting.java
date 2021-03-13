package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Client;
import seedu.address.model.client.Address;
import seedu.address.model.client.Name;
import seedu.address.model.tag.Tag;

import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

    private final String client;
    private final String dateTime;
    private final String address;
    private final String description;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final String isDone;

    /**
     * Constructs a {@code JsonAdaptedClient} with the given client details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("client") String client, @JsonProperty("dateTime") String dateTime,
                             @JsonProperty("address") String address, @JsonProperty("description") String description,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags, @JsonProperty("isDone") String isDone) {
        this.client = client;
        this.dateTime = dateTime;
        this.address = address;
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
        client = source.getClient().getName().fullName;
        dateTime = source.getDateTime().toString();
        address = source.getAddress().value;
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

        if (client == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Client.class.getSimpleName()));
        }
        if (!Name.isValidName(client)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelClient = new Name(client);

        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName()));
        }

//        if (!Phone.isValidPhone(phone)) {
//            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
//        }
        final LocalDateTime modelDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.BASIC_ISO_DATE);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        final Set<Tag> modelTags = new HashSet<>(meetingTags);

        return new Meeting(modelClient, modelDateTime, modelAddress, modelDescription, modelTags);
    }

}
