package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.storemando.logic.commands.SortCommand;
import seedu.storemando.logic.commands.SortExpiryDateCommand;
import seedu.storemando.logic.commands.SortQuantityCommand;
import seedu.storemando.logic.parser.exceptions.ParseException;

public class SortCommandParser implements Parser<SortCommand> {

    public static final String QUANTITY_ASCENDING_KEYWORD = "quantity asc";
    public static final String QUANTITY_DESCENDING_KEYWORD = "quantity desc";
    public static final String EXPIRYDATE_KEYWORD = "expirydate";

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.equalsIgnoreCase(EXPIRYDATE_KEYWORD)) {
            return new SortExpiryDateCommand();
        } else if (trimmedArgs.equalsIgnoreCase(QUANTITY_ASCENDING_KEYWORD)) {
            return new SortQuantityCommand(true);
        } else if (trimmedArgs.equalsIgnoreCase(QUANTITY_DESCENDING_KEYWORD)) {
            return new SortQuantityCommand(false);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }
}
