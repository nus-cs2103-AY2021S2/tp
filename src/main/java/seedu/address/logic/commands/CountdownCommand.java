package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.conditions.ConditionLogic;
import seedu.address.model.Model;
import seedu.address.model.task.AttributeManager;
import seedu.address.model.task.Task;

/**
 *
 */

public class CountdownCommand extends Command {

    public static final String COMMAND_WORD = "count";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the number of days and hours left until "
            + "a deadline is due or an event is happening.\n"
            + "Parameters: INDEX \n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String SHORT_MESSAGE_USAGE = COMMAND_WORD + " INDEX\n";

    public static final String MESSAGE_COUNTDOWN_TASK_SUCCESS = "There are %1$s day(s) left until "
            + "the deadline of this task:\n%2$s";

    public static final String MESSAGE_DEADLINE_OVER = "Date is already over for this task.";

    public static final String MESSAGE_EMPTY_DEADLINE = "There is no deadline in this task.";

    private final Index index;

    private final Logger logger = LogsCenter.getLogger(getClass());

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
        ConditionLogic.verifyIndex(index, lastShownList);

        Task taskToCountdown = lastShownList.get(targetIndexValue);
        AttributeManager attributeManager = new AttributeManager(taskToCountdown);

        if (attributeManager.isEmptyDate()) {
            throw new CommandException(MESSAGE_EMPTY_DEADLINE);
        }

        if (attributeManager.dateOver()) {
            logger.info("User entered date that's already over.");
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
