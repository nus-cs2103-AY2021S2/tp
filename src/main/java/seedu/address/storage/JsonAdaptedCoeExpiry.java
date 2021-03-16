package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.customer.CoeExpiry;
import seedu.address.model.tag.Tag;

public class JsonAdaptedCoeExpiry {
    private final String expiryDate;

    /**
     * Constructs a {@code JsonAdaptedCoeExpiry} with the given {@code expiryDate}.
     */
    @JsonCreator
    public JsonAdaptedCoeExpiry(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Converts a given {@code CoeExpiry} into this class for Jackson use.
     */
    public JsonAdaptedCoeExpiry(CoeExpiry source) {
        expiryDate = source.expiryDate;
    }

    @JsonValue
    public String getCoeExpiryDate() {
        return expiryDate;
    }

    /**
     * Converts this Jackson-friendly adapted CoeExpiry object into the model's {@code CoeExpiry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted CoeExpiry.
     */
    public CoeExpiry toModelType() throws IllegalValueException {
        if (!CoeExpiry.isValidCoeExpiry(expiryDate)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new CoeExpiry(expiryDate);
    }

}
