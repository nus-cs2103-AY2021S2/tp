package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.ViewAppointmentPredicate;

/**
 * View a tutor by its index in tutor list.
 */
public class ViewAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "view_appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the appointment identified by the index number used in the displayed appointment list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_APPOINTMENT_SUCCESS = "View Appointment: %1$s";

    private final Index targetIndex;

    public ViewAppointmentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> updatedAppointmentList = model.getFilteredAppointmentList();
        if (targetIndex.getZeroBased() >= updatedAppointmentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointment = updatedAppointmentList.get(targetIndex.getZeroBased());
        model.updateFilteredAppointmentList(new ViewAppointmentPredicate(appointment));
        return new CommandResult(String.format(MESSAGE_VIEW_APPOINTMENT_SUCCESS, appointment.getEmail()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewAppointmentCommand // instanceof handles nulls
                && targetIndex.equals(((ViewAppointmentCommand) other).targetIndex)); // state check
    }
}
