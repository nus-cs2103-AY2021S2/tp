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
            + " and an optional DAYS argument, both of which are positive integers.\n"
            + "The INDEX and DAYS values should have a single whitespace separating the values.";

    public static final String MESSAGE_NUMBER_OUT_OF_BOUND = String.format("INDEX must be positive and within the "
            + "range of the list. DAYS must be positive and less than %d.", SnoozeCommand.MAX_SNOOZE_AMOUNT);

    private static final String SNOOZE_COMMAND_REGEX = "[0-9]+\\s?[0-9]*";

    private String[] argValues;

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
        processArguments(args);

        Index index = getIndex();
        int days = getDays();
        return new SnoozeCommand(index, days);
    }

    private void validateParameter(String parameter) throws ParseException {
        String trimmedArgs = parameter.trim();

        if (trimmedArgs == "" || !(Pattern.matches(SNOOZE_COMMAND_REGEX, trimmedArgs))) {
            throw new ParseException(MESSAGE_INVALID_ARGUMENT);
        }
    }

    private void processArguments(String args) {
        String trimmedArgs = args.trim();
        argValues = trimmedArgs.split(" ");
    }

    private Index getIndex() throws ParseException {
        assert(argValues.length == 1 || argValues.length == 2);

        try {
            if (Integer.valueOf(argValues[0]) < 1) {
                throw new ParseException(MESSAGE_INVALID_ARGUMENT);
            }
        } catch (ParseException pe) {
            throw pe;
        } catch (NumberFormatException nfe) {
            throw new ParseException(MESSAGE_NUMBER_OUT_OF_BOUND);
        }
        return ParserUtil.parseIndex(argValues[0]);
    }

    private int getDays() throws ParseException {
        assert(argValues.length == 1 || argValues.length == 2);

        if (argValues.length == 1) {
            return 1;
        }
        try {
            if (Integer.valueOf(argValues[1]) < 1) {
                throw new ParseException(MESSAGE_INVALID_ARGUMENT);
            }
        } catch (ParseException pe) {
            throw pe;
        } catch (NumberFormatException nfe) {
            throw new ParseException(MESSAGE_NUMBER_OUT_OF_BOUND);
        }
        return Integer.parseInt(argValues[1]);
    }
}
