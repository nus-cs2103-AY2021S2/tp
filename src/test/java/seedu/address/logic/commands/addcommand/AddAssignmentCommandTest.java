package seedu.address.logic.commands.addcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRemindMe.MOD_1;
import static seedu.address.testutil.TypicalRemindMe.VALID_ASSIGNMENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Module;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.typicalmodules.ModuleBuilder;

public class AddAssignmentCommandTest {

    private AddAssignmentCommand addAssignmentCommandDummy = new AddAssignmentCommand(MOD_1, VALID_ASSIGNMENT);

    @Test
    public void constructor_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAssignmentCommand(MOD_1, null));
    }

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAssignmentCommand(null, VALID_ASSIGNMENT));
    }

    @Test
    public void execute_invalidModule_throwsCommandException() {
        ModelStubWithoutModule modelStubWithoutModule = new ModelStubWithoutModule();
        assertThrows(CommandException.class, AddAssignmentCommand.MESSAGE_MODULE_NOT_FOUND, () ->
            addAssignmentCommandDummy.execute(modelStubWithoutModule));
    }

    @Test
    public void execute_duplicateAssignment_throwsCommandException() {
        ModelStubWithModuleWithAssignment modelStubWithModuleWithAssignment = new ModelStubWithModuleWithAssignment();
        assertThrows(CommandException.class, AddAssignmentCommand.MESSAGE_DUPLICATE_ASSIGNMENT, () ->
            addAssignmentCommandDummy.execute(modelStubWithModuleWithAssignment));
    }

    @Test
    public void execute_assignmentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubWithModuleWithoutAssignment modelStubWithModuleWithoutAssignment =
            new ModelStubWithModuleWithoutAssignment();

        CommandResult commandResult = addAssignmentCommandDummy.execute(modelStubWithModuleWithoutAssignment);

        assertEquals(String.format(AddAssignmentCommand.MESSAGE_SUCCESS, VALID_ASSIGNMENT),
            commandResult.getFeedbackToUser());
        assertTrue(modelStubWithModuleWithoutAssignment.isAssignmentAdded);
    }

    @Test
    public void equals() {
        Assignment assignment1 = new AssignmentBuilder().withDescription("assignment 1").build();
        Assignment assignment2 = new AssignmentBuilder().withDescription("assignment 2").build();
        Module module1 = new ModuleBuilder().withTitle("mod 1").build();
        Module module2 = new ModuleBuilder().withTitle("mod 2").build();
        AddAssignmentCommand aac1 = new AddAssignmentCommand(module1, assignment1);
        AddAssignmentCommand aac2 = new AddAssignmentCommand(module1, assignment2);
        AddAssignmentCommand aac3 = new AddAssignmentCommand(module2, assignment1);
        AddAssignmentCommand aac4 = new AddAssignmentCommand(module2, assignment2);

        // same object -> returns true
        assertTrue(aac1.equals(aac1));

        // same values -> returns true
        AddAssignmentCommand aac1Copy = new AddAssignmentCommand(module1, assignment1);
        assertTrue(aac1.equals(aac1Copy));
        AddAssignmentCommand aac4Copy = new AddAssignmentCommand(module2, assignment2);
        assertTrue(aac4.equals(aac4Copy));

        // different types -> returns false
        assertFalse(aac1.equals(1));

        // null -> returns false
        assertFalse(aac1.equals(null));

        // same module, different assignment -> returns false
        assertFalse(aac1.equals(aac2));
        assertFalse(aac3.equals(aac4));

        // different module, same assignment -> returns false
        assertFalse(aac1.equals(aac3));
        assertFalse(aac2.equals(aac4));
    }

    private class ModelStubWithoutModule extends ModelStub {
        @Override
        public boolean hasModule(Module module) {
            return false;
        }
    }

    private class ModelStubWithModuleWithAssignment extends ModelStub {
        @Override
        public boolean hasModule(Module module) {
            return true;
        }

        @Override
        public boolean hasAssignment(Module module, Assignment assignment) {
            return true;
        }
    }

    private class ModelStubWithModuleWithoutAssignment extends ModelStub {
        private boolean isAssignmentAdded = false;

        @Override
        public boolean hasModule(Module module) {
            return true;
        }

        @Override
        public boolean hasAssignment(Module module, Assignment assignment) {
            return false;
        }

        @Override
        public void addAssignment(Module module, Assignment assignment) {
            isAssignmentAdded = true;
        }
    }
}
