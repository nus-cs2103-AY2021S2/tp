package seedu.dictionote.logic.parser;

import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.DeleteContactCommand;
import seedu.dictionote.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteContactCommandParser implements Parser<DeleteContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteContactCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteContactCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteContactCommand.MESSAGE_USAGE), pe);
        }
    }

}
