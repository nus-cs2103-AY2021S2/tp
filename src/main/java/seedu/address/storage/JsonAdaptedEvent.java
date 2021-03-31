package seedu.address.storage;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.exceptions.TimeConversionException;
import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.TimeUtil;
import seedu.address.model.task.repeatable.Event;

/**
 * Jackson-friendly version of {@link Event}.
 */
class JsonAdaptedEvent {
    private final String description;
    private final String date;
    private final String time;
    private final Boolean isWeekly;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given {@code description}, {@code date},
     * {@code time} and {@code isWeekly}.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("description")String description,
                            @JsonProperty("date") String date,
                            @JsonProperty("time") String time,
                            @JsonProperty("isWeekly") Boolean isWeekly) {
        this.description = description;
        this.date = date;
        this.time = time;
        this.isWeekly = isWeekly;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        description = source.getDescription();
        date = DateUtil.decodeDateForStorage(source.getDate());
        time = TimeUtil.decodeTime(source.getTime());
        isWeekly = source.getIsWeekly();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException("Event description should not be null.");
        }

        if (date == null) {
            throw new IllegalValueException("Event date should not be null.");
        }
        LocalDate encodedDate;
        try {
            encodedDate = DateUtil.encodeDate(date);
        } catch (DateConversionException e) {
            throw new IllegalValueException(e.getMessage());
        }

        if (time == null) {
            throw new IllegalValueException("Event time should not be null.");
        }
        LocalTime encodedTime;
        try {
            encodedTime = TimeUtil.encodeTime(time);
        } catch (TimeConversionException e) {
            throw new IllegalValueException(e.getMessage());
        }

        if (isWeekly == null) {
            throw new IllegalValueException("Event isWeekly should not be null.");
        }

        return new Event(description, encodedDate, encodedTime, isWeekly);
    }

}
