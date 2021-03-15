package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.us.among.commons.core.index.Index;
import seedu.us.among.logic.commands.SendCommand;
import seedu.us.among.logic.parser.exceptions.ParseException;

public class SendCommandParser implements Parser<SendCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SendCommand
     * and returns a SendCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SendCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SendCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SendCommand.MESSAGE_USAGE), pe);
        }
    }
}
