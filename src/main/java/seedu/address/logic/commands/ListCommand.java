package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;
import static seedu.address.model.Model.PREDICATE_SHOW_UNDONE_TASKS;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;

/**
 * Lists all tasks or tasks filtered by status in the planner to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "ls";

    public static final String MESSAGE_UNDONE_TASKS_SUCCESS = "Listed all uncompleted tasks. "
            + "Better get to work soon!\n\n"
            + Messages.MESSAGE_CALENDAR_SHOWING_CURRENT_MONTH;

    public static final String MESSAGE_ALL_TASKS_SUCCESS = "Listed all tasks.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists out all tasks in the planner.\n\n"
            + "Parameters: "
            + "[not done]";

    public static final String INVALID_INPUT = "\nInvalid List commands detected! \n\nThe only acceptable list command "
            + "formats are ls, ls not done ";

    public static final String SHORT_MESSAGE_USAGE = COMMAND_WORD + " [not done]\n";

    private final boolean isListEverything;

    /**
     * Creates a ListCommmand with a boolean to determine whether to list out all tasks or just uncompleted tasks.
     */
    public ListCommand(boolean isListEverything) {
        this.isListEverything = isListEverything;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetCalendarDate();

        if (this.isListEverything) {
            model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
            return new CommandResult(MESSAGE_ALL_TASKS_SUCCESS);
        } else {
            model.updateFilteredTaskList(PREDICATE_SHOW_UNDONE_TASKS);
            return new CommandResult(MESSAGE_UNDONE_TASKS_SUCCESS);
        }
    }

    @Override
    public boolean equals(Object other) {
        //ListCommand that = (ListCommand) other;
        return this == other || (other instanceof ListCommand // instanceof handles nulls
                && isListEverything == (((ListCommand) other).isListEverything)); // state check;
    }
}
