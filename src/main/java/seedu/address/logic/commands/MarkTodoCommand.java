package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MARK_TASK_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ViewProjectAndTodosUiCommand;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * Marks a Todo as done. The Todo is identified using it's displayed index from CoLAB.
 */
public class MarkTodoCommand extends Command {

    public static final String COMMAND_WORD = "markT";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the todo identified by the index number used within the displayed project as done.\n"
            + "Parameters: PROJECT_INDEX"
            + PREFIX_MARK_TASK_INDEX + "TODO_INDEX \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MARK_TASK_INDEX + " 2";

    public static final String MESSAGE_ALREADY_MARKED_TODO = "This todo has already been marked as done.";

    private final Index projectIndex;
    private final Index targetTodoIndex;

    /**
     * Creates a MarkTodoCommand to mark the specified {@code Todo} from {@code Project} as done.
     *
     * @param projectIndex Index of project in which {@code Todo} is to be marked as done.
     * @param targetTodoIndex Index of todo in {@code Project} that is to be marked as done.
     */
    public MarkTodoCommand(Index projectIndex, Index targetTodoIndex) {
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
                .getSortedTodos().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX);
        }

        Project projectToEdit = lastShownList.get(projectIndex.getZeroBased());
        requireNonNull(projectToEdit);

        if (projectToEdit.getSortedTodos().get(targetTodoIndex.getZeroBased()).getIsDone()) {
            throw new CommandException(MESSAGE_ALREADY_MARKED_TODO);
        }

        projectToEdit.markTodo(targetTodoIndex.getZeroBased());
        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);

        return new CommandResult(String.format(Messages.MESSAGE_MARK_TODO_SUCCESS, targetTodoIndex.getOneBased()),
                new ViewProjectAndTodosUiCommand(projectIndex));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkTodoCommand // instanceof handles nulls
                && projectIndex.equals(((MarkTodoCommand) other).projectIndex))
                && targetTodoIndex.equals(((MarkTodoCommand) other).targetTodoIndex); // state check
    }

}
