package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProjectAtIndex;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.uicommands.ViewProjectAndTodosUiCommand;
import seedu.address.model.ColabFolder;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.project.TodoList;
import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.todo.Todo;

public class UpdateTodoCommandTest {

    private Model model = new ModelManager(getTypicalColabFolder(), new UserPrefs());

    @Test
    public void execute_unfilteredList_success() {
        CompletableTodo todo = new Todo("new todo");
        UpdateTodoCommand updateTodoCommand = new UpdateTodoCommand(INDEX_FIRST, INDEX_FIRST, todo);

        Model expectedModel = new ModelManager(new ColabFolder(model.getColabFolder()), new UserPrefs());
        Project projectToUpdate = expectedModel.getFilteredProjectList().get(0);
        TodoList todos = projectToUpdate.getTodos();

        if (todos.checkIsDone(INDEX_FIRST.getZeroBased())) {
            todo.markAsDone();
        }

        todos.setTodo(INDEX_FIRST.getZeroBased(), todo);

        String expectedMessage = String.format(UpdateTodoCommand.MESSAGE_UPDATE_TODO_SUCCESS, todo);

        assertCommandSuccess(updateTodoCommand, model, expectedMessage,
                new ViewProjectAndTodosUiCommand(INDEX_FIRST), expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST);
        CompletableTodo todo = new Todo("new todo");
        UpdateTodoCommand updateTodoCommand = new UpdateTodoCommand(INDEX_FIRST, INDEX_FIRST, todo);

        Model expectedModel = new ModelManager(new ColabFolder(model.getColabFolder()), new UserPrefs());
        Project projectRelated = expectedModel.getFilteredProjectList().get(0);
        TodoList todos = projectRelated.getTodos();

        if (todos.checkIsDone(INDEX_FIRST.getZeroBased())) {
            todo.markAsDone();
        }

        todos.setTodo(INDEX_FIRST.getZeroBased(), todo);

        String expectedMessage = String.format(UpdateTodoCommand.MESSAGE_UPDATE_TODO_SUCCESS, todo);

        assertCommandSuccess(updateTodoCommand, model, expectedMessage,
                new ViewProjectAndTodosUiCommand(INDEX_FIRST), expectedModel);
    }

    @Test
    public void execute_duplicateDescription_failure() {
        Project targetProject = model.getFilteredProjectList().get(0);
        TodoList todos = targetProject.getTodos();
        CompletableTodo todo = todos.getTodo(1);
        String duplicateDescription = todo.getDescription();

        UpdateTodoCommand updateTodoCommand = new UpdateTodoCommand(INDEX_FIRST, INDEX_FIRST,
                new Todo(duplicateDescription));

        assertCommandFailure(updateTodoCommand, model, UpdateTodoCommand.MESSAGE_DUPLICATE_TODO);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of project list
     */
    @Test
    public void execute_invalidProjectIndexFilteredList_failure() {
        showProjectAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of project list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getColabFolder().getProjectsList().size());

        UpdateTodoCommand updateTodoCommand = new UpdateTodoCommand(outOfBoundIndex, INDEX_FIRST,
                new Todo("new todo"));

        assertCommandFailure(updateTodoCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CompletableTodo todo = new Todo("new todo");
        CompletableTodo differentTodo = new Todo("different todo");

        final UpdateTodoCommand standardCommand = new UpdateTodoCommand(INDEX_FIRST, INDEX_FIRST, todo);

        // same values -> returns true
        assertTrue(standardCommand.equals(new UpdateTodoCommand(INDEX_FIRST, INDEX_FIRST, todo)));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types ->  returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different project index -> returns false
        assertFalse(standardCommand.equals(new UpdateTodoCommand(INDEX_SECOND, INDEX_FIRST, todo)));

        // different project index -> returns false
        assertFalse(standardCommand.equals(new UpdateTodoCommand(INDEX_FIRST, INDEX_SECOND, todo)));

        // different name -> returns false
        assertFalse(standardCommand.equals(new UpdateTodoCommand(INDEX_FIRST, INDEX_FIRST, differentTodo)));
    }
}
