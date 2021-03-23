package dog.pawbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.NoSuchElementException;

import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;

public abstract class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Deletes the owner/dog/program identified by ID.\n"
            + "Parameters: ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + Owner.ENTITY_WORD + " 1\n"
            + "Example: " + COMMAND_WORD + " " + Dog.ENTITY_WORD + " 2\n"
            + "Example: " + COMMAND_WORD + " " + Program.ENTITY_WORD + "3";

    public static final String MESSAGE_DELETE_SUCCESS_FORMAT = "Deleted %s: ";

    protected final Integer targetId;

    /**
     * Create a new Delete command.
     */
    public DeleteCommand(Integer id) {
        requireNonNull(id);

        this.targetId = id;
    }

    /**
     * Attempt to find the entity to be deleted.
     */
    protected Entity getEntityToDelete(Model model) throws CommandException {
        Entity entityToDelete;
        try {
            entityToDelete = model.getFilteredEntityList().stream()
                    .filter(p -> p.getKey().equals(targetId))
                    .findFirst().orElseThrow()
                    .getValue();
        } catch (NoSuchElementException e) {
            throw new CommandException(getInvalidIdMessage());
        }
        return entityToDelete;
    }

    protected abstract String getInvalidIdMessage();
}
