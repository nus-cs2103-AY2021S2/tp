package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FilterEntryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entry.EntryTagsContainKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterEntryCommand object
 */
public class FilterEntryCommandParser implements Parser<FilterEntryCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FilterEntryCommand
     * and returns a FilterEntryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterEntryCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterEntryCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FilterEntryCommand(new EntryTagsContainKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
