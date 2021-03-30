package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearAssigneesCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClearAssigneesCommand object
 */
public class ClearAssigneesCommandParser implements Parser<ClearAssigneesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearAssigneesCommand
     * and returns a ClearAssigneesCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearAssigneesCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ClearAssigneesCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearAssigneesCommand.MESSAGE_USAGE), pe);
        }
    }
}
