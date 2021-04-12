package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DoneCommand object.
 */
public class DoneCommandParser implements Parser<DoneCommand> {
    public static final String MESSAGE_DONE_FORMAT = "Invalid command format! Note that index must be positive and "
            + "within the range of the displayed list. There should also be no additional parameters.\n\n"
            + DoneCommand.MESSAGE_USAGE;

    /**
     * Parses the given {@code String} of arguments in the context of the DoneCommand
     * and returns a DoneCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DoneCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DoneCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_DONE_FORMAT);
        }
    }

}
