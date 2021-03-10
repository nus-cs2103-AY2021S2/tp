package seedu.address.storage;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.person.Meeting;

public class JsonAdaptedMeeting {

    private final LocalDate date;
    private final LocalTime time;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given meeting details.
     */
    @JsonCreator
    public JsonAdaptedMeeting(@JsonProperty("date") LocalDate date, @JsonProperty("time") LocalTime time,
            @JsonProperty("description") String description) {
        this.date = date;
        this.time = time;
        this.description = description;
    }

    /**
     * Converts a given {@code Meeting} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting source) {
        date = source.getDate();
        time = source.getTime();
        description = source.getDescription();
    }

    public Meeting toModelType() {
        return new Meeting(date, time, description);
    }
}
