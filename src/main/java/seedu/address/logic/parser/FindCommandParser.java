package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.logic.commands.FindCategoryCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindPriorityCommand;
import seedu.address.logic.commands.FindQuestionCommand;
import seedu.address.logic.commands.FindTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.flashcard.CategoryContainsKeywordsPredicate;
import seedu.address.model.flashcard.PriorityContainsKeywordsPredicate;
import seedu.address.model.flashcard.QuestionContainsKeywordsPredicate;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a correct FindCommand object according to criteria for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || trimmedArgs.length() < 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String searchCriteria = trimmedArgs.substring(0, 2);
        trimmedArgs = trimmedArgs.substring(2).trim();
        if (invalidSearchCriteria(searchCriteria) || trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return findCommandByCriteria(searchCriteria, nameKeywords);
    }

    /**
     * Returns a boolean stating whether the search criteria is valid.
     *
     * @param searchCriteria Field that the user wants to search by.
     * @return True if search criteria is not valid. False if search criteria is valid.
     */
    public boolean invalidSearchCriteria(String searchCriteria) {
        return !isQuestion(searchCriteria)
                && !isCategory(searchCriteria)
                && !isTag(searchCriteria)
                && !isPriority(searchCriteria);
    }

    /**
     * Returns a boolean stating whether the search criteria is according to question.
     *
     * @param searchCriteria Field that the user wants to search by.
     * @return True if search criteria is according to question. False otherwise.
     */
    public boolean isQuestion(String searchCriteria) {
        return searchCriteria.equals(PREFIX_QUESTION.getPrefix());
    }

    /**
     * Returns a boolean stating whether the search criteria is according to category.
     *
     * @param searchCriteria Field that the user wants to search by.
     * @return True if search criteria is according to category. False otherwise.
     */
    public boolean isCategory(String searchCriteria) {
        return searchCriteria.equals(PREFIX_CATEGORY.getPrefix());
    }

    /**
     * Returns a boolean stating whether the search criteria is according to tag.
     *
     * @param searchCriteria Field that the user wants to search by.
     * @return True if search criteria is according to tag. False otherwise.
     */
    public boolean isTag(String searchCriteria) {
        return searchCriteria.equals(PREFIX_TAG.getPrefix());
    }

    /**
     * Returns a boolean stating whether the search criteria is according to priority.
     *
     * @param searchCriteria Field that the user wants to search by.
     * @return True if search criteria is according to tag. False otherwise.
     */
    public boolean isPriority(String searchCriteria) {
        return searchCriteria.equals(PREFIX_PRIORITY.getPrefix());
    }

    /**
     * Returns the FindCommand object according to the search criteria.
     *
     * @param searchCriteria Field that the user wants to search by.
     * @param nameKeywords Keywords the user wish to search by.
     * @return The FindCommand object according to the search criteria.
     */
    public FindCommand findCommandByCriteria(String searchCriteria, String[] nameKeywords) {
        if (isQuestion(searchCriteria)) {
            return new FindQuestionCommand(new QuestionContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (isCategory(searchCriteria)) {
            return new FindCategoryCommand(new CategoryContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else if (isTag(searchCriteria)) {
            return new FindTagCommand(new TagContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        } else {
            return new FindPriorityCommand(new PriorityContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }
    }

}
