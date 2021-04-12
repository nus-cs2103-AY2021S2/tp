package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteDateCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteDateCommand object
 */
public class DeleteDateCommandParser implements Parser<DeleteDateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteDateCommand
     * and returns a DeleteDateCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DeleteDateCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteDateCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(pe.getMessage(), DeleteDateCommand.MESSAGE_USAGE), pe);
        }
    }
}
