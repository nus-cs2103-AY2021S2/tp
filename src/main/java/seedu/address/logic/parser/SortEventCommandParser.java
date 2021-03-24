package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SortEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SortEventCommandParser implements Parser<SortEventCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortTaskCommand
     * and returns a SortTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortEventCommand parse(String args) throws ParseException {
        try {
            String comparingVar = SocheduleParserUtil.parseEventComparingVar(args);
            return new SortEventCommand(comparingVar);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortEventCommand.MESSAGE_USAGE), pe);
        }
    }
}
