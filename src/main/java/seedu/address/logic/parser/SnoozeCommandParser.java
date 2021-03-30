package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DoneCommand;
import seedu.address.logic.commands.SnoozeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SnoozeCommand object.
 */
public class SnoozeCommandParser implements Parser<SnoozeCommand> {

    private static final String MESSAGE_INVALID_ARGUMENT = "Snooze Command can only have a compulsory INDEX"
            + " and an optional NUMBER argument, both of which are positive integers.";

    public SnoozeCommand parse(String args) throws ParseException {
        try {
            String trimmedArgs = args.trim();
            if (!(Pattern.matches("[0-9]+\\s?[0-9]*", trimmedArgs))) {
                throw new ParseException(MESSAGE_INVALID_ARGUMENT);
            }
            String[] argValues = trimmedArgs.split(" ");

            assert(argValues.length == 1 || argValues.length == 2);

            Index index = ParserUtil.parseIndex(argValues[0]);
            int days = 0;
            if (argValues.length == 2) {
                days = Integer.parseInt(argValues[1]);
            } else {
                days = 1;
            }

            return new SnoozeCommand(index, days);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SnoozeCommand.MESSAGE_USAGE), pe);
        }
    }
}
