package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.AppointmentSchedule;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "clear-appt";
    public static final String MESSAGE_SUCCESS = "Appointment Schedule has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAppointmentSchedule(new AppointmentSchedule());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
