package seedu.address.logic.commands.editcommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXAM_DATETIME_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ASSIGNMENT;
import static seedu.address.testutil.TypicalRemindMe.getTypicalRemindMeWithFilledModules;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.clearcommand.ClearModulesCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RemindMe;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Description;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.testutil.typicalmodules.ModuleBuilder;

public class EditAssignmentCommandTest {
    private Model model = new ModelManager(getTypicalRemindMeWithFilledModules(), new UserPrefs());

    @Test
    public void execute_descriptionFieldSpecified_success() {
        Model modelCopy = new ModelManager(new RemindMe(model.getRemindMe()), new UserPrefs());
        Module target = new ModuleBuilder().withTitle("MOD 1").emptyBuild();
        Description edit = new Description("change something");

        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(target,
                                                                                INDEX_FIRST_ASSIGNMENT.getOneBased(),
                                                                                edit, null);

        Module editedMod = new ModuleBuilder(modelCopy.getFilteredModuleList().get(0)).build();
        editedMod.editAssignment(0, edit);
        Assignment editedAssignment = editedMod.getAssignment(0);

        String expectedMessage = String.format(EditAssignmentCommand.MESSAGE_SUCCESS, editedAssignment);
        Model expectedModel = new ModelManager(getTypicalRemindMeWithFilledModules(), new UserPrefs());
        expectedModel.editAssignment(target, 1, edit);

        assertCommandSuccess(editAssignmentCommand, modelCopy, expectedMessage, expectedModel);
    }

    @Test
    public void execute_dateFieldSpecified_success() {
        Model modelCopy = new ModelManager(new RemindMe(model.getRemindMe()), new UserPrefs());
        Module target = new ModuleBuilder().withTitle("MOD 1").emptyBuild();
        LocalDateTime edit = LocalDateTime.parse(VALID_EXAM_DATETIME_2, Exam.EXAM_DATE_FORMATTER);

        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(target,
                                                                                INDEX_FIRST_ASSIGNMENT.getOneBased(),
                                                                                null, edit);

        Module editedMod = new ModuleBuilder(modelCopy.getFilteredModuleList().get(0)).build();
        editedMod.editAssignment(0, edit);
        Assignment editedAssignment = editedMod.getAssignment(0);

        String expectedMessage = String.format(EditAssignmentCommand.MESSAGE_SUCCESS, editedAssignment);
        Model expectedModel = new ModelManager(getTypicalRemindMeWithFilledModules(), new UserPrefs());
        expectedModel.editAssignment(target, 1, edit);

        assertCommandSuccess(editAssignmentCommand, modelCopy, expectedMessage, expectedModel);
    }


    @Test
    public void execute_duplicateAssignment_failure() {
        Model modelCopy = new ModelManager(new RemindMe(model.getRemindMe()), new UserPrefs());
        Module target = new ModuleBuilder().withTitle("MOD 1").emptyBuild();
        Description existingDescription = modelCopy.getFilteredModuleList()
                                                .get(0).getAssignment(1).getDescription();

        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(target,
                                                                                INDEX_FIRST_ASSIGNMENT.getOneBased(),
                                                                                existingDescription, null);

        String expectedMessage = String.format(EditAssignmentCommand.MESSAGE_DUPLICATE_ASSIGNMENT);

        assertCommandFailure(editAssignmentCommand, modelCopy, expectedMessage);
    }

    @Test
    public void execute_invalidModuleTitle_failure() {
        Model modelCopy = new ModelManager(new RemindMe(model.getRemindMe()), new UserPrefs());
        Module target = new ModuleBuilder().withTitle("MOD 3").emptyBuild();
        Description edit = new Description("change");

        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(target,
                                                                                INDEX_FIRST_ASSIGNMENT.getOneBased(),
                                                                                edit, null);

        String expectedMessage = String.format(EditAssignmentCommand.MESSAGE_NO_MODULE);

        assertCommandFailure(editAssignmentCommand, modelCopy, expectedMessage);
    }

    @Test
    public void execute_invalidAssignmentIndex_failure() {
        Model modelCopy = new ModelManager(new RemindMe(model.getRemindMe()), new UserPrefs());
        Module target = new ModuleBuilder().withTitle("MOD 1").emptyBuild();
        Description edit = new Description("change");

        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(target,
                                                                                INDEX_THIRD_ASSIGNMENT.getOneBased(),
                                                                                edit, null);

        String expectedMessage = String.format(EditAssignmentCommand.MESSAGE_NO_ASSIGNMENT);

        assertCommandFailure(editAssignmentCommand, modelCopy, expectedMessage);
    }

    @Test
    public void execute_noDescriptionChange_failure() {
        Model modelCopy = new ModelManager(new RemindMe(model.getRemindMe()), new UserPrefs());
        Module target = new ModuleBuilder().withTitle("MOD 1").emptyBuild();
        Description edit = modelCopy.getFilteredModuleList().get(0).getAssignment(0).getDescription();

        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(target,
                                                                                INDEX_FIRST_ASSIGNMENT.getOneBased(),
                                                                                edit, null);

        String expectedMessage = String.format(EditAssignmentCommand.MESSAGE_NO_CHANGE);

        assertCommandFailure(editAssignmentCommand, modelCopy, expectedMessage);
    }

