package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class JsonAdaptedPatient extends JsonAdaptedPerson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";
    public static final String UUID_MESSAGE_CONSTRAINTS = "This is not a valid UUID field";

    private final String phone;
    private final String email;
    private final String address;

    /**
     * Constructs a {@code JsonAdaptedPatient} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("uuid") String uuid, @JsonProperty("name") String name,
                             @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                             @JsonProperty("address") String address,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        super(uuid, name, tagged);
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    /**
     * Converts a given {@code Patient} into this class for Jackson use.
     */
    public JsonAdaptedPatient(Patient source) {
        super(source);
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
    }

    /**
     * Converts this Jackson-friendly adapted patient object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patient.
     */
    @Override
    public Patient toModelType() throws IllegalValueException {
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

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Patient(modelUuid, modelName, modelPhone, modelEmail, modelAddress, modelTags);
    }
}
