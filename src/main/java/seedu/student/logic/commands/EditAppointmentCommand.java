package seedu.student.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.student.logic.commands.AddAppointmentCommand.MESSAGE_OVERLAPPING_APPOINTMENT;
import static seedu.student.logic.commands.AddAppointmentCommand.MESSAGE_STUDENT_DOES_NOT_EXIST;
import static seedu.student.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.student.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.student.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.student.model.Model.PREDICATE_SHOW_ALL_APPOINTMENT_LISTS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import seedu.student.commons.util.CollectionUtil;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.Model;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.MatriculationNumber;

public class EditAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "editAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an appointment identified in Vax@NUS \n"
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: Matriculation Number "
            + PREFIX_DATE + "DATE "
            + PREFIX_START_TIME + "START TIME \n"
            + "Example: " + COMMAND_WORD + " A01234567X "
            + PREFIX_DATE + "2021-03-25 "
            + PREFIX_START_TIME + "11:00 \n";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "The appointment clashes with another appointment.";
    public static final String MESSAGE_APPOINTMENT_DOES_NOT_EXIST = "The requested appointment does not exist.";

    private final MatriculationNumber matriculationNumber;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param matriculationNumber of the student in the appointment in the filtered appointment list to edit
     * @param editAppointmentDescriptor details to edit the appointment with
     */
    public EditAppointmentCommand(MatriculationNumber matriculationNumber,
                                  EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(matriculationNumber);
        requireNonNull(editAppointmentDescriptor);

        this.matriculationNumber = matriculationNumber;
        this.editAppointmentDescriptor = new EditAppointmentDescriptor(editAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Appointment appointmentToEdit = model.getAppointmentToEdit(matriculationNumber);
        if (appointmentToEdit == null) {
            throw new CommandException(MESSAGE_APPOINTMENT_DOES_NOT_EXIST);
        }

        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor);

        boolean studentExists = model.isExistingMatricNumber(editedAppointment.getMatriculationNumber());

        if (model.hasOverlappingAppointment(editedAppointment)) {
            throw new CommandException(MESSAGE_OVERLAPPING_APPOINTMENT);
        } else if (!studentExists) {
            throw new CommandException(MESSAGE_STUDENT_DOES_NOT_EXIST);
        }


        if (!appointmentToEdit.isSameAppointment(editedAppointment) && model.hasAppointment(editedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENT_LISTS, PREDICATE_SHOW_ALL_APPOINTMENTS);

        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment));
    }

    /**
     * Creates and returns a {@code Appointment} with the details of {@code appointmentToEdit}
     * edited with {@code editAppointmentDescriptor}.
     */
    private static Appointment createEditedAppointment(Appointment appointmentToEdit,
                                                       EditAppointmentDescriptor editAppointmentDescriptor) {
        assert appointmentToEdit != null;
        MatriculationNumber matriculationNumber = appointmentToEdit.getMatriculationNumber();
        LocalDate updatedDate = editAppointmentDescriptor.getDate().orElse(appointmentToEdit.getDate());
        LocalTime updatedStartTime = editAppointmentDescriptor.getStartTime().orElse(appointmentToEdit.getStartTime());

        return new Appointment(matriculationNumber, updatedDate, updatedStartTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditAppointmentCommand // instanceof handles nulls
                && editAppointmentDescriptor.equals(((EditAppointmentCommand) other).editAppointmentDescriptor));
    }

    public static class EditAppointmentDescriptor {
        private MatriculationNumber matriculationNumber;
        private LocalDate date;
        private LocalTime startTime;

        public EditAppointmentDescriptor() {}

        /**
         * Constructor for an EditAppointmentDescriptor, which copies an existing EditAppointmentDescriptor.
         * @param toCopy the EditAppointmentDescriptor to be copied.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setMatriculationNumber(toCopy.matriculationNumber);
            setDate(toCopy.date);
            setStartTime(toCopy.startTime);
        }

        @Override
        public String toString() {
            return matriculationNumber.toString() + " " + date.toString() + " " + startTime.toString();
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(date, startTime);
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
                    && getStartTime().equals(e.getStartTime());
        }
    }
}
