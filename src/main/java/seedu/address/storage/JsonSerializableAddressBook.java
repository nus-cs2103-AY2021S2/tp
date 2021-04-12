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
import seedu.address.model.pool.Pool;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PASSENGER = "Passengers list contains duplicate passenger(s).";
    public static final String MESSAGE_DUPLICATE_POOL = "Pool list contains duplicate pool(s).";
    public static final String MESSAGE_DUPLICATE_PASSENGER_REF = "Two or more Pool(s) reference the same passenger.";
    public static final String MESSAGE_POOL_PASSENGER_INVALID = "Pool(s) contain passenger(s) not in passenger list.";

    private final List<JsonAdaptedPassenger> passengers = new ArrayList<>();
    private final List<JsonAdaptedPool> pools = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given passengers.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("passengers") List<JsonAdaptedPassenger> passengers,
                                       @JsonProperty("pools") List<JsonAdaptedPool> pools) {
        this.passengers.addAll(passengers);
        this.pools.addAll(pools);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        this.passengers.addAll(source.getPassengerList().stream().map(JsonAdaptedPassenger::new)
                .collect(Collectors.toList()));
        this.pools.addAll(source.getPoolList().stream().map(JsonAdaptedPool::new)
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
        for (JsonAdaptedPool jsonAdaptedPool : pools) {
            Pool pool = jsonAdaptedPool.toModelType();
            if (addressBook.hasPool(pool)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_POOL);
            }
            if (pool.getPassengers().stream().anyMatch(addressBook::hasPoolWithPassenger)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PASSENGER_REF);
            }
            if (pool.getPassengers().stream().anyMatch(passenger -> !addressBook.hasEqualPassenger(passenger))) {
                throw new IllegalValueException(MESSAGE_POOL_PASSENGER_INVALID);
            }
            addressBook.addPool(pool);
        }

        return addressBook;
    }

}
