package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_OWNER_ID;
import static dog.pawbook.model.managedentity.owner.Owner.ENTITY_WORD;
import static java.util.Objects.requireNonNull;

import java.util.Set;

import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;

/**
 * Deletes a owner identified using it's displayed index from the address book.
 */
public class DeleteOwnerCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Deletes the owner identified by ID.\n"
            + "Parameters: ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + ENTITY_WORD + " 1";

    public static final String MESSAGE_SUCCESS = String.format(MESSAGE_DELETE_SUCCESS_FORMAT, ENTITY_WORD);

    public DeleteOwnerCommand(Integer targetId) {
        super(targetId);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Entity entityToDelete = getEntityToDelete(model);

        // if the id exists but doesn't belong to owner means it is invalid
        if (!(entityToDelete instanceof Owner)) {
            throw new CommandException(MESSAGE_INVALID_OWNER_ID);
        }

        Owner ownerToDelete = (Owner) entityToDelete;
        Set<Integer> dogsToDelete = ownerToDelete.getDogIdSet();

        // delete all the dogs owned by this owner first
        for (int dogId : dogsToDelete) {
            assert model.hasEntity(dogId) && model.getEntity(dogId) instanceof Dog : "Dog ID is invalid!";
            model.deleteEntity(dogId);
        }

        // then actually delete the owner
        model.deleteEntity(targetId);
        return new CommandResult(MESSAGE_SUCCESS + ownerToDelete);
    }

    @Override
    protected String getInvalidIdMessage() {
        return MESSAGE_INVALID_OWNER_ID;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteOwnerCommand && targetId.equals(((DeleteOwnerCommand) other).targetId));
    }
}
