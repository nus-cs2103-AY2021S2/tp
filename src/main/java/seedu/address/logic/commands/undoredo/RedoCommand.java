// Solution below adapted from AB-4: https://github.com/se-edu/addressbook-level4
package seedu.address.logic.commands.undoredo;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.undoredo.exceptions.RedoException;

/**
 * Undoes the most recent undo, if any.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_SUCCESS = "Redo successful.";

    /**
     * Constructs a new {@code RedoCommand}.
     */
    public RedoCommand() {
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof RedoCommand; // instanceof handles nulls
    }

    /**
     * Undoes the most recent undo command, if any.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A {@code CommandResult} with a success message.
     * @throws CommandException If the redo fails for whatever reason (e.g. no next state).
     * @throws NullPointerException If model is null.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.redoAddressBook();
            model.updateFilteredIssueList(Model.PREDICATE_SHOW_ALL_ISSUES);
            model.updateFilteredResidentList(Model.PREDICATE_SHOW_ALL_RESIDENTS);
            model.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);
        } catch (RedoException ex) {
            // We let the redo operation decide what the exact error is.
            throw new CommandException(ex.getMessage());
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
