package seedu.address.logic.parser.meetings;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meetings.ShowMeetingCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ShowMeetingCommand object
 */
public class ShowMeetingCommandParser implements Parser<ShowMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ShowMeetingCommand
     * and returns a ShowMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowMeetingCommand parse(String args) throws ParseException {
        Index index;
        try {
            requireNonNull(args);
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ShowMeetingCommand.MESSAGE_USAGE), pe);
        }

        return new ShowMeetingCommand(index);
    }
}
