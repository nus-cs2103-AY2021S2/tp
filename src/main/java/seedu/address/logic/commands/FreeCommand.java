package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_FREE;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_FREE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entry.ListOccupyingEntryPredicate;

/**
 * Checks and indicates if time interval provided as argument is free.
 */
public class FreeCommand extends Command {
    public static final String COMMAND_WORD = "free";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Indicates if an interval is free. "
            + "If free, 'free' will be shown. If not, tasks occupying that interval will be shown in "
            + "the entries listed below.\n"
            + "Parameters: "
            + PREFIX_START_DATE + "START_DATE "
            + PREFIX_END_DATE + "END_DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_START_DATE + " 2021-12-20 12:00 "
            + PREFIX_END_DATE + " 2021-12-20 13:00 ";

    private final ListOccupyingEntryPredicate predicate;

    public FreeCommand(ListOccupyingEntryPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredEntryList(predicate);
        if (model.getFilteredEntryList().size() == 0) {
            return new CommandResult(MESSAGE_FREE);
        } else {
            return new CommandResult(MESSAGE_NOT_FREE);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FreeCommand // instanceof handles nulls
                && predicate.equals(((FreeCommand) other).predicate));
    }
}
