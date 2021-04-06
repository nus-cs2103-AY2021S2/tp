package seedu.address.logic.commands.deletecommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSIGNMENT;
import static seedu.address.testutil.TypicalModuleTitles.TITLE_CS1101;
import static seedu.address.testutil.TypicalModuleTitles.TITLE_CS2101;
import static seedu.address.testutil.TypicalRemindMe.getTypicalRemindMeWithFilledModules;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteAssignmentCommand}.
 */
public class DeleteAssignmentCommandTest {

    private Model model = new ModelManager(getTypicalRemindMeWithFilledModules(), new UserPrefs());

    @Test
    public void execute_validModuleTitleValidAssignmentIndex_success() {

        Module moduleTarget = model.getFilteredModuleList()
                .get(INDEX_FIRST_MODULE.getZeroBased());

        Assignment assignmentToDelete = moduleTarget.getAssignment(0);
        Title title = moduleTarget.getTitle();

        DeleteAssignmentCommand deleteAssignmentCommand = new DeleteAssignmentCommand(title, INDEX_FIRST_ASSIGNMENT);

        String expectedMessage = String.format(DeleteAssignmentCommand.MESSAGE_DELETE_ASSIGNMENT_SUCCESS,
                assignmentToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalRemindMeWithFilledModules(), new UserPrefs());
        expectedModel.deleteAssignment(moduleTarget, assignmentToDelete);
        assertCommandSuccess(deleteAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validModuleTitleInvalidAssignmentIndex_failure() {
        Module moduleTarget = model.getFilteredModuleList()
                .get(INDEX_FIRST_MODULE.getZeroBased());
        Title title = moduleTarget.getTitle();
        Index outOfBoundIndex = Index.fromOneBased(moduleTarget.getAssignments().size() + 1);

        DeleteAssignmentCommand deleteAssignmentCommand = new DeleteAssignmentCommand(title, outOfBoundIndex);
        assertCommandFailure(deleteAssignmentCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidModuleTitleValidAssignmentIndex_failure() {
        Title invalidTitle = new Title("CS999999");
        Index validIndex = Index.fromOneBased(1);
        DeleteAssignmentCommand deleteAssignmentCommand = new DeleteAssignmentCommand(invalidTitle, validIndex);
        assertCommandFailure(deleteAssignmentCommand, model, Messages.MESSAGE_INVALID_MODULE_TITLE);
    }

    @Test
    public void equals() {
        DeleteAssignmentCommand deleteAssignmentFirstCommand = new DeleteAssignmentCommand(TITLE_CS1101,
                INDEX_FIRST_ASSIGNMENT);

        DeleteAssignmentCommand deleteAssignmentSecondCommand = new DeleteAssignmentCommand(TITLE_CS2101,
                INDEX_SECOND_ASSIGNMENT);

        // same object -> return true
        assertTrue(deleteAssignmentFirstCommand.equals(deleteAssignmentFirstCommand));

        DeleteAssignmentCommand deleteAssignmentFirstCommandCopy = new DeleteAssignmentCommand(TITLE_CS1101,
                INDEX_FIRST_ASSIGNMENT);

        // same values -> return true
        assertTrue(deleteAssignmentFirstCommand.equals(deleteAssignmentFirstCommandCopy));

        // different values -> return false
        assertFalse(deleteAssignmentFirstCommand.equals(deleteAssignmentSecondCommand));

    }


}
