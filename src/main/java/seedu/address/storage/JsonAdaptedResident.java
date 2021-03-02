package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.resident.Email;
import seedu.address.model.resident.Name;
import seedu.address.model.resident.Resident;
import seedu.address.model.resident.Phone;
import seedu.address.model.resident.Room;

/**
 * Jackson-friendly version of {@link Resident}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Resident's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String room;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given resident details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("room") String room) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.room = room;
    }

    /**
     * Converts a given {@code Resident} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Resident source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        room = source.getRoom().value;
    }

    /**
     * Converts this Jackson-friendly adapted resident object into the model's {@code Resident} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted resident.
     */
    public Resident toModelType() throws IllegalValueException {
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

        if (!Room.isValidRoom(room)) {
            throw new IllegalValueException(Room.MESSAGE_CONSTRAINTS);
        }
        final Room modelRoom = new Room(room);

        return new Resident(modelName, modelPhone, modelEmail, modelRoom);
    }

}
