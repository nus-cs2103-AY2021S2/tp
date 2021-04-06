package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.SpecialDate;

/**
 * Deletes an existing special date from an existing person in the FriendDex.
 */
public class DeleteDateCommand extends Command {

    public static final String COMMAND_WORD = "del-date";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the special date identified "
            + "by the date index number used, for the person identified by the index number used.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_INDEX + "DATE_INDEX\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INDEX + "1";

    public static final String MESSAGE_DELETE_DATE_SUCCESS = "Deleted date for %1$s";

    private final Index index;
    private final Index dateIndex;

    /**
     * @param index     of the person in the filtered person list to delete date from
     * @param dateIndex of the date to be deleted
     */
    public DeleteDateCommand(Index index, Index dateIndex) {
        requireAllNonNull(index, dateIndex);

        this.index = index;
        this.dateIndex = dateIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        List<SpecialDate> dates = new ArrayList<>(personToEdit.getDates());

        if (dates.size() == 0) {
            throw new CommandException(String.format(Messages.MESSAGE_NO_DATES, personToEdit.getName()));
        }

        if (dateIndex.getZeroBased() >= dates.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX_ARGUMENT);
        }
        dates.remove(dateIndex.getZeroBased());

        Person editedPerson = personToEdit.withDates(dates);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateUpcomingDates();

        return new CommandResult(String.format(MESSAGE_DELETE_DATE_SUCCESS, editedPerson.getName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteDateCommand)) {
            return false;
        }

        DeleteDateCommand e = (DeleteDateCommand) other;
        return index.equals(e.index) && dateIndex.equals(e.dateIndex);
    }
}
