package seedu.address.logic.commands.appointmentcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_APPOINTMENT;
import static seedu.address.commons.core.Messages.MESSAGE_TUTOR_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_TUTOR_DOES_NOT_TEACH_SUBJECT;
import static seedu.address.commons.core.Messages.MESSAGE_UNABLE_TO_EDIT_PAST_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_TO;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENT;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.DateTimeValidationUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.tutor.Address;
import seedu.address.model.tutor.Name;

/**
 * Edits the details of an existing appointment in the appointment list.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "edit_appointment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment identified "
            + "by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_SUBJECT_NAME + "SUBJECT] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TIME_FROM + "TIME FROM] "
            + "[" + PREFIX_TIME_TO + "TIME TO] "
            + "[" + PREFIX_LOCATION + "LOCATION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "chloe lim "
            + PREFIX_SUBJECT_NAME + "Science "
            + PREFIX_DATE + "2021-1-5 "
            + PREFIX_TIME_FROM + "9:00am "
            + PREFIX_TIME_TO + "11:00am";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

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
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        if (appointmentToEdit.getTimeFrom().isBeforeNow() && appointmentToEdit.getTimeTo().isBeforeNow()) {
            throw new CommandException(MESSAGE_UNABLE_TO_EDIT_PAST_APPOINTMENT);
        }

        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor);

        if (appointmentToEdit.equals(editedAppointment) || model.hasAppointment(editedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        if (!model.hasTutorByName(editedAppointment.getName())) {
            throw new CommandException(MESSAGE_TUTOR_DOES_NOT_EXIST);
        }

        if (!model.doesTutorTeachSubject(editedAppointment.getName(),
                editedAppointment.getSubject())) {
            throw new CommandException(String.format(MESSAGE_TUTOR_DOES_NOT_TEACH_SUBJECT,
                    editedAppointment.getSubject()));
        }

        DateTimeValidationUtil.validateDateTime(model, editedAppointment, appointmentToEdit);

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENT);
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment),
                TabName.APPOINTMENT);
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

        public EditAppointmentDescriptor() {
        }

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

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<SubjectName> getSubjectName() {
            return Optional.ofNullable(subjectName);
        }

        public void setSubjectName(SubjectName subjectName) {
            this.subjectName = subjectName;
        }

        public Optional<AppointmentDateTime> getTimeFrom() {
            return Optional.ofNullable(this.timeFrom);
        }

        public void setTimeFrom(AppointmentDateTime timeFrom) {
            this.timeFrom = timeFrom;
        }

        public Optional<AppointmentDateTime> getTimeTo() {
            return Optional.ofNullable(this.timeTo);
        }

        public void setTimeTo(AppointmentDateTime timeTo) {
            this.timeTo = timeTo;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAddress(Address address) {
            this.address = address;
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
