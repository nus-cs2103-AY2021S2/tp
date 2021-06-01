package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ViewProjectAndTodosUiCommand;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.project.exceptions.DuplicateTodoException;
import seedu.address.model.task.todo.Todo;

public class AddTodoCommand extends Command {

    public static final String COMMAND_WORD = "addT";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo to CoLAB.\n"
            + "Parameters: PROJECT_INDEX "
            + PREFIX_DESCRIPTION + "DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Submit project report ";

    private final Index index;
    private final Todo toAdd;

    /**
     * Creates an AddTodoCommand to add the specified {@code Task}.
     *
     * @param index index of {@code Project} to add event in the list.
     * @param todo {@code Todo} to add.
     */
    public AddTodoCommand(Index index, Todo todo) {
        requireNonNull(todo);
        this.index = index;
        this.toAdd = todo;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Project> lastShownList = model.getFilteredProjectList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
        }

        Project projectToEdit = lastShownList.get(index.getZeroBased());
        assert projectToEdit != null;

        try {
            projectToEdit.addTodo(toAdd);
        } catch (DuplicateTodoException e) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_TODO);
        }

        model.updateFilteredProjectList(Model.PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(Messages.MESSAGE_ADD_TODO_SUCCESS, toAdd,
                projectToEdit.getProjectName()), new ViewProjectAndTodosUiCommand(index));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTodoCommand // instanceof handles nulls
                && toAdd.equals(((AddTodoCommand) other).toAdd)
                && index.equals(((AddTodoCommand) other).index));
    }

}
