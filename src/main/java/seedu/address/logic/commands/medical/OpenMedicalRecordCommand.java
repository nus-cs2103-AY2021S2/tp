package seedu.address.logic.commands.medical;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Patient;

public class OpenMedicalRecordCommand extends Command {

    public static final String COMMAND_WORD = "mrec";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Opens an editor for a medical report for a patient "
            + "identified by the index number used in the displayed patient list. \n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 3";

    public static final String MESSAGE_SUCCESS = "New Medical Record for: %s";

    private final Index index;

    /**
     * @param index of the patient in the filtered patient list to edit
     */
    public OpenMedicalRecordCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Patient patient = lastShownList.get(index.getZeroBased());
        model.selectPatient(patient);
        return new CommandResult(String.format(MESSAGE_SUCCESS, patient.getName()), false, true,
                patient, null, null, null, false);
    }
}
