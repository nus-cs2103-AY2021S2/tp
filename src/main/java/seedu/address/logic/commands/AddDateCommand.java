package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Event;
import seedu.address.model.person.Person;

/**
 * Adds a special date to an existing person in the FriendDex.
 */
public class AddDateCommand extends Command {

    public static final String COMMAND_WORD = "add-date";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Save a special date for the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DATE + "DATE "
            + PREFIX_DESCRIPTION + "DESCRIPTION \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "12-12-2021 "
            + PREFIX_DESCRIPTION + "Wedding Anniversary";

    public static final String MESSAGE_ADD_DATE_SUCCESS = "Added date for %1$s";

    private final Index index;
    private final Event event;

    /**
     * @param index of the person in the filtered person list to add date to
     * @param event details of the date to add
     */
    public AddDateCommand(Index index, Event event) {
        requireAllNonNull(index, event);

        this.index = index;
        this.event = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        List<Event> datesToEdit = new ArrayList<>(personToEdit.getDates());
        datesToEdit.add(event);

        Person editedPerson = personToEdit.withDates(datesToEdit);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateUpcomingDates();

        return new CommandResult(String.format(MESSAGE_ADD_DATE_SUCCESS, editedPerson.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddDateCommand)) {
            return false;
        }

        AddDateCommand e = (AddDateCommand) other;
        return index.equals(e.index) && event.equals(e.event);
    }
}
