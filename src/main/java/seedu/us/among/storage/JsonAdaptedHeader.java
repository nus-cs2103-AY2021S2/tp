package seedu.us.among.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.us.among.commons.exceptions.IllegalValueException;
import seedu.us.among.model.header.Header;

/**
 * Jackson-friendly version of {@link Header}.
 */
class JsonAdaptedHeader {

    private final String headerName;

    /**
     * Constructs a {@code JsonAdaptedHeader} with the given {@code headerName}.
     */
    @JsonCreator
    public JsonAdaptedHeader(String headerName) {
        this.headerName = headerName;
    }

    /**
     * Converts a given {@code Header} into this class for Jackson use.
     */
    public JsonAdaptedHeader(Header source) {
        headerName = source.headerName;
    }

    @JsonValue
    public String getHeaderName() {
        return headerName;
    }

    /**
     * Converts this Jackson-friendly adapted Header object into the model's {@code Header} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Header toModelType() throws IllegalValueException {
        if (!Header.isValidHeaderName(headerName)) {
            throw new IllegalValueException(Header.MESSAGE_CONSTRAINTS);
        }
        return new Header(headerName);
    }

}
