package seedu.iscam.logic.parser.meetingcommands;

import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.logic.commands.DeleteCommand;
import seedu.iscam.logic.commands.DeleteMeetingCommand;
import seedu.iscam.logic.parser.Parser;
import seedu.iscam.logic.parser.ParserUtil;
import seedu.iscam.logic.parser.exceptions.ParseException;
import seedu.iscam.logic.parser.exceptions.ParseFormatException;
import seedu.iscam.logic.parser.exceptions.ParseIndexException;

public class DeleteMeetingCommandParser implements Parser<DeleteMeetingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteMeetingCommand
     * and returns a DeleteMeetingCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteMeetingCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteMeetingCommand(index);
        } catch (ParseIndexException pie) {
            throw pie;
        } catch (ParseException pe) {
            throw new ParseFormatException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            DeleteMeetingCommand.MESSAGE_USAGE), pe);
        }
    }
}
