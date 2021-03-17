package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.*;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalProjects.getTypicalProjectsFolder;

import java.time.LocalDate;

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

    @BeforeEach
    public void setUp() throws DateConversionException {
        model = new ModelManager(getTypicalAddressBook(), getTypicalProjectsFolder(), new UserPrefs());
    }

    @Test
    public void execute_validParameters_success() throws Exception {
        Todo validTodo = new TodoBuilder().withDescription("CS2106 Assignment").build();

        CommandResult commandResult = new AddTodoCommand(INDEX_FIRST, validTodo).execute(model);

        assertEquals(String.format(Messages.MESSAGE_ADD_TODO_SUCCESS, validTodo), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidProjectIndex_throwsCommandException() {
        Todo todoToAdd = new TodoBuilder().withDescription("CS2106 Assignment").build();
        // Typical project list contains only 2 projects
        AddTodoCommand addTodoCommand = new AddTodoCommand(INDEX_THIRD, todoToAdd);

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, () -> addTodoCommand.execute(model)
        );
    }

    @Test
    public void execute_duplicateTodo_throwsCommandException() {
        Todo todoToAdd = new TodoBuilder().withDescription("CS2106 Assignment")
                .withAtDate(LocalDate.of(2020, 01, 01)).withInterval(Interval.WEEKLY).build();
        Project projectToAddTo = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        AddEventCommand addEventCommand = new AddEventCommand(INDEX_FIRST, eventToAdd);

        projectToAddTo.addEvent(eventToAdd);

        assertThrows(CommandException.class, Messages.MESSAGE_DUPLICATE_EVENT, () -> addEventCommand.execute(model));
    }

    @Test
    public void equals() {
        Event eventToAdd = new EventBuilder().withDescription("CS2106 Assignment")
                .withAtDate(LocalDate.of(2020, 01, 01)).withInterval(Interval.WEEKLY).build();
        AddEventCommand addEventToOneCommand = new AddEventCommand(INDEX_FIRST, eventToAdd);
        AddEventCommand addEventToTwoCommand = new AddEventCommand(INDEX_SECOND, eventToAdd);

        // same object -> returns true
        assertEquals(addEventToOneCommand, addEventToOneCommand);

        // same values -> returns true
        AddEventCommand addEventToOneCommandCopy = new AddEventCommand(INDEX_FIRST, eventToAdd);;
        assertEquals(addEventToOneCommandCopy, addEventToOneCommand);

        // different types -> returns false
        assertNotEquals(addEventToOneCommand, 1);

        // null -> returns false
        assertNotEquals(addEventToOneCommand, null);

        // different person -> returns false
        assertNotEquals(addEventToTwoCommand, addEventToOneCommand);
    }
}
