package seedu.ta.logic.parser;

import static seedu.ta.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.ta.logic.commands.FindEntryCommand;
import seedu.ta.logic.parser.exceptions.ParseException;
import seedu.ta.model.entry.EntryNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindEntryCommand object.
 */
public class FindEntryCommandParser implements Parser<FindEntryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindEntryCommand
     * and returns a FindEntryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindEntryCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEntryCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        return new FindEntryCommand(new EntryNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
