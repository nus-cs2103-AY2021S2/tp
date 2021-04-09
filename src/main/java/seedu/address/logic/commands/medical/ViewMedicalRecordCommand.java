package seedu.address.logic.commands.medical;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.medical.MedicalRecord;
import seedu.address.model.person.Patient;

public class ViewMedicalRecordCommand extends Command {

    public static final String COMMAND_WORD = "vrec";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views a past medical report of a patient "
            + "identified by the index number of the medical record of the currently selected patient. \n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 3";

    public static final String MESSAGE_SUCCESS = "New Medical Record for: %s";

    private final Index index;

    /**
     * @param index of the patient in the filtered patient list to edit
     */
    public ViewMedicalRecordCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Patient patient = model.getSelectedPatient();
        if (patient == null) {
            throw new CommandException(Messages.MESSAGE_NOT_VIEWING_PATIENT);
        }
        if (patient.getRecords().size() == 0) {
            throw new CommandException(String.format(Messages.MESSAGE_NO_MEDICAL_RECORD_FOUND,
                                                        patient.getName().fullName, patient.getName().fullName));
        } else if (index.getZeroBased() >= patient.getRecords().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEDICAL_RECORD_INDEX);
        }
        MedicalRecord mrec = patient.getRecords().get(index.getZeroBased());
        return new CommandResult(String.format(MESSAGE_SUCCESS, patient.getName()), false, true,
                patient, mrec, null, null, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewMedicalRecordCommand // instanceof handles nulls
                && index.equals(((ViewMedicalRecordCommand) other).index)); // state check
    }
}
