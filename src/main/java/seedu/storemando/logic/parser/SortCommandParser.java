package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.storemando.logic.commands.SortCommand;
import seedu.storemando.logic.commands.SortExpiryDateCommand;
import seedu.storemando.logic.commands.SortQuantityCommand;
import seedu.storemando.logic.parser.exceptions.ParseException;

public class SortCommandParser implements Parser<SortCommand> {

    public static final String QUANTITY_KEYWORD = "quantity";
    public static final String EXPIRYDATE_KEYWORD = "expirydate";

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim().toLowerCase();
        if (trimmedArgs.equals(QUANTITY_KEYWORD)) {
            System.out.println("1");
            return new SortQuantityCommand();
        } else if (trimmedArgs.equals(EXPIRYDATE_KEYWORD)) {
            return new SortExpiryDateCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }
}
