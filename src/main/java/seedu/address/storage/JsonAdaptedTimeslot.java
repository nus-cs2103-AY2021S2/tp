package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.appointment.exceptions.NegativeOrZeroDurationException;

/**
 * Jackson-friendly version of {@link Timeslot}.
 */
public class JsonAdaptedTimeslot {
    private final String start;
    private final String end;

    /**
     * Constructs a {@code JsonAdaptedTimeslot} with the given {@code start} and {@code end}.
     */
    @JsonCreator
    public JsonAdaptedTimeslot(@JsonProperty("start") String start, @JsonProperty("end") String end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Converts a given {@code Timeslot} into this class for Jackson use.
     */
    public JsonAdaptedTimeslot(Timeslot source) {
        start = source.getStart().toString();
        end = source.getEnd().toString();
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    /**
     * Converts this Jackson-friendly adapted timeslot object into the model's {@code Timeslot} object.
     *
     * @throws IllegalValueException if the end dateTime is equal to or before the start dateTime
     * raises a {@code NegativeOrZeroDurationException}.
     */
    public Timeslot toModelType() throws IllegalValueException {
        try {
            return new Timeslot(LocalDateTime.parse(start), LocalDateTime.parse(end));
        } catch (NegativeOrZeroDurationException e) {
            throw new IllegalValueException(Timeslot.MESSAGE_CONSTRAINTS);
        }
    }

}
