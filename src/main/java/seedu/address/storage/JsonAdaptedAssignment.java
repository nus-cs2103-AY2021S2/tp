package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Description;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedAssignment {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Assignment's %s field is missing!";
    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedAssignment.class);
    public final String description;
    public final String deadline;

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given {@code description} and {@code deadline}.
     */
    @JsonCreator
    public JsonAdaptedAssignment(@JsonProperty("description") String description,
                                 @JsonProperty("deadline") String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Converts a given {@code source} into this class for Jackson use.
     */
    public JsonAdaptedAssignment(Assignment source) {
        description = source.description.description;
        deadline = source.deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    @JsonValue
    public String getAssignmentDescription() {
        return description;
    }

    @JsonValue
    public String getAssignmentDeadline() {
        return deadline;
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

        final Description modelDescription;
        if (description.equals("")) {
            logger.info("Description for Assignment is empty.");
        } else if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        } else {
            modelDescription = new Description(description);
        }

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }

        final LocalDateTime modelDeadline = LocalDateTime.parse(deadline,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));

        return new Assignment(modelDescription, deadline);
    }

}
