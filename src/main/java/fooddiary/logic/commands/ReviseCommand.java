package fooddiary.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import fooddiary.commons.core.Messages;
import fooddiary.commons.core.index.Index;
import fooddiary.logic.commands.exceptions.CommandException;
import fooddiary.model.Model;
import fooddiary.model.entry.Entry;

/**
 * Allows revision of specified entry in a separate window.
 */
public class ReviseCommand extends Command {
    public static final String COMMAND_WORD = "revise";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Enables revision of entry based on the index number used in the displayed entry list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REVISE_ENTRY_SUCCESS = "Revising Entry: %1$s";

    private final Index targetIndex;

    /**
     * Creates an ReviseCommand to revise the specified {@code Entry}
     */
    public ReviseCommand(Index targetIndex) {
        assert targetIndex.getZeroBased() >= 0 : "Negative integer supplied";
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null : "Model is null";
        requireNonNull(model);
        List<Entry> lastShownList = model.getFilteredEntryList();

        if (targetIndex.getZeroBased() >= lastShownList.size() && lastShownList.size() == 1) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_SINGULAR);
        } else if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX_PLURAL,
                    lastShownList.size()));
        }

        assert lastShownList.get(targetIndex.getZeroBased()) != null : "Entry do not exist";
        Entry entry = lastShownList.get(targetIndex.getZeroBased());

        return new CommandResult(entry, targetIndex, String.format(MESSAGE_REVISE_ENTRY_SUCCESS, entry),
                false , false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReviseCommand // instanceof handles nulls
                && targetIndex.equals(((ReviseCommand) other).targetIndex)); // state check
    }
}
