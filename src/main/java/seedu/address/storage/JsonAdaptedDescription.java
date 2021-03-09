package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.description.Description;

/**
 * Jackson-friendly version of {@link seedu.address.model.description.Description}.
 */
class JsonAdaptedDescription {

    private final String descriptionName;

    /**
     * Constructs a {@code JsonAdaptedDescription} with the given {@code descriptionName}.
     */
    @JsonCreator
    public JsonAdaptedDescription(String descriptionName) {
        this.descriptionName = descriptionName;
    }

    /**
     * Converts a given {@code Description} into this class for Jackson use.
     */
    public JsonAdaptedDescription(Description source) {
        descriptionName = source.descriptionName;
    }

    @JsonValue
    public String getDescriptionName() {
        return descriptionName;
    }

    /**
     * Converts this Jackson-friendly adapted description object into the model's {@code Description} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted description.
     */
    public Description toModelType() throws IllegalValueException {
        if (!Description.isValidDescriptionName(descriptionName)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(descriptionName);
    }

}
