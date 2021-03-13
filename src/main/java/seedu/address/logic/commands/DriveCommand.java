package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMUTER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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
import seedu.address.model.person.passenger.TripDay;
import seedu.address.model.person.passenger.TripTime;
import seedu.address.model.tag.Tag;

/**
 * Associates a Driver with the selected Persons
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
    private final Set<Index> persons;

    /**
     * Creates an AddCommand to add the specified {@code Passenger}
     */
    public DriveCommand(Driver driver, Set<Index> persons) {
        requireNonNull(driver);
        requireNonNull(persons);
        this.driver = driver;
        this.persons = persons;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (persons.size() == 0) {
            throw new CommandException(MESSAGE_NO_COMMUTERS);
        }
        StringJoiner joiner = new StringJoiner(", ");

        List<Passenger> lastShownList = model.getFilteredPersonList();

        for (Index idx : persons) {
            if (idx.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }

        for (Index idx : persons) {
            Passenger personToEdit = lastShownList.get(idx.getZeroBased());
            Passenger editedPerson = assignDriverToPerson(personToEdit, driver);
            joiner.add(editedPerson.getName().toString());
            model.setPerson(personToEdit, editedPerson);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_DRIVE_SUCCESS, driver.toString(), joiner.toString()));
    }

    /**
     * Assigns the given {@code Driver} to the given {@code Passenger}.
     * @param personToEdit the {@code Passenger} to add the {@code Driver} to.
     * @param driver the {@code Driver} to add to the {@code Passenger}.
     * @return a new {@code Passenger}, with the given driver assigned.
     */
    private static Passenger assignDriverToPerson(Passenger personToEdit, Driver driver) {
        requireNonNull(personToEdit);
        requireNonNull(driver);

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Address updatedAddress = personToEdit.getAddress();
        Set<Tag> updatedTags = personToEdit.getTags();
        TripDay updatedTripDay = personToEdit.getTripDay();
        TripTime updatedTripTime = personToEdit.getTripTime();

        return new Passenger(updatedName, updatedPhone, updatedAddress, updatedTripDay, updatedTripTime, driver,
                updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DriveCommand // instanceof handles nulls
                && (driver.equals(((DriveCommand) other).driver)
                && persons.equals(((DriveCommand) other).persons)));
    }
}
