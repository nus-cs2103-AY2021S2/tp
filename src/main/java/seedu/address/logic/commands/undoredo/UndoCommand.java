// Solution below adapted from AB-4: https://github.com/se-edu/addressbook-level4
package seedu.address.logic.commands.undoredo;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.undoredo.exceptions.UndoException;

/**
 * Undoes the most recent state-changing command.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Undo successful.";

    /**
     * Constructs a new {@code UndoCommand}.
     */
    public UndoCommand() {
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof UndoCommand; // instanceof handles nulls
    }

    /**
     * Undoes the most recent state-changing command, if any.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A {@code CommandResult} with a success message.
     * @throws CommandException If the undo fails for whatever reason (e.g. no previous state).
     * @throws NullPointerException If model is null.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.undoAddressBook();
            model.updateFilteredIssueList(Model.PREDICATE_SHOW_ALL_ISSUES);
            model.updateFilteredResidentList(Model.PREDICATE_SHOW_ALL_RESIDENTS);
            model.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);
        } catch (UndoException ex) {
            // We let the undo operation decide what the exact error is.
            throw new CommandException(ex.getMessage());
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
