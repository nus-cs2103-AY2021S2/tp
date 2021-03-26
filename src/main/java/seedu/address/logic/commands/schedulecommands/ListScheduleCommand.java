package seedu.address.logic.commands.schedulecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.schedule.ScheduleModel.PREDICATE_SHOW_ALL_SCHEDULE;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all schedules in the schedule list to the user.
 */
public class ListScheduleCommand extends Command {

    public static final String COMMAND_WORD = "list_schedules";

    public static final String MESSAGE_SUCCESS = "Listed all schedules";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULE);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
