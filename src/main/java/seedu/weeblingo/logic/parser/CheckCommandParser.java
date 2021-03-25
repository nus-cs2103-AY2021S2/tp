package seedu.weeblingo.logic.parser;

import static seedu.weeblingo.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;


import seedu.weeblingo.logic.commands.CheckCommand;
import seedu.weeblingo.logic.parser.exceptions.ParseException;

public class CheckCommandParser implements Parser<CheckCommand> {

    public CheckCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCommand.MESSAGE_USAGE));
        }
        return new CheckCommand(trimmedArgs);
    }
}
