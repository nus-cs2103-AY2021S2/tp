package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.EventFindSchedulePredicate;
import seedu.address.model.task.TaskFindSchedulePredicate;

/**
 * Finds and displays uncompleted tasks with deadline before or on the specified date
 * and events with start date before or on the specified date and end date after or on the specified date.
 */
public class FindScheduleCommand extends Command {
    public static final String COMMAND_WORD = "find_schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds and displays ongoing tasks and events before or on the specified date.\n"
            + "Ongoing tasks refer to uncompleted tasks with deadline before or on the specified date.\n"
            + "Ongoing events refer to events with start date before or on the specified date "
            + "and end date after or on the specified date.\n"
            + "Parameters: DATE (one single valid date in YYYY-MM-DD format)\n"
            + "Example: " + COMMAND_WORD + " 2021-03-29";

    public static final String MESSAGE_FIND_SCHEDULE_SUCCESS = "Displayed the required tasks and events.\n";

    private final TaskFindSchedulePredicate taskPredicate;
    private final EventFindSchedulePredicate eventPredicate;

    /**
     * Creates a FindScheduleCommand to find the desired tasks and events.
     * @param taskPredicate a predicate that tests a Task's deadline is before or on the by date given.
     * @param eventPredicate a predicate that tests a Event's start date is before or on the by date given.
     */
    public FindScheduleCommand(TaskFindSchedulePredicate taskPredicate,
                               EventFindSchedulePredicate eventPredicate) {
        this.taskPredicate = taskPredicate;
        this.eventPredicate = eventPredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTaskList(taskPredicate);
        model.updateFilteredEventList(eventPredicate);
        return new CommandResult(MESSAGE_FIND_SCHEDULE_SUCCESS
                + String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size())
                + "\n"
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
