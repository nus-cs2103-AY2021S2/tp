package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Appointment;
import seedu.address.model.Model;

/**
 * Deletes an appointment identified using it's displayed index from the appointment list.
 */
public class DeleteAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "delete_appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the appointment identified by the index number used in the displayed appointment list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Appointment: %1$s";
    public static final String MESSAGE_DELETE_APPOINTMENT_FAILURE = "Appointment does"
            + " not exists in appointment list.";

    private final Index targetIndex;
    private final Appointment toDelete;

    public DeleteAppointmentCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
        this.toDelete = null;
    }

    public DeleteAppointmentCommand(Appointment toDelete) {
        requireNonNull(toDelete);
        this.targetIndex = null;
        this.toDelete = toDelete;
    }

    /**
     * Deletes appointment if exists in appointment list (Offer two ways to delete by
     * index or by appointment)
     * @param model {@code Model} which the command should operate on.
     * @return Command Result indicating success or failure of delete operation
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Delete by appointment
        if (targetIndex == null) {
            if (model.hasAppointment(toDelete)) {
                model.removeAppointment(toDelete);
                return new CommandResult(MESSAGE_DELETE_APPOINTMENT_SUCCESS);
            } else {
                return new CommandResult(MESSAGE_DELETE_APPOINTMENT_FAILURE);
            }
        } else {
            // Delete by index
            try {
                model.removeAppointmentIndex(targetIndex.getZeroBased());
                return new CommandResult(MESSAGE_DELETE_APPOINTMENT_SUCCESS);
            } catch (IndexOutOfBoundsException e) {
                return new CommandResult(MESSAGE_DELETE_APPOINTMENT_FAILURE);
            }
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAppointmentCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteAppointmentCommand) other).targetIndex)); // state check
    }
}
