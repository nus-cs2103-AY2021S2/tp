package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
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
     * Parses the given arguments in the context of the FindCommand
     *
     * @param args String argument parse in from Find command
     * @return FindCommand object for execution
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        FindCommandParserUtil.checkEmptyInputField(trimmedArgs, "input");

        String[] keywords = trimmedArgs.split("\\s+");
        boolean isTagWord = Arrays.toString(keywords).contains("t/");
        boolean isDescription = Arrays.toString(keywords).contains("d/");

        if (isTagWord) {
            checkMultiplePrefix(keywords, "tag");
            Set<String> tagWords = FindCommandParserUtil.handleSearchByTag(keywords);
            return new FindCommand(new TagContainsKeywordsPredicate(tagWords));
        } else if (isDescription) {
            checkMultiplePrefix(keywords, "description");
            List<String> descriptionWords = FindCommandParserUtil.handleSearchByDescription(keywords);
            return new FindCommand(new DescriptionContainsKeywordsPredicate(descriptionWords));
        } else {
            checkMultiplePrefix(keywords, "title");
            return new FindCommand(new TitleContainsKeywordsPredicate(Arrays.asList(keywords)));
        }
    }

    /**
     * Check for mixed parse format prefixes within the keyword(s) of find query
     *
     * @param keywords Keyword(s) which is within the find query
     * @param findType Type of find queries can be description, tag, title
     * @throws ParseException if a find query has multiple parse format prefixes
     * Like d/ t/ being detected from the keywords
     */
    private static void checkMultiplePrefix(String[] keywords, String findType) throws ParseException {
        int numDescriptionPrefix = 0;
        int numTagPrefix = 0;

        checkMultiplePrefixInTitle(keywords, findType);
        for (String keyword : keywords) {
            boolean isTagPrefix = keyword.contains("t/");
            boolean isDescriptionPrefix = keyword.contains("d/");

            if (isTagPrefix) {
                numTagPrefix++;
            } else if (isDescriptionPrefix) {
                numDescriptionPrefix++;
            }
        }
        checkMultiplePrefixInDescriptionTag(numDescriptionPrefix, numTagPrefix, findType);
    }

    /**
     * Checks the mixed find query prefixes within keyword(s) in find by title query only
     *
     * @param keywords Keyword(s) which is within the find by title query
     * @param findPrefix Type of find prefixes can be description, tag hidden in find by title query
     * @throws ParseException if a find query by title has multiple parse formats prefixes
     * Like d/ t/ being detected from the keywords
     */
    private static void checkMultiplePrefixInTitle(String[] keywords, String findPrefix) throws ParseException {
        boolean isDescriptionPrefixInTitle = findPrefix.equals("description") && !(keywords[0].contains("d/"));
        boolean isTagInTitle = findPrefix.equals("tag") && !(keywords[0].contains("t/"));

        if (isDescriptionPrefixInTitle) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommandParserUtil.MULTIPLE_COMMANDS));
        } else if (isTagInTitle) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommandParserUtil.MULTIPLE_COMMANDS));
        }
    }

    /**
     * Checks the given keyword(s) and find query type of description or tag(s)
     *
     * @param numDescriptionPrefix Number of description prefix in keyword(s)
     * @param numTagPrefix Number of tag prefix in keyword(s)
     * @param findType Type of find query which is either find by description or find by tag only
     * @throws ParseException if a find query by description or tag(s) has multiple parse format prefixes
     * Like d/ t/ being detected from the keywords
     */
    private static void checkMultiplePrefixInDescriptionTag(int numDescriptionPrefix, int numTagPrefix, String findType)
            throws ParseException {
        boolean isMultipleCommands;

        switch (findType) {
        case "description":
            isMultipleCommands = numTagPrefix > 0 || numDescriptionPrefix > 1;

            if (isMultipleCommands) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindCommandParserUtil.MULTIPLE_COMMANDS));
            }
            break;
        case "tag":
            isMultipleCommands = numDescriptionPrefix > 0;

            if (isMultipleCommands) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindCommandParserUtil.MULTIPLE_COMMANDS));
            }
            break;
        default:
            break;
        }
    }
}
