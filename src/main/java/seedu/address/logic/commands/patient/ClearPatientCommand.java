package seedu.address.logic.commands.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_CLEAR_APPOINTMENTS_BEFORE_PATIENTS_REQUIRED;
import static seedu.address.commons.core.Messages.MESSAGE_CLEAR_PATIENT_SUCCESS;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Patient;

/**
 * Clears the Patient Records.
 */
public class ClearPatientCommand extends Command {

    public static final String COMMAND_WORD = "clear-patient";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Patient> lastShownList = model.getFilteredPatientList();
        for (Patient patientToClear : lastShownList) {
            if (model.hasPatientInAppointmentSchedule(patientToClear)) {
                throw new CommandException(MESSAGE_CLEAR_APPOINTMENTS_BEFORE_PATIENTS_REQUIRED);
            }
        }

        model.setPatientRecords(new AddressBook<>());
        return new CommandResult(MESSAGE_CLEAR_PATIENT_SUCCESS);
    }
}
