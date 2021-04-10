package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_KEYWORD_SUPPLIED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.School;
import seedu.address.model.person.predicate.NameSchoolAndSubjectContainsKeywordsPredicate;
import seedu.address.model.subject.Subject;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SCHOOL, PREFIX_SUBJECT);

        String[] nameKeywords = null;
        String[] schoolKeywords = null;
        String[] subjectKeywords = null;
        Subject[] subjects = null;

        if (!argMultimap.getValue(PREFIX_NAME).isPresent()
                && !argMultimap.getValue(PREFIX_SCHOOL).isPresent()
                && !argMultimap.getValue(PREFIX_SUBJECT).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            nameKeywords = extractKeywordsAsArray(argMultimap, PREFIX_NAME);
        }
        if (argMultimap.getValue(PREFIX_SCHOOL).isPresent()) {
            schoolKeywords = extractKeywordsAsArray(argMultimap, PREFIX_SCHOOL);
        }
        if (argMultimap.getValue(PREFIX_SUBJECT).isPresent()) {
            subjectKeywords = extractKeywordsAsArray(argMultimap, PREFIX_SUBJECT);
            subjects = new Subject[subjectKeywords.length];
            int i = 0;
            for (String name: subjectKeywords) {
                subjects[i] = ParserUtil.parseSubject(name);
                i++;
            }
        }

        List<String> nameKeywordsList = nameKeywords == null ? null : Arrays.asList(nameKeywords);
        List<String> schoolKeywordsList = schoolKeywords == null ? null : Arrays.asList(schoolKeywords);
        List<Subject> subjectsList = subjectKeywords == null ? null : Arrays.asList(subjects);

        return new SearchCommand(new NameSchoolAndSubjectContainsKeywordsPredicate(
                nameKeywordsList, schoolKeywordsList, subjectsList));
    }

    /**
     * Extracts out the keywords and place them in an array
     *
     * @param argMultimap ArgumentMultimap that maps the keywords to the prefixes
     * @param prefix The prefix for which the keywords it is mapped to is to be extracted
     * @return An array of keywords
     * @throws ParseException if there is no keyword following the prefix or the keyword is invalid
     */
    public String[] extractKeywordsAsArray(ArgumentMultimap argMultimap, Prefix prefix) throws ParseException {
        String keywords = "";
        String prefixType = "";

        if (prefix.equals(PREFIX_NAME)) {
            keywords = argMultimap.getValue(PREFIX_NAME).get();
            prefixType = "name";
        } else if (prefix.equals(PREFIX_SCHOOL)) {
            keywords = argMultimap.getValue(PREFIX_SCHOOL).get();
            prefixType = "school";
        } else if (prefix.equals(PREFIX_SUBJECT)) {
            keywords = argMultimap.getValue(PREFIX_SUBJECT).get();
            prefixType = "subject";
        }

        requireNonNull(keywords);
        String trimmedKeyword = keywords.trim();
        if (trimmedKeyword.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }

        switch (prefixType) {
        case "name":
            if (!Name.isValidName(trimmedKeyword)) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_KEYWORD_SUPPLIED, Name.MESSAGE_CONSTRAINTS));
            }
            break;
        case "school":
            if (!School.isValidSchool(trimmedKeyword)) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_KEYWORD_SUPPLIED, School.MESSAGE_CONSTRAINTS));
            }
            break;
        case "subject":
            String[] test = trimmedKeyword.split("\\s+");
            for (int i = 0; i < test.length; i++) {
                if (!Subject.isValidSubjectName(test[i])) {
                    throw new ParseException(
                        String.format(MESSAGE_INVALID_KEYWORD_SUPPLIED, Subject.MESSAGE_CONSTRAINTS));
                }
            }
            break;
        default:
            break;
        }

        return trimmedKeyword.split("\\s+");
    }

}
