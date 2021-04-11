package seedu.address.logic.commands.persons;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MEETINGS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.meetings.EditMeetingCommand;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.meeting.DateTime;
import seedu.address.model.meeting.Description;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;
import seedu.address.model.meeting.Priority;
import seedu.address.model.meeting.UniqueMeetingList;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.Phone;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditPersonCommand extends Command {

    public static final String COMMAND_WORD = "editp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_GROUP + "GROUP]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditPersonCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (!model.getFilteredMeetingListByPersonConnection(personToEdit).isEmpty()) {
            updatePersonMeetingConnection(personToEdit, editedPerson, model);
            model.updateFilteredMeetingList(PREDICATE_SHOW_ALL_MEETINGS);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        PersonName updatedPersonName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Group> updatedGroups = editPersonDescriptor.getGroups().orElse(personToEdit.getGroups());

        return new Person(updatedPersonName, updatedPhone, updatedEmail, updatedAddress, updatedGroups);
    }

    private void updatePersonMeetingConnection(Person personToEdit, Person editedPerson, Model model) {
        UniqueMeetingList toEditPersonRelatedMeetings = model.getUniqueMeetingListByPersonConnection(personToEdit);
        Set<Meeting> toEditPersonRelatedMeetingsSet = new HashSet<>();
        // Do a deep copy
        for (Meeting meeting : toEditPersonRelatedMeetings) {
            toEditPersonRelatedMeetingsSet.add(meeting);
        }
        // Start to edit connection(i.e. reconstruct connection)
        for (Meeting meetingToEdit : toEditPersonRelatedMeetingsSet) {
            Meeting meetingEdited = createEditedMeeting(meetingToEdit, new EditMeetingCommand.EditMeetingDescriptor());
            editConnectionsToPersons(meetingToEdit, meetingEdited, model, personToEdit, editedPerson);
            model.updateMeeting(meetingToEdit, meetingEdited);
        }
        model.deleteAllPersonMeetingConnectionByPerson(personToEdit);
    }

    private void editConnectionsToPersons(Meeting toDelete, Meeting toAdd, Model model,
                                          Person personToDelete, Person personToAdd) {
        Set<Person> prevPersonsConnection = toDelete.getConnectionToPerson();
        toAdd.setPersonMeetingConnection(model.getPersonMeetingConnection());

        prevPersonsConnection.remove(personToDelete);
        prevPersonsConnection.add(personToAdd);
        Set<Person> updatedPersonsConnection = prevPersonsConnection;

        model.deleteAllPersonMeetingConnectionByMeeting(toDelete);

        for (Person allPersonToAddConnection : updatedPersonsConnection) {
            model.addPersonMeetingConnection(allPersonToAddConnection, toAdd);
        }
    }
    /**
     * Creates and returns a {@code Meeting} with the details of {@code meetingToEdit}
     * edited with {@code editMeetingDescriptor}.
     */
    private static Meeting createEditedMeeting(Meeting meetingToEdit,
                                               EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptor) {
        assert meetingToEdit != null;

        MeetingName updatedMeetingName = editMeetingDescriptor
                .getName()
                .orElse(meetingToEdit.getName());
        DateTime updatedStart = editMeetingDescriptor
                .getStart()
                .orElse(meetingToEdit.getStart());
        DateTime updatedTerminate = editMeetingDescriptor
                .getTerminate()
                .orElse(meetingToEdit.getTerminate());
        Priority updatedPriority = editMeetingDescriptor
                .getPriority()
                .orElse(meetingToEdit.getPriority());
        Description updatedDescription = editMeetingDescriptor
                .getDescription()
                .orElse(meetingToEdit.getDescription());
        Set<Group> updatedGroups = editMeetingDescriptor
                .getGroups()
                .orElse(meetingToEdit.getGroups());

        return new Meeting(updatedMeetingName, updatedStart,
                updatedTerminate, updatedPriority, updatedDescription, updatedGroups);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditPersonCommand)) {
            return false;
        }

        // state check
        EditPersonCommand e = (EditPersonCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private PersonName personName;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Group> groups;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code groups} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.personName);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setGroups(toCopy.groups);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(personName, phone, email, address, groups);
        }

        public void setName(PersonName personName) {
            this.personName = personName;
        }

        public Optional<PersonName> getName() {
            return Optional.ofNullable(personName);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code groups} to this object's {@code groups}.
         * A defensive copy of {@code groups} is used internally.
         */
        public void setGroups(Set<Group> groups) {
            this.groups = (groups != null) ? new HashSet<>(groups) : null;
        }

        /**
         * Returns an unmodifiable group set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code groups} is null.
         */
        public Optional<Set<Group>> getGroups() {
            return (groups != null) ? Optional.of(Collections.unmodifiableSet(groups)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getGroups().equals(e.getGroups());
        }
    }
}
