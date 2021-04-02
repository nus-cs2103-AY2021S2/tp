package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Address;
import seedu.address.model.Name;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.contact.Contact;
import seedu.address.model.tag.Tag;

public class JsonAdaptedAppointment {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final String name;
    private final String address;
    private final DateTime date;
    private final Set<JsonAdaptedContact> contacts = new HashSet<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("name") String name, @JsonProperty("address") String address,
                                  @JsonProperty("date") DateTime date,
                                  @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                                  @JsonProperty("contacts") Set<JsonAdaptedContact> contacts) {
        this.name = name;
        this.address = address;
        this.date = date;

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (contacts != null) {
            this.contacts.addAll(contacts);
        }
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        name = source.getName().fullName;
        address = source.getAddress().value;
        date = source.getDateTime();

        contacts.addAll(source.getContacts().stream()
                .map(JsonAdaptedContact::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        final Set<Contact> appointmentContacts = new HashSet<>();

        for (JsonAdaptedContact contact : contacts) {
            appointmentContacts.add(contact.toModelType());
        }

        final List<Tag> appointmentTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            appointmentTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateTime.class.getSimpleName()));
        }
        final DateTime modelDate = date;

        final Set<Contact> modelContacts = new HashSet<>(appointmentContacts);
        final Set<Tag> modelTags = new HashSet<>(appointmentTags);
        return new Appointment(modelName, modelAddress, modelDate, modelContacts, modelTags);
    }
}
