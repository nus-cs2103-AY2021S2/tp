package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertAppointmentCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertPropertyCommandFailure;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_NO_COMMAND_TO_UNDO;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalAppointmentBook(), getTypicalPropertyBook(), new UserPrefs());

    @Test
    public void execute_emptyCommandHistory_throwsCommandException() {
        assertAppointmentCommandFailure(new UndoCommand(), model, MESSAGE_NO_COMMAND_TO_UNDO);
        assertPropertyCommandFailure(new UndoCommand(), model, MESSAGE_NO_COMMAND_TO_UNDO);
    }

    @Test
    public void execute_notEmptyCommandHistory_throwsCommandException() {

        assertAppointmentCommandFailure(new UndoCommand(), model, MESSAGE_NO_COMMAND_TO_UNDO);
        assertPropertyCommandFailure(new UndoCommand(), model, MESSAGE_NO_COMMAND_TO_UNDO);
    }

    @Test
    public void execute_notEmptyCommandHistory_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS);
        ModelManager expectedModel = new ModelManager(getTypicalAppointmentBook(),
                getTypicalPropertyBook(), new UserPrefs());
        expectedModel.deleteProperty(expectedModel.getProperty(0));
        assertCommandSuccess(new UndoCommand(), model, expectedCommandResult, expectedModel);
        assertAppointmentCommandFailure(new UndoCommand(), model, MESSAGE_NO_COMMAND_TO_UNDO);
        assertPropertyCommandFailure(new UndoCommand(), model, MESSAGE_NO_COMMAND_TO_UNDO);
    }
}
