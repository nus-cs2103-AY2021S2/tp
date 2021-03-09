package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.person.Event;

/**
 * Jackson-friendly version of {@link Event}.
 */
public class JsonAdaptedEvent {

    private final String date;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given {@code date} and {@code description}.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("date") String date, @JsonProperty("description") String description) {
        this.date = date;
        this.description = description;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        date = source.getDate();
        description = source.getDescription();
    }

    /**
     * Converts this Jackson-friendly adapted event object into the model's {@code Event} object.
     */
    public Event toModelType() {
        return new Event(date, description);
    }
}
