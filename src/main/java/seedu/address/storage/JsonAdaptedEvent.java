package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.task.Interval;
import seedu.address.model.task.repeatable.Event;

/**
 * Jackson-friendly version of {@link Event}.
 */
class JsonAdaptedEvent {
    private final String description;
    private final String interval;
    private final String at;
    private final Boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given {@code description}, {@code interval},
     * {@code at} and {@code isDone}.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("description")String description,
                            @JsonProperty("interval") String interval,
                            @JsonProperty("date") String at,
                            @JsonProperty("isDone") Boolean isDone) {
        this.description = description;
        this.interval = interval;
        this.at = at;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        description = source.getDescription();
        interval = source.getRecurrence().name();
        at = DateUtil.decodeDateForStorage(source.getAt());
        isDone = source.getIsDone();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType() throws IllegalValueException {
        Interval eventInterval;
        try {
            eventInterval = Interval.valueOf(interval);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        }

        LocalDate date;
        try {
            date = DateUtil.encodeDate(at);
        } catch (DateConversionException e) {
            throw new IllegalValueException(e.getMessage());
        }

        return new Event(description, eventInterval, isDone, date);
    }

}
