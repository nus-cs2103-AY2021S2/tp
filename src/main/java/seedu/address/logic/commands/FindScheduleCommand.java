package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Finds and displays all tasks with deadline and all events with end date
 * before or on the specified date in SOChedule.
 */
public class FindScheduleCommand {
    public static final String COMMAND_WORD = "find_schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds and displays all tasks with deadline "
            + "and all events with end date before or on the specified date.\n"
            + "Parameters: DATE \n"
            + "Example: " + COMMAND_WORD + " 2021-03-29";

    public static final String MESSAGE_FIND_SCHEDULE_SUCCESS = "Found required tasks and events\n";

    private final TaskDeadlineByDatePredicate taskPredicate;
    private final EventEndDateByDatePredicate eventPredicate;

    /**
     *
     * @param taskPredicate
     * @param eventPredicate
     */
    public FindScheduleCommand(TaskDeadlineByDatePredicate taskPredicate, EventEndDateByDatePredicate eventPredicate) {
        this.taskPredicate = taskPredicate;
        this.eventPredicate = eventPredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTaskList(taskPredicate);
        model.updateFilteredEventList(eventPredicate);
        return new CommandResult(MESSAGE_FIND_SCHEDULE_SUCCESS
                + String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()))
                + String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, model.getFilteredEventList().size()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindScheduleCommand)) {
            return false;
        }

        FindScheduleCommand f = (FindScheduleCommand) other;

        return taskPredicate.equals(f.taskPredicate)
                && eventPredicate.equals(f.eventPredicate); // state check
    }
}
