package seedu.smartlib.logic.parser;

import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.smartlib.logic.commands.FindBookCommand;
import seedu.smartlib.logic.parser.exceptions.ParseException;
import seedu.smartlib.model.book.BookNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindBookCommand object.
 */
public class FindBookCommandParser implements Parser<FindBookCommand> {

    /**
     * Verifies that the keyword(s) for book search is not empty.
     *
     * @param trimmedArgs trimmed keyword(s) for book search.
     * @throws ParseException the input keyword(s) is empty.
     */
    private void verifyArgsNonEmpty(String trimmedArgs) throws ParseException {
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBookCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindBookCommand
     * and returns a FindBookCommand object for execution.
     *
     * @param args arguments given in the user input.
     * @return a FindBookCommand object required for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindBookCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        verifyArgsNonEmpty(trimmedArgs);

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindBookCommand(new BookNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
