package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID_TUTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonId;
import seedu.address.model.person.PersonType;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.person.Tutor;
import seedu.address.model.session.SessionId;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person (student or tutor) in the address book.
 */
public class EditPersonCommand extends Command {

    public static final String COMMAND_WORD = "edit_person";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the person's ID used. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: student or tutor ID (must be in the format of " + PREFIX_PERSON_ID_STUDENT + "ID "
            + "or " + PREFIX_PERSON_ID_TUTOR + "ID) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PERSON_ID_STUDENT + "2 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final PersonId personId;
    private final EditPersonPersonDescriptor editPersonPersonDescriptor;

    /**
     * @param personId of the person in the person list to edit
     * @param editPersonPersonDescriptor details to edit the person with
     */
    public EditPersonCommand(PersonId personId, EditPersonPersonDescriptor editPersonPersonDescriptor) {
        requireNonNull(personId);
        requireNonNull(editPersonPersonDescriptor);

        this.personId = personId;
        this.editPersonPersonDescriptor = new EditPersonPersonDescriptor(editPersonPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getUnfilteredPersonList();

        Optional<Person> personToEdit = lastShownList.stream()
                .filter(x-> x.getPersonId().equals(personId)).findAny();

        if (!personToEdit.isPresent()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person editedPerson = createEditedPerson(personToEdit.get(), editPersonPersonDescriptor);

        if (!personToEdit.get().isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit.get(), editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonPersonDescriptor editPersonPersonDescriptor)
            throws CommandException {
        assert personToEdit != null;

        PersonType originalType = personToEdit.getPersonType();
        PersonId originalId = personToEdit.getPersonId();
        Name updatedName = editPersonPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        List<SessionId> originalSessions = personToEdit.getSessions();
        Set<Tag> updatedTags = editPersonPersonDescriptor.getTags().orElse(personToEdit.getTags());

        if (originalType.equals(new PersonType("student"))) {
            return new Student(originalId, updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                    originalSessions);
        } else if (originalType.equals(new PersonType("tutor"))) {
            return new Tutor(originalId, updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags,
                    originalSessions);
        } else {
            throw new CommandException("Person's original type should be student or tutor");
        }
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
        return personId.equals(e.personId)
                && editPersonPersonDescriptor.equals(e.editPersonPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private PersonType personType;
        private PersonId personId;
        private List<SessionId> sessions = new ArrayList<>();
        public EditPersonPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonPersonDescriptor(EditPersonPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setPersonType(toCopy.personType);
            setPersonId(toCopy.personId);
            setSessions(toCopy.sessions);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
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

        public Optional<PersonType> getPersonType() {
            return Optional.ofNullable(personType);
        }

        public void setPersonType(PersonType personType) {
            this.personType = personType;
        }

        public Optional<PersonId> getPersonId() {
            return Optional.ofNullable(personId);
        }

        public void setPersonId(PersonId personId) {
            this.personId = personId;
        }

        public Optional<List<SessionId>> getSessions() {
            return Optional.ofNullable(sessions);
        }

        public void setSessions(List<SessionId> sessions) {
            this.sessions.addAll(sessions);
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
            if (!(other instanceof EditPersonPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonPersonDescriptor e = (EditPersonPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags())
                    && getPersonType().equals(e.getPersonType())
                    && getPersonId().equals(e.getPersonId())
                    && getSessions().equals(e.getSessions());
        }
    }
}
