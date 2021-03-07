package dog.pawbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.commons.core.index.Index;
import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.owner.Owner;

/**
 * Deletes a owner identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the owner identified by the index number used in the displayed owner list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_OWNER_SUCCESS = "Deleted Owner: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Owner> lastShownList = model.getFilteredOwnerList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_OWNER_DISPLAYED_INDEX);
        }

        Owner ownerToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteOwner(ownerToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_OWNER_SUCCESS, ownerToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
