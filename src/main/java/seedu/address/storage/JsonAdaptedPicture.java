package seedu.address.storage;

import java.nio.file.Path;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Picture;

/**
 * Jackson-friendly version of {@link Picture}
 */
public class JsonAdaptedPicture {

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

    /**
     * Converts this Jackson-friendly adapted picture object into the model's {@code Picture} object.
     */
    public Picture toModelType() throws IllegalValueException {
        if (!Picture.isValidFilePath(filePath)) {
            throw new IllegalValueException(this.filePath + " " + Picture.MESSAGE_CONSTRAINTS);
        }

        return new Picture(filePath);
    }
}
