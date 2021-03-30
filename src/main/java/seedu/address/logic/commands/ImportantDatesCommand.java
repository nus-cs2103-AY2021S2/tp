package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_IMPORTANT_DATES;

import seedu.address.model.Model;

/**
 * Opens up a window displaying the list of important dates input by the user.
 */
public class ImportantDatesCommand extends Command {

    public static final String COMMAND_WORD = "list-date";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows list of important dates.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_DATES_MESSAGE = "Opened important dates window.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // TODO: change switch to model.updateSortedImportantDatesList(); after implementing sorting
        model.updateFilteredImportantDatesList(PREDICATE_SHOW_ALL_IMPORTANT_DATES);
        return new CommandResult(SHOWING_DATES_MESSAGE, false, true, false, false);
    }
}
