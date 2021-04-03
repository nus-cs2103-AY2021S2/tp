package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

public class JsonAdaptedDoctor extends JsonAdaptedPerson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Doctor's %s field is missing!";
    public static final String UUID_MESSAGE_CONSTRAINTS = "This is not a valid UUID field";

    /**
     * Constructs a {@code JsonAdaptedDoctor} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedDoctor(@JsonProperty("uuid") String uuid, @JsonProperty("name") String name,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        super(uuid, name, tagged);
    }

    /**
     * Converts a given {@code Doctor} into this class for Jackson use.
     */
    public JsonAdaptedDoctor(Doctor source) {
        super(source);
    }

    /**
     * Converts this Jackson-friendly adapted doctor object into the model's {@code Doctor} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted doctor.
     */
    @Override
    public Doctor toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        if (uuid == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, UUID.class.getSimpleName()));
        }
        final UUID modelUuid;
        try {
            modelUuid = UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(UUID_MESSAGE_CONSTRAINTS);
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Doctor(modelUuid, modelName, modelTags);
    }
}
