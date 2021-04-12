package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.storemando.logic.commands.SortAscendingQuantityCommand;
import seedu.storemando.logic.commands.SortCommand;
import seedu.storemando.logic.commands.SortDescendingQuantityCommand;
import seedu.storemando.logic.commands.SortExpiryDateCommand;
import seedu.storemando.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand> {

    public static final String QUANTITY_KEYWORD = "quantity";
    public static final String ASCENDING_KEYWORD = "asc";
    public static final String DESCENDING_KEYWORD = "desc";
    public static final String EXPIRYDATE_KEYWORD = "expirydate";

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @param args Parses the given {@code String} of arguments in the context of the SortCommand.
     * @return a SortCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.equalsIgnoreCase(EXPIRYDATE_KEYWORD)) {
            return new SortExpiryDateCommand();
        }
        String[] splitWords = trimmedArgs.split("\\s+");
        if (splitWords.length == 2 && splitWords[0].equalsIgnoreCase(QUANTITY_KEYWORD)
            && splitWords[1].equalsIgnoreCase(ASCENDING_KEYWORD)) {
            return new SortAscendingQuantityCommand();
        } else if (splitWords.length == 2 && splitWords[0].equalsIgnoreCase(QUANTITY_KEYWORD)
            && splitWords[1].equalsIgnoreCase(DESCENDING_KEYWORD)) {
            return new SortDescendingQuantityCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }
}
