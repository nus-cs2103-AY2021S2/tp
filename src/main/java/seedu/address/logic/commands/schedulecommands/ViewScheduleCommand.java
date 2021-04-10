package seedu.address.logic.commands.schedulecommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.schedule.ScheduleDateViewPredicate;

/**
 * View schedules by its date in schedule list.
 */
public class ViewScheduleCommand extends Command {
    public static final String COMMAND_WORD = "view_schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the schedules identified by date.\n"
            + "Parameters: DATE (must be in YYYY-MM-DD format)\n"
            + "Example: " + COMMAND_WORD + " 2021-03-17";

    public static final String MESSAGE_VIEW_SCHEDULE_SUCCESS = "Viewing schedule(s) on %1$s (%2$d displayed)";

    private final ScheduleDateViewPredicate predicate;

    public ViewScheduleCommand(ScheduleDateViewPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredScheduleList(predicate);
        return new CommandResult(String.format(MESSAGE_VIEW_SCHEDULE_SUCCESS, predicate.toString(),
                model.getFilteredScheduleList().size()),
                TabName.SCHEDULE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewScheduleCommand // instanceof handles nulls
                && predicate.equals(((ViewScheduleCommand) other).predicate)); // state check
    }
}
