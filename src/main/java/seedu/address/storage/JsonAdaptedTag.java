package seedu.address.storage;

import static seedu.address.commons.core.Messages.MESSAGE_DESERIALIZE_ERROR_DUMP_DATA;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTag {

    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedTag.class);
    private final String tagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(Tag source) {
        tagName = source.tagName;
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    private IllegalValueException internalIllegalValueException(String message) {
        logger.warning(String.format(MESSAGE_DESERIALIZE_ERROR_DUMP_DATA, "Tag"));
        logger.warning(this.toString());
        return new IllegalValueException(message);
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Tag toModelType() throws IllegalValueException {
        String trimmedTag = tagName.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw internalIllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    @Override
    public String toString() {
        return "JsonAdaptedTag{"
                + "tagName='" + tagName + '\''
                + "}";
    }
}
