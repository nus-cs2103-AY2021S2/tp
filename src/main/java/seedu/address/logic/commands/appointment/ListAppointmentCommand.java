package seedu.address.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all persons in the Appointment Schedule to the user.
 */
public class ListAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "list-appt";
    public static final String MESSAGE_SUCCESS = "Listed all Appointments";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        for (int i = 0; i < model.getAppointmentSchedule().getAppointmentList().size(); i++) {
            System.out.println(model.getAppointmentSchedule().getAppointmentList().get(i));
        }

        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
