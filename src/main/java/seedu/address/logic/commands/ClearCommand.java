package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.Planner;

/**
 * Clears the planner.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "The planner has been cleared.\n"
            + Messages.MESSAGE_CALENDAR_SHOWING_CURRENT_MONTH;
    public static final String SHORT_MESSAGE_USAGE = COMMAND_WORD + "\n";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPlanner(new Planner());
        model.resetCalendarDate();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
