package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryNameContainsKeywordsPredicate;

public class DeleteEntryCommand extends Command {

    public static final String COMMAND_WORD = "edelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the entry identified by the given name in the list.\n"
            + "Parameters: NAME\n"
            + "Example: " + COMMAND_WORD + " online class";

    private final EntryNameContainsKeywordsPredicate predicate;

    public DeleteEntryCommand(EntryNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Entry> lastShownList = model.getFilteredEntryList();

        if (!lastShownList.stream().anyMatch(predicate)) {
            throw new CommandException(Messages.MESSAGE_NO_SUCH_ENTRY);
        }

        Entry entryToDelete = lastShownList.stream().filter(predicate).findFirst().get();
        model.deleteEntry(entryToDelete);
        return new CommandResult(String.format(Messages.MESSAGE_DELETE_ENTRY_SUCCESS, entryToDelete));
    }
}
