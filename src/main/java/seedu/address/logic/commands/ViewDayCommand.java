package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * A ViewDayCommand with a predicate to filter the task list with, and a date to update the calendar with.
 */
public class ViewDayCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View all tasks for the day.\n"
            + "Parameters: DATE\n"
            + "Example: " + COMMAND_WORD + " 01/01/2022";

    public static final String MESSAGE_CALENDAR_SHOWING_DATE = "Calendar is now showing %2$s %3$s.\n";

    public static final String MESSAGE_VIEW_DAY_SUCCESS = Messages.MESSAGE_TASKS_LISTED_OVERVIEW
            + MESSAGE_CALENDAR_SHOWING_DATE;

    private final Predicate<Task> predicate;
    private final LocalDate date;

    /**
     * Creates a ViewDayCommand with a predicate to filter the task list with, and a date to update the calendar with.
     *
     * @param predicate Predicate determining whether a task's date or schedule falls on the given date.
     * @param date      LocalDate object to update the calendar with.
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

        String calendarMonthText = StringUtil.toSentenceCase(date.getMonth().toString());
        return new CommandResult(String.format(
                MESSAGE_VIEW_DAY_SUCCESS, model.getFilteredTaskList().size(), calendarMonthText, date.getYear()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewDayCommand // instanceof handles nulls
                && predicate.equals(((ViewDayCommand) other).predicate)
                && date.equals(((ViewDayCommand) other).date)); // state check
    }
}
