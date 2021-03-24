package fooddiary.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import fooddiary.commons.core.Messages;
import fooddiary.commons.core.index.Index;
import fooddiary.logic.commands.exceptions.CommandException;
import fooddiary.model.Model;
import fooddiary.model.entry.Entry;

/**
 * Views the full specified entry in a separate window.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the full entry identified by the index number used in the displayed entry list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_ENTRY_SUCCESS = "Viewing Entry: %1$s";

    private final Index targetIndex;

    /**
     * Creates an ViewCommand to view the specified {@code Entry}
     */
    public ViewCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Entry> lastShownList = model.getFilteredEntryList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
        }

        Entry entry = lastShownList.get(targetIndex.getZeroBased());
        HashMap<String, String> entryDetails = new HashMap<>();
        entryDetails.put("name", entry.getName().fullName);
        entryDetails.put("rating", entry.getRating().value);
        entryDetails.put("address", entry.getAddress().value);
        entryDetails.put("review", entry.getReview().value);
        String tags = entry.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tag))
                .map(tag -> tag.tag + ";")
                .collect(Collectors.joining());
        entryDetails.put("tags", tags);
        return new CommandResult(entryDetails, String.format(MESSAGE_VIEW_ENTRY_SUCCESS, entry),
                false , true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCommand) other).targetIndex)); // state check
    }
}
