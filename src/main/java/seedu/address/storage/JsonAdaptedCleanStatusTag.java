package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.CleanStatusTag;

/**
 * Jackson-friendly version of {@link CleanStatusTag}.
 */
class JsonAdaptedCleanStatusTag {

    private final String cleanStatus;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedCleanStatusTag(String cleanStatus) {
        this.cleanStatus = cleanStatus;
    }

    /**
     * Converts a given {@code CleanStatusTag} into this class for Jackson use.
     */
    public JsonAdaptedCleanStatusTag(CleanStatusTag source) {
        cleanStatus = source.getValue();
    }

    @JsonValue
    public String getCleanStatus() {
        return cleanStatus;
    }

    /**
     * Converts this Jackson-friendly adapted cleanStatusTag object into the model's {@code CleanStatusTag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted CleanStatusTag.
     */
    public CleanStatusTag toModelType() throws IllegalValueException {
        if (!CleanStatusTag.isValidCleanStatusTag(cleanStatus)) {
            throw new IllegalValueException(CleanStatusTag.getMessageConstraints());
        }
        return new CleanStatusTag(cleanStatus);
    }

}
