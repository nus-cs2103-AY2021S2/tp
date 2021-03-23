package dog.pawbook.logic.commands;

import dog.pawbook.commons.core.index.Index;
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

    protected final Index targetIndex;

    /**
     * Create a new Delete command.
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }
}
