package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains utility methods used for parsing strings into FindCommandParser class.
 */
public class FindCommandParserUtil {

    public static final String TAG_USAGE = "Please enter valid input field to find tasks by tag(s) "
            + "in correct format as follows:" + FindCommand.COMMAND_WORD + " t/ [TAG_NAME]";

    public static final String DESCRIPTION_USAGE = "Please enter valid input field to find tasks by description "
            + "in correct format as follows:" + FindCommand.COMMAND_WORD + " d/ [DESCRIPTION_NAME]";

    public static final String MULTIPLE_COMMANDS = "Multiple commands detected !!! For find by title query, "
            + "please do not include search by tag (t/) or description (d/). For find by description (d/), "
            + "please do not include any tag search (t/) and only one description search is allowed. "
            + "For find by tag (t/), multiple tags search are allowed but do not include any description search (d/).";
    /**
     * Check for empty input field within the parse input from find command
     *
     * @param input Parse input from find command
     * @param findType Type of find queries can be description, tag, title
     * @throws ParseException if an empty user input excluding parse format prefix such as d/, t/ is detected
     */
    public static void checkEmptyInputField(String input, String findType) throws ParseException {
        boolean isEmptyInput = input.isEmpty();

        if (isEmptyInput) {
            boolean isEmptyTagInput = findType.equals("t/"); // t/(empty input)
            boolean isEmptyDescriptionInput = findType.equals("d/"); // d/(empty input)

            if (isEmptyTagInput) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TAG_USAGE));
            } else if (isEmptyDescriptionInput) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DESCRIPTION_USAGE));
            } else { // default condition is empty input
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
        }
    }

    /**
     * Manages the given arguments to search matching tasks by tag
     *
     * @param keywords Keyword(s) which is within the find by tag query
     * @return Set object that contains the list of tag keywords for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public static Set<String> handleSearchByTag(String[] keywords) throws ParseException {
        Set<String> tagWords = new HashSet<>();

        for (String word : keywords) {
            String tagKeyword = word.replaceFirst("t/", "");
            checkEmptyInputField(tagKeyword, "t/");
            tagWords.add(tagKeyword);
        }
        return tagWords;
    }

    /**
     * Manages the given arguments to search matching tasks by description
     *
     * @param keywords Keyword(s) which is within the find by description query
     * @return List object that contains the list of tag keywords for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public static List<String> handleSearchByDescription(String[] keywords) throws ParseException {
        List<String> descriptionWords = new ArrayList<>();

        for (String keyword : keywords) {
            String currStr = formatDescription(keyword);
            checkEmptyInputField(currStr, "d/");
            descriptionWords.add(currStr);
        }
        return descriptionWords;
    }

    /**
     * Manages the given keyword to remove the parse format prefix d/ in description
     *
     * @param keyword Keyword which is within the find by description query
     * @return String object that contains only the description keywords for execution
     */
    private static String formatDescription(String keyword) {
        assert keyword != null : "Find by description do not have any keyword(s) !!!";
        String currStr = keyword;
        boolean isDescriptionFormat = currStr.contains("d/");

        if (isDescriptionFormat) {
            currStr = currStr.replaceFirst("d/", "");
        }
        return currStr;
    }
}
