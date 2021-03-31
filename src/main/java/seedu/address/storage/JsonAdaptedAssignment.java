package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.LocalDateTimeUtil;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Description;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Assignment}.
 */
class JsonAdaptedAssignment {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Assignment's %s field is missing!";
    public static final String MESSAGE_CONSTRAINTS = "Assignment deadline must be formatted "
        + "to a valid DD/MM/YYYY HHmm";
    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedAssignment.class);
    public final String description;
    public final String deadline;
    public final String tag;
    public final String doneStatus;

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given {@code description}
     * ,{@code deadline}, {@code tag} and {@code doneStatus}.
     */
    @JsonCreator
    public JsonAdaptedAssignment(@JsonProperty("description") String description,
                                 @JsonProperty("deadline") String deadline,
                                 @JsonProperty("tag") String tag,
                                 @JsonProperty("doneStatus") String doneStatus) {
        this.description = description;
        this.deadline = deadline;
        this.tag = tag;
        this.doneStatus = doneStatus;
    }

    /**
     * Converts a given {@code source} into this class for Jackson use.
     */
    public JsonAdaptedAssignment(Assignment source) {
        description = source.description.description;
        deadline = source.deadline.format(LocalDateTimeUtil.DATETIME_FORMATTER);
        tag = source.getTag().tagName;
        doneStatus = source.isDone();
    }


    /**
     * Converts this Jackson-friendly adapted assignment object into the model's {@code assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assignment.
     */
    public Assignment toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        assert description != null;

        final Description modelDescription;
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        } else {
            modelDescription = new Description(description);
        }

        if (deadline == null || !LocalDateTimeUtil.isValidDateTime(deadline)) {
            throw new IllegalValueException(String.format(MESSAGE_CONSTRAINTS,
                    LocalDateTime.class.getSimpleName()));
        }

        assert deadline != null;
        final LocalDateTime modelDeadline = LocalDateTime.parse(deadline,
                LocalDateTimeUtil.DATETIME_FORMATTER);

        if (tag == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Tag.class.getSimpleName()));
        }

        assert tag != null;
        final Tag modelTag;
        if (!Tag.isValidTagName(tag)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        } else {
            modelTag = new Tag(tag);
        }

        if (doneStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Boolean.class.getSimpleName()));
        }

        final boolean isDone;

        if (doneStatus.equals("[X]")) {
            isDone = true;
        } else {
            isDone = false;
        }

        return new Assignment(modelDescription, modelDeadline, modelTag, isDone);
    }

}
