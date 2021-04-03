package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assignee.Assignee;

/**
 * Jackson-friendly version of {@link Assignee}.
 */
class JsonAdaptedAssignee {

    private final String assigneeName;

    /**
     * Constructs a {@code JsonAdaptedAssignee} with the given {@code assigneeName}.
     */
    @JsonCreator
    public JsonAdaptedAssignee(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    /**
     * Converts a given {@code Assignee} into this class for Jackson use.
     */
    public JsonAdaptedAssignee(Assignee source) {
        assigneeName = source.assigneeName;
    }

    @JsonValue
    public String getAssigneeName() {
        return assigneeName;
    }

    /**
     * Converts this Jackson-friendly adapted assignee object into the model's {@code Assignee} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assignee.
     */
    public Assignee toModelType() throws IllegalValueException {
        if (!Assignee.isValidAssigneeName(assigneeName)) {
            throw new IllegalValueException(Assignee.MESSAGE_CONSTRAINTS);
        }
        return new Assignee(assigneeName);
    }
}
