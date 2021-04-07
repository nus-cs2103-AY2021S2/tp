package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UndoneTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UndoneTaskCommand object.
 */
public class UndoneTaskCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the DoneTaskCommand
     * and returns a DoneTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UndoneTaskCommand parse(String args) throws ParseException {
        try {
            Index index = SocheduleParserUtil.parseIndex(args);
            return new UndoneTaskCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(pe.getMessage() + "%1$s", UndoneTaskCommand.MESSAGE_USAGE), pe);
        }
    }
}
