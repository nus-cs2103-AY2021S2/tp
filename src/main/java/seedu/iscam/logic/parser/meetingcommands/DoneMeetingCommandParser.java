package seedu.iscam.logic.parser.meetingcommands;

import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.DoneMeetingCommand;
import seedu.iscam.logic.parser.Parser;
import seedu.iscam.logic.parser.ParserUtil;
import seedu.iscam.logic.parser.exceptions.ParseException;
import seedu.iscam.logic.parser.exceptions.ParseFormatException;
import seedu.iscam.logic.parser.exceptions.ParseIndexException;

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
        } catch (ParseIndexException pie) {
            throw pie;
        } catch (ParseException pe) {
            throw new ParseFormatException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DoneMeetingCommand.MESSAGE_USAGE));
        }
    }
}
