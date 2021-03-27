package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskTracker;

/**
 * Redoes the last non-undo Command
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redoes the changes from an undo command";

    public static final String MESSAGE_SUCCESS = "Last command has been successfully redone!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoTaskTracker()) {
            throw new CommandException(Messages.MESSAGE_INVALID_REDO_COMMAND);
        }

        TaskTracker redoneTaskTracker = model.redoTaskTracker();
        model.setTaskTracker((redoneTaskTracker));

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public String toString() {
        return "REDO";
    }
}
