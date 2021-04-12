package seedu.address.storage;

import static seedu.address.commons.core.Messages.MESSAGE_DESERIALIZE_ERROR_DUMP_DATA;

import java.nio.file.Path;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Picture;

/**
 * Jackson-friendly version of {@link Picture}
 */
public class JsonAdaptedPicture {

    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedPicture.class);
    private final Path filePath;

    /**
     * Constructs a {@code JsonAdaptedPicture} with the given args.
     */
    @JsonCreator
    public JsonAdaptedPicture(@JsonProperty("filePath") Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Converts a given {@code Picture} into this class for Jackson use
     */
    public JsonAdaptedPicture(Picture source) {
        filePath = source.getFilePath();
    }

    private IllegalValueException internalIllegalValueException(String message) {
        logger.warning(String.format(MESSAGE_DESERIALIZE_ERROR_DUMP_DATA, "Picture}"));
        logger.warning(this.toString());
        return new IllegalValueException(message);
    }

    /**
     * Converts this Jackson-friendly adapted picture object into the model's {@code Picture} object.
     */
    public Picture toModelType() throws IllegalValueException {
        if (!Picture.isValidPicture(filePath)) {
            throw internalIllegalValueException(this.filePath + " " + Picture.MESSAGE_CONSTRAINTS);
        }

        return new Picture(filePath);
    }

    @Override
    public String toString() {
        return "JsonAdaptedPicture{"
                + "filePath=" + filePath
                + "}";
    }
}
