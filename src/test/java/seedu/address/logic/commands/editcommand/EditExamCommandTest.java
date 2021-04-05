package seedu.address.logic.commands.editcommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXAM;
import static seedu.address.testutil.TypicalRemindMe.getTypicalRemindMeWithFilledModules;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.clearcommand.ClearModulesCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RemindMe;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.testutil.typicalmodules.ModuleBuilder;

public class EditExamCommandTest {
    private Model model = new ModelManager(getTypicalRemindMeWithFilledModules(), new UserPrefs());
    private LocalDateTime newDate = LocalDateTime.parse("30/03/2021 1800", Exam.EXAM_DATE_FORMATTER);

    @Test
    public void execute_descriptionFieldSpecified_success() {
        Model modelCopy = new ModelManager(new RemindMe(model.getRemindMe()), new UserPrefs());
        Module target = new ModuleBuilder().withTitle("MOD 2").emptyBuild();
        LocalDateTime edit = newDate;

        EditExamCommand editExamCommand = new EditExamCommand(target, INDEX_FIRST_EXAM.getOneBased(), edit);

        Module editedMod = new ModuleBuilder(modelCopy.getFilteredModuleList().get(1)).build();
        editedMod.editExam(0, edit);
        Exam editedExam = editedMod.getExam(0);

        String expectedMessage = String.format(EditExamCommand.MESSAGE_SUCCESS, editedExam);
        Model expectedModel = new ModelManager(getTypicalRemindMeWithFilledModules(), new UserPrefs());

        expectedModel.editExam(target, 1, edit);
        assertCommandSuccess(editExamCommand, modelCopy, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateExam_failure() {
        Model modelCopy = new ModelManager(new RemindMe(model.getRemindMe()), new UserPrefs());
        Module target = new ModuleBuilder().withTitle("MOD 2").emptyBuild();
        LocalDateTime existingDate = modelCopy.getFilteredModuleList()
                                                    .get(1).getExam(1).getDateTime();

        EditExamCommand editExamCommand = new EditExamCommand(target, INDEX_FIRST_EXAM.getOneBased(), existingDate);

        String expectedMessage = String.format(EditExamCommand.MESSAGE_DUPLICATE_EXAM);

        assertCommandFailure(editExamCommand, modelCopy, expectedMessage);
    }

    @Test
    public void execute_invalidModuleTitle_failure() {
        Model modelCopy = new ModelManager(new RemindMe(model.getRemindMe()), new UserPrefs());
        Module target = new ModuleBuilder().withTitle("MOD 3").emptyBuild();
        LocalDateTime edit = newDate;

        EditExamCommand editExamCommand = new EditExamCommand(target, INDEX_FIRST_EXAM.getOneBased(), edit);

        String expectedMessage = String.format(EditExamCommand.MESSAGE_NO_MODULE);

        assertCommandFailure(editExamCommand, modelCopy, expectedMessage);
    }

    @Test
    public void execute_invalidExamIndex_failure() {
        Model modelCopy = new ModelManager(new RemindMe(model.getRemindMe()), new UserPrefs());
        Module target = new ModuleBuilder().withTitle("MOD 1").emptyBuild();
        LocalDateTime edit = newDate;

        EditExamCommand editExamCommand = new EditExamCommand(target, INDEX_FIRST_EXAM.getOneBased(), edit);

        String expectedMessage = String.format(EditExamCommand.MESSAGE_NO_EXAM);

        assertCommandFailure(editExamCommand, modelCopy, expectedMessage);
    }

    @Test
    public void execute_noDateChange_failure() {
        Model modelCopy = new ModelManager(new RemindMe(model.getRemindMe()), new UserPrefs());
        Module target = new ModuleBuilder().withTitle("MOD 2").emptyBuild();
        LocalDateTime edit = modelCopy.getFilteredModuleList().get(1).getExam(0).getDateTime();

        EditExamCommand editExamCommand = new EditExamCommand(target, INDEX_FIRST_EXAM.getOneBased(), edit);

        String expectedMessage = String.format(EditExamCommand.MESSAGE_NO_CHANGE);

        assertCommandFailure(editExamCommand, modelCopy, expectedMessage);
    }

    @Test
    public void execute_noFieldsSpecified_failure() {
        Model modelCopy = new ModelManager(new RemindMe(model.getRemindMe()), new UserPrefs());
        Module target = new ModuleBuilder().withTitle("MOD 2").emptyBuild();

        EditExamCommand editExamCommand = new EditExamCommand(target, INDEX_FIRST_EXAM.getOneBased(), null);

        String expectedMessage = String.format(EditExamCommand.MESSAGE_NO_VALID_CHANGES);

        assertCommandFailure(editExamCommand, modelCopy, expectedMessage);
    }

    @Test
    public void equals() {
        Module target = new ModuleBuilder().withTitle("MOD 2").emptyBuild();
        LocalDateTime edit = newDate;
        final EditExamCommand standardCommand = new EditExamCommand(target, INDEX_FIRST_EXAM.getOneBased(), edit);

        // same values -> returns true
        Module copy = new ModuleBuilder().withTitle("MOD 2").emptyBuild();
        EditExamCommand commandWithSameValues = new EditExamCommand(copy, INDEX_FIRST_EXAM.getOneBased(), newDate);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearModulesCommand()));

        // different targets -> returns false
        Module diffTarget = new ModuleBuilder().withTitle("DIFF MOD").emptyBuild();
        assertFalse(standardCommand.equals(new EditExamCommand(diffTarget, INDEX_SECOND_EXAM.getOneBased(), edit)));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditExamCommand(target, INDEX_SECOND_EXAM.getOneBased(), edit)));

        // different date -> returns false
        LocalDateTime diffDate = LocalDateTime.parse(VALID_EXAM_DATETIME_2, Exam.EXAM_DATE_FORMATTER);
        assertFalse(standardCommand.equals(new EditExamCommand(target, INDEX_FIRST_EXAM.getOneBased(), diffDate)));
    }
}
