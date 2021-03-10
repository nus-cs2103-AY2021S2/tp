package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
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
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");
        boolean isTagWord = Arrays.toString(keywords).contains("t/");
        if (isTagWord) {
            Set<String> tagWords = new HashSet<>();
            for (int index = 0; index < keywords.length; index++) {
                String word = keywords[index];
                tagWords.add(word.replaceFirst("t/", ""));
            }
            return new FindCommand(new TagContainsKeywordsPredicate(tagWords));
        } else {
            return new FindCommand(new TitleContainsKeywordsPredicate(Arrays.asList(keywords)));
        }
    }
}
