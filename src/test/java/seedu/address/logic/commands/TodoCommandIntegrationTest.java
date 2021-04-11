package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code TodoCommand}.
 */
public class TodoCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new UserPrefs(), getTypicalEventBook());
    }

    @Test
    public void execute_newTodo_success() {
        Event validEvent = new EventBuilder().build();

        Model expectedModel = new ModelManager(new UserPrefs(), model.getEventBook());
        expectedModel.addEvent(validEvent);

        assertCommandSuccess(new TodoCommand(validEvent), model,
                String.format(TodoCommand.MESSAGE_SUCCESS, validEvent), expectedModel);
    }

    @Test
    public void execute_duplicateEvent_throwsCommandException() {
        Event eventInList = model.getEventBook().getEventList().get(0);
        assertCommandFailure(new TodoCommand(eventInList), model, TodoCommand.MESSAGE_DUPLICATE_EVENT);
    }

}
