package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * A ViewDayCommand with a predicate to filter the task list with, and a date to update the calendar with.
 */
public class ViewDayCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View all tasks for the day and shows free timings for "
            + "the day. Date specified should be a future date after today.\n"
            + "Parameters: DATE\n"
            + "Example: " + COMMAND_WORD + " 01/01/2022";

    private final Predicate<Task> predicate;
    private final LocalDate date;

    /**
     * Creates a ViewDayCommand with a predicate to filter the task list with, and a date to update the calendar with.
     *
     * @param predicate Predicate determining whether a task's deadline or schedule falls on the given date.
     * @param date LocalDate object to update the calendar with.
     */
    public ViewDayCommand(Predicate<Task> predicate, LocalDate date) {
        this.predicate = predicate;
        this.date = Objects.requireNonNullElseGet(date, LocalDate::now);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        model.setCalendarDate(date);

        // getting free timings in the day // not implemented yet
//        List<Task> filteredTaskList = model.getFilteredTaskList();
//        List<Duration> durationList = new ArrayList<>();
//        for (Task task : filteredTaskList) {
//            durationList.add(task.getDuration());
//        }
//        Collections.sort(durationList);
//
//        List<Duration> freeTimings = new ArrayList<>();
//        Duration startOfTiming = new Duration("00:00-00:00");
//        for (Duration duration : durationList) {
//            if (startOfTiming.compareTo(duration) == 1) {
//
//            }
//        }

        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }
}
