package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.entry.ListEntryFormatPredicate;

/**
 * Lists all entries in Teaching Assistant or lists entries based on day or week.
 */
public class ListEntryCommand extends Command {

    public static final String COMMAND_WORD = "elist";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all entries "
            + "by displaying them as a list sorted by date. Entries can also be listed by day/week.\n"
            + "Optional parameters: [FORMAT] \n"
            + "The format is only restricted to two cases: day/week."
            + "Example: " + COMMAND_WORD + " week";

    final ListEntryFormatPredicate predicate;

    /**
     * Creates a ListEntryCommand in the relevant format according to the specified {@code predicate}.
     */
    public ListEntryCommand(ListEntryFormatPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEntryList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ENTRIES_LISTED_OVERVIEW, model.getFilteredEntryList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ListEntryCommand
                && predicate.equals(((ListEntryCommand) other).predicate));
    }
}
