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
        checkEmptyInputField(trimmedArgs, "title");

        String[] keywords = trimmedArgs.split("\\s+");
        boolean isTagWord = Arrays.toString(keywords).contains("t/");
        boolean isDescription = Arrays.toString(keywords).contains("d/");

        if (isTagWord) {
            Set<String> tagWords = handleSearchByTag(keywords);
            return new FindCommand(new TagContainsKeywordsPredicate(tagWords));
        } else if (isDescription) {
            List<String> descriptionWords = handleSearchByDescription(keywords);
            return new FindCommand(new DescriptionContainsKeywordsPredicate(descriptionWords));
        } else { // default case is find by title
            return new FindCommand(new TitleContainsKeywordsPredicate(Arrays.asList(keywords)));
        }
    }

    /**
     * Manages the given {@code String[]} of arguments to search matching tasks by tag
     * and returns a Set object that contains the list of tag keywords for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public Set<String> handleSearchByTag(String[] keywords) throws ParseException {
        Set<String> tagWords = new HashSet<>();

        for (String word : keywords) {
            String tagKeyword = word.replaceFirst("t/", "");
            checkEmptyInputField(tagKeyword, "tag");
            tagWords.add(tagKeyword);
        }
        return tagWords;
    }

    /**
     * Manages the given {@code String[]} of arguments to search matching tasks by tag
     * and returns a List object that contains the description keywords for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public List<String> handleSearchByDescription(String[] keywords) throws ParseException {
        List<String> descriptionWords = new ArrayList<>();

        for (String keyword : keywords) {
            String currStr = formatDescription(keyword);
            checkEmptyInputField(currStr, "description");
            descriptionWords.add(currStr);
        }
        return descriptionWords;
    }

    private String formatDescription(String keyword) {
        String currStr = keyword;
        boolean isDescriptionFormat = currStr.contains("d/");

        if (isDescriptionFormat) {
            currStr = currStr.replaceFirst("d/", "");
        }
        return currStr;
    }

    private void checkEmptyInputField(String input, String findType) throws ParseException {
        if (input.isEmpty()) {
            if (findType.equals("tag")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.TAG_USAGE));
            } else if (findType.equals("description")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.DESCRIPTION_USAGE));
            } else { // default condition is empty title
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
        }
    }
}
