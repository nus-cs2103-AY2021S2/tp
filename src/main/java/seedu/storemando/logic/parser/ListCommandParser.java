package seedu.storemando.logic.parser;

import java.util.Arrays;

import seedu.storemando.logic.commands.ListCommand;
import seedu.storemando.model.item.LocationContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     */
    public ListCommand parse(String args) {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new ListCommand();
        }

        String[] locationKeywords = trimmedArgs.split("\\s+");

        return new ListCommand(new LocationContainsKeywordsPredicate(Arrays.asList(locationKeywords)));
    }

}
