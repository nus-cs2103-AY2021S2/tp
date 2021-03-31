package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    public static final String SPECIAL_INDEX = "shown";
    public static final String SELECTED = "selected";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand and returns
     * a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (args.trim().equals(SPECIAL_INDEX)) {
            return DeleteCommand.buildDeleteShownCommand();
        }
        if (args.trim().equals(SELECTED)) {
            return DeleteCommand.buildDeleteSelectedCommand();
        }

        try {
            return DeleteCommand.buildDeleteIndexCommand(ParserUtil.parseIndexes(args));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand and returns
     * true if arguments are valid to be aliased.
     */
    @Override
    public boolean isValidCommandToAlias(String userInput) {
        if (userInput.trim().isEmpty()) {
            return true;
        }

        if (userInput.trim().equals(SPECIAL_INDEX)) {
            return true;
        }

        if (userInput.trim().equals(SELECTED)) {
            return true;
        }

        try {
            parse(userInput);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

}
