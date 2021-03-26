package seedu.address.logic.commands.patient;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Patient;

import java.util.List;

/**
 * Clears the address book.
 */
public class ClearPatientCommand extends Command {

    public static final String COMMAND_WORD = "clear-patient";
    public static final String MESSAGE_SUCCESS = "Patient records has been cleared!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Patient> lastShownList = model.getFilteredPatientList();
        for (Patient patientToClear : lastShownList) {
            if (model.hasPatientInAppointmentSchedule(patientToClear)) {
                throw new CommandException(Messages.MESSAGE_CLEAR_APPOINTMENTS_REQUIRED);
            }
        }

        model.setPatientRecords(new AddressBook<>());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
