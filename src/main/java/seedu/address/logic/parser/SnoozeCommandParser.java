package seedu.address.logic.parser;

import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SnoozeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SnoozeCommand object.
 */
public class SnoozeCommandParser implements Parser<SnoozeCommand> {

    public static final String MESSAGE_INVALID_ARGUMENT = "Invalid command format!\n"
            + "Snooze Command must have a compulsory INDEX"
            + " and an optional DAYS argument, both of which are positive integers.";

    private static final String SNOOZE_COMMAND_REGEX = "[0-9]+\\s?[0-9]*";

    /**
     * Parses the given {@code String} of arguments in the context of the SnoozeCommand
     * and returns an SnoozeCommand object for execution.
     *
     * @param args the user's inputted arguments in string form.
     * @return SnoozeCommand object.
     * @throws ParseException
     */
    public SnoozeCommand parse(String args) throws ParseException {
        validateParameter(args);
        Index index = getIndex(args);
        int days = getDays(args);

        return new SnoozeCommand(index, days);
    }

    private void validateParameter(String parameter) throws ParseException {
        String trimmedArgs = parameter.trim();

        if (trimmedArgs == "" || !(Pattern.matches(SNOOZE_COMMAND_REGEX, trimmedArgs))) {
            throw new ParseException(MESSAGE_INVALID_ARGUMENT);
        }
    }

    private Index getIndex(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] argValues = trimmedArgs.split(" ");

        assert(argValues.length == 1 || argValues.length == 2);

        if (Integer.valueOf(argValues[0]) < 1) {
            throw new ParseException(MESSAGE_INVALID_ARGUMENT);
        }
        return ParserUtil.parseIndex(argValues[0]);
    }

    private int getDays(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] argValues = trimmedArgs.split(" ");

        if (argValues.length == 2) {
            if (Integer.valueOf(argValues[1]) < 1) {
                throw new ParseException(MESSAGE_INVALID_ARGUMENT);
            }
            return Integer.parseInt(argValues[1]);
        } else {
            return 1;
        }
    }
}
