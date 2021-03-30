package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.DateViewPredicate;

/**
 * View appointments by its date in appointment list.
 */
public class ViewAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "view_appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the appointment identified by the index number used in the displayed appointment list.\n"
            + "Parameters: DATE (must be in YYYY-MM-DD format)\n"
            + "Example: " + COMMAND_WORD + " 2021-03-17";

    public static final String MESSAGE_VIEW_APPOINTMENT_SUCCESS = "View Appointment: %1$s";

    private final DateViewPredicate predicate;

    public ViewAppointmentCommand(DateViewPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        return new CommandResult(String.format(MESSAGE_VIEW_APPOINTMENT_SUCCESS, predicate.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewAppointmentCommand // instanceof handles nulls
                && predicate.equals(((ViewAppointmentCommand) other).predicate)); // state check
    }
}
