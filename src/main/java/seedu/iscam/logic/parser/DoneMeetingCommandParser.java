package seedu.iscam.logic.parser;

import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.DoneMeetingCommand;
import seedu.iscam.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DoneMeetingCommand object.
 */
public class DoneMeetingCommandParser implements Parser<DoneMeetingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DoneMeetingCommand
     * and returns a DoneMeetingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public DoneMeetingCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DoneMeetingCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneMeetingCommand.MESSAGE_USAGE));
        }
    }
}
