package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import dog.pawbook.logic.commands.DeleteOwnerCommand;

/**
 * Parses input arguments and creates a new DeleteOwnerCommand object
 */
public class DeleteOwnerCommandParser extends DeleteCommandParser<DeleteOwnerCommand> {

    public DeleteOwnerCommandParser() {
        super(DeleteOwnerCommand.class);
    }

    @Override
    protected String getUsageText() {
        return String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOwnerCommand.MESSAGE_USAGE);
    }

}
