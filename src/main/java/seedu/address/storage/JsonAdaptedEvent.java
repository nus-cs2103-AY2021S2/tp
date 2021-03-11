package seedu.address.storage;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.person.Event;

/**
 * Jackson-friendly version of {@link Event}.
 */
public class JsonAdaptedEvent {

    private final LocalDate date;
    private final LocalTime time;
    private final String description;
    private final boolean hasTime;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given {@code date}, {@code time} and {@code description}.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("date") LocalDate date, @JsonProperty("time") LocalTime time,
            @JsonProperty("description") String description) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.hasTime = (time != null);
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        date = source.getDate();
        time = source.getTime();
        description = source.getDescription();
        hasTime = source.hasTime();
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     */
    public Event toModelType() {
        if (hasTime) {
            return new Event (date, time, description);
        } else {
            return new Event(date, description);
        }
    }
}
