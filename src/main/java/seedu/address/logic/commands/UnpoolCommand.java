package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.pool.Pool;

/**
 * Deletes a passenger identified using it's displayed index from the pool list.
 */
public class UnpoolCommand extends Command {
    public static final String COMMAND_WORD = "unpool";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the pool identified by the index number from the displayed pool list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNPOOL_SUCCESS = "Successfully unpooled: %s";

    private final Index targetIndex;

    /**
     * Creates an UnpoolCommand to remove the specified {@code Index}.
     */
    public UnpoolCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Pool> lastShownList = model.getFilteredPoolList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_POOL_DISPLAYED_INDEX);
        }

        Pool poolToRemove = lastShownList.get(targetIndex.getZeroBased());
        model.deletePool(poolToRemove);

        return new CommandResult(String.format(MESSAGE_UNPOOL_SUCCESS, poolToRemove.getPassengerNames()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnpoolCommand // instanceof handles nulls
                && targetIndex.equals(((UnpoolCommand) other).targetIndex)); // state check
    }
}
