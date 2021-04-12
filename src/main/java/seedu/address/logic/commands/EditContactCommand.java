package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_CONTACT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactEmail;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.ContactPhone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing contact in Teaching Assistant.
 */
public class EditContactCommand extends Command {

    public static final String COMMAND_WORD = "cedit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the contact identified "
            + "by index used in the displayed contact list. Index must be a positive integer. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD
            + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "alexyeoh@example.com";

    public static final String MESSAGE_EDIT_CONTACT_SUCCESS = "Edited Contact: %1$s";

    private final Index targetIndex;

    private final EditContactDescriptor editContactDescriptor;

    /**
     * Creates an EditContactCommand to edit the contact corresponding to the specified {@code Index}
     * with the details as specified by the {@code editContactDescriptor}.
     */
    public EditContactCommand(Index targetIndex, EditContactDescriptor editContactDescriptor) {
        requireNonNull(targetIndex);
        requireNonNull(editContactDescriptor);

        this.targetIndex = targetIndex;
        this.editContactDescriptor = new EditContactDescriptor(editContactDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToEdit = lastShownList.get(targetIndex.getZeroBased());
        Contact editedContact = createEditedContact(contactToEdit, editContactDescriptor);

        if (!contactToEdit.isSameContact(editedContact) && model.hasContact(editedContact)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONTACT);
        }

        model.setContact(contactToEdit, editedContact);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        return new CommandResult(String.format(MESSAGE_EDIT_CONTACT_SUCCESS, editedContact));
    }

    /**
     * Creates and returns a {@code Contact} with the details of {@code contactToEdit}
     * edited with {@code editContactDescriptor}.
     */
    private static Contact createEditedContact(Contact contactToEdit, EditContactDescriptor editContactDescriptor) {
        assert contactToEdit != null;

        ContactName updatedName = editContactDescriptor.getName().orElse(contactToEdit.getName());
        ContactPhone updatedPhone = editContactDescriptor.getPhone().orElse(contactToEdit.getPhone());
        ContactEmail updatedEmail = editContactDescriptor.getEmail().orElse(contactToEdit.getEmail());
        Set<Tag> updatedTags = editContactDescriptor.getTags().orElse(contactToEdit.getTags());

        return new Contact(updatedName, updatedPhone, updatedEmail, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditContactCommand)) {
            return false;
        }

        // state check
        EditContactCommand otherEditContactCommand = (EditContactCommand) other;
        return targetIndex.equals(otherEditContactCommand.targetIndex)
                && editContactDescriptor.equals(otherEditContactCommand.editContactDescriptor);
    }

    /**
     * Stores the details needed to edit the contact. Each non-empty field value will replace the
     * corresponding field value of the contact.
     */
    public static class EditContactDescriptor {
        private ContactName name;
        private ContactPhone phone;
        private ContactEmail email;
        private Set<Tag> tags;

        public EditContactDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditContactDescriptor(EditContactDescriptor toCopy) {
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

        public void setName(ContactName name) {
            this.name = name;
        }

        public Optional<ContactName> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(ContactPhone phone) {
            this.phone = phone;
        }

        public Optional<ContactPhone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(ContactEmail email) {
            this.email = email;
        }

        public Optional<ContactEmail> getEmail() {
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
            if (!(other instanceof EditContactDescriptor)) {
                return false;
            }

            // state check
            EditContactDescriptor otherEditContactDescriptor = (EditContactDescriptor) other;
            return getName().equals(otherEditContactDescriptor.getName())
                    && getPhone().equals(otherEditContactDescriptor.getPhone())
                    && getEmail().equals(otherEditContactDescriptor.getEmail())
                    && getTags().equals(otherEditContactDescriptor.getTags());
        }
    }
}
