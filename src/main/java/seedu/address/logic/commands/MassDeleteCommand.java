package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.index.Index.getInterval;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes all contacts within a specified index range(inclusive).
 */
public class MassDeleteCommand extends Command {

    public static final String COMMAND_WORD = "massdelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all contacts within a specified index range(inclusive).\n"
            + "Parameters: "
            + PREFIX_START_INDEX + "STARTINDEX "
            + PREFIX_END_INDEX + "ENDINDEX\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_START_INDEX + "1 " + PREFIX_END_INDEX + "21";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Successfully deleted all contacts"
            + " within the specified range";
    public static final String MESSAGE_INVALID_START_INDEX = "Start index must be positive";
    public static final String MESSAGE_INVALID_END_INDEX = "End index must be larger than start index but "
            + "smaller than the number of people in the contact list.";

    private final Index startIndex;
    private final Index endIndex;

    /**
     * Creates a MassDeleteCommand to delete all contacts within the specified index range.
     */
    public MassDeleteCommand(Index startIndex, Index endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (endIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_END_INDEX);
        }
        int intervalToDelete = getInterval(startIndex, endIndex);
        for (int i = 0; i <= intervalToDelete; i++) {
            Person personToDelete = lastShownList.get(startIndex.getZeroBased());
            model.deletePerson(personToDelete);
        }
        return new CommandResult(MESSAGE_DELETE_PERSON_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        }
        if (other instanceof MassDeleteCommand) {
            return startIndex.equals(((MassDeleteCommand) other).startIndex)
                    && endIndex.equals(((MassDeleteCommand) other).endIndex);
        } else {
            return false;
        }
    }
}
