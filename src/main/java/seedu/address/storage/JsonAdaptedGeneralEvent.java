package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.LocalDateTimeUtil;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Description;

/**
 * Jackson-friendly version of {@link GeneralEvent}.
 */
class JsonAdaptedGeneralEvent {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "General event's %s field is missing!";
    public static final String MESSAGE_CONSTRAINTS = "Event date must be formatted "
        + "to a valid DD/MM/YYYY TIME";
    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedGeneralEvent.class);
    public final String description;
    public final String date;

    /**
     * Constructs a {@code JsonAdaptedGeneralEvent} with the given {@code description} and {@code date}.
     */
    @JsonCreator
    public JsonAdaptedGeneralEvent(@JsonProperty("description") String description,
                                 @JsonProperty("date") String date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Converts a given {@code source} into this class for Jackson use.
     */
    public JsonAdaptedGeneralEvent(GeneralEvent source) {
        description = source.description.description;
        date = source.date.format(LocalDateTimeUtil.DATETIME_FORMATTER);
    }


    /**
     * Converts this Jackson-friendly adapted general event object into the model's {@code generalEvent} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted general event.
     */
    public GeneralEvent toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Description.class.getSimpleName()));
        }

        final Description modelDescription;
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        } else {
            modelDescription = new Description(description);
        }

        if (date == null || !LocalDateTimeUtil.isValidDateTime(date)) {
            throw new IllegalValueException(String.format(MESSAGE_CONSTRAINTS,
                LocalDateTime.class.getSimpleName()));
        }

        final LocalDateTime modelDeadline = LocalDateTime.parse(date,
            LocalDateTimeUtil.DATETIME_FORMATTER);


        return new GeneralEvent(modelDescription, modelDeadline);
    }

}
