package seedu.iscam.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.iscam.logic.CommandHistory;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.logic.events.Event;
import seedu.iscam.model.Model;

/**
 * Undo the most recent Undoable command.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Undo successful!";
    public static final String MESSAGE_FAILURE = "No commands to undo.";

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);

        if (CommandHistory.isEmptyUndoStack()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        Event undoableEvent = CommandHistory.getUndoEvent();
        CommandHistory.addToRedoStack(undoableEvent);
        UndoableCommand commandToUndo = undoableEvent.undo();
        return commandToUndo.execute(model);
    }
}
