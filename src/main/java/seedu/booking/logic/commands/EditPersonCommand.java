package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_BOOKINGS;
import static seedu.booking.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.booking.commons.core.Messages;
import seedu.booking.commons.util.CollectionUtil;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.Model;
import seedu.booking.model.Tag;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Name;
import seedu.booking.model.person.Person;
import seedu.booking.model.person.Phone;

/**
 * Edits the details of an existing person in the booking system.
 */
public class EditPersonCommand extends Command {

    public static final String COMMAND_WORD = "edit_person";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the person's email used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: eo/EMAIL "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Example: " + COMMAND_WORD + " eo/johndoe@example.com "
            + PREFIX_EMAIL + "doe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided:\n";
    public static final String MESSAGE_FIELDS = "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TAG + "TAG]\n";
    public static final String MESSAGE_DUPLICATE_EMAIL =
            "The email to be edited to belongs to another person in the booking system.";
    public static final String MESSAGE_DUPLICATE_PHONE =
            "The phone number to be edited to belongs to another person in the booking system.";

    private final Email email;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param email of the person in the filtered person list to edit.
     * @param editPersonDescriptor details to edit the person with.
     */
    public EditPersonCommand(Email email, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(email);
        requireNonNull(editPersonDescriptor);

        this.email = email;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (!lastShownList.stream().anyMatch(email::isSameEmail)) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_EMAIL);
        }

        Person personToEdit = getPersonByEmail(email, lastShownList);
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.getEmail().isSameEmail(editedPerson)
                && model.hasPersonWithEmail(editedPerson.getEmail())) {
            throw new CommandException(MESSAGE_DUPLICATE_EMAIL);
        }

        if (!personToEdit.getPhone().equals(editedPerson.getPhone())
                && model.hasPersonWithPhone(editedPerson.getPhone())) {
            throw new CommandException(MESSAGE_DUPLICATE_PHONE);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updatePersonInBookings(personToEdit.getEmail(), editedPerson.getEmail());
        model.updateFilteredBookingList(PREDICATE_SHOW_ALL_BOOKINGS);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    private static Person getPersonByEmail(Email email, List<Person> personList) {
        return personList.stream().filter(email::isSameEmail).findFirst().orElse(null);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Set<Tag> updatedTag = editPersonDescriptor.getTags().orElse(personToEdit.getTags());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedTag);
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
        return email.equals(e.email)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Set<Tag> tags;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, tags);
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
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getTags().equals(e.getTags());
        }
    }
}
