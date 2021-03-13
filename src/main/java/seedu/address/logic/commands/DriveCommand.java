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
import seedu.address.model.human.Name;
import seedu.address.model.human.Phone;
import seedu.address.model.human.driver.Driver;
import seedu.address.model.human.person.Address;
import seedu.address.model.human.person.Person;
import seedu.address.model.human.person.TripDay;
import seedu.address.model.human.person.TripTime;
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
     * Creates an AddCommand to add the specified {@code Person}
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

        List<Person> lastShownList = model.getFilteredPersonList();

        for (Index idx : persons) {
            if (idx.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
        }

        for (Index idx : persons) {
            Person personToEdit = lastShownList.get(idx.getZeroBased());
            Person editedPerson = assignDriverToPerson(personToEdit, driver);
            joiner.add(editedPerson.getName().toString());
            model.setPerson(personToEdit, editedPerson);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_DRIVE_SUCCESS, driver.toString(), joiner.toString()));
    }

    /**
     * Assigns the given {@code Driver} to the given {@code Person}.
     * @param personToEdit the {@code Person} to add the {@code Driver} to.
     * @param driver the {@code Driver} to add to the {@code Person}.
     * @return a new {@code Person}, with the given driver assigned.
     */
    private static Person assignDriverToPerson(Person personToEdit, Driver driver) {
        requireNonNull(personToEdit);
        requireNonNull(driver);

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Address updatedAddress = personToEdit.getAddress();
        Set<Tag> updatedTags = personToEdit.getTags();
        TripDay updatedTripDay = personToEdit.getTripDay();
        TripTime updatedTripTime = personToEdit.getTripTime();

        return new Person(updatedName, updatedPhone, updatedAddress, updatedTripDay, updatedTripTime, driver,
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
