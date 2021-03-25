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
import seedu.address.model.person.Patient;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "patientrecords")
class JsonSerializablePatientRecords {

    public static final String MESSAGE_DUPLICATE_PATIENT = "Patients list contains duplicate person(s).";

    private final List<JsonAdaptedPatient> patients = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */

    @JsonCreator
    public JsonSerializablePatientRecords(@JsonProperty("patients") List<JsonAdaptedPatient> patients) {
        this.patients.addAll(patients);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializablePatientRecords(ReadOnlyAddressBook<Patient> source) {
        patients.addAll(source.getPersonList().stream().map(JsonAdaptedPatient::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook<Patient> toModelType() throws IllegalValueException {
        AddressBook<Patient> addressBook = new AddressBook<>();
        for (JsonAdaptedPatient jsonAdaptedPatient : patients) {
            Patient patient = jsonAdaptedPatient.toModelType();

            if (addressBook.hasPerson(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT);
            }

            addressBook.addPerson(patient);
        }
        return addressBook;
    }

}
