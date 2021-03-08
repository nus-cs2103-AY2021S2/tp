package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.storemando.logic.commands.FindCommand;
import seedu.storemando.logic.parser.exceptions.ParseException;
import seedu.storemando.model.item.ItemNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private boolean isGenericFind = false;

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (trimmedArgs.charAt(0) == '*') {
            isGenericFind = true;
        }

        if (isGenericFind) {
            trimmedArgs = trimmedArgs.substring(1);
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        return new FindCommand(new ItemNameContainsKeywordsPredicate(Arrays.asList(nameKeywords), isGenericFind));
    }

}
