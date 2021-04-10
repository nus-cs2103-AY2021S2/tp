package seedu.address.logic.commands.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_MAIN_PATIENTS;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.medical.Appointment;
import seedu.address.model.medical.MedicalRecord;
import seedu.address.model.person.Patient;

public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment with a patient "
            + "identified by the index number used in the displayed patient list. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DATE + "DATE] \n" + Appointment.MESSAGE_CONSTRAINTS_DATE_FORMAT
            + "\nExample: " + COMMAND_WORD + " 1 " + PREFIX_DATE + "24051800";

    public static final String MESSAGE_SUCCESS = "Appointment added: %s";

    public static final String MESSAGE_ARCHIVED_PERSON = "You cannot add appointments to an archived patient.\n"
            + "Unarchive the patient and try again.";

    private final Index index;
    private final LocalDateTime date;

    /**
     * @param index of the person in the filtered person list to edit
     * @param date  details to edit the person with
     */
    public AddAppointmentCommand(Index index, LocalDateTime date) {
        requireNonNull(index);
        requireNonNull(date);

        this.index = index;
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patient = lastShownList.get(index.getZeroBased());
        for (MedicalRecord rec : patient.getRecords()) {
            System.out.println(rec.toString());
        }

        if (patient.isArchived()) {
            throw new CommandException(MESSAGE_ARCHIVED_PERSON);
        }
        Appointment appointment = new Appointment(date);
        patient.addAppointment(appointment);
        model.setPerson(patient, patient);
        model.selectPatient(patient);
        model.updateFilteredPersonList(PREDICATE_SHOW_MAIN_PATIENTS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, appointment.getDateDisplay()),
                false, false, patient, null, null, null, false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAppointmentCommand)) {
            return false;
        }

        // state check
        AddAppointmentCommand c = (AddAppointmentCommand) other;
        return index.equals(c.index)
                && date.equals(c.date);
    }
}
