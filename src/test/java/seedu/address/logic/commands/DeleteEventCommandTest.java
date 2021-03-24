package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.task.todo.Todo;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.TodoBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_DELETE_TODO_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalProjects.getTypicalProjectsFolder;

public class DeleteEventCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() throws DateConversionException {
        model = new ModelManager(getTypicalAddressBook(), getTypicalProjectsFolder(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() throws DateConversionException {
        Todo todoToDelete = new TodoBuilder().build();
        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Project editedProject = new ProjectBuilder(projectToEdit).build();
        editedProject.addTodo(todoToDelete);

        model.setProject(
                projectToEdit,
                editedProject
        );

        Index lastTodoIndex = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getTodos().getTodos().size());

        DeleteTodoCommand deleteTodoCommand = new DeleteTodoCommand(INDEX_FIRST, lastTodoIndex);

        String expectedMessage = String.format(MESSAGE_DELETE_TODO_SUCCESS, lastTodoIndex.getOneBased());

        ModelManager expectedModel = new ModelManager(
                getTypicalAddressBook(), getTypicalProjectsFolder(), new UserPrefs()
        );

        assertCommandSuccess(deleteTodoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidProjectIndex_throwsCommandException() {
        Todo todoToDelete = new TodoBuilder().build();
        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Project editedProject = new ProjectBuilder(projectToEdit).build();
        editedProject.addTodo(todoToDelete);

        model.setProject(
                projectToEdit,
                editedProject
        );

        Index lastTodoIndex = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getTodos().getTodos().size());

        DeleteTodoCommand deleteTodoCommand = new DeleteTodoCommand(INDEX_THIRD, lastTodoIndex);

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, () -> deleteTodoCommand.execute(model)
        );
    }

    @Test
    public void execute_invalidTodoIndex_throwsCommandException() {
        Todo todoToDelete = new TodoBuilder().build();
        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Project editedProject = new ProjectBuilder(projectToEdit).build();
        editedProject.addTodo(todoToDelete);

        model.setProject(
                projectToEdit,
                editedProject
        );

        Index invalidTodoIndex = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getTodos().getTodos().size() + 1);

        DeleteTodoCommand deleteTodoCommand = new DeleteTodoCommand(INDEX_FIRST, invalidTodoIndex);

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX, () -> deleteTodoCommand.execute(model)
        );
    }

    @Test
    public void equals() {
        Todo todoToDelete = new TodoBuilder().build();
        Project project1ToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Project editedProject1 = new ProjectBuilder(project1ToEdit).build();
        editedProject1.addTodo(todoToDelete);

        model.setProject(
                project1ToEdit,
                editedProject1
        );

        Project project2ToEdit = model.getFilteredProjectList().get(INDEX_SECOND.getZeroBased());
        Project editedProject2 = new ProjectBuilder(project2ToEdit).build();
        editedProject2.addTodo(todoToDelete);


        model.setProject(
                project2ToEdit,
                editedProject2
        );

        Index lastTodoFromProject1 = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getTodos().getTodos().size());
        Index lastTodoFromProject2 = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_SECOND.getZeroBased()).getTodos().getTodos().size());

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

        // different person -> returns false
        assertNotEquals(deleteTodoFromProject1Command, deleteTodoFromProject2Command);
    }

}
