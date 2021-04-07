package seedu.iscam.logic.events;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.DeleteCommand;
import seedu.iscam.logic.events.exceptions.EventException;
import seedu.iscam.model.Model;

/**
 * Class to create corresponding delete Events according to the delete Commands input by user.
 */
public class DeleteEventFactory {
    public static final String MESSAGE_NOT_UNDOABLE = "The following command \'%1$s\' \'%2$s\' is not undoable";

    /**
     * A static method to generate the delete events based on the delete commands parsed.
     *
     * @param command Delete Command to be parsed.
     * @return Corresponding event representing the delete command parsed.
     * @throws EventException
     */
    public static Event parse(DeleteCommand command, Model model) throws EventException {
        String commandWord = command.getCommandWord();

        switch (commandWord) {
        case (DeleteCommand.COMMAND_WORD):
            DeleteCommand tempCommand = command;
            return generateDeleteClientEvent(tempCommand.getTargetIndex(model), model);

        /*
        case (DeleteMeetingCommand.COMMAND_WORD):
            DeleteMeetingCommand tempCommand = (DeleteMeetingCommand) command;
            return generateDeleteMeetingEvent(tempCommand.getTargetIndex(), model);
        */

        default:
            throw new EventException(
                    String.format(MESSAGE_NOT_UNDOABLE, command.getCommandWord())
            );
        }
    }

    public static DeleteClientEvent generateDeleteClientEvent(Index index, Model model) {
        return new DeleteClientEvent(index, model);
    }

    /*
    public static DeleteMeetingEvent generateDeleteMeetingEvent(Index index, Model model) {
        return new DeleteMeetingEvent(index, model);
    }
    */
}
