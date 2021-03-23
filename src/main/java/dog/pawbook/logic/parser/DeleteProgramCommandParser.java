package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import dog.pawbook.commons.core.index.Index;
import dog.pawbook.logic.commands.DeleteProgramCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteProgramCommand object
 */
public class DeleteProgramCommandParser extends DeleteCommandParser<DeleteProgramCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteProgramCommand parse(String args) throws ParseException {
        try {
            Index index = extractIndex(args);
            return new DeleteProgramCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(getUsageText(), pe);
        }
    }

    @Override
    protected String getUsageText() {
        return String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProgramCommand.MESSAGE_USAGE);
    }

}
