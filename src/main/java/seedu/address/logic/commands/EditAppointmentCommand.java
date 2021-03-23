package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Time;
import seedu.address.model.name.Name;
import seedu.address.model.remark.Remark;

/**
 * Edits an appointment in the app.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "edit appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an appointment in the app. \n"
            + "Parameters: INDEX"
            + PREFIX_NAME + "NAME "
            + PREFIX_REMARK + "REMARK "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Meet Alex "
            + PREFIX_REMARK + "at M hotel "
            + PREFIX_DATE + "17-2-2021 "
            + PREFIX_TIME + "1500 ";

    public static final String MESSAGE_SUCCESS = "Edited appointment: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the app";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * Creates an EditAppointmentCommand to edit the specified {@code Appointment}.
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

        if (index.getZeroBased() >= model.getAppointmentSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = model.getAppointment(index.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor);

        if (!appointmentToEdit.isSameAppointment(editedAppointment) && model.hasAppointment(editedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedAppointment));
    }

    /**
     * Creates and returns an {@code Appointment} with the details of {@code appointmentToEdit}
     * edited with {@code editAppointmentDescriptor}.
     */
    private static Appointment createEditedAppointment(Appointment appointmentToEdit,
                                                       EditAppointmentDescriptor editAppointmentDescriptor) {
        assert appointmentToEdit != null;

        Name updatedName = editAppointmentDescriptor.getName().orElse(appointmentToEdit.getName());
        Remark updatedRemark = editAppointmentDescriptor.getRemarks().orElse(appointmentToEdit.getRemarks());
        Date updatedDate = editAppointmentDescriptor.getDate().orElse(appointmentToEdit.getDate());
        Time updatedTime = editAppointmentDescriptor.getTime().orElse(appointmentToEdit.getTime());


        return new Appointment(updatedName, updatedRemark, updatedDate, updatedTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditAppointmentCommand // instanceof handles nulls
                && editAppointmentDescriptor.equals(((EditAppointmentCommand) other).editAppointmentDescriptor));
    }

    public static class EditAppointmentDescriptor {
        private Name name;
        private Remark remark;
        private Date date;
        private Time time;

        public EditAppointmentDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setName(toCopy.name);
            setRemarks(toCopy.remark);
            setDate(toCopy.date);
            setTime(toCopy.time);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, remark, date, time);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setRemarks(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemarks() {
            return Optional.ofNullable(remark);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setTime(Time time) {
            this.time = time;
        }

        public Optional<Time> getTime() {
            return Optional.ofNullable(time);
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

            return getName().equals(e.getName())
                    && getRemarks().equals(e.getRemarks())
                    && getDate().equals(e.getDate())
                    && getTime().equals(e.getTime());
        }
    }
}
