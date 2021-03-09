package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.model.person.Event;

public class JsonAdaptedEvent {

    private final String date;
    private final String description;

    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("date") String date, @JsonProperty("description") String description) {
        this.date = date;
        this.description = description;
    }

    public JsonAdaptedEvent(Event source) {
        date = source.getDate();
        description = source.getDescription();
    }

    public Event toModelType() {
        return new Event(date, description);
    }
}
