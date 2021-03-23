package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CHEESES;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.cheese.CheeseId;

/**
 * Deletes a cheese identified using its displayed index from the cheese list.
 */
public class DeleteCheeseCommand extends DeleteCommand {

    public static final String COMMAND_WORD = "deletecheese";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the cheese identified by the index number used in the displayed cheese list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CHEESE_SUCCESS = "Deleted Cheese: %1$s";

    private final Index targetIndex;

    public DeleteCheeseCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Get index of cheeses to be deleted based on CheeseId.
     */
    public DeleteCheeseCommand(CheeseId cheeseId, Model model) {
        requireNonNull(model);
        List<Cheese> lastShownList = model.getFilteredCheeseList();
        model.updateFilteredCheeseList(PREDICATE_SHOW_ALL_CHEESES);

        Index temp = Index.fromZeroBased(0); // Default value if cheeses are already deleted

        for (int i = 0; i < lastShownList.size(); i++) {
            if (lastShownList.get(i).getCheeseId().equals(cheeseId)) {
                temp = Index.fromZeroBased(i);
                break;
            }
        }

        this.targetIndex = temp;
    }

    public int getTargetIndexValue() {
        return targetIndex.getZeroBased();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Cheese> lastShownList = model.getFilteredCheeseList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CHEESE_DISPLAYED_INDEX);
        }

        Cheese cheeseToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteCheese(cheeseToDelete);
        model.setPanelToCheeseList(); // Display cheese list
        return new CommandResult(String.format(MESSAGE_DELETE_CHEESE_SUCCESS, cheeseToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCheeseCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCheeseCommand) other).targetIndex)); // state check
    }
}
