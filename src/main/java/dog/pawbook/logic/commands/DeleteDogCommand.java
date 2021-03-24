package dog.pawbook.logic.commands;

import static dog.pawbook.model.managedentity.dog.Dog.ENTITY_WORD;
import static java.util.Objects.requireNonNull;

import java.util.NoSuchElementException;

import dog.pawbook.commons.core.Messages;
import dog.pawbook.commons.core.index.Index;
import dog.pawbook.logic.commands.exceptions.CommandException;
import dog.pawbook.model.Model;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.dog.Dog;

/**
 * Deletes a owner identified using it's displayed index from the address book.
 */
public class DeleteDogCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Deletes the dog identified by ID.\n"
            + "Parameters: ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + ENTITY_WORD + " 1";

    public static final String MESSAGE_SUCCESS = String.format(MESSAGE_DELETE_SUCCESS_FORMAT, ENTITY_WORD);

    public DeleteDogCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Entity dogToDelete;
        try {
            dogToDelete = model.getFilteredEntityList().stream()
                    .filter(p -> p.getKey() == targetIndex.getZeroBased())
                    .findFirst().orElseThrow()
                    .getValue();
        } catch (NoSuchElementException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOG_DISPLAYED_ID);
        }
        // if the id exists but doesn't belong to dog means it is invalid
        if (!(dogToDelete instanceof Dog)) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOG_DISPLAYED_ID);
        }

        model.deleteEntity(targetIndex.getZeroBased());
        return new CommandResult(MESSAGE_SUCCESS + dogToDelete);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteDogCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteDogCommand) other).targetIndex)); // state check
    }
}
