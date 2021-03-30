package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.DeleteEntryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entry.EntryNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new DeleteEntryCommand object
 */
public class DeleteEntryCommandParser implements Parser<DeleteEntryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteEntryCommand
     * and returns a DeleteEntryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteEntryCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteEntryCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");
        EntryNameContainsKeywordsPredicate predicate = new EntryNameContainsKeywordsPredicate(Arrays.asList(keywords));
        return new DeleteEntryCommand(predicate);
    }
}
