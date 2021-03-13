package seedu.smartlib.logic.parser;

import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.smartlib.logic.commands.FindReaderCommand;
import seedu.smartlib.logic.parser.exceptions.ParseException;
import seedu.smartlib.model.reader.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindReaderCommand object
 */
public class FindReaderCommandParser implements Parser<FindReaderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindReaderCommand
     * and returns a FindReaderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindReaderCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindReaderCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindReaderCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
