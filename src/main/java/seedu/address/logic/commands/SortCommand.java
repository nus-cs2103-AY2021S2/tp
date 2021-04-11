package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Sorts all persons in the address book by name in alphabetical order. The address book can either
 * be sorted in ascending or descending order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String SORT_ASCENDING_KEYWORD = "ascending";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all persons in the address book by name in alphabetical order.\n"
            + "Parameters: ASCENDING_OR_DESCENDING\n"
            + "Example: " + COMMAND_WORD + " "
            + "ascending";

    public static final String MESSAGE_SORT_SUCCESS = "Successfully sorted all persons in the "
            + "address book in %1$s order";

    private final boolean isAscending;

    /**
     * Creates a sort command to sort the address book by name in alphabetical order.
     */
    public SortCommand(boolean isAscending) {
        requireNonNull(isAscending);
        this.isAscending = isAscending;
    }

    /**
     * Returns true if the given string is a valid sort direction (either ascending or descending).
     */
    public static boolean isValidSortDirection(String sortDirection) {
        return sortDirection.equals("ascending") || sortDirection.equals("descending");
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortByName(isAscending);
        String outputMessage;
        if (isAscending) {
            outputMessage = "ascending";
        } else {
            outputMessage = "descending";
        }
        return new CommandResult(String.format(MESSAGE_SORT_SUCCESS, outputMessage));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && isAscending == ((SortCommand) other).isAscending); // state check
    }
}
