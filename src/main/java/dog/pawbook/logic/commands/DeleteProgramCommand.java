package dog.pawbook.logic.commands;

import static dog.pawbook.commons.core.Messages.MESSAGE_INVALID_PROGRAM_ID;
import static dog.pawbook.model.managedentity.program.Program.ENTITY_WORD;
import static java.util.Objects.requireNonNull;

import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.program.Program;

/**
 * Deletes a program identified using it's displayed index from the address book.
 */
public class DeleteProgramCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE =
        COMMAND_WORD + ": Deletes the program identified by ID.\n"
            + "Parameters: ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + ENTITY_WORD + " 1";

    public static final String MESSAGE_SUCCESS = String.format(MESSAGE_DELETE_SUCCESS_FORMAT, ENTITY_WORD);

    public DeleteProgramCommand(Integer targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Entity programToDelete = getEntityToDelete(model);

        // if the id exists but doesn't belong to a program means it is invalid
        if (!(programToDelete instanceof Program)) {
            throw new CommandException(MESSAGE_INVALID_PROGRAM_ID);
        }

        model.deleteEntity(targetId);
        return new CommandResult(MESSAGE_SUCCESS + programToDelete);
    }

    @Override
    protected String getInvalidIdMessage() {
        return MESSAGE_INVALID_PROGRAM_ID;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteProgramCommand // instanceof handles nulls
                    && targetId.equals(((DeleteProgramCommand) other).targetId)); // state check
    }
}
