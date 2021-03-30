package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Sorts all persons in the address book by name.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the entries in the contact list by name.\n"
            + "Parameters: ASCENDING_OR_DESCENDING\n"
            + "Example: " + COMMAND_WORD
            + " ascending";

    public static final String MESSAGE_SUCCESS = "Successfully sorted the list";

    private final boolean isAscending;

    /**
     * Creates a sort command to sort the list by name.
     */
    public SortCommand(boolean isAscending) throws ParseException {
        requireNonNull(isAscending);
        this.isAscending = isAscending;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortByName(isAscending);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
