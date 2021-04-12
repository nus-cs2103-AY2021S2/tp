package seedu.heymatez.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.heymatez.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_NEW_NAME;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.heymatez.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.heymatez.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;

import seedu.heymatez.commons.util.CollectionUtil;
import seedu.heymatez.logic.commands.exceptions.CommandException;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.person.Email;
import seedu.heymatez.model.person.Name;
import seedu.heymatez.model.person.Person;
import seedu.heymatez.model.person.Phone;
import seedu.heymatez.model.person.Role;

/**
 * Edits the details of an existing person in HeyMatez.
 */
public class EditMemberCommand extends Command {

    public static final String COMMAND_WORD = "editMember";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the member identified "
            + "by the exact name (case-sensitive and format-sensitive) used in the displayed members list. "
            + "Existing details will be overwritten by the input details.\n"
            + "Parameters: NAME_IN_LIST "
            + "[" + PREFIX_NEW_NAME + "NEW_NAME] "
            + "[" + PREFIX_PHONE + "NEW_PHONE] "
            + "[" + PREFIX_EMAIL + "NEW_EMAIL] "
            + "[" + PREFIX_ROLE + "NEW_ROLE] "
            + "Example: " + COMMAND_WORD + "John "
            + PREFIX_NEW_NAME + "John Lim "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "john@example.com "
            + PREFIX_ROLE + "Assistant treasurer";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Member: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This member already exists in HeyMatez!";

    private final Name name;
    private final EditMemberDescriptor editMemberDescriptor;

    /**
     * @param name of the person in the filtered person list to edit
     * @param editMemberDescriptor details to edit the person with
     */
    public EditMemberCommand(Name name, EditMemberDescriptor editMemberDescriptor) {
        requireNonNull(editMemberDescriptor);

        this.name = name;
        this.editMemberDescriptor = new EditMemberDescriptor(editMemberDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToEdit = null;

        for (Person person : lastShownList) {
            Name currentName = person.getName();

            if (name.equals(currentName)) {
                personToEdit = person;
                break;
            }
        }

        if (personToEdit == null) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
        }

        Person editedPerson = createEditedPerson(personToEdit, editMemberDescriptor);

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.editAssignee(personToEdit, editedPerson);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditMemberDescriptor editMemberDescriptor) {
        assert personToEdit != null;

        Name updatedName = editMemberDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editMemberDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editMemberDescriptor.getEmail().orElse(personToEdit.getEmail());
        Role updatedRole = editMemberDescriptor.getRole().orElse(personToEdit.getRole());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedRole);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditMemberCommand)) {
            return false;
        }

        // state check
        EditMemberCommand e = (EditMemberCommand) other;
        return name.equals(e.name)
                && editMemberDescriptor.equals(e.editMemberDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditMemberDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Role role;

        public EditMemberDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code assignees} is used internally.
         */
        public EditMemberDescriptor(EditMemberDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setRole(toCopy.role);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, role);
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

        public void setRole(Role role) {
            this.role = role;
        }

        public Optional<Role> getRole() {
            return Optional.ofNullable(role);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMemberDescriptor)) {
                return false;
            }

            // state check
            EditMemberDescriptor e = (EditMemberDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getRole().equals(e.getRole());
        }
    }
}
