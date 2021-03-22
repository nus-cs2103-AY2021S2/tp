package seedu.booking.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.booking.commons.exceptions.IllegalValueException;
import seedu.booking.model.venue.Capacity;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;

public class JsonAdaptedVenue {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Venue's %s field is missing!";

    private final String name;
    private final String capacity;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedVenue} with the given venue details.
     */
    @JsonCreator
    public JsonAdaptedVenue(@JsonProperty("name") String name,
                            @JsonProperty("capacity") String capacity,
                            @JsonProperty("description") String description) {
        this.name = name;
        this.capacity = capacity;
        this.description = description;
    }

    /**
     * Converts a given {@code Venue} into this class for Jackson use.
     */
    public JsonAdaptedVenue(Venue source) {
        name = source.getVenueName().venueName;
        capacity = String.valueOf(source.getCapacity());
        description = source.getDescription();
    }


    /**
     * Converts this Jackson-friendly adapted venue object into the model's {@code Venue} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted venue.
     */
    public Venue toModelType() throws IllegalValueException {
        // needs to be changed after implementation of classes for each of the attributes
        if (name == null) {
            throw new IllegalValueException(MISSING_FIELD_MESSAGE_FORMAT);
        }

        final VenueName modelName = new VenueName(name);

        if (capacity == null) {
            throw new IllegalValueException(MISSING_FIELD_MESSAGE_FORMAT);
        }

        final Capacity modelCapacity = new Capacity(Integer.parseInt(capacity));

        if (modelCapacity.venueCapacity < 1) {
            throw new IllegalValueException(MISSING_FIELD_MESSAGE_FORMAT);
        }

        final String modelDescription = description;

        return new Venue(modelName, modelCapacity, description);
    }

}
