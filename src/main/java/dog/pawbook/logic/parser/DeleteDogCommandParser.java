package dog.pawbook.logic.parser;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import dog.pawbook.commons.core.index.Index;
import dog.pawbook.logic.commands.DeleteDogCommand;
import dog.pawbook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteDogCommand object
 */
public class DeleteDogCommandParser extends DeleteCommandParser<DeleteDogCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteDogCommand parse(String args) throws ParseException {
        assert(!args.isEmpty());
        try {
            Index index = extractIndex(args);
            return new DeleteDogCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(getUsageText(), pe);
        }
    }

    @Override
    protected String getUsageText() {
        return String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteDogCommand.MESSAGE_USAGE);
    }

}
