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
import java.util.UUID;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDisplay;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.person.Doctor;
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
            + PREFIX_PATIENT + "PATIENT INDEX (must be a positive integer) "
            + PREFIX_DOCTOR + "DOCTOR INDEX (must be a positive integer)"
            + PREFIX_TIMESLOT_START + "TIMESLOT START "
            + "[" + PREFIX_TIMESLOT_END + "TIMESLOT END] "
            + "[" + PREFIX_TIMESLOT_DURATION + "TIMESLOT DURATION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PATIENT + "1 "
            + PREFIX_DOCTOR + "2 "
            + PREFIX_TIMESLOT_START + "2021-01-01 00:00 "
            + PREFIX_TIMESLOT_DURATION + "1H 30M "
            + PREFIX_TAG + "severe "
            + PREFIX_TAG + "brainDamage";

    public final Index patientIndex;
    public final Index doctorIndex;
    public final Timeslot timeslot;
    public final Set<Tag> tagList;

    /**
     * Creates an AddCommand to add the specified {@code Appointment}
     */
    public AddAppointmentCommand(Index patientIndex, Index doctorIndex, Timeslot timeslot, Set<Tag> tagList) {
        requireAllNonNull(patientIndex, doctorIndex, timeslot);

        this.patientIndex = patientIndex;
        this.doctorIndex = doctorIndex;
        this.timeslot = timeslot;
        this.tagList = tagList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Patient> displayedPatientRecords = model.getFilteredPatientList();
        assert displayedPatientRecords != null : "getFilteredPatientList method should not return null";

        if (patientIndex.getZeroBased() >= displayedPatientRecords.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }
        Patient patient = displayedPatientRecords.get(patientIndex.getZeroBased());
        UUID patientUuid = patient.getUuid();


        List<Doctor> displayedDoctorRecords = model.getFilteredDoctorList();
        assert displayedDoctorRecords != null : "getFilteredDoctorList method should not return null";

        if (doctorIndex.getZeroBased() >= displayedDoctorRecords.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
        }
        Doctor doctor = displayedDoctorRecords.get(doctorIndex.getZeroBased());
        UUID doctorUuid = doctor.getUuid();


        Appointment toAdd = new Appointment(patientUuid, doctorUuid, timeslot, tagList);

        if (model.hasConflictingAppointment(toAdd)) {
            throw new CommandException(Messages.MESSAGE_ADD_APPOINTMENT_CONFLICT);
        }

        model.addAppointment(toAdd);
        return new CommandResult(String.format(Messages.MESSAGE_ADD_APPOINTMENT_SUCCESS,
                new AppointmentDisplay(patient, doctor, timeslot, tagList)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAppointmentCommand // instanceof handles nulls
                && patientIndex.equals(((AddAppointmentCommand) other).patientIndex)
                && doctorIndex.equals(((AddAppointmentCommand) other).doctorIndex)
                && timeslot.equals(((AddAppointmentCommand) other).timeslot)
                && tagList.equals(((AddAppointmentCommand) other).tagList));
    }

}
