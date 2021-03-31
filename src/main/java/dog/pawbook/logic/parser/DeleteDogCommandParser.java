package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import dog.pawbook.logic.commands.DeleteDogCommand;

/**
 * Parses input arguments and creates a new DeleteDogCommand object
 */
public class DeleteDogCommandParser extends DeleteCommandParser<DeleteDogCommand> {

    protected DeleteDogCommandParser() {
        super(DeleteDogCommand.class);
    }

    @Override
    protected String getUsageText() {
        return String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDogCommand.MESSAGE_USAGE);
    }

}
