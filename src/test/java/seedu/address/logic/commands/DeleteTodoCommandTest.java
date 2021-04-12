package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_DELETE_TODO_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ViewProjectAndTodosUiCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.task.todo.Todo;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.TodoBuilder;

public class DeleteTodoCommandTest {

    private Model model;
    private Project projectToEdit;
    private Project editedProject;


    @BeforeEach
    public void setUp() throws DateConversionException {
        model = new ModelManager(getTypicalColabFolder(), new UserPrefs());
        projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        editedProject = new ProjectBuilder(projectToEdit).build();
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Todo todoToDelete = new TodoBuilder().withDescription("a").build();
        editedProject.addTodo(todoToDelete);

        model.setProject(
                projectToEdit,
                editedProject
        );

        Index firstTodoIndex = Index.fromOneBased(1); // todo is first in sorted list

        DeleteTodoCommand deleteTodoCommand = new DeleteTodoCommand(INDEX_FIRST, firstTodoIndex);

        String expectedMessage = String.format(MESSAGE_DELETE_TODO_SUCCESS, firstTodoIndex.getOneBased(),
                projectToEdit.getProjectName());

        ModelManager expectedModel = new ModelManager(getTypicalColabFolder(), new UserPrefs());

        assertCommandSuccess(deleteTodoCommand, model, expectedMessage,
                new ViewProjectAndTodosUiCommand(INDEX_FIRST), expectedModel);
    }

    @Test
    public void execute_invalidProjectIndex_throwsCommandException() {
        Todo todoToDelete = new TodoBuilder().build();
        editedProject.addTodo(todoToDelete);

        model.setProject(
                projectToEdit,
                editedProject
        );

        Index lastTodoIndex = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getTodos().getSortedTodos().size());

        DeleteTodoCommand deleteTodoCommand = new DeleteTodoCommand(INDEX_THIRD, lastTodoIndex);

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, () -> deleteTodoCommand.execute(model)
        );
    }

    @Test
    public void execute_invalidTodoIndex_throwsCommandException() {
        Todo todoToDelete = new TodoBuilder().build();
        editedProject.addTodo(todoToDelete);

        model.setProject(
                projectToEdit,
                editedProject
        );

        Index invalidTodoIndex = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getTodos().getSortedTodos().size() + 1);

        DeleteTodoCommand deleteTodoCommand = new DeleteTodoCommand(INDEX_FIRST, invalidTodoIndex);

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX, () -> deleteTodoCommand.execute(model)
        );
    }

    @Test
    public void equals() {
        Todo todoToDelete = new TodoBuilder().build();
        editedProject.addTodo(todoToDelete);

        model.setProject(
                projectToEdit,
                editedProject
        );

        Project project2ToEdit = model.getFilteredProjectList().get(INDEX_SECOND.getZeroBased());
        Project editedProject2 = new ProjectBuilder(project2ToEdit).build();
        editedProject2.addTodo(todoToDelete);


        model.setProject(
                project2ToEdit,
                editedProject2
        );

        Index lastTodoFromProject1 = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getTodos().getSortedTodos().size());
        Index lastTodoFromProject2 = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_SECOND.getZeroBased()).getTodos().getSortedTodos().size());

        DeleteTodoCommand deleteTodoFromProject1Command = new DeleteTodoCommand(
                INDEX_FIRST, lastTodoFromProject1);
        DeleteTodoCommand deleteTodoFromProject2Command = new DeleteTodoCommand(
                INDEX_SECOND, lastTodoFromProject2);

        // same object -> returns true
        assertEquals(deleteTodoFromProject1Command, deleteTodoFromProject1Command);

        // same values -> returns true
        DeleteTodoCommand deleteTodoFromProject1CommandCopy = new DeleteTodoCommand(
                INDEX_FIRST, lastTodoFromProject1);
        assertEquals(deleteTodoFromProject1Command, deleteTodoFromProject1CommandCopy);

        // different types -> returns false
        assertNotEquals(deleteTodoFromProject1Command, 1);

        // null -> returns false
        assertNotEquals(deleteTodoFromProject1Command, null);

        // different project -> returns false
        assertNotEquals(deleteTodoFromProject1Command, deleteTodoFromProject2Command);
    }

}
