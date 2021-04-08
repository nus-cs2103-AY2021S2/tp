package seedu.iscam.logic.events;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.EditCommand;
import seedu.iscam.logic.commands.EditCommand.EditClientDescriptor;
import seedu.iscam.logic.events.exceptions.EventException;
import seedu.iscam.model.Model;

/**
 * Class to create corresponding edit Events according to the edit Commands input by user.
 */
public class EditEventFactory {
    public static final String MESSAGE_NOT_UNDOABLE = "The following command \'%1$s\' \'%2$s\' is not undoable";

    /**
     * A static method to generate the edit events based on the edit commands parsed.
     *
     * @param command Edit Command to be parsed.
     * @param model   Current model of the application.
     * @return Corresponding event representing the edit command parsed.
     * @throws EventException
     */
    public static Event parse(EditCommand command, Model model) throws EventException {
        String commandWord = command.getCommandWord();

        switch (commandWord) {
        case (EditCommand.COMMAND_WORD):
            EditCommand tempCommand = (EditCommand) command;
            return generateEditClientEvent(tempCommand.getIndex(),
                    tempCommand.getEditClientDescriptor(), model);

        /*
        case (EditMeeting.COMMAND_WORD):
            EditMeeting tempCommand = (EditMeeting) command;
            return generateEditMeetingEvent(tempCommand.getIndex(),
                tempCommand.getEditMeetingDescriptor(), model);
         */
        default:
            throw new EventException(
                    String.format(MESSAGE_NOT_UNDOABLE, command.getCommandWord())
            );
        }
    }

    public static EditClientEvent generateEditClientEvent(
            Index index, EditClientDescriptor editInfo, Model model) {
        return new EditClientEvent(index, editInfo, model);
    }

    /*
    public static EditMeetingEvent generateEditMeetingEvent() {
        return ;
    }
    */
}
