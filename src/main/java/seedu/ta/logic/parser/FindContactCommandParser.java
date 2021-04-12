package seedu.ta.logic.parser;

import static seedu.ta.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.ta.logic.commands.FindContactCommand;
import seedu.ta.logic.parser.exceptions.ParseException;
import seedu.ta.model.contact.ContactNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindContactCommand object.
 */
public class FindContactCommandParser implements Parser<FindContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindContactCommand
     * and returns a FindContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindContactCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");
        return new FindContactCommand(new ContactNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
