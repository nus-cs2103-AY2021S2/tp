package seedu.address.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_START;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.person.Patient;
import seedu.address.model.tag.Tag;

/**
 * Adds an appointment to the appointment schedule
 */
public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "add-appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to the appointment schedule "
            + "referencing the indexes in the displayed patient records and doctor records.\n"
            + "Parameters: "
            + PREFIX_PATIENT + "PATIENT (positive integer) "
            + PREFIX_DOCTOR + "DOCTOR "
            + PREFIX_TIMESLOT_START + "TIMESLOT START "
            + "[" + PREFIX_TIMESLOT_END + "TIMESLOT END] "
            + "[" + PREFIX_TIMESLOT_DURATION + "TIMESLOT DURATION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PATIENT + "1 "
            + PREFIX_DOCTOR + "Dr. Grey "
            + PREFIX_TIMESLOT_START + "2021-01-01 00:00 "
            + PREFIX_TIMESLOT_DURATION + "1H 30M "
            + PREFIX_TAG + "severe "
            + PREFIX_TAG + "brainDamage";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_APPOINTMENT_CONFLICT = "This appointment will result in conflicts "
            + "in the appointment schedule";

    public final Index patientIndex;
    public final String doctor;
    public final Timeslot timeslot;
    public final Set<Tag> tagList;

    /**
     * Creates an AddCommand to add the specified {@code Appointment}
     */
    public AddAppointmentCommand(Index patientIndex, String doctor, Timeslot timeslot, Set<Tag> tagList) {
        requireAllNonNull(patientIndex, doctor, timeslot);

        this.patientIndex = patientIndex;
        this.doctor = doctor;
        this.timeslot = timeslot;
        this.tagList = tagList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Patient> displayedPatientRecords = model.getFilteredPatientList();

        if (patientIndex.getZeroBased() >= displayedPatientRecords.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patient = displayedPatientRecords.get(patientIndex.getZeroBased());
        Appointment toAdd = new Appointment(patient, doctor, timeslot, tagList);

        if (model.hasConflictingAppointment(toAdd)) {
            throw new CommandException(MESSAGE_APPOINTMENT_CONFLICT);
        }

        model.addAppointment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAppointmentCommand // instanceof handles nulls
                && patientIndex.equals(((AddAppointmentCommand) other).patientIndex)
                && doctor.equals(((AddAppointmentCommand) other).doctor)
                && timeslot.equals(((AddAppointmentCommand) other).timeslot)
                && tagList.equals(((AddAppointmentCommand) other).tagList));
    }

}
