package seedu.student.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.student.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.student.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.student.logic.parser.CliSyntax.PREFIX_MATRICULATION_NUMBER;
import static seedu.student.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.student.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import seedu.student.commons.core.Messages;
import seedu.student.commons.core.index.Index;
import seedu.student.commons.util.CollectionUtil;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.Model;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.MatriculationNumber;

public class EditAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "editAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an appointment identified in Vax@NUS "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MATRICULATION_NUMBER + "MATRICULATION NUMBER] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_START_TIME + " START TIME] "
            + "[" + PREFIX_END_TIME + "END TIME] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "2021-03-25 "
            + PREFIX_START_TIME + "11:00 "
            + PREFIX_END_TIME + "11:30 \n";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "The appointment overlaps with existing records";

    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param index of the appointment in the filtered appointment list to edit
     * @param editAppointmentDescriptor details to edit the appointment with
     */
    public EditAppointmentCommand(Index index,
                                  EditAppointmentCommand.EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(index);
        requireNonNull(editAppointmentDescriptor);

        this.index = index;
        this.editAppointmentDescriptor = new
                EditAppointmentCommand.EditAppointmentDescriptor(editAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_OUT_OF_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor);

        if (!appointmentToEdit.isSameAppointment(editedAppointment) && model.hasAppointment(editedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);

        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment));
    }

    /**
     * Creates and returns a {@code Appointment} with the details of {@code appointmentToEdit}
     * edited with {@code editAppointmentDescriptor}.
     */
    private static Appointment createEditedAppointment(Appointment appointmentToEdit,
                                                       EditAppointmentDescriptor editAppointmentDescriptor) {
        assert appointmentToEdit != null;

        MatriculationNumber updatedMatriculationNumber = editAppointmentDescriptor.getMatriculationNumber()
                .orElse(appointmentToEdit.getMatriculationNumber());
        LocalDate updatedDate = editAppointmentDescriptor.getDate().orElse(appointmentToEdit.getDate());
        LocalTime updatedStartTime = editAppointmentDescriptor.getStartTime().orElse(appointmentToEdit.getStartTime());
        LocalTime updatedEndTime = editAppointmentDescriptor.getEndTime().orElse(appointmentToEdit.getEndTime());

        return new Appointment(updatedMatriculationNumber, updatedDate, updatedStartTime, updatedEndTime);
    }


    public static class EditAppointmentDescriptor {
        private MatriculationNumber matriculationNumber;
        private LocalDate date;
        private LocalTime startTime;
        private LocalTime endTime;

        public EditAppointmentDescriptor() {}

        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setMatriculationNumber(toCopy.matriculationNumber);
            setDate(toCopy.date);
            setStartTime(toCopy.startTime);
            setEndTime(toCopy.endTime);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull( matriculationNumber, date, startTime, endTime);
        }

        public void setMatriculationNumber(MatriculationNumber matriculationNumber) {
            this.matriculationNumber = matriculationNumber;
        }

        public Optional<MatriculationNumber> getMatriculationNumber() {
            return Optional.ofNullable(matriculationNumber);
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public Optional<LocalDate> getDate() {
            return Optional.ofNullable(date);
        }

        public void setStartTime(LocalTime time) {
            this.startTime = time;
        }

        public Optional<LocalTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setEndTime(LocalTime time) {
            this.endTime = time;
        }

        public Optional<LocalTime> getEndTime() {
            return Optional.ofNullable(endTime);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppointmentCommand.EditAppointmentDescriptor)) {
                return false;
            }

            // state check
            EditAppointmentCommand.EditAppointmentDescriptor e = (EditAppointmentDescriptor) other;

            return getMatriculationNumber().equals(e.getMatriculationNumber())
                    && getDate().equals(e.getDate())
                    && getStartTime().equals(e.getStartTime())
                    && getEndTime().equals(e.getEndTime());
        }
    }
}
