package dog.pawbook.logic.commands;

import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;
import static dog.pawbook.logic.commands.CommandUtil.disconnectFromOwner;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_BREED;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOB;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_OWNERID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_SEX;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.commons.util.CollectionUtil;
import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.dog.Breed;
import dog.pawbook.model.managedentity.dog.DateOfBirth;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.dog.Sex;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.tag.Tag;

public class EditDogCommand extends EditEntityCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the " + Dog.ENTITY_WORD
            + " identified by the ID number. "
            + "Existing values will be overwritten by the input values.\n"
            + "ID (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_BREED + "BREED] "
            + "[" + PREFIX_DOB + "DATE OF BIRTH] "
            + "[" + PREFIX_SEX + "SEX] "
            + "[" + PREFIX_OWNERID + "OWNER ID] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + Dog.ENTITY_WORD + " 1 "
            + PREFIX_NAME + "Bruce "
            + PREFIX_OWNERID + "10";

    public static final String MESSAGE_EDIT_DOG_SUCCESS = "Edited Dog: %1$s";

    /**
     * @param id                of the dog in the entity list to edit
     * @param editDogDescriptor details to edit the dog with
     */
    public EditDogCommand(Integer id, EditDogDescriptor editDogDescriptor) {
        super(id, editDogDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasEntity(id)) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOG_ID);
        }
        Entity targetEntity = model.getEntity(id);

        if (!(targetEntity instanceof Dog)) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOG_ID);
        }
        Dog targetDog = (Dog) targetEntity;
        Dog editedDog = createEditedEntity(targetEntity, editEntityDescriptor);

        if (!targetDog.equals(editedDog) && model.hasEntity(editedDog)) {
            throw new CommandException(getDuplicateEntityMessage());
        }

        // special handling of Owner ID change
        int originalOwnerId = targetDog.getOwnerId();
        int editedOwnerId = editedDog.getOwnerId();
        if (originalOwnerId != editedOwnerId) {
            changeOwner(model, originalOwnerId, editedOwnerId);
        }

        model.setEntity(id, editedDog);
        return new CommandResult(getSuccessMessage(editedDog));
    }

    private void changeOwner(Model model, int originalOwnerId, int editedOwnerId) throws CommandException {
        // ensure that new owner exists first exists first
        if (!model.hasEntity(editedOwnerId)) {
            throw new CommandException(Messages.MESSAGE_INVALID_OWNER_ID);
        }
        Entity entity = model.getEntity(editedOwnerId);
        if (!(entity instanceof Owner)) {
            throw new CommandException(Messages.MESSAGE_INVALID_OWNER_ID);
        }
        Owner newOwner = (Owner) entity;

        // delete the ID of the dog from the owner first
        disconnectFromOwner(model, originalOwnerId, id);

        // transfer to the new owner
        Set<Integer> editedDogIdSet = new HashSet<>(newOwner.getDogIdSet());
        editedDogIdSet.add(id);

        Owner editedOwner = new Owner(newOwner.getName(), newOwner.getPhone(), newOwner.getEmail(),
                newOwner.getAddress(), newOwner.getTags(), editedDogIdSet);
        model.setEntity(editedOwnerId, editedOwner);
    }

    @Override
    protected String getSuccessMessage(Entity editedEntity) {
        return String.format(MESSAGE_EDIT_DOG_SUCCESS, editedEntity);
    }

    @Override
    protected String getDuplicateEntityMessage() {
        return Messages.MESSAGE_DUPLICATE_DOG;
    }

    @Override
    protected String getInvalidIdMessage() {
        return Messages.MESSAGE_INVALID_DOG_ID;
    }

    /**
     * Creates and returns an {@code Dog} with the details of {@code entityToEdit}
     * edited with {@code editEntityDescriptor}.
     */
    @Override
    protected Dog createEditedEntity(Entity entityToEdit, EditEntityDescriptor editEntityDescriptor)
            throws CommandException {
        requireAllNonNull(entityToEdit, editEntityDescriptor);

        if (!(entityToEdit instanceof Dog)) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOG_ID);
        }

        assert editEntityDescriptor instanceof EditDogDescriptor;

        EditDogDescriptor editDogDescriptor = (EditDogDescriptor) editEntityDescriptor;

        Dog dogToEdit = (Dog) entityToEdit;

        Name updatedName = editDogDescriptor.getName().orElse(dogToEdit.getName());
        Breed updatedBreed = editDogDescriptor.getBreed().orElse(dogToEdit.getBreed());
        DateOfBirth updatedDob = editDogDescriptor.getDob().orElse(dogToEdit.getDob());
        Sex updatedSex = editDogDescriptor.getSex().orElse(dogToEdit.getSex());
        Integer updatedOwnerId = editDogDescriptor.getOwnerId().orElse(dogToEdit.getOwnerId());
        Set<Tag> updatedTags = editDogDescriptor.getTags().orElse(dogToEdit.getTags());

        return new Dog(updatedName, updatedBreed, updatedDob, updatedSex, updatedOwnerId, updatedTags);
    }

    public static class EditDogDescriptor extends EditEntityDescriptor {
        private Breed breed;
        private DateOfBirth dob;
        private Sex sex;
        private Integer ownerID;

        public EditDogDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditDogDescriptor(EditDogDescriptor toCopy) {
            super(toCopy);
            setBreed(toCopy.breed);
            setDob(toCopy.dob);
            setSex(toCopy.sex);
            setOwnerId(toCopy.ownerID);
        }

        @Override
        public boolean isNoFieldEdited() {
            return super.isNoFieldEdited()
                    && CollectionUtil.isAllNull(breed, dob, sex, ownerID);
        }

        public Optional<Breed> getBreed() {
            return Optional.ofNullable(breed);
        }

        public void setBreed(Breed breed) {
            this.breed = breed;
        }

        public Optional<DateOfBirth> getDob() {
            return Optional.ofNullable(dob);
        }

        public void setDob(DateOfBirth dob) {
            this.dob = dob;
        }

        public Optional<Sex> getSex() {
            return Optional.ofNullable(sex);
        }

        public void setSex(Sex sex) {
            this.sex = sex;
        }

        public Optional<Integer> getOwnerId() {
            return Optional.ofNullable(ownerID);
        }

        public void setOwnerId(Integer ownerID) {
            this.ownerID = ownerID;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditDogDescriptor)) {
                return false;
            }

            // state check
            EditDogDescriptor e = (EditDogDescriptor) other;

            return super.equals(e)
                    && getBreed().equals(e.getBreed())
                    && getDob().equals(e.getDob())
                    && getSex().equals(e.getSex())
                    && getOwnerId().equals(e.getOwnerId());
        }
    }
}
