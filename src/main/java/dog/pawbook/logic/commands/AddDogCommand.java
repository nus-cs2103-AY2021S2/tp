package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_DUPLICATE_DOG;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_BREED;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DOB;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_NAME;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_OWNERID;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_SEX;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_TAG;
import static dog.pawbook.model.managedentity.dog.Dog.ENTITY_WORD;
import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;

/**
 * Adds a dog to the database.
 */
public class AddDogCommand extends AddCommand<Dog> {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds dog to the database. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_BREED + "BREED "
            + PREFIX_DOB + "DATE OF BIRTH "
            + PREFIX_SEX + "SEX "
            + PREFIX_OWNERID + "OWNER ID "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + ENTITY_WORD + " "
            + PREFIX_NAME + "Bruce "
            + PREFIX_BREED + "Chihuahua "
            + PREFIX_DOB + "12-02-2019 "
            + PREFIX_SEX + "Male "
            + PREFIX_OWNERID + "1 "
            + PREFIX_TAG + "playful "
            + PREFIX_TAG + "active";

    public static final String MESSAGE_SUCCESS = String.format(MESSAGE_SUCCESS_FORMAT, ENTITY_WORD);

    /**
     * Creates an AddCommand to add the specified {@code Dog}
     */
    public AddDogCommand(Dog dog) {
        super(dog);
    }

    @Override
    protected String getSuccessMessage() {
        return MESSAGE_SUCCESS + toAdd;
    }

    @Override
    protected String getDuplicateMessage() {
        return MESSAGE_DUPLICATE_DOG;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Ensure that the owner exists and retrieve it
        if (!model.hasEntity(toAdd.getOwnerId())) {
            throw new CommandException(Messages.MESSAGE_INVALID_OWNER_ID);
        }

        Entity entity = model.getEntity(toAdd.getOwnerId());

        if (!(entity instanceof Owner)) {
            throw new CommandException(Messages.MESSAGE_INVALID_OWNER_ID);
        }

        Owner owner = (Owner) entity;

        // Adding the dog entity
        int idNumber = executeAdd(model);

        // Modify the owner accordingly
        Set<Integer> editedDogIdSet = new HashSet<>(owner.getDogIdSet());
        editedDogIdSet.add(idNumber);

        Owner editedOwner = new Owner(owner.getName(), owner.getPhone(), owner.getEmail(), owner.getAddress(),
                owner.getTags(), editedDogIdSet);
        model.setEntity(toAdd.getOwnerId(), editedOwner);
        return new CommandResult(getSuccessMessage());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDogCommand // instanceof handles nulls
                && toAdd.equals(((AddDogCommand) other).toAdd));
    }
}
