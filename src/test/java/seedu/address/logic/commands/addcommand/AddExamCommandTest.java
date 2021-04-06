package seedu.address.logic.commands.addcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRemindMe.DATE_1;
import static seedu.address.testutil.TypicalRemindMe.DATE_2;
import static seedu.address.testutil.TypicalRemindMe.MOD_1;
import static seedu.address.testutil.TypicalRemindMe.VALID_EXAM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.testutil.ExamBuilder;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.typicalmodules.ModuleBuilder;

public class AddExamCommandTest {
    private AddExamCommand addExamCommandDummy = new AddExamCommand(MOD_1, VALID_EXAM);

    @Test
    public void constructor_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddExamCommand(MOD_1, null));
    }

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddExamCommand(null, VALID_EXAM));
    }

    @Test
    public void execute_invalidModule_throwsCommandException() {
        ModelStubWithoutModule modelStubWithoutModule = new ModelStubWithoutModule();
        assertThrows(CommandException.class, AddExamCommand.MESSAGE_MODULE_NOT_FOUND, () ->
                addExamCommandDummy.execute(modelStubWithoutModule));
    }

    @Test
    public void execute_duplicateExam_throwsCommandException() {
        ModelStubWithModuleWithExam modelStubWithModuleWithExam = new ModelStubWithModuleWithExam();
        assertThrows(CommandException.class, AddExamCommand.MESSAGE_DUPLICATE_EXAM, () ->
                addExamCommandDummy.execute(modelStubWithModuleWithExam));
    }

    @Test
    public void execute_examAcceptedByModel_addSuccessful() throws Exception {
        ModelStubWithModuleWithoutExam modelStubWithModuleWithoutExam = new ModelStubWithModuleWithoutExam();

        CommandResult commandResult = addExamCommandDummy.execute(modelStubWithModuleWithoutExam);

        assertEquals(String.format(AddExamCommand.MESSAGE_SUCCESS, VALID_EXAM),
                commandResult.getFeedbackToUser());
        assertTrue(modelStubWithModuleWithoutExam.isExamAdded);
    }

    @Test
    public void equals() {
        Exam exam1 = new ExamBuilder().withExamDate(DATE_1).build();
        Exam exam2 = new ExamBuilder().withExamDate(DATE_2).build();
        Module module1 = new ModuleBuilder().withTitle("mod 1").build();
        Module module2 = new ModuleBuilder().withTitle("mod 2").build();
        AddExamCommand addExamCommand1 = new AddExamCommand(module1, exam1);
        AddExamCommand addExamCommand2 = new AddExamCommand(module1, exam2);
        AddExamCommand addExamCommand3 = new AddExamCommand(module2, exam1);
        AddExamCommand addExamCommand4 = new AddExamCommand(module2, exam2);

        // same object -> returns true
        assertTrue(addExamCommand1.equals(addExamCommand1));

        // same values -> returns true
        AddExamCommand addExamCommand1Copy = new AddExamCommand(module1, exam1);
        assertTrue(addExamCommand1.equals(addExamCommand1Copy));
        AddExamCommand addExamCommand4Copy = new AddExamCommand(module2, exam2);
        assertTrue(addExamCommand4.equals(addExamCommand4Copy));

        // different types -> returns false
        assertFalse(addExamCommand1.equals(1));

        // null -> returns false
        assertFalse(addExamCommand1.equals(null));

        // same module, different exams -> returns false
        assertFalse(addExamCommand1.equals(addExamCommand2));
        assertFalse(addExamCommand3.equals(addExamCommand4));

        // different module, same exams -> returns false
        assertFalse(addExamCommand1.equals(addExamCommand3));
        assertFalse(addExamCommand2.equals(addExamCommand4));
    }

    private class ModelStubWithoutModule extends ModelStub {
        @Override
        public boolean hasModule(Module module) {
            return false;
        }
    }

    private class ModelStubWithModuleWithExam extends ModelStub {
        @Override
        public boolean hasModule(Module module) {
            return true;
        }

        @Override
        public boolean hasExam(Module module, Exam exam) {
            return true;
        }
    }

    private class ModelStubWithModuleWithoutExam extends ModelStub {
        private boolean isExamAdded = false;

        @Override
        public boolean hasModule(Module module) {
            return true;
        }

        @Override
        public boolean hasExam(Module module, Exam exam) {
            return false;
        }

        @Override
        public void addExam(Module module, Exam exam) {
            isExamAdded = true;
        }
    }
}
