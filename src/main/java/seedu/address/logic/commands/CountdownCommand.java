package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 *
 */

public class CountdownCommand extends Command {

    public static final String COMMAND_WORD = "countdown";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the number of days and hours left until "
            + "a deadline is due.\n"
            + "Parameters: INDEX \n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String SHORT_MESSAGE_USAGE = COMMAND_WORD + " INDEX\n";

    public static final String MESSAGE_COUNTDOWN_TASK_SUCCESS = "There are %1$s day(s) left until "
            + "the deadline of this task:\n%2$s";

    public static final String MESSAGE_DEADLINE_OVER = "Deadline is already over for this task.";

    private final Index index;

    /**
     * @param index of the task in the filtered task list to find how much time is left before deadline
     */

    public CountdownCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        int targetIndexValue = index.getZeroBased();
        boolean isInvalidIndex = targetIndexValue >= lastShownList.size();

        if (isInvalidIndex) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToCountdown = lastShownList.get(targetIndexValue);

        if (taskToCountdown.dateOver()) {
            throw new CommandException(MESSAGE_DEADLINE_OVER);
        }
        String daysLeft = model.countdownTask(taskToCountdown);

        return new CommandResult(String.format(MESSAGE_COUNTDOWN_TASK_SUCCESS, daysLeft, taskToCountdown));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CountdownCommand // instanceof handles nulls
                && index.equals(((CountdownCommand) other).index)); // state check
    }


}
