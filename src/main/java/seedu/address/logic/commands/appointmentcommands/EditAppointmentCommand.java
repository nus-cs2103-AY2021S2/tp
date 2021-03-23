package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_TO;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENT;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.subject.SubjectName;

/**
 * Edits the details of an existing appointment in the appointment list.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "edit_appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment identified "
            + "by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_SUBJECT_NAME + "SUBJECT] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TIME_FROM + "TIME FROM] "
            + "[" + PREFIX_TIME_TO + "TIME TO] "
            + "[" + PREFIX_LOCATION + "LOCATION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EMAIL + "chloelim@example.com "
            + PREFIX_SUBJECT_NAME + "Science";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists.";

    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param index of the appointment in the filtered appointment list to edit
     */
    public EditAppointmentCommand(
            Index index,
            EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(index);
        requireNonNull(editAppointmentDescriptor);

        this.index = index;
        this.editAppointmentDescriptor = editAppointmentDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor);

        if (!appointmentToEdit.equals(editedAppointment) && model.hasAppointment(editedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENT);
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Appointment createEditedAppointment(
            Appointment appointmentToEdit,
            EditAppointmentDescriptor editAppointmentDescriptor) {
        assert appointmentToEdit != null;

        Name updatedName =
                editAppointmentDescriptor.getName().orElse(appointmentToEdit.getName());
        SubjectName updatedSubjectName = editAppointmentDescriptor.getSubjectName()
                .orElse(appointmentToEdit.getSubject());
        AppointmentDateTime updatedTimeFrom =
                editAppointmentDescriptor.getTimeFrom().orElse(appointmentToEdit.getTimeFrom());
        AppointmentDateTime updatedTimeTo =
                editAppointmentDescriptor.getTimeTo().orElse(appointmentToEdit.getTimeTo());
        Address updatedAddress = editAppointmentDescriptor.getAddress().orElse(appointmentToEdit.getLocation());

        return new Appointment(updatedName, updatedSubjectName, updatedTimeFrom,
                updatedTimeTo, updatedAddress);
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
        private Name name;
        private SubjectName subjectName;
        private AppointmentDateTime timeFrom;
        private AppointmentDateTime timeTo;
        private Address address;

        public EditAppointmentDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setName(toCopy.name);
            setSubjectName(toCopy.subjectName);
            setTimeFrom(toCopy.timeFrom);
            setTimeTo(toCopy.timeTo);
            setAddress(toCopy.address);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, subjectName, timeFrom, address);
        }

        public void setName(Name email) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setSubjectName(SubjectName subjectName) {
            this.subjectName = subjectName;
        }

        public Optional<SubjectName> getSubjectName() {
            return Optional.ofNullable(subjectName);
        }

        public void setTimeFrom(AppointmentDateTime timeFrom) {
            this.timeFrom = timeFrom;
        }

        public Optional<AppointmentDateTime> getTimeFrom() {
            return Optional.ofNullable(this.timeFrom);
        }

        public void setTimeTo(AppointmentDateTime timeTo) {
            this.timeTo = timeTo;
        }

        public Optional<AppointmentDateTime> getTimeTo() {
            return Optional.ofNullable(this.timeTo);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
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
                    && getSubjectName().equals(e.getSubjectName())
                    && getTimeFrom().equals(e.getTimeFrom())
                    && getTimeTo().equals(e.getTimeTo())
                    && getAddress().equals(e.getAddress());
        }
    }
}
