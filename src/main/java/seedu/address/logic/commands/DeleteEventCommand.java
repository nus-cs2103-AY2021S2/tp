package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMOVE_TASK_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * Deletes a Event identified using it's displayed index from CoLAB.
 */
public class DeleteEventCommand extends Command {

    public static final String COMMAND_WORD = "deleteE";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the event identified by it's index number within the displayed project.\n"
            + "Parameters: PROJECT_INDEX (must be a positive integer)"
            + PREFIX_REMOVE_TASK_INDEX + "EVENT_INDEX \n"
            + "Example: " + COMMAND_WORD + " 1" + " "
            + PREFIX_REMOVE_TASK_INDEX + " 2";

    private final Index projectIndex;
    private final Index targetTodoIndex;

    /**
     * Creates a DeleteTodoCommand to delete the specified {@code Todo} from {@code Project}.
     * @param projectIndex Index of project that {@code Todo} is to be deleted from.
     * @param targetTodoIndex Index of todo that is to be deleted form {@code Project}.
     */
    public DeleteEventCommand(Index projectIndex, Index targetTodoIndex) {
        this.projectIndex = projectIndex;
        this.targetTodoIndex = targetTodoIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (projectIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        if (targetTodoIndex.getZeroBased() >= lastShownList.get(projectIndex.getZeroBased())
                .getTodos().getTodos().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX);
        }

        Project projectToEdit = lastShownList.get(projectIndex.getZeroBased());
        assert projectToEdit != null;

        projectToEdit.deleteTodo(targetTodoIndex.getZeroBased());
        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);

        return new CommandResult(String.format(Messages.MESSAGE_DELETE_TODO_SUCCESS, targetTodoIndex.getZeroBased()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteEventCommand // instanceof handles nulls
                && projectIndex.equals(((DeleteEventCommand) other).projectIndex))
                && targetTodoIndex.equals(((DeleteEventCommand) other).targetTodoIndex); // state check
    }

}
