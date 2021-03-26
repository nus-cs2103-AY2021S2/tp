package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertAppointmentCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertPropertyCommandFailure;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_NO_COMMAND_TO_UNDO;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.property.Property;

public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalAppointmentBook(), getTypicalPropertyBook(), new UserPrefs());

    @Test
    public void execute_emptyCommandHistory_throwsCommandException() {
        assertAppointmentCommandFailure(new UndoCommand(), model, MESSAGE_NO_COMMAND_TO_UNDO);
        assertPropertyCommandFailure(new UndoCommand(), model, MESSAGE_NO_COMMAND_TO_UNDO);
    }

    @Test
    public void execute_notEmptyCommandHistory_throwsCommandException() {
        Comparator<Appointment> comparator = (o1, o2) -> {
            if (o1.getDate().compareTo(o2.getDate()) == 0) {
                return o1.getTime().compareTo(o2.getTime());
            } else {
                return o1.getDate().compareTo(o2.getDate());
            }
        };
        model.sortAppointmentList(comparator);
        assertAppointmentCommandFailure(new UndoCommand(), model, MESSAGE_NO_COMMAND_TO_UNDO);
        assertPropertyCommandFailure(new UndoCommand(), model, MESSAGE_NO_COMMAND_TO_UNDO);
    }

    @Test
    public void execute_undoDeleteAppointmentCommand_success() {
        final String commandString = "delete appointment 1";
        Appointment appointmentToDelete = model.getAppointment(0);
        model.deleteAppointment(appointmentToDelete);
        try {
            UndoCommand.logCommand(commandString);
        } catch (CommandException e) {
            // Cannot reach here
        }
        CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS, commandString));
        ModelManager expectedModel = new ModelManager(getTypicalAppointmentBook(),
                getTypicalPropertyBook(), new UserPrefs());
        assertCommandSuccess(new UndoCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_undoDeletePropertyCommand_success() {
        final String commandString = "delete property 1";
        Property propertyToDelete = model.getProperty(0);
        System.out.println(propertyToDelete);
        model.deleteProperty(propertyToDelete);
        try {
            UndoCommand.logCommand(commandString);
        } catch (CommandException e) {
            // Cannot reach here
        }
        CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS, commandString));
        ModelManager expectedModel = new ModelManager(getTypicalAppointmentBook(),
                getTypicalPropertyBook(), new UserPrefs());
        assertCommandSuccess(new UndoCommand(), model, expectedCommandResult, expectedModel);
    }
}
