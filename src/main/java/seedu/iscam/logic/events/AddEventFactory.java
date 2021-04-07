package seedu.iscam.logic.events;

import seedu.iscam.logic.commands.AddCommand;
import seedu.iscam.logic.events.exceptions.EventException;
import seedu.iscam.model.client.Client;

/**
 * Class to create corresponding add Events according to the add Commands input by user.
 */
public class AddEventFactory {
    public static final String MESSAGE_NOT_UNDOABLE = "The following command \'%1$s\' \'%2$s\' is not undoable";

    /**
     * A static method to generate the add events based on the add commands parsed.
     *
     * @param command Add Command to be parsed.
     * @return Corresponding event representing the add command parsed.
     * @throws EventException
     */
    public static Event parse(AddCommand command) throws EventException {
        String commandWord = command.getCommandWord();

        switch (commandWord) {
        case (AddCommand.COMMAND_WORD):
            AddCommand tempCommand = command;
            return generateAddClientEvent(tempCommand.getClient());

        /*
        case (AddMeetingCommand.COMMAND_WORD):
            AddMeetingCommand tempCommand = (AddMeetingCommand) command;
            return generateAddMeetingEvent(tempCommand.getToAdd());
        */

        default:
            throw new EventException(
                    String.format(MESSAGE_NOT_UNDOABLE, command.getCommandWord())
            );
        }
    }

    public static AddClientEvent generateAddClientEvent(Client clientAdded) {
        return new AddClientEvent(clientAdded);
    }

    /*
    public static AddMeetingEvent generateAddMeetingEvent(Meeting meetingAdded) {
        return new AddMeetingEvent(meetingAdded);
    }
    */

}
