package seedu.taskify.logic.parser;

import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.taskify.logic.commands.TagSearchCommand;
import seedu.taskify.logic.parser.exceptions.ParseException;
import seedu.taskify.model.task.predicates.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new TagSearchCommand object
 */
public class TagSearchCommandParser implements Parser<TagSearchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagSearchCommand
     * and returns a TagSearchCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TagSearchCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagSearchCommand.MESSAGE_USAGE));
        }

        String[] tagKeywords = trimmedArgs.split("\\s+");

        return new TagSearchCommand(new TagContainsKeywordsPredicate(Arrays.asList(tagKeywords)));
    }

}
