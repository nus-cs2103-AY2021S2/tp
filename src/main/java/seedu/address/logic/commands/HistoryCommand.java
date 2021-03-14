package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.util.History;

/**
 * Adds a plan to the address book.
 */
public class HistoryCommand extends Command {

    public static final String COMMAND_WORD = "history";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the user a list of modules that they " +
            "have completed up until before the current semester. " +
            "\nNote that the current semester is the semester that was " +
            "marked using the `semester current` command.";

    public static final String MESSAGE_SUCCESS = "History: %1$s";


    /**
     * Outputs all completed semesters (up until but excluding the current semester) and modules of the
     * master plan.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult containing formatted output of all completed modules, by semester.
     * @throws CommandException If `master plan` and `current semester` have not been set.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        History history = model.getHistory();
        return new CommandResult(String.format(MESSAGE_SUCCESS, history.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this || other instanceof HistoryCommand;
    }
}
