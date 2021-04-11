package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHILD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Address;
import seedu.address.model.Model;
import seedu.address.model.Name;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.contact.Contact;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing appointment in the AppointmentBook.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "editAppt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment identified "
            + "by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_CONTACT + "CONTACT]... "
            + "[" + PREFIX_CHILD + "CHILDTAG]... \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Parent Support Group Meeting "
            + PREFIX_DATE + "22/01/2021 10:00";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Appointment: \n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param index of the contact in the filtered appointment list to edit
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
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor);

        if (!appointmentToEdit.isSameAppointment(editedAppointment) && model.hasAppointment(editedAppointment)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        // model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment));
    }

    /**
     * Creates and returns a {@code Contact} with the details of {@code personToEdit}
     * edited with {@code editedAppointmentDescriptor}.
     */
    private static Appointment createEditedAppointment(Appointment apptToEdit,
                                                  EditAppointmentDescriptor editedAppointmentDescriptor) {
        assert apptToEdit != null;

        Name updatedName = editedAppointmentDescriptor.getName().orElse(apptToEdit.getName());
        Address updatedAddress = editedAppointmentDescriptor.getAddress().orElse(apptToEdit.getAddress());
        DateTime updatedDateTime = editedAppointmentDescriptor.getDateTime().orElse(apptToEdit.getDateTime());
        Set<Contact> updatedContacts = editedAppointmentDescriptor.getContacts().orElse(apptToEdit.getContacts());
        Set<Tag> updatedTags = editedAppointmentDescriptor.getTags().orElse(apptToEdit.getTags());

        return new Appointment(updatedName, updatedAddress, updatedDateTime, updatedContacts, updatedTags);
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
     * Stores the details to edit the contact with. Each non-empty field value will replace the
     * corresponding field value of the contact.
     */
    public static class EditAppointmentDescriptor {
        private Name name;
        private Address address;
        private DateTime dateTime;
        private Set<Contact> contacts;
        private Set<Tag> tags;

        public EditAppointmentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setName(toCopy.name);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setDateTime(toCopy.dateTime);
            setContacts(toCopy.contacts);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, address, dateTime, contacts, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setDateTime(DateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Optional<DateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        public void setContacts(Set<Contact> contacts) {
            this.contacts = (contacts != null) ? new HashSet<>(contacts) : null;
        }

        public Optional<Set<Contact>> getContacts() {
            return (contacts != null) ? Optional.of(Collections.unmodifiableSet(contacts)) : Optional.empty();
        }

        /**
         * Adds additional {@code contacts} to this object's {@code contacts}.
         */
        public void addAllContacts(Set<Contact> contacts) {
            if (this.contacts == null) {
                setContacts(contacts);
            } else {
                this.contacts.addAll(contacts);
            }
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Adds additional {@code tags} to this object's {@code tags}.
         */
        public void addAllTags(Set<Tag> tags) {
            if (this.tags == null) {
                setTags(tags);
            } else {
                this.tags.addAll(tags);
            }
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

            return getName().equals(e.getName())
                    && getAddress().equals(e.getAddress())
                    && getDateTime().equals(e.getDateTime())
                    && getContacts().equals(e.getContacts())
                    && getTags().equals(e.getTags());
        }
    }
}
