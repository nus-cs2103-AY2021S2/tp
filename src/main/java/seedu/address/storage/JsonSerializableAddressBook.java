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
import seedu.address.model.person.passenger.Passenger;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PASSENGER = "Passengers list contains duplicate passenger(s).";

    private final List<JsonAdaptedPassenger> passengers = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given passengers.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("passengers") List<JsonAdaptedPassenger> passengers) {
        this.passengers.addAll(passengers);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        passengers.addAll(source.getPassengerList().stream().map(JsonAdaptedPassenger::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPassenger jsonAdaptedPassenger : passengers) {
            Passenger passenger = jsonAdaptedPassenger.toModelType();
            if (addressBook.hasPassenger(passenger)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PASSENGER);
            }
            addressBook.addPassenger(passenger);
        }
        return addressBook;
    }

}