    @Test
    public void execute_noDateChange_failure() {
        Model modelCopy = new ModelManager(new RemindMe(model.getRemindMe()), new UserPrefs());
        Module target = new ModuleBuilder().withTitle("MOD 1").emptyBuild();
        LocalDateTime edit = modelCopy.getFilteredModuleList().get(0).getAssignment(0).getDateTime();

        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(target,
                                                                                INDEX_FIRST_ASSIGNMENT.getOneBased(),
                                                                        null, edit);

        String expectedMessage = String.format(EditAssignmentCommand.MESSAGE_NO_CHANGE);

        assertCommandFailure(editAssignmentCommand, modelCopy, expectedMessage);
    }

    @Test
    public void execute_allFieldsSpecified_failure() {
        Model modelCopy = new ModelManager(new RemindMe(model.getRemindMe()), new UserPrefs());
        Module target = new ModuleBuilder().withTitle("MOD 1").emptyBuild();
        Description descriptionEdit = new Description("change something");
        LocalDateTime dateEdit = LocalDateTime.parse(VALID_EXAM_DATETIME_2, Exam.EXAM_DATE_FORMATTER);
        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(target,
                                                                                INDEX_FIRST_ASSIGNMENT.getOneBased(),
                                                                                descriptionEdit, dateEdit);

        String expectedMessage = String.format(EditAssignmentCommand.MESSAGE_TWO_CHANGES);

        assertCommandFailure(editAssignmentCommand, modelCopy, expectedMessage);
    }

    @Test
    public void execute_noFieldsSpecified_failure() {
        Model modelCopy = new ModelManager(new RemindMe(model.getRemindMe()), new UserPrefs());
        Module target = new ModuleBuilder().withTitle("MOD 1").emptyBuild();

        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(target,
                                                                                INDEX_FIRST_ASSIGNMENT.getOneBased(),
                                                                        null, null);

        String expectedMessage = String.format(EditAssignmentCommand.MESSAGE_NO_VALID_CHANGES);

        assertCommandFailure(editAssignmentCommand, modelCopy, expectedMessage);
    }

    @Test
    public void equals() {
        Module target = new ModuleBuilder().withTitle("MOD 1").emptyBuild();
        Description descriptionEdit = new Description("change");
        LocalDateTime dateEdit = LocalDateTime.parse(VALID_EXAM_DATETIME_2, Exam.EXAM_DATE_FORMATTER);
        final EditAssignmentCommand standardCommandDescription = new EditAssignmentCommand(target,
                                                                                INDEX_FIRST_ASSIGNMENT.getOneBased(),
                                                                                descriptionEdit, null);
        final EditAssignmentCommand standardCommandDate = new EditAssignmentCommand(target,
                                                                                INDEX_FIRST_ASSIGNMENT.getOneBased(),
                                                                        null, dateEdit);

        // same values for description edit -> returns true
        EditAssignmentCommand commandWithSameValuesDesc = new EditAssignmentCommand(target,
                                                                                INDEX_FIRST_MODULE.getOneBased(),
                                                                                descriptionEdit, null);
        assertTrue(standardCommandDescription.equals(commandWithSameValuesDesc));

        // same values for date edit -> returns true
        EditAssignmentCommand commandWithSameValuesDate = new EditAssignmentCommand(target,
                                                                                INDEX_FIRST_MODULE.getOneBased(),
                                                                                null, dateEdit);
        assertTrue(standardCommandDate.equals(commandWithSameValuesDate));

        // same object -> returns true
        assertTrue(standardCommandDescription.equals(standardCommandDescription));
        assertTrue(standardCommandDate.equals(standardCommandDate));

        // null -> returns false
        assertFalse(standardCommandDescription.equals(null));
        assertFalse(standardCommandDate.equals(null));

        // different types -> returns false
        assertFalse(standardCommandDescription.equals(new ClearModulesCommand()));
        assertFalse(standardCommandDate.equals(new ClearModulesCommand()));

        // different targets -> returns false
        Module diffTarget = new ModuleBuilder().withTitle("DIFF MOD").emptyBuild();
        assertFalse(standardCommandDescription.equals(new EditAssignmentCommand(diffTarget,
                                                                                INDEX_FIRST_ASSIGNMENT.getOneBased(),
                                                                                descriptionEdit, null)));
        assertFalse(standardCommandDate.equals(new EditAssignmentCommand(diffTarget,
                                                                            INDEX_FIRST_ASSIGNMENT.getOneBased(),
                                                                            null, dateEdit)));

        // different index -> returns false
        assertFalse(standardCommandDescription.equals(new EditAssignmentCommand(target,
                                                                                INDEX_SECOND_ASSIGNMENT.getOneBased(),
                                                                                descriptionEdit, null)));
        assertFalse(standardCommandDate.equals(new EditAssignmentCommand(target,
                                                                            INDEX_SECOND_ASSIGNMENT.getOneBased(),
                                                                    null, dateEdit)));

        // different description -> returns false
        Description diffDescription = new Description("different");
        assertFalse(standardCommandDescription.equals(new EditAssignmentCommand(target,
                                                                                INDEX_FIRST_ASSIGNMENT.getOneBased(),
                                                                                diffDescription, null)));

        // different date -> returns false
        LocalDateTime diffDate = LocalDateTime.parse(VALID_EXAM_DATETIME_1, Exam.EXAM_DATE_FORMATTER);
        assertFalse(standardCommandDescription.equals(new EditAssignmentCommand(target,
                                                                                INDEX_FIRST_ASSIGNMENT.getOneBased(),
                                                                                null, diffDate)));
    }
}
