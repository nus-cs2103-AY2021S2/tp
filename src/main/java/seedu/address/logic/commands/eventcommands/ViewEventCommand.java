package seedu.address.logic.commands.eventcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.appointmentcommands.ViewAppointmentCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.schedulecommands.ViewScheduleCommand;
import seedu.address.model.Model;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.appointment.DateViewPredicate;
import seedu.address.model.schedule.ScheduleDateViewPredicate;

/**
 * View events by its date in event list.
 */
public class ViewEventCommand extends Command {
    public static final String COMMAND_WORD = "view_event";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the events identified by date.\n"
            + "Parameters: DATE (must be in YYYY-MM-DD format)\n"
            + "Example: " + COMMAND_WORD + " 2021-03-17";

    public static final String MESSAGE_VIEW_EVENT_SUCCESS = "Viewing events on %1$s (%2$d appointment(s) and "
            + "%3$d schedule(s) displayed)";

    private final AppointmentDateTime date;

    public ViewEventCommand(AppointmentDateTime date) {
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ViewAppointmentCommand appointmentCommand = new ViewAppointmentCommand(new DateViewPredicate(date));
        ViewScheduleCommand viewScheduleCommand = new ViewScheduleCommand(new ScheduleDateViewPredicate(date));

        appointmentCommand.execute(model);
        viewScheduleCommand.execute(model);
        return new CommandResult(String.format(MESSAGE_VIEW_EVENT_SUCCESS, date.toDateString(),
                model.getFilteredAppointmentList().size(), model.getFilteredScheduleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewEventCommand // instanceof handles nulls
                && date.equals(((ViewEventCommand) other).date)); // state check
    }
}
