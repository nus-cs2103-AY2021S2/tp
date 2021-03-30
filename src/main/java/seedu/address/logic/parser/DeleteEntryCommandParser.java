package seedu.address.logic.parser;

import seedu.address.logic.commands.DeleteEntryCommand;
import seedu.address.model.entry.EntryNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new DeleteEntryCommand object
 */
public class DeleteEntryCommandParser implements Parser<DeleteEntryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteEntryCommand
     * and returns a DeleteEntryCommand object for execution.
     */
    public DeleteEntryCommand parse(String args) {
        EntryNameContainsKeywordsPredicate predicate = new EntryNameContainsKeywordsPredicate(args);
        return new DeleteEntryCommand(predicate);
    }
}
