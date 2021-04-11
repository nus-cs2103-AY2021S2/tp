package seedu.flashback.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.flashback.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.flashback.logic.commands.FindCommand;
import seedu.flashback.logic.parser.exceptions.ParseException;
import seedu.flashback.model.flashcard.FlashcardContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand for execution.
     *
     * @param args user input, cannot be null.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        assert(!trimmedArgs.isEmpty());

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCommand(new FlashcardContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
