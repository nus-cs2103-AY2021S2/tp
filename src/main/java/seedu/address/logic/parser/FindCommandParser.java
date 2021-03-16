package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.DescriptionContainsKeywordsPredicate;
import seedu.address.model.task.TagContainsKeywordsPredicate;
import seedu.address.model.task.TitleContainsKeywordsPredicate;

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
        String trimmedArgs = args.trim();
        checkEmptyInputField(trimmedArgs, "input");

        String[] keywords = trimmedArgs.split("\\s+");
        boolean isTagWord = Arrays.toString(keywords).contains("t/");
        boolean isDescription = Arrays.toString(keywords).contains("d/");

        if (isTagWord) {
            checkMultipleFindTypes(keywords, "tag");
            Set<String> tagWords = handleSearchByTag(keywords);
            return new FindCommand(new TagContainsKeywordsPredicate(tagWords));
        } else if (isDescription) {
            checkMultipleFindTypes(keywords, "description");
            List<String> descriptionWords = handleSearchByDescription(keywords);
            return new FindCommand(new DescriptionContainsKeywordsPredicate(descriptionWords));
        } else {
            checkMultipleFindTypes(keywords, "title");
            return new FindCommand(new TitleContainsKeywordsPredicate(Arrays.asList(keywords)));
        }
    }

    /**
     * Manages the given {@code String[]} of arguments to search matching tasks by tag
     * and returns a Set object that contains the list of tag keywords for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    private static Set<String> handleSearchByTag(String[] keywords) throws ParseException {
        Set<String> tagWords = new HashSet<>();

        for (String word : keywords) {
            String tagKeyword = word.replaceFirst("t/", "");
            checkEmptyInputField(tagKeyword, "tag");
            tagWords.add(tagKeyword);
        }
        return tagWords;
    }

    /**
     * Manages the given {@code String[]} of arguments to search matching tasks by description
     * and returns a List object that contains the description keywords for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    private static List<String> handleSearchByDescription(String[] keywords) throws ParseException {
        List<String> descriptionWords = new ArrayList<>();

        for (String keyword : keywords) {
            String currStr = formatDescription(keyword);
            checkEmptyInputField(currStr, "description");
            descriptionWords.add(currStr);
        }
        return descriptionWords;
    }

    /**
     * Manages the given {@code String} of keyword to remove the parse format d/ in description
     * and returns a String object that contains only the description keywords for execution.
     */
    private static String formatDescription(String keyword) {
        String currStr = keyword;
        boolean isDescriptionFormat = currStr.contains("d/");

        if (isDescriptionFormat) {
            currStr = currStr.replaceFirst("d/", "");
        }
        return currStr;
    }

    /**
     * Manages the given {@code String, String} of input and find query type to check for empty input field
     * @throws ParseException if an empty user input excluding parse format such as d/, t/ is detected
     */
    private static void checkEmptyInputField(String input, String findType) throws ParseException {
        boolean isEmptyInput = input.isEmpty();
        if (isEmptyInput) {
            boolean isEmptyTagInput = findType.equals("tag"); // t/(empty input)
            boolean isEmptyDescriptionInput = findType.equals("description"); // d/(empty input)

            if (isEmptyTagInput) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.TAG_USAGE));
            } else if (isEmptyDescriptionInput) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.DESCRIPTION_USAGE));
            } else { // default condition is empty input
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
        }
    }

    /**
     * Manages the given {@code String[], String} of keywords and find query type of description or tag(s)
     * To check for mixed find query types
     * @throws ParseException if a find query has multiple parse formats like d/ t/ being detected from the keywords
     */
    private static void checkMultipleFindTypes(String[] keywords, String findType) throws ParseException {
        int numDescription = 0;
        int numTag = 0;

        checkMultipleTypesInTitle(keywords, findType);
        for (String keyword : keywords) {
            boolean isTagFind = keyword.contains("t/");
            boolean isDescriptionFind = keyword.contains("d/");

            if (isTagFind) {
                numTag++;
            } else if (isDescriptionFind) {
                numDescription++;
            }
        }
        checkMultipleDescriptionTag(numDescription, numTag, findType);
    }

    /**
     * Manages the given {@code String[], String} of keywords and find query type of title only
     * To check for mixed find query types
     * @throws ParseException if a find query by title has multiple parse formats
     * Like d/ t/ being detected from the keywords
     */
    private static void checkMultipleTypesInTitle(String[] keywords, String findType) throws ParseException {
        boolean isDescriptionInTitle = findType.equals("description") && !(keywords[0].contains("d/"));
        boolean isTagInTitle = findType.equals("tag") && !(keywords[0].contains("t/"));

        if (isDescriptionInTitle) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MULTIPLE_COMMANDS));
        } else if (isTagInTitle) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MULTIPLE_COMMANDS));
        }
    }

    /**
     * Checks the given {@code String[], String} of keywords and find query type of description or tag(s)
     * @throws ParseException if a find query by description or tag(s) has multiple parse formats
     * Like d/ t/ being detected from the keywords
     */
    private static void checkMultipleDescriptionTag(int numDescription, int numTag, String findType)
            throws ParseException {
        boolean isMultipleCommands;

        switch (findType) {
        case "description":
            isMultipleCommands = numTag > 0 || numDescription > 1;

            if (isMultipleCommands) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MULTIPLE_COMMANDS));
            }
            break;
        case "tag":
            isMultipleCommands = numDescription > 0;

            if (isMultipleCommands) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MULTIPLE_COMMANDS));
            }
            break;
        default:
            break;
        }
    }
}
