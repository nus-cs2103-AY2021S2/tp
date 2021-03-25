package dog.pawbook.logic.commands;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;
import static dog.pawbook.model.Model.PREDICATE_SHOW_ALL_ENTITIES;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.commons.util.CollectionUtil;
import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.owner.Address;
import dog.pawbook.model.managedentity.owner.Email;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.owner.Phone;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * Edits the details of an existing owner in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the owner identified "
            + "by the index number used in the displayed owner list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_OWNER_SUCCESS = "Edited Owner: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_OWNER = "This owner already exists in the address book.";

    private final Integer id;
    private final EditOwnerDescriptor editOwnerDescriptor;

    /**
     * @param id of the owner in the filtered owner list to edit
     * @param editOwnerDescriptor details to edit the owner with
     */
    public EditCommand(Integer id, EditOwnerDescriptor editOwnerDescriptor) {
        requireAllNonNull(id, editOwnerDescriptor);

        this.id = id;
        this.editOwnerDescriptor = new EditOwnerDescriptor(editOwnerDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasEntity(id)) {
            throw new CommandException(Messages.MESSAGE_INVALID_OWNER_ID);
        }

        Entity targetEntity = model.getEntity(id);

        if (!(targetEntity instanceof Owner)) {
            throw new CommandException(Messages.MESSAGE_INVALID_OWNER_ID);
        }

        Owner ownerToEdit = (Owner) targetEntity;
        Owner editedOwner = createEditedOwner(ownerToEdit, editOwnerDescriptor);

        if (!ownerToEdit.equals(editedOwner) && model.hasEntity(editedOwner)) {
            throw new CommandException(MESSAGE_DUPLICATE_OWNER);
        }

        model.setEntity(id, editedOwner);
        model.updateFilteredEntityList(PREDICATE_SHOW_ALL_ENTITIES);
        return new CommandResult(String.format(MESSAGE_EDIT_OWNER_SUCCESS, editedOwner));
    }

    /**
     * Creates and returns a {@code Owner} with the details of {@code ownerToEdit}
     * edited with {@code editOwnerDescriptor}.
     */
    private static Owner createEditedOwner(Owner ownerToEdit, EditOwnerDescriptor editOwnerDescriptor) {
        assert ownerToEdit != null;

        Name updatedName = editOwnerDescriptor.getName().orElse(ownerToEdit.getName());
        Phone updatedPhone = editOwnerDescriptor.getPhone().orElse(ownerToEdit.getPhone());
        Email updatedEmail = editOwnerDescriptor.getEmail().orElse(ownerToEdit.getEmail());
        Address updatedAddress = editOwnerDescriptor.getAddress().orElse(ownerToEdit.getAddress());
        Set<Tag> updatedTags = editOwnerDescriptor.getTags().orElse(ownerToEdit.getTags());

        return new Owner(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return id.equals(e.id)
                && editOwnerDescriptor.equals(e.editOwnerDescriptor);
    }

    /**
     * Stores the details to edit the owner with. Each non-empty field value will replace the
     * corresponding field value of the owner.
     */
    public static class EditOwnerDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        public EditOwnerDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditOwnerDescriptor(EditOwnerDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
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
            if (!(other instanceof EditOwnerDescriptor)) {
                return false;
            }

            // state check
            EditOwnerDescriptor e = (EditOwnerDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
