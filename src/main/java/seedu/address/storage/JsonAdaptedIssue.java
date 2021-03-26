package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.issue.Category;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.RoomNumber;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Timestamp;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Issue}.
 */
class JsonAdaptedIssue {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Issue's %s field is missing!";

    private final String roomNumber;
    private final String description;
    private final String timestamp;
    private final String status;
    private final String category;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedIssue} with the given issue details.
     */
    @JsonCreator
    public JsonAdaptedIssue(@JsonProperty("roomNumber") String roomNumber,
            @JsonProperty("description") String description,
            @JsonProperty("timestamp") String timestamp,
            @JsonProperty("status") String status,
            @JsonProperty("category") String category,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.roomNumber = roomNumber;
        this.description = description;
        this.timestamp = timestamp;
        this.status = status;
        this.category = category;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Issue} into this class for Jackson use.
     */
    public JsonAdaptedIssue(Issue source) {
        roomNumber = source.getRoomNumber().value;
        description = source.getDescription().value;
        timestamp = source.getTimestamp().toString();
        status = source.getStatus().value.toString();
        category = source.getCategory().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted issue object into the model's {@code Issue} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted issue.
     */
    public Issue toModelType() throws IllegalValueException {
        final List<Tag> roomTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            roomTags.add(tag.toModelType());
        }
        if (roomNumber == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, RoomNumber.class.getSimpleName()));
        }
        if (!RoomNumber.isValidRoomNumber(roomNumber)) {
            throw new IllegalValueException(RoomNumber.MESSAGE_CONSTRAINTS);
        }
        final RoomNumber modelRoomNumber = new RoomNumber(roomNumber);

        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (timestamp == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Timestamp.class.getSimpleName()));
        }
        if (!Timestamp.isValidTimestamp(timestamp)) {
            throw new IllegalValueException(Timestamp.MESSAGE_CONSTRAINTS);
        }
        final Timestamp modelTimestamp = new Timestamp(timestamp);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(status);

        if (category == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Category.class.getSimpleName()));
        }
        if (!Category.isValidCategory(category)) {
            throw new IllegalValueException(Category.MESSAGE_CONSTRAINTS);
        }
        final Category modelCategory = new Category(category);

        final Set<Tag> modelIssueTags = new HashSet<>(roomTags);

        return new Issue(modelRoomNumber, modelDescription, modelTimestamp, modelStatus, modelCategory, modelIssueTags);
    }

}
