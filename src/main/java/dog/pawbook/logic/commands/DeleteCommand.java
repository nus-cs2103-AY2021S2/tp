package dog.pawbook.logic.commands;

import dog.pawbook.commons.core.index.Index;

public abstract class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the owner/dog/program identified by the index number used in the displayed entity list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " owner 1\n"
            + "Example: " + COMMAND_WORD + " dog 1";

    public static final String MESSAGE_DELETE_SUCCESS_FORMAT = "Deleted %s: ";

    protected final Index targetIndex;

    /**
     * Create a new Delete command.
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }
}
