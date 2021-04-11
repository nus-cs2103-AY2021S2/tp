package seedu.address.logic.commands.patient;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Patient;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeletePatientCommand extends Command {

    public static final String COMMAND_WORD = "delete-patient";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the patient identified by the index number used in the displayed patient records.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String FORCE_DELETE_MESSAGE_USAGE = COMMAND_WORD + " --force"
            + ": Deletes the patient identified by the index number used in the displayed patient records,\n"
            + "along with all the existing appointments associated with the patient in the appointment schedule.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " --force " + " 1";

    private final Index targetIndex;
    private final boolean isForceDelete;

    /**
     * Constructor: creates a DeletePatientCommand
     * @param targetIndex index of patient to be deleted
     * @param isForceDelete true if force delete is required
     */
    public DeletePatientCommand(Index targetIndex, boolean isForceDelete) {
        this.targetIndex = targetIndex;
        this.isForceDelete = isForceDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToDelete = lastShownList.get(targetIndex.getZeroBased());

        if (isForceDelete) {
            model.deletePatientAppointments(patientToDelete.getUuid());
        }

        // checks if patient has any existing appointments
        if (model.hasPatientInAppointmentSchedule(patientToDelete)) {
            throw new CommandException(String.format(
                    Messages.MESSAGE_FORCE_DELETE_PATIENT_REQUIRED, FORCE_DELETE_MESSAGE_USAGE));
        }

        model.deletePatient(patientToDelete);
        return new CommandResult(String.format(Messages.MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePatientCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePatientCommand) other).targetIndex)); // state check
    }
}
