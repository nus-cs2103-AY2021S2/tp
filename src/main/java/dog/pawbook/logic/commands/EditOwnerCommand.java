package dog.pawbook.logic.commands;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_PHONE;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.Set;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.commons.util.CollectionUtil;
import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.owner.Address;
import dog.pawbook.model.managedentity.owner.Email;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.owner.Phone;
import dog.pawbook.model.managedentity.tag.Tag;

public class EditOwnerCommand extends EditEntityCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the " + Owner.ENTITY_WORD
            + " identified by the ID number. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: ENTITY_TYPE (owner|dog|program)"
            + "ID (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + Owner.ENTITY_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_OWNER_SUCCESS = "Edited Owner: %1$s";
    public static final String MESSAGE_DUPLICATE_OWNER = "This owner already exists in the address book.";

    /**
     * @param id                  of the owner in the filtered owner list to edit
     * @param editOwnerDescriptor details to edit the owner with
     */
    public EditOwnerCommand(Integer id, EditOwnerDescriptor editOwnerDescriptor) {
        super(id, editOwnerDescriptor);
    }

    @Override
    protected String getSuccessMessage(Entity editedEntity) {
        return String.format(MESSAGE_EDIT_OWNER_SUCCESS, editedEntity);
    }

    @Override
    protected String getDuplicateEntityMessage() {
        return MESSAGE_DUPLICATE_OWNER;
    }

    /**
     * Creates and returns an {@code Owner} with the details of {@code entityToEdit}
     * edited with {@code editEntityDescriptor}.
     */
    @Override
    protected Owner createEditedEntity(Entity entityToEdit, EditEntityDescriptor editEntityDescriptor)
            throws CommandException {
        requireAllNonNull(entityToEdit, editEntityDescriptor);

        if (!(entityToEdit instanceof Owner)) {
            throw new CommandException(Messages.MESSAGE_INVALID_OWNER_ID);
        }

        assert editEntityDescriptor instanceof EditOwnerDescriptor;

        EditOwnerDescriptor editOwnerDescriptor = (EditOwnerDescriptor) editEntityDescriptor;

        Owner ownerToEdit = (Owner) entityToEdit;

        Name updatedName = editOwnerDescriptor.getName().orElse(ownerToEdit.getName());
        Phone updatedPhone = editOwnerDescriptor.getPhone().orElse(ownerToEdit.getPhone());
        Email updatedEmail = editOwnerDescriptor.getEmail().orElse(ownerToEdit.getEmail());
        Address updatedAddress = editOwnerDescriptor.getAddress().orElse(ownerToEdit.getAddress());
        Set<Tag> updatedTags = editOwnerDescriptor.getTags().orElse(ownerToEdit.getTags());

        return new Owner(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags);
    }

    public static class EditOwnerDescriptor extends EditEntityDescriptor {
        private Phone phone;
        private Email email;
        private Address address;

        public EditOwnerDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditOwnerDescriptor(EditOwnerDescriptor toCopy) {
            super(toCopy);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
        }

        @Override
        public boolean isNoFieldEdited() {
            return super.isNoFieldEdited()
                    && CollectionUtil.isAllNull(phone, email, address);
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setEmail(Email email) {
            this.email = email;
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
            if (!(other instanceof EditOwnerDescriptor)) {
                return false;
            }

            // state check
            EditOwnerDescriptor e = (EditOwnerDescriptor) other;

            return super.equals(e)
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress());
        }
    }
}
