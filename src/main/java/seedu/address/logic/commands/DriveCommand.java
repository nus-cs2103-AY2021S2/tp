package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMUTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PASSENGERS;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.driver.Driver;
import seedu.address.model.person.passenger.Address;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.person.passenger.Price;
import seedu.address.model.person.passenger.TripDay;
import seedu.address.model.person.passenger.TripTime;
import seedu.address.model.tag.Tag;

/**
 * Associates a Driver with the selected Passengers.
 */
public class DriveCommand extends Command {
    public static final String COMMAND_WORD = "drive";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Associates a Driver with the selected Commuters. "
            + "Parameters: "
            + PREFIX_NAME + "DRIVER NAME "
            + PREFIX_PHONE + "DRIVER PHONE "
            + PREFIX_COMMUTER + "COMMUTER "
            + "[" + PREFIX_COMMUTER + "COMMUTER]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_COMMUTER + "1 "
            + PREFIX_COMMUTER + "4 ";

    public static final String MESSAGE_NO_COMMUTERS = "No commuters were selected.";
    public static final String MESSAGE_DRIVE_SUCCESS = "Assigned %s to: %s";

    private final Driver driver;
    private final Set<Index> passengers;

    //todo remove STUD_VALID_PRICE declaration
    private static final Price STUD_VALID_PRICE = new Price("1.69");

    /**
     * Creates an AddCommand to add the specified {@code Passenger}
     */
    public DriveCommand(Driver driver, Set<Index> passengers) {
        requireNonNull(driver);
        requireNonNull(passengers);
        this.driver = driver;
        this.passengers = passengers;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (passengers.size() == 0) {
            throw new CommandException(MESSAGE_NO_COMMUTERS);
        }
        StringJoiner joiner = new StringJoiner(", ");

        // Freeze the list so we we don't have to manage the model filtering the passengers
        List<Passenger> lastShownList = List.copyOf(model.getFilteredPassengerListByDriverStatus(false));

        for (Index idx : passengers) {
            if (idx.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);
            }
        }

        for (Index idx : passengers) {
            Passenger passengerToEdit = lastShownList.get(idx.getZeroBased());
            Passenger editedPassenger = assignDriverToPassenger(passengerToEdit, driver);
            joiner.add(editedPassenger.getName().toString());
            model.setPassenger(passengerToEdit, editedPassenger);
        }

        model.updateFilteredPassengerList(PREDICATE_SHOW_ALL_PASSENGERS);

        return new CommandResult(String.format(MESSAGE_DRIVE_SUCCESS, driver.toString(), joiner.toString()));
    }

    /**
     * Assigns the given {@code Driver} to the given {@code Passenger}.
     * @param passengerToEdit the {@code Passenger} to add the {@code Driver} to.
     * @param driver the {@code Driver} to add to the {@code Passenger}.
     * @return a new {@code Passenger}, with the given driver assigned.
     */
    private static Passenger assignDriverToPassenger(Passenger passengerToEdit, Driver driver) {
        requireNonNull(passengerToEdit);
        requireNonNull(driver);

        Name updatedName = passengerToEdit.getName();
        Phone updatedPhone = passengerToEdit.getPhone();
        Address updatedAddress = passengerToEdit.getAddress();
        Set<Tag> updatedTags = passengerToEdit.getTags();
        TripDay updatedTripDay = passengerToEdit.getTripDay();
        TripTime updatedTripTime = passengerToEdit.getTripTime();

        //todo remove price stud usage
        Price price = STUD_VALID_PRICE;

        return new Passenger(updatedName, updatedPhone, updatedAddress, updatedTripDay, updatedTripTime, price, driver,
                updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DriveCommand // instanceof handles nulls
                && (driver.equals(((DriveCommand) other).driver)
                && passengers.equals(((DriveCommand) other).passengers)));
    }
}
