//@@author branzuelajohn
package dog.pawbook.logic.commands;

import static dog.pawbook.model.managedentity.program.Program.ENTITY_WORD;
import static java.util.Objects.requireNonNull;

import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.program.Program;

/**
 * Deletes a program identified using it's displayed index from the database.
 */
public class DeleteProgramCommand extends DeleteCommand<Program> {

    public static final String MESSAGE_USAGE =
        COMMAND_WORD + ": Deletes the program identified by ID.\n"
            + "Parameters: ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + ENTITY_WORD + " 1";

    public static final String MESSAGE_SUCCESS = String.format(MESSAGE_DELETE_SUCCESS_FORMAT, ENTITY_WORD);

    public DeleteProgramCommand(Integer targetIndex) {
        super(targetIndex, Program.class);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Program programToDelete = getEntityToDelete(model);

        model.deleteEntity(targetId);

        return new CommandResult(MESSAGE_SUCCESS + programToDelete);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteProgramCommand // instanceof handles nulls
                && targetId.equals(((DeleteProgramCommand) other).targetId)); // state check
    }

    @Override
    protected String getEntityWord() {
        return ENTITY_WORD;
    }
}
