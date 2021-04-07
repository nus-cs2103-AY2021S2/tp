package seedu.address.storage;

import static seedu.address.commons.core.Messages.MESSAGE_DESERIALIZE_ERROR_DUMP_DATA;

import java.time.LocalDate;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.SpecialDate;

/**
 * Jackson-friendly version of {@link SpecialDate}.
 */
public class JsonAdaptedSpecialDate {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "A special date's %s field is missing!";

    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedSpecialDate.class);

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

    private IllegalValueException internalIllegalValueException(String message) {
        logger.warning(String.format(MESSAGE_DESERIALIZE_ERROR_DUMP_DATA, "Special Date"));
        logger.warning(this.toString());
        return new IllegalValueException(message);
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code SpecialDate} object.
     */
    public SpecialDate toModelType() throws IllegalValueException {
        if (date == null) {
            throw internalIllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "date"));
        }

        if (description == null) {
            throw internalIllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "description"));
        }

        String trimmedDescription = description.trim();
        if (!SpecialDate.isValidSpecialDate(date, trimmedDescription)) {
            throw internalIllegalValueException(SpecialDate.MESSAGE_CONSTRAINTS);
        }
        return new SpecialDate(date, trimmedDescription);
    }

    @Override
    public String toString() {
        return "JsonAdaptedSpecialDate{"
                + "date=" + date
                + ", description='" + description + '\''
                + "}";
    }
}
