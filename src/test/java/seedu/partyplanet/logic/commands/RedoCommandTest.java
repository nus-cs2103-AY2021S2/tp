package seedu.partyplanet.logic.commands;

import static seedu.partyplanet.logic.commands.CommandTestUtil.addDefaultEvent;
import static seedu.partyplanet.logic.commands.CommandTestUtil.addDefaultPerson;
import static seedu.partyplanet.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.partyplanet.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.partyplanet.logic.commands.CommandTestUtil.deleteFirstEvent;
import static seedu.partyplanet.logic.commands.CommandTestUtil.deleteFirstPerson;
import static seedu.partyplanet.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.partyplanet.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.ModelManager;
import seedu.partyplanet.model.UserPrefs;


/**
 * Contains integration tests (interaction with the Model) and unit tests for RedoCommand.
 */
public class RedoCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
    }

    @Test
    public void execute_noValidRedo_failure() {
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_INVALID_REDO);
    }

    @Test
    public void execute_redoAddCommand_success() {
        // One redoable state
        addDefaultPerson(model);
        addDefaultPerson(expectedModel);
        model.undo();
        expectedModel.undo();

        expectedModel.redo();
        String expectedMessage = RedoCommand.MESSAGE_SUCCESS + CommandTestUtil.ADD_DEFAULT_PERSON_COMMAND;
        assertCommandSuccess(new RedoCommand(), model, expectedMessage, expectedModel);

        // No redoable states left
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_INVALID_REDO);
    }

    @Test
    public void execute_redoDeleteCommand_success() {
        // One redoable state
        deleteFirstPerson(model);
        deleteFirstPerson(expectedModel);
        model.undo();
        expectedModel.undo();

        expectedModel.redo();
        String expectedMessage = RedoCommand.MESSAGE_SUCCESS + CommandTestUtil.DELETE_FIRST_PERSON_COMMAND;
        assertCommandSuccess(new RedoCommand(), model, expectedMessage, expectedModel);

        // No redoable states left
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_INVALID_REDO);
    }

    @Test
    public void execute_redoEAddCommand_success() {
        // One redoable state
        addDefaultEvent(model);
        addDefaultEvent(expectedModel);
        model.undo();
        expectedModel.undo();

        expectedModel.redo();
        String expectedMessage = RedoCommand.MESSAGE_SUCCESS + CommandTestUtil.ADD_DEFAULT_EVENT_COMMAND;
        assertCommandSuccess(new RedoCommand(), model, expectedMessage, expectedModel);

        // No redoable states left
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_INVALID_REDO);
    }

    @Test
    public void execute_redoEDeleteCommand_success() {
        // One redoable state
        deleteFirstEvent(model);
        deleteFirstEvent(expectedModel);
        model.undo();
        expectedModel.undo();

        expectedModel.redo();
        String expectedMessage = RedoCommand.MESSAGE_SUCCESS + CommandTestUtil.DELETE_FIRST_EVENT_COMMAND;
        assertCommandSuccess(new RedoCommand(), model, expectedMessage, expectedModel);

        // No redoable states left
        assertCommandFailure(new RedoCommand(), model, RedoCommand.MESSAGE_INVALID_REDO);
    }
}
