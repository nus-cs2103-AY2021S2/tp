package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.task.todo.Todo;
import seedu.address.testutil.TodoBuilder;

public class AddTodoCommandTest {

    private Model model;
    private Todo todoToAdd;

    @BeforeEach
    public void setUp() throws DateConversionException {
        model = new ModelManager(getTypicalColabFolder(), new UserPrefs());
        todoToAdd = new TodoBuilder().withDescription("CS2106 Assignment").build();
    }

    @Test
    public void execute_validParameters_success() throws Exception {
        CommandResult commandResult = new AddTodoCommand(INDEX_FIRST, todoToAdd).execute(model);
        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());

        assertEquals(String.format(Messages.MESSAGE_ADD_TODO_SUCCESS, todoToAdd,
                projectToEdit.getProjectName()), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidProjectIndex_throwsCommandException() {
        // Typical project list contains only 2 projects
        AddTodoCommand addTodoCommand = new AddTodoCommand(INDEX_THIRD, todoToAdd);

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, () -> addTodoCommand.execute(model)
        );
    }

    @Test
    public void execute_duplicateTodo_throwsCommandException() {
        Project projectToAddTo = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        projectToAddTo.addTodo(todoToAdd);

        AddTodoCommand addTodoCommand = new AddTodoCommand(INDEX_FIRST, todoToAdd);

        assertThrows(CommandException.class, Messages.MESSAGE_DUPLICATE_TODO, () -> addTodoCommand.execute(model));
    }

    @Test
    public void equals() {
        AddTodoCommand addTodoToOneCommand = new AddTodoCommand(INDEX_FIRST, todoToAdd);
        AddTodoCommand addTodoToTwoCommand = new AddTodoCommand(INDEX_SECOND, todoToAdd);

        // same object -> returns true
        assertEquals(addTodoToOneCommand, addTodoToOneCommand);

        // same values -> returns true
        AddTodoCommand addTodoToOneCommandCopy = new AddTodoCommand(INDEX_FIRST, todoToAdd);;
        assertEquals(addTodoToOneCommandCopy, addTodoToOneCommand);

        // different types -> returns false
        assertNotEquals(addTodoToOneCommand, 1);

        // null -> returns false
        assertNotEquals(addTodoToOneCommand, null);

        // different todo -> returns false
        assertNotEquals(addTodoToTwoCommand, addTodoToOneCommand);
    }

}
