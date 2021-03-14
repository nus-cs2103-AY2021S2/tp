package seedu.iScam.logic.parser;

import static seedu.iScam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.iScam.commons.core.index.Index;
import seedu.iScam.logic.commands.DeleteCommand;
import seedu.iScam.logic.commands.DeleteMeetingCommand;
import seedu.iScam.logic.parser.exceptions.ParseException;

public class DeleteMeetingCommandParser implements Parser<DeleteMeetingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteMeetingCommand
     * and returns a DeleteMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteMeetingCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteMeetingCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
