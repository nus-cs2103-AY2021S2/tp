package fooddiary.logic.parser;

import static fooddiary.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import fooddiary.commons.core.Messages;
import fooddiary.commons.core.index.Index;
import fooddiary.logic.commands.ReviseCommand;
import fooddiary.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ReviseCommand object
 */
public class ReviseCommandParser implements Parser<ReviseCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ReviseCommand
     * and returns a ReviseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReviseCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ReviseCommand(index);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReviseCommand.MESSAGE_USAGE), pe);
        }
    }
}
