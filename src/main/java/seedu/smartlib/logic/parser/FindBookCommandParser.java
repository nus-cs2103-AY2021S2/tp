package seedu.smartlib.logic.parser;

import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.smartlib.logic.commands.FindBookCommand;
import seedu.smartlib.logic.parser.exceptions.ParseException;
import seedu.smartlib.model.book.BookNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindReaderCommand object
 */
public class FindBookCommandParser implements Parser<FindBookCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindBookCommand
     * and returns a FindBookCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindBookCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBookCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindBookCommand(new BookNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}

