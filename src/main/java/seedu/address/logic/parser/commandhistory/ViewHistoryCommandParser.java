package seedu.address.logic.parser.commandhistory;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.commandhistory.ViewHistoryCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code ViewHistoryCommand} object.
 */
public class ViewHistoryCommandParser implements Parser<ViewHistoryCommand> {

    private static boolean isInvalidCount(int count) {
        return count <= 0;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the {@code ViewHistoryCommand}
     * and returns a {@code ViewHistoryCommand} object for execution.
     *
     * @param args The arguments to be parsed.
     * @return The parsed {@code ViewHistoryCommand}.
     * @throws ParseException If the user input does not conform the expected format
     */
    @Override
    public ViewHistoryCommand parse(String args) throws ParseException {
        if (args == null || args.isEmpty()) {
            return new ViewHistoryCommand();
        }

        try {
            int count = Integer.parseInt(args.trim());

            if (isInvalidCount(count)) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewHistoryCommand.MESSAGE_USAGE));
            }

            return new ViewHistoryCommand(count);
        } catch (NumberFormatException nfe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewHistoryCommand.MESSAGE_USAGE), nfe);
        }
    }
}
