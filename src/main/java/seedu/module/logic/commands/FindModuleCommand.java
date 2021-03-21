package seedu.module.logic.commands;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.module.commons.core.Messages;
import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;
import seedu.module.model.task.Task;

/**
 * Finds and list all tasks in module book whose module code matches the module code provided.
 */
public class FindModuleCommand extends Command {
    public static final String COMMAND_WORD = "mod";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Find tasks associated with "
            + "the Module code provided and displays them as a list with index numbers.\n"
            + "Parameters: MODULE_CODE \n"
            + "Example: " + COMMAND_WORD + " CS2103T";

    private final String moduleCode;
    private final Predicate<Task> predicate;

    /**
     * Constructor of FindModuleCommand
     * @param moduleCode
     */
    public FindModuleCommand(String moduleCode) {
        this.moduleCode = moduleCode;
        this.predicate = (Task x) -> x.getModule().toString().equals(moduleCode);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();
        List<Task> listOfModule = new ArrayList<>();
        for (Task t : lastShownList) {
            if (t.getModule().equals(moduleCode)) {
                listOfModule.add(t);
            }
        }
        model.updateFilteredTaskList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW,
                model.getFilteredTaskList().size()));
    }
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindModuleCommand)) {
            return false;
        }

        // state check
        FindModuleCommand e = (FindModuleCommand) other;
        return moduleCode.equals(e.moduleCode);
    }
}
