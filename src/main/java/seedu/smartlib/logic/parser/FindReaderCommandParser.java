package seedu.smartlib.logic.parser;

import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.smartlib.logic.commands.FindReaderCommand;
import seedu.smartlib.logic.parser.exceptions.ParseException;
import seedu.smartlib.model.reader.NameContainsKeywordsPredicate;
import seedu.smartlib.model.reader.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindReaderCommand object.
 */
public class FindReaderCommandParser implements Parser<FindReaderCommand> {

    /**
     * Verifies that the keyword(s) for reader search is not empty.
     *
     * @param trimmedArgs trimmed keyword(s) for reader search.
     * @throws ParseException the input keyword(s) is empty.
     */
    private void verifyArgsNonEmpty(String trimmedArgs) throws ParseException {
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindReaderCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindReaderCommand
     * and returns a FindReaderCommand object for execution.
     *
     * @param args arguments given in the user input.
     * @return a FindReaderCommand object required for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public FindReaderCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        verifyArgsNonEmpty(trimmedArgs);

        String[] nameKeywords = trimmedArgs.split("\\s+");
        int endOfTag = 2;

        if (nameKeywords[0].substring(0, endOfTag).equals(PREFIX_TAG.toString())) {
            nameKeywords[0] = nameKeywords[0].substring(endOfTag); // removes the tag from the search list
            return new FindReaderCommand(new TagContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else {
            return new FindReaderCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
    }

}
