package seedu.address.logic.conditions;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.task.Task;

public class IndexManager {

    /**
     * Verifies that the given index is not out of bounds.
     *
     * @param index Index given by a user input.
     * @param lastShownList the last shown list on the user's screen.
     * @throws CommandException
     */
    public static void verifyIndex(Index index, List<Task> lastShownList) throws CommandException {
        int indexValue = index.getZeroBased();
        int listSize = lastShownList.size();
        boolean isInvalidIndex = indexValue >= listSize || indexValue < 0;

        if (isInvalidIndex) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }
    }
}
