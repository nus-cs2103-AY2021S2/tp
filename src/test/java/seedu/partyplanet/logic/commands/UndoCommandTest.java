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
 * Contains integration tests (interaction with the Model) and unit tests for UndoCommand.
 */
public class UndoCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
    }

    @Test
    public void execute_noValidUndo_failure() {
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_INVALID_UNDO);
    }

    @Test
    public void execute_undoAddCommand_success() {
        // One undoable state
        addDefaultPerson(model);
        addDefaultPerson(expectedModel);

        expectedModel.undo();
        String expectedMessage = UndoCommand.MESSAGE_SUCCESS + CommandTestUtil.ADD_DEFAULT_PERSON_COMMAND;
        assertCommandSuccess(new UndoCommand(), model, expectedMessage, expectedModel);

        // No undoable states left
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_INVALID_UNDO);
    }

    @Test
    public void execute_undoDeleteCommand_success() {
        // One undoable state
        deleteFirstPerson(model);
        deleteFirstPerson(expectedModel);

        expectedModel.undo();
        String expectedMessage = UndoCommand.MESSAGE_SUCCESS + CommandTestUtil.DELETE_FIRST_PERSON_COMMAND;
        assertCommandSuccess(new UndoCommand(), model, expectedMessage, expectedModel);

        // No undoable states left
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_INVALID_UNDO);
    }

    @Test
    public void execute_undoEAddCommand_success() {
        // One undoable state
        addDefaultEvent(model);
        addDefaultEvent(expectedModel);

        expectedModel.undo();
        String expectedMessage = UndoCommand.MESSAGE_SUCCESS + CommandTestUtil.ADD_DEFAULT_EVENT_COMMAND;
        assertCommandSuccess(new UndoCommand(), model, expectedMessage, expectedModel);

        // No undoable states left
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_INVALID_UNDO);
    }

    @Test
    public void execute_undoEDeleteCommand_success() {
        // One undoable state
        deleteFirstEvent(model);
        deleteFirstEvent(expectedModel);

        expectedModel.undo();
        String expectedMessage = UndoCommand.MESSAGE_SUCCESS + CommandTestUtil.DELETE_FIRST_EVENT_COMMAND;
        assertCommandSuccess(new UndoCommand(), model, expectedMessage, expectedModel);

        // No undoable states left
        assertCommandFailure(new UndoCommand(), model, UndoCommand.MESSAGE_INVALID_UNDO);
    }
}
