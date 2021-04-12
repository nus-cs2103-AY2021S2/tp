package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DetailCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DetailCommand object
 */
public class DetailCommandParser implements Parser<DetailCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DetailCommand
     * and returns a DetailCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DetailCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DetailCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(pe.getMessage(), DetailCommand.MESSAGE_USAGE), pe);
        }
    }

}
