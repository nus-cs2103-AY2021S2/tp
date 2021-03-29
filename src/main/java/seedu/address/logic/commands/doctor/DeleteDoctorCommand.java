package seedu.address.logic.commands.doctor;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Doctor;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteDoctorCommand extends Command {

    public static final String COMMAND_WORD = "delete-doctor";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String FORCE_DELETE_MESSAGE_USAGE = COMMAND_WORD + " --force"
            + ": Deletes the person identified by the index number used in the displayed person list,\n"
            + "along with all the existing appointments associated with the person in the appointment schedule.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " --force " + " 1";

    private final Index targetIndex;

    public DeleteDoctorCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Doctor> lastShownList = model.getFilteredDoctorList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
        }

        Doctor doctorToDelete = lastShownList.get(targetIndex.getZeroBased());

        // checks if doctor has any existing appointments
        if (model.hasDoctorInAppointmentSchedule(doctorToDelete)) {
            throw new CommandException(String.format(
                    Messages.MESSAGE_FORCE_DELETE_PATIENT_REQUIRED, FORCE_DELETE_MESSAGE_USAGE));
        }

        model.deleteDoctor(doctorToDelete);
        return new CommandResult(String.format(Messages.MESSAGE_DELETE_DOCTOR_SUCCESS, doctorToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDoctorCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteDoctorCommand) other).targetIndex)); // state check
    }
}
