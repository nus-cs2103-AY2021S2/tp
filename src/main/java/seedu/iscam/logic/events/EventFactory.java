package seedu.iscam.logic.events;

import static java.util.Objects.requireNonNull;

import seedu.iscam.logic.commands.AddCommand;
import seedu.iscam.logic.commands.DeleteCommand;
import seedu.iscam.logic.commands.EditCommand;
import seedu.iscam.logic.commands.UndoableCommand;
import seedu.iscam.logic.events.exceptions.EventException;
import seedu.iscam.model.Model;

/**
 * Class to create corresponding events that undo the current command from the user's input.
 */
public class EventFactory {
    private static final String MESSAGE_COMMAND_ERROR = "\'%1$s\' command is not undoable.";

    /**
     * A static method to parse the command and generate the corresponding event.
     *
     * @param command Command to be parsed.
     * @param model   Current model of the application.
     * @return Corresponding event for the command parsed.
     * @throws EventException
     */
    public static Event parse(UndoableCommand command, Model model) throws EventException {

        requireNonNull(command);
        requireNonNull(model);

        String commandWord = command.getCommandWord();

        switch (commandWord) {
        case (AddCommand.COMMAND_WORD):
            return AddEventFactory.parse((AddCommand) command);

        case (DeleteCommand.COMMAND_WORD):
            return DeleteEventFactory.parse((DeleteCommand) command, model);

        case (EditCommand.COMMAND_WORD):
            return EditEventFactory.parse((EditCommand) command, model);

        default:
            throw new EventException(String.format(MESSAGE_COMMAND_ERROR, commandWord));
        }
    }
}
