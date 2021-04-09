package dog.pawbook.logic.commands;

import static dog.pawbook.model.managedentity.owner.Owner.ENTITY_WORD;
import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;
import javafx.util.Pair;

/**
 * Deletes a owner identified using it's displayed index from the database.
 */
public class DeleteOwnerCommand extends DeleteCommand<Owner> {

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Deletes the owner identified by ID.\n"
            + "Parameters: ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + ENTITY_WORD + " 1";

    public static final String MESSAGE_SUCCESS = String.format(MESSAGE_DELETE_SUCCESS_FORMAT, ENTITY_WORD);

    public DeleteOwnerCommand(Integer targetId) {
        super(targetId, Owner.class);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Owner ownerToDelete = getEntityToDelete(model);
        Set<Integer> dogsToDelete = ownerToDelete.getDogIdSet();

        // delete all the dogs owned by this owner first
        for (int dogId : dogsToDelete) {
            assert model.hasEntity(dogId) && model.getEntity(dogId) instanceof Dog : "Dog ID is invalid!";
            model.deleteEntity(dogId);
        }

        // remove all delete dogs from the programs
        for (Pair<Integer, Entity> idEntityPair : model.getUnfilteredEntityList()) {
            Entity entity = idEntityPair.getValue();
            if (!(entity instanceof Program)) {
                continue;
            }

            Program program = (Program) entity;
            HashSet<Integer> updatedDogIdSet = new HashSet<>(program.getDogIdSet());
            updatedDogIdSet.removeAll(dogsToDelete);
            model.setEntity(idEntityPair.getKey(),
                    new Program(program.getName(), program.getSessions(), program.getTags(), updatedDogIdSet));
        }

        // then actually delete the owner
        model.deleteEntity(targetId);

        return new CommandResult(MESSAGE_SUCCESS + ownerToDelete);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteOwnerCommand
                    && targetId.equals(((DeleteOwnerCommand) other).targetId));
    }

    @Override
    protected String getEntityWord() {
        return ENTITY_WORD;
    }
}
