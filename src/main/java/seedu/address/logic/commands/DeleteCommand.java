package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_PERSONS_SUCCESS = "Deleted People:\n - %1$s";

    private final List<Index> targetIndexList;

    /**
     * Takes a list of {@code Index} and constructs a DeleteCommand
     * @param targetIndexList a list of {@code Index}
     */
    public DeleteCommand(List<Index> targetIndexList) {
        this.targetIndexList = targetIndexList;
        assert targetIndexList.size() > 0 : "No indexes for DeleteCommand";
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        final StringBuilder outputString = new StringBuilder();
        List<Person> personToDeleteList = new ArrayList<>();

        for (Index targetIndex : targetIndexList) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            personToDeleteList.add(personToDelete);
        }

        for (Person personToDelete : personToDeleteList) {
            model.deletePerson(personToDelete);
            outputString.append(personToDelete);
            outputString.append("\n");
        }

        return new CommandResult(String.format(
                targetIndexList.size() == 1
                ? MESSAGE_DELETE_PERSON_SUCCESS
                : MESSAGE_DELETE_PERSONS_SUCCESS, outputString));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndexList.equals(((DeleteCommand) other).targetIndexList)); // state check
    }
}
