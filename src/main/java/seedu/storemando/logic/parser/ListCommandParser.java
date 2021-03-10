package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.storemando.logic.commands.ListCommand;
import seedu.storemando.logic.parser.exceptions.ParseException;
import seedu.storemando.model.item.LocationContainsKeywordsPredicate;
import seedu.storemando.model.tag.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     */
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new ListCommand();
        } else if (trimmedArgs.length() < 3) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.COMMAND_WORD));
        }

        if (trimmedArgs.substring(0, 2).equals(PREFIX_LOCATION.getPrefix())) {
            trimmedArgs = trimmedArgs.substring(2);
            String[] keywords = trimmedArgs.split("\\s+");
            return new ListCommand(new LocationContainsKeywordsPredicate(Arrays.asList(keywords)));
        } else if (trimmedArgs.substring(0, 2).equals(PREFIX_TAG.getPrefix())) {
            trimmedArgs = trimmedArgs.substring(2);
            String[] keywords = trimmedArgs.split("\\s+");
            return new ListCommand(new TagContainsKeywordsPredicate(Arrays.asList(keywords)));
        } else {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.COMMAND_WORD));
        }
    }

}
