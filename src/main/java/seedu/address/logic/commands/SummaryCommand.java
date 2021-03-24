package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class SummaryCommand extends Command {
    public static final String COMMAND_WORD = "summary";

    public static final String MESSAGE_USAGE = COMMAND_WORD +
            ": Shows the summary of task of event completion status.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Summary:\n" +
            "Task\n" +
            "%d task(s) completed out of %d task(s).\n" +
            "%d task(s) is/are overdue.\n" +
            "%d tasks(s) to be completed before deadline.\n"+
            "Events\n" +
            "%d event(s) happening in the next 7 days";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int numCompletedTask = model.getNumCompletedTask();
        int numTotalTask = model.getFilteredTaskList().size();
        int numOverdueTask = model.getNumOverdueTask();
        int numIncompleteTask = model.getNumIncompleteTask();
        int numIncomingEvents = model.getNumIncomingEvents();
        return new CommandResult(String.format(MESSAGE_SUCCESS, numCompletedTask,
                numTotalTask, numOverdueTask, numIncompleteTask, numIncomingEvents));
    }
}
