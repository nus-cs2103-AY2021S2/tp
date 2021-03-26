package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PASSENGERS;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.Address;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.person.passenger.Price;
import seedu.address.model.pool.TripDay;
import seedu.address.model.pool.TripTime;
import seedu.address.model.tag.Tag;

/**
 * Dissociates Passengers from the specified driver.
 */
public class UnpoolCommand extends Command {
    public static final String COMMAND_WORD = "unpool";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes specified passengers from driver's carpool."
            + "Parameters: "
            + PREFIX_NAME + "DRIVER NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe ";

    public static final String MESSAGE_NO_DRIVER = "No driver has been specified.";
    public static final String MESSAGE_DRIVER_NOT_EXIST = "There is no such driver that has carpooled passengers.";
    public static final String MESSAGE_UNPOOL_SUCCESS = "%s successfully removed: %s from carpool";

    private final Driver driver;

    /**
     * Creates an UnpoolCommand to add the specified {@code Passenger}
     */
    public UnpoolCommand(Driver driver) {
        requireNonNull(driver);
        this.driver = driver;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (driver == null) {
            throw new CommandException(MESSAGE_NO_DRIVER);
        }

        // Freeze the list so we don't have to manage the model filtering the passengers
        List<Passenger> listWithDriverSpecified = List.copyOf(model.getFilteredPassengerListByDriver(driver));

        if (listWithDriverSpecified.isEmpty()) {
            throw new CommandException(MESSAGE_DRIVER_NOT_EXIST);
        }

        StringJoiner joiner = new StringJoiner(", ");

        for (Passenger passengerToEdit : listWithDriverSpecified) {
            Passenger editedPassenger = unassignDriverFromPassenger(passengerToEdit);
            joiner.add(editedPassenger.getName().toString());
            model.setPassenger(passengerToEdit, editedPassenger);
        }

        model.updateFilteredPassengerList(PREDICATE_SHOW_ALL_PASSENGERS);

        return new CommandResult(String.format(MESSAGE_UNPOOL_SUCCESS, driver.toString(), joiner.toString()));
    }

    /**
     * Removes the given {@code Driver} from the given {@code Passenger}.
     * @param passengerToEdit the {@code Passenger} to be removed from the {@code Driver}.
     * @return a new {@code Passenger}, with the given driver removed.
     */
    private static Passenger unassignDriverFromPassenger(Passenger passengerToEdit) {
        requireNonNull(passengerToEdit);

        Name updatedName = passengerToEdit.getName();
        Phone updatedPhone = passengerToEdit.getPhone();
        Address updatedAddress = passengerToEdit.getAddress();
        Set<Tag> updatedTags = passengerToEdit.getTags();
        TripDay updatedTripDay = passengerToEdit.getTripDay();
        TripTime updatedTripTime = passengerToEdit.getTripTime();
        Optional<Price> price = passengerToEdit.getPrice();

        return new Passenger(updatedName, updatedPhone, updatedAddress, updatedTripDay, updatedTripTime, price,
                updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnpoolCommand // instanceof handles nulls
                && (driver.equals(((UnpoolCommand) other).driver)));
    }
}
