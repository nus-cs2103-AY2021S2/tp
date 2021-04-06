package seedu.address.logic.commands.addcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRemindMe.MOD_1;
import static seedu.address.testutil.TypicalRemindMe.MOD_2;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModelStub;

public class AddModuleCommandTest {
    private AddModuleCommand addModuleCommandDummy = new AddModuleCommand(MOD_1);

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddModuleCommand(null));
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        ModelStubWithModule modelStubWithModule = new ModelStubWithModule();

        assertThrows(CommandException.class,
                AddModuleCommand.MESSAGE_DUPLICATE_MODULE, () -> addModuleCommandDummy.execute(modelStubWithModule));
    }

    @Test
    public void execute_moduleAcceptedByModel_addSuccessful() throws Exception {
        ModelStubWithoutModule modelStubWithoutModule = new ModelStubWithoutModule();

        CommandResult commandResult = addModuleCommandDummy.execute(modelStubWithoutModule);
        assertEquals(String.format(AddModuleCommand.MESSAGE_SUCCESS, MOD_1),
                commandResult.getFeedbackToUser());
        assertTrue(modelStubWithoutModule.isModuleAdded);
    }

    @Test
    public void equals() {
        AddModuleCommand addModuleCommand1 = new AddModuleCommand(MOD_1);
        AddModuleCommand addModuleCommand2 = new AddModuleCommand(MOD_2);

        //same object -> returns true
        assertTrue(addModuleCommand1.equals(addModuleCommand1));

        //same values -> returns true
        AddModuleCommand addModuleCommand1Copy = new AddModuleCommand(MOD_1);
        assertTrue(addModuleCommand1.equals(addModuleCommand1Copy));

        // different types -> returns false
        assertFalse(addModuleCommand1.equals(1));

        // null -> returns false
        assertFalse(addModuleCommand1.equals(null));

        // different event -> returns false
        assertFalse(addModuleCommand1.equals(addModuleCommand2));
    }

    private class ModelStubWithModule extends ModelStub {
        @Override
        public boolean hasModule(Module module) {
            return true;
        }
    }

    private class ModelStubWithoutModule extends ModelStub {
        private boolean isModuleAdded = false;

        @Override
        public boolean hasModule(Module module) {
            return false;
        }

        @Override
        public void addModule(Module module) {
            isModuleAdded = true;
        }
    }
}
