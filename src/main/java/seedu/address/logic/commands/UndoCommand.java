package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskTracker;

/**
 * Undoes the last command and reverts to the previous TaskTracker state
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undoes the last command";

    public static final String MESSAGE_SUCCESS = "Last command has been successfully undone!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canUndoTaskTracker()) {
            throw new CommandException(Messages.MESSAGE_INVALID_UNDO_COMMAND);
        }

        TaskTracker undoneTaskTracker = model.undoTaskTracker();
        model.setTaskTracker((undoneTaskTracker));

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public String toString() {
        return "UNDO";
    }
}
