package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes all contacts within the specified index range (inclusive).
 */
public class MassDeleteCommand extends Command {

    public static final String COMMAND_WORD = "massdelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all contacts within the specified index range (inclusive).\n"
            + "Parameters: START-END (Both must be positive integers) \n"
            + "Example: " + COMMAND_WORD + " 1-37";

    public static final String MESSAGE_MASS_DELETE_PERSON_SUCCESS = "Successfully deleted all "
            + "contacts within the index range %1$d-%2$d";

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
        if (!Index.isValidIndexRange(startIndex, endIndex)) {
            throw new CommandException(Messages.MESSAGE_INVALID_START_INDEX);
        }
        if (endIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_END_INDEX);
        }
        model.massDelete(startIndex.getOneBased(), endIndex.getOneBased());
        String outputMessage = String.format(MESSAGE_MASS_DELETE_PERSON_SUCCESS,
                startIndex.getOneBased(), endIndex.getOneBased());
        return new CommandResult(outputMessage);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        }
        if (other instanceof MassDeleteCommand) {
            return startIndex.equals(((MassDeleteCommand) other).startIndex)
                    && endIndex.equals(((MassDeleteCommand) other).endIndex);
        }
        return false;
    }
}
