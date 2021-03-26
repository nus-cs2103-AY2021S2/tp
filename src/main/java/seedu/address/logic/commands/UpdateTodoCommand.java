package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UPDATE_INDEX;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.task.CompletableTodo;

/**
 * Updates an todo inside a project.
 */
public class UpdateTodoCommand extends Command {

    public static final String COMMAND_WORD = "updateT";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates a todo of a project specified "
            + "by 2 index numbers: project index and target todo index.\n"
            + "Parameters: PROJECT_INDEX "
            + PREFIX_UPDATE_INDEX + "TARGET_TODO_INDEX "
            + PREFIX_DESCRIPTION + "DESCRIPTION\n"
            + "Example:\n" + COMMAND_WORD + " 1 "
            + PREFIX_UPDATE_INDEX + "1 "
            + PREFIX_DESCRIPTION + "Project tasks";

    public static final String MESSAGE_UPDATE_TODO_SUCCESS = "Edited todo: %1$s";
    public static final String MESSAGE_DUPLICATE_TODO = "This todo already exists in this project.";

    private final Index projectIndex;
    private final Index targetTodoIndex;
    private final CompletableTodo todo;

    /**
     * Constructs an {@code updateTodoCommand} with a {@code projectIndex},
     * {@code targetTodoIndex} and a {@code Todo}.
     *
     * @param projectIndex     index of the project in the filtered project list.
     * @param targetTodoIndex index of the {@code Todo} in the {@code TodoList} to update.
     * @param todo            new todo given for updating.
     */
    public UpdateTodoCommand(Index projectIndex, Index targetTodoIndex, CompletableTodo todo) {
        requireAllNonNull(projectIndex, targetTodoIndex, todo);

        this.projectIndex = projectIndex;
        this.targetTodoIndex = targetTodoIndex;
        this.todo = todo;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (projectIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }
        Project projectRelated = lastShownList.get(projectIndex.getZeroBased());

        if (targetTodoIndex.getZeroBased() >= projectRelated.getTodos().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX);
        }

        if (projectRelated.getTodos().hasTodo(todo)) {
            throw new CommandException(MESSAGE_DUPLICATE_TODO);
        }

        if (projectRelated.getTodos().checkIsDone(targetTodoIndex.getZeroBased())) {
            projectRelated.getTodos().setTodo(targetTodoIndex.getZeroBased(), todo);
            projectRelated.getTodos().markAsDone(targetTodoIndex.getZeroBased());
        } else {
            projectRelated.getTodos().setTodo(targetTodoIndex.getZeroBased(), todo);
        }
        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_UPDATE_TODO_SUCCESS, todo));
    }
}

