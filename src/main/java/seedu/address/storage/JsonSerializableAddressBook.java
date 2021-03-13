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
import seedu.address.model.resident.Resident;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_RESIDENT = "Residents list contains duplicate resident(s).";

    private final List<JsonAdaptedResident> residents = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given residents.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("residents") List<JsonAdaptedResident> residents) {
        this.residents.addAll(residents);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        residents.addAll(source.getResidentList().stream().map(JsonAdaptedResident::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedResident jsonAdaptedResident : residents) {
            Resident resident = jsonAdaptedResident.toModelType();
            if (addressBook.hasResident(resident)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_RESIDENT);
            }
            addressBook.addResident(resident);
        }
        return addressBook;
    }

}
