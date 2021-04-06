package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_ID_MISMATCH_FORMAT;
import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_ID_FORMAT;
import static dog.pawbook.commons.util.CollectionUtil.requireAllNonNull;
import static dog.pawbook.model.Model.COMPARATOR_ID_ASCENDING_ORDER;
import static dog.pawbook.model.Model.PREDICATE_SHOW_ALL_ENTITIES;
import static java.util.Objects.requireNonNull;

import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;

public abstract class DeleteCommand<T extends Entity> extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Deletes the owner/dog/program identified by ID. \n"
            + "Parameters: ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + Owner.ENTITY_WORD + " 1\n"
            + "Example: " + COMMAND_WORD + " " + Dog.ENTITY_WORD + " 2\n"
            + "Example: " + COMMAND_WORD + " " + Program.ENTITY_WORD + " 3";

    public static final String MESSAGE_DELETE_SUCCESS_FORMAT = "Deleted %s: ";

    protected final Integer targetId;

    private final Class<T> cls;

    /**
     * Create a new Delete command.
     */
    public DeleteCommand(Integer id, Class<T> entityClass) {
        requireAllNonNull(id, entityClass);
        this.targetId = id;
        this.cls = entityClass;
    }

    /**
     * Attempt to find the entity to be deleted and convert it to {@code T}.
     */
    protected T getEntityToDelete(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasEntity(targetId)) {
            throw new CommandException(String.format(MESSAGE_INVALID_ID_FORMAT, getEntityWord()));
        }

        Entity entityToDelete = model.getEntity(targetId);
        if (!cls.isInstance(entityToDelete)) {
            throw new CommandException(String.format(MESSAGE_ID_MISMATCH_FORMAT, getEntityWord()));
        }

        return cls.cast(entityToDelete);
    }

    /**
     * Updates the filtered list and sorts it in the desired order.
     */
    protected final void filteredListShowAllAscendingId(Model model) {
        requireNonNull(model);

        model.updateFilteredEntityList(PREDICATE_SHOW_ALL_ENTITIES);
        model.sortEntities(COMPARATOR_ID_ASCENDING_ORDER);
    }

    protected abstract String getEntityWord();
}
