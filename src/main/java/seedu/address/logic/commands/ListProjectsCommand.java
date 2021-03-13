package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.ui.UiCommand;

/**
 * Lists all projects in the projects folder to the user.
 */
public class ListProjectsCommand extends Command {

    public static final String COMMAND_WORD = "listP";

    public static final String MESSAGE_SUCCESS = "Listed all projects";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS, UiCommand.SHOW_PROJECTS);
    }
}
