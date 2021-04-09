package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given arguments in the context of the ListCommand
     *
     * @param args String argument parse in from List command
     * @return ListCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {

        String trimmedArgs = args.trim();
        boolean isListNotDoneTask = trimmedArgs.equalsIgnoreCase("not done");
        boolean isListEverything = trimmedArgs.equals("");

        if (isListNotDoneTask) {
            return new ListCommand(false);
        } else if (isListEverything) {
            return new ListCommand(true);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.INVALID_INPUT));
        }
    }
}
