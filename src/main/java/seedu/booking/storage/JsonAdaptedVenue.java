package seedu.booking.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.booking.commons.exceptions.IllegalValueException;
import seedu.booking.model.Tag;
import seedu.booking.model.venue.Capacity;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;

public class JsonAdaptedVenue {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Venue's %s field is missing!";
    public static final String INVALID_FIELD_MESSAGE_FORMAT = "Venue's %s field is invalid!";

    private final String name;
    private final String capacity;
    private final String description;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedVenue} with the given venue details.
     */
    @JsonCreator
    public JsonAdaptedVenue(@JsonProperty("name") String name,
                            @JsonProperty("capacity") String capacity,
                            @JsonProperty("description") String description,
                            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Venue} into this class for Jackson use.
     */
    public JsonAdaptedVenue(Venue source) {
        name = source.getVenueName().venueName;
        capacity = String.valueOf(source.getCapacity());
        description = source.getDescription();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }


    /**
     * Converts this Jackson-friendly adapted venue object into the model's {@code Venue} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted venue.
     */
    public Venue toModelType() throws IllegalValueException {
        final List<Tag> venueTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            venueTags.add(tag.toModelType());
        }

        // needs to be changed after implementation of classes for each of the attributes
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }

        final VenueName modelName = new VenueName(name);

        if (capacity == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "capacity"));
        }

        if (Integer.parseInt(capacity) < 1) {
            throw new IllegalArgumentException(String.format(INVALID_FIELD_MESSAGE_FORMAT, "capacity"));
        }

        final Capacity modelCapacity = new Capacity(Integer.parseInt(capacity));

        final String modelDescription = description;

        if (modelDescription == null) {
            throw new IllegalArgumentException(String.format(INVALID_FIELD_MESSAGE_FORMAT, "description"));
        }

        final Set<Tag> modelTags = new HashSet<>(venueTags);

        return new Venue(modelName, modelCapacity, description, modelTags);
    }

}
