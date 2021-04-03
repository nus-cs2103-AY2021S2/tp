package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Doctor;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "doctorRecords")
class JsonSerializableDoctorRecords {

    public static final String MESSAGE_DUPLICATE_DOCTOR = "Doctors list contains duplicate person(s).";

    private final List<JsonAdaptedDoctor> doctors = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */

    @JsonCreator
    public JsonSerializableDoctorRecords(@JsonProperty("doctors") List<JsonAdaptedDoctor> doctors) {
        this.doctors.addAll(doctors);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableDoctorRecords(ReadOnlyAddressBook<Doctor> source) {
        doctors.addAll(source.getPersonList().stream().map(JsonAdaptedDoctor::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook<Doctor> toModelType() throws IllegalValueException {
        AddressBook<Doctor> addressBook = new AddressBook<>();
        for (JsonAdaptedDoctor jsonAdaptedDoctor : doctors) {
            Doctor doctor = jsonAdaptedDoctor.toModelType();

            if (addressBook.hasPerson(doctor)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DOCTOR);
            }

            addressBook.addPerson(doctor);
        }
        return addressBook;
    }

}
