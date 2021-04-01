package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.iscam.logic.CommandHistory;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.logic.events.Event;
import seedu.iscam.model.Model;

/**
 * Redo the most recent undone command.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public static final String SUCCESS_MESSAGE = "Redo successful!";
    public static final String FAILURE_MESSAGE = "No commands to redo.";

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);

        if (CommandHistory.isEmptyRedoStack()) {
            throw new CommandException(FAILURE_MESSAGE);
        }

        Event redoableEvent = CommandHistory.getRedoEvent();
        CommandHistory.addToUndoStack(redoableEvent);
        UndoableCommand commandToUndo = redoableEvent.redo();
        return commandToUndo.execute(model);
    }
}