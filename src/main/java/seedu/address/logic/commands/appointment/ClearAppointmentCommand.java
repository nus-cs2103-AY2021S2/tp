package seedu.address.logic.commands.appointment;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AppointmentSchedule;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "clear-appt";
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAppointmentSchedule(new AppointmentSchedule());
        return new CommandResult(Messages.MESSAGE_CLEAR_APPOINTMENT_SUCCESS);
    }
}
