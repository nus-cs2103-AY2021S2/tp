package seedu.taskify.logic.commands;

import static seedu.taskify.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.taskify.logic.commands.exceptions.CommandException;
import seedu.taskify.model.Model;



public class HomeCommand extends Command {
    public static final String COMMAND_WORD = "home";
    public static final String MESSAGE_USAGE = "Switched to home tab";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return CommandResult.switchToHome(MESSAGE_USAGE);
    }

}
