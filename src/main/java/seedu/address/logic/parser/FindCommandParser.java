package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ARGUMENT_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.FindCommand.MESSAGE_INVALID_REGEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATTERN;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsPatternPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_PATTERN);
        String searchString = argMultiMap.getPreamble();
        if (argMultiMap.getValue(PREFIX_PATTERN).isPresent()) {
            try {
                // The p/ is only an indicator to use the searchString as the regex pattern.
                // E.g. "abc p/" should use "abc" as the regex to match.
                Pattern pattern = Pattern.compile(searchString, Pattern.CASE_INSENSITIVE);
                return new FindCommand(new NameContainsPatternPredicate(pattern));
            } catch (PatternSyntaxException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_ARGUMENT_FORMAT, MESSAGE_INVALID_REGEX));
            }
        }
        if (searchString.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        String[] nameKeywords = searchString.split("\\s+");

        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
