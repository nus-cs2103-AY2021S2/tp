package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.DisplayFilterPredicate;
import seedu.address.model.Model;

/**
 * Sets the person list filter.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Toggles the visibility of fields based on user input options\n"
                    + "Parameters: "
                    + "[" + PREFIX_NAME + "] "
                    + "[" + PREFIX_PHONE + "] "
                    + "[" + PREFIX_EMAIL + "] "
                    + "[" + PREFIX_COMPANY + "] "
                    + "[" + PREFIX_JOB_TITLE + "] "
                    + "[" + PREFIX_ADDRESS + "] "
                    + "[" + PREFIX_REMARK + "] "
                    + "[" + PREFIX_TAG + "]\n"
                    + "Examples:\n"
                    + COMMAND_WORD + "\n"
                    + COMMAND_WORD + " " + PREFIX_TAG + " " + PREFIX_PHONE + "\n"
                    + COMMAND_WORD + " " + PREFIX_REMARK + " " + PREFIX_ADDRESS;

    public static final String MESSAGE_SUCCESS = "Applied filter %1$s";
    public static final String MESSAGE_REMOVE_FILTER = "Removed filter";

    private final DisplayFilterPredicate filterPredicate;

    private final List<String> prefixes;

    /**
     * Initializes FilterCommand with no filters.
     *
     * @param filterPredicate predicate for filter.
     */
    public FilterCommand(DisplayFilterPredicate filterPredicate) {
        this.filterPredicate = filterPredicate;
        this.prefixes = new ArrayList<>();
    }

    /**
     * Initializes FilterCommand with prefixes.
     *
     * @param filterPredicate predicate for filter.
     * @param prefixes        parsed valid prefixes.
     */
    public FilterCommand(DisplayFilterPredicate filterPredicate, List<String> prefixes) {
        this.filterPredicate = filterPredicate;
        this.prefixes = prefixes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateDisplayFilter(filterPredicate);

        if (prefixes.size() == 0) {
            return new CommandResult(MESSAGE_REMOVE_FILTER);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, String.join(" ", prefixes)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand) // instanceof handles nulls
                && filterPredicate.equals(((FilterCommand) other).filterPredicate)
                && prefixes.containsAll(((FilterCommand) other).prefixes)
                && ((FilterCommand) other).prefixes.containsAll(prefixes);
    }
}
