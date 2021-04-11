package seedu.address.logic.commands.doctor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_CLEAR_APPOINTMENTS_BEFORE_DOCTORS_REQUIRED;
import static seedu.address.commons.core.Messages.MESSAGE_CLEAR_DOCTOR_SUCCESS;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Doctor;

/**
 * Clears the address book.
 */
public class ClearDoctorCommand extends Command {

    public static final String COMMAND_WORD = "clear-doctor";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Doctor> lastShownList = model.getFilteredDoctorList();
        for (Doctor doctorToClear : lastShownList) {
            if (model.hasDoctorInAppointmentSchedule(doctorToClear)) {
                throw new CommandException(MESSAGE_CLEAR_APPOINTMENTS_BEFORE_DOCTORS_REQUIRED);
            }
        }

        model.setDoctorRecords(new AddressBook<>());
        return new CommandResult(MESSAGE_CLEAR_DOCTOR_SUCCESS);
    }
}
