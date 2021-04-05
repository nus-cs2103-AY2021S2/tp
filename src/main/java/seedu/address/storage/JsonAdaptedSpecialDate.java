package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.SpecialDate;

/**
 * Jackson-friendly version of {@link SpecialDate}.
 */
public class JsonAdaptedSpecialDate {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "A special date's %s field is missing!";

    private final LocalDate date;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedSpecialDate} with the given {@code date} and {@code description}.
     */
    @JsonCreator
    public JsonAdaptedSpecialDate(@JsonProperty("date") LocalDate date,
            @JsonProperty("description") String description) {
        this.date = date;
        this.description = description;
    }

    /**
     * Converts a given {@code SpecialDate} into this class for Jackson use.
     */
    public JsonAdaptedSpecialDate(SpecialDate source) {
        date = source.getDate();
        description = source.getDescription();
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code SpecialDate} object.
     */
    public SpecialDate toModelType() throws IllegalValueException {
        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "date"));
        }

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "description"));
        }

        if (!SpecialDate.isValidSpecialDate(date, description)) {
            throw new IllegalValueException(SpecialDate.MESSAGE_CONSTRAINTS);
        }
        return new SpecialDate(date, description);
    }
}
