package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DetailsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class DetailsCommandParser implements Parser<DetailsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DetailsCommand
     * and returns a DetailsCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DetailsCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DetailsCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DetailsCommand.MESSAGE_USAGE), pe);
        }
    }
}
