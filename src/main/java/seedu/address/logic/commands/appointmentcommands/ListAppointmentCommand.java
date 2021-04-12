package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENT;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all appointments in the appointment list to the user.
 */
public class ListAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "list_appointments";

    public static final String MESSAGE_SUCCESS = "Listed all appointments (%1$s displayed)";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENT);
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredAppointmentList().size()),
                TabName.APPOINTMENT);
    }
}
