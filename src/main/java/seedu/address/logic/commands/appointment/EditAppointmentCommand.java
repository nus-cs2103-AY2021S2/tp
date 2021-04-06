package seedu.address.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_START;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.appointment.exceptions.NegativeOrZeroDurationException;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing appointment in the appointment list.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "edit-appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment identified "
            + "by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_PATIENT + "PATIENT_INDEX] "
            + "[" + PREFIX_DOCTOR + "DOCTOR_INDEX] "
            + "[" + PREFIX_TIMESLOT_START + "TIMESLOT START] "
            + "[" + PREFIX_TIMESLOT_END + "TIMESLOT END] "
            + "[" + PREFIX_TIMESLOT_DURATION + "TIMESLOT DURATION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PATIENT + "1 "
            + PREFIX_DOCTOR + "2 "
            + PREFIX_TIMESLOT_START + "2021-05-08 09:00 "
            + PREFIX_TIMESLOT_DURATION + "1H 00M "
            + PREFIX_TAG + "severe "
            + PREFIX_TAG + "fever";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_APPOINTMENT_CONFLICT = "This appointment will result in conflicts.";
    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param index of the appointment in the filtered appointment list to edit
     * @param editAppointmentDescriptor details to edit the appointment with
     */
    public EditAppointmentCommand(Index index, EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(index);
        requireNonNull(editAppointmentDescriptor);
        this.index = index;
        this.editAppointmentDescriptor = new EditAppointmentDescriptor(editAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // get the patient, doctor and appointment lists
        List<Patient> displayedPatientRecords = model.getFilteredPatientList();
        List<Doctor> displayedDoctorRecords = model.getFilteredDoctorList();
        List<Appointment> appointmentList = model.getFilteredAppointmentList();

        // check index
        if (index.getZeroBased() >= appointmentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        //get appointment to be edited
        Appointment appointmentToEdit = appointmentList.get(index.getZeroBased());
        UUID patientUuid;
        UUID doctorUuid;

        // check if patient index is present
        if (editAppointmentDescriptor.getPatientIndex().isPresent()) {
            //check if patient index is valid
            if (editAppointmentDescriptor.patientIndex.getZeroBased() >= displayedPatientRecords.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
            }
            // assign patient
            patientUuid = displayedPatientRecords.get(editAppointmentDescriptor.patientIndex.getZeroBased()).getUuid();
            // if patient index is not present
        } else {
            patientUuid = appointmentToEdit.getPatientUuid();
        }

        // check if doctor index is present
        if (editAppointmentDescriptor.getDoctorIndex().isPresent()) {
            //check if doctor index is valid
            if (editAppointmentDescriptor.doctorIndex.getZeroBased() >= displayedDoctorRecords.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
            }
            // assign doctor
            doctorUuid = displayedDoctorRecords.get(editAppointmentDescriptor.doctorIndex.getZeroBased()).getUuid();
            // if doctor index is not present
        } else {
            doctorUuid = appointmentToEdit.getDoctorUuid();
        }

        // Timeslot
        Timeslot updatedTimeslot;
        LocalDateTime start = (editAppointmentDescriptor.getStart().isPresent()) ? editAppointmentDescriptor.start
                : appointmentToEdit.getTimeslot().getStart();
        try {
            if (editAppointmentDescriptor.getEnd().isPresent()) {
                updatedTimeslot = new Timeslot(start, editAppointmentDescriptor.end);
            } else if (editAppointmentDescriptor.getDuration().isPresent()) {
                updatedTimeslot = new Timeslot(start, editAppointmentDescriptor.duration);
            } else {
                updatedTimeslot = new Timeslot(start, appointmentToEdit.getTimeslot().getEnd());
            }
        } catch (NegativeOrZeroDurationException e) {
            throw new CommandException(Timeslot.MESSAGE_CONSTRAINTS);
        }

        // Tags
        Set<Tag> updatedTags = editAppointmentDescriptor.getTags().orElse(appointmentToEdit.getTags());
        Appointment editedAppointment = new Appointment(patientUuid, doctorUuid, updatedTimeslot, updatedTags);

        if (model.hasConflictingAppointmentExcludingTarget(appointmentToEdit, editedAppointment)) {
            throw new CommandException(MESSAGE_APPOINTMENT_CONFLICT);
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAppointmentCommand)) {
            return false;
        }

        // state check
        EditAppointmentCommand e = (EditAppointmentCommand) other;
        return index.equals(e.index)
                && editAppointmentDescriptor.equals(e.editAppointmentDescriptor);
    }

    /**
     * Stores the details to edit the appointment with. Each non-empty field value will replace the
     * corresponding field value of the appointment.
     */
    public static class EditAppointmentDescriptor {
        private Index patientIndex;
        private Index doctorIndex;
        private LocalDateTime start;
        private LocalDateTime end;
        private Duration duration;
        private Set<Tag> tags;

        public EditAppointmentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setPatientIndex(toCopy.patientIndex);
            setDoctorIndex(toCopy.doctorIndex);
            setStart(toCopy.start);
            setEnd(toCopy.end);
            setDuration(toCopy.duration);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(patientIndex, doctorIndex, start, end, duration, tags);
        }

        public void setPatientIndex(Index patientIndex) {
            this.patientIndex = patientIndex;
        }

        public Optional<Index> getPatientIndex() {
            return Optional.ofNullable(patientIndex);
        }

        public void setDoctorIndex(Index doctorIndex) {
            this.doctorIndex = doctorIndex;
        }

        public Optional<Index> getDoctorIndex() {
            return Optional.ofNullable(doctorIndex);
        }

        public void setStart(LocalDateTime start) {
            this.start = start;
        }

        public Optional<LocalDateTime> getStart() {
            return Optional.ofNullable(start);
        }

        public void setEnd(LocalDateTime end) {
            this.end = end;
        }

        public Optional<LocalDateTime> getEnd() {
            return Optional.ofNullable(end);
        }

        public void setDuration(Duration duration) {
            this.duration = duration;
        }

        public Optional<Duration> getDuration() {
            return Optional.ofNullable(duration);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppointmentDescriptor)) {
                return false;
            }

            // state check
            EditAppointmentDescriptor e = (EditAppointmentDescriptor) other;

            return getPatientIndex().equals(e.getPatientIndex())
                    && getDoctorIndex().equals(e.getDoctorIndex())
                    && getStart().equals(e.getStart())
                    && getEnd().equals(e.getEnd())
                    && getDuration().equals(e.getDuration())
                    && getTags().equals(e.getTags());
        }
    }
}
