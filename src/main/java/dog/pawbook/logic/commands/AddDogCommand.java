package dog.pawbook.logic.commands;

import static dog.pawbook.logic.parser.CliSyntax.PREFIX_BREED;
import static dog.pawbook.logic.parser.CliSyntax.PREFIX_DATEOFBIRTH;
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
 * Adds a dog to the address book.
 */
public class AddDogCommand extends AddCommand<Dog> {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds dog to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_BREED + "BREED "
            + PREFIX_DATEOFBIRTH + "DATE OF BIRTH "
            + PREFIX_SEX + "SEX "
            + PREFIX_OWNERID + "OWNER ID "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " " + ENTITY_WORD + " "
            + PREFIX_NAME + "Bruce "
            + PREFIX_BREED + "Chihuahua "
            + PREFIX_DATEOFBIRTH + "12-02-2019 "
            + PREFIX_SEX + "Male "
            + PREFIX_OWNERID + "1 "
            + PREFIX_TAG + "playful "
            + PREFIX_TAG + "active";

    public static final String MESSAGE_SUCCESS = String.format(MESSAGE_SUCCESS_FORMAT, ENTITY_WORD);
    public static final String MESSAGE_DUPLICATE_DOG = "This " + ENTITY_WORD + " already exists";
    public static final String MESSAGE_OWNER_NOT_FOUND = "This owner does not exist in the address book";
    public static final String MESSAGE_ID_NOT_OWNER = "The provided ID does not belong to an owner";


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
        //Ensure that getOwnerId() is not null
        assert(toAdd.getOwnerId() != null) : "OwnerID should not return a null";
        // ensure that the owner exists and retrieve it
        if (!model.hasEntity(toAdd.getOwnerId())) {
            throw new CommandException(Messages.MESSAGE_INVALID_OWNER_DISPLAYED_ID);
        }
        Entity entity = model.getEntity(toAdd.getOwnerId());

        if (!(entity instanceof Owner)) {
            throw new CommandException(MESSAGE_ID_NOT_OWNER);
        }
        Owner owner = (Owner) entity;

        // the actual adding
        int idNumber = executeAdd(model);

        // modify the owner accordingly
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
