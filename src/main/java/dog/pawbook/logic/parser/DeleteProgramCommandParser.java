package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import dog.pawbook.logic.commands.DeleteProgramCommand;

/**
 * Parses input arguments and creates a new DeleteProgramCommand object
 */
public class DeleteProgramCommandParser extends DeleteCommandParser<DeleteProgramCommand> {

    public DeleteProgramCommandParser() {
        super(DeleteProgramCommand.class);
    }

    @Override
    protected String getUsageText() {
        return String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProgramCommand.MESSAGE_USAGE);
    }

}
