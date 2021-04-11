package seedu.plan.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.plan.commons.core.LogsCenter;
import seedu.plan.logic.commands.exceptions.CommandException;
import seedu.plan.model.Model;
import seedu.plan.model.util.History;

import java.util.logging.Logger;

/**
 * Adds a plan to the address book.
 */
public class HistoryCommand extends Command {
    private final Logger logger = LogsCenter.getLogger(HistoryCommand.class);
    public static final String COMMAND_WORD = "history";
    public static final String MESSAGE_SUCCESS = "History: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the user a list of modules that they "
            + "have completed up until before the current semester of the master plan.\n"
            + "Parameters: "
            + "None\n"
            + "The current semester is the semester that was marked using the `current s/SEM_NUM` command.\n"
            + "The master plan is the plan that was marked using the `master p/PLAN_NUM` command.";

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
        logger.info("----------------[EXECUTE][START]");
        requireNonNull(model);
        History history = model.getHistory();
        logger.info("----------------[EXECUTE][END]");
        return new CommandResult(String.format(MESSAGE_SUCCESS, history.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this || other instanceof HistoryCommand;
    }
}
