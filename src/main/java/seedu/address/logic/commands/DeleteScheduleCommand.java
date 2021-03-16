package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleDescription;

/**
 * Deletes a schedule identified using its description
 */
public class DeleteScheduleCommand extends Command {

    public static final String COMMAND_WORD = "sdelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the schedule identified by the description used in the schedule list.\n"
            + "Parameters: NAME\n"
            + "Example: " + COMMAND_WORD + " meeting";

    public static final String MESSAGE_DELETE_SCHEDULE_SUCCESS = "Deleted schedule: %1$s";

    private final ScheduleDescription targetScheduleDescription;

    public DeleteScheduleCommand(ScheduleDescription targetScheduleDescription) {
        this.targetScheduleDescription = targetScheduleDescription;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Schedule> lastShownScheduleList = model.getFilteredScheduleList();
        boolean scheduleDeleted = false;
        for (Schedule schedule : lastShownScheduleList) {
            if (targetScheduleDescription.toString().equals(schedule.getScheduleDescription().toString())) {
                model.deleteSchedule(schedule);
                scheduleDeleted = true;
                break;
            }
        }
        if (scheduleDeleted) {
            return new CommandResult(String.format(MESSAGE_DELETE_SCHEDULE_SUCCESS, targetScheduleDescription));
        } else {
            throw new CommandException(Messages.MESSAGE_SCHEDULE_DOES_NOT_EXIST);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteScheduleCommand
                && targetScheduleDescription.equals(((DeleteScheduleCommand) other).targetScheduleDescription));
    }
}
