package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameSchoolAndTagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new SearchCommand object
 */
public class SearchCommandParser implements Parser<SearchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SearchCommand
     * and returns a SearchCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SCHOOL, PREFIX_TAG);

        String[] nameKeywords = null;
        String[] schoolKeywords = null;
        String[] tagKeywords = null;

        if (!argMultimap.getValue(PREFIX_NAME).isPresent()
                && !argMultimap.getValue(PREFIX_SCHOOL).isPresent()
                && !argMultimap.getValue(PREFIX_TAG).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            nameKeywords = extractKeywordsAsArray(argMultimap, PREFIX_NAME);
        }
        if (argMultimap.getValue(PREFIX_SCHOOL).isPresent()) {
            schoolKeywords = extractKeywordsAsArray(argMultimap, PREFIX_SCHOOL);
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            tagKeywords = extractKeywordsAsArray(argMultimap, PREFIX_TAG);
        }
        List<String> nameKeywordsList = nameKeywords == null ? null : Arrays.asList(nameKeywords);
        List<String> schoolKeywordsList = schoolKeywords == null ? null : Arrays.asList(schoolKeywords);
        List<String> tagKeywordsList = tagKeywords == null ? null : Arrays.asList(tagKeywords);

        return new SearchCommand(new NameSchoolAndTagContainsKeywordsPredicate(
                nameKeywordsList, schoolKeywordsList, tagKeywordsList));
    }

    /**
     * Extracts out the keywords and place them in an array
     *
     * @param argMultimap ArgumentMultimap that maps the keywords to the prefixes
     * @param prefix The prefix for which the keywords it is mapped to is to be extracted
     * @return An array of keywords
     * @throws ParseException if there is no keyword following the prefix
     */
    public String[] extractKeywordsAsArray(ArgumentMultimap argMultimap, Prefix prefix) throws ParseException {
        String keywords = "";

        if (prefix.equals(PREFIX_NAME)) {
            keywords = argMultimap.getValue(PREFIX_NAME).get();
        } else if (prefix.equals(PREFIX_SCHOOL)) {
            keywords = argMultimap.getValue(PREFIX_SCHOOL).get();
        } else if (prefix.equals(PREFIX_TAG)) {
            keywords = argMultimap.getValue(PREFIX_TAG).get();
        }

        requireNonNull(keywords);
        String trimmedName = keywords.trim();
        if (trimmedName.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }
        return trimmedName.split("\\s+");
    }

}
