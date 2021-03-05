package seedu.address.logic.commands;

import seedu.address.model.task.Task;

public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "add_task";

    private final Task toAdd;

    public AddTaskCommand(Task task) {
        toAdd = task;
    }
}
