package seedu.address.logic.commands.deletecommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.TypicalModuleTitles.TITLE_CS1101;
import static seedu.address.testutil.TypicalModuleTitles.TITLE_CS2101;
import static seedu.address.testutil.TypicalRemindMe.getTypicalRemindMeWithFilledModules;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteExamCommand}.
 */
public class DeleteExamCommandTest {

    private Model model = new ModelManager(getTypicalRemindMeWithFilledModules(), new UserPrefs());

    @Test
    public void execute_validModuleTitleValidExamIndex_success() {

        Module moduleTarget = model.getFilteredModuleList()
                .get(INDEX_SECOND_MODULE.getZeroBased());

        Exam examToDelete = moduleTarget.getExam(0);
        Title title = moduleTarget.getTitle();

        DeleteExamCommand deleteExamCommand = new DeleteExamCommand(title, INDEX_FIRST_EXAM);

        String expectedMessage = String.format(DeleteExamCommand.MESSAGE_DELETE_EXAM_SUCCESS,
                examToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalRemindMeWithFilledModules(), new UserPrefs());
        expectedModel.deleteExam(moduleTarget, examToDelete);
        assertCommandSuccess(deleteExamCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validModuleTitleInvalidExamIndex_failure() {
        Module moduleTarget = model.getFilteredModuleList()
                .get(INDEX_FIRST_MODULE.getZeroBased());
        Title title = moduleTarget.getTitle();
        Index outOfBoundIndex = Index.fromOneBased(moduleTarget.getExams().size() + 1);

        DeleteExamCommand deleteExamCommand = new DeleteExamCommand(title, outOfBoundIndex);
        assertCommandFailure(deleteExamCommand, model, DeleteExamCommand.MESSAGE_EMPTY_EXAMLIST);
    }

    @Test
    public void execute_invalidModuleTitleValidExamIndex_failure() {
        Title invalidTitle = new Title("CS999999");
        Index validIndex = Index.fromOneBased(1);
        DeleteExamCommand deleteExamCommand = new DeleteExamCommand(invalidTitle, validIndex);
        assertCommandFailure(deleteExamCommand, model, Messages.MESSAGE_INVALID_MODULE_TITLE);
    }

    @Test
    public void equals() {
        DeleteExamCommand deleteExamFirstCommand = new DeleteExamCommand(TITLE_CS1101,
                INDEX_FIRST_EXAM);

        DeleteExamCommand deleteExamSecondCommand = new DeleteExamCommand(TITLE_CS2101,
                INDEX_SECOND_EXAM);

        // same object -> return true
        assertTrue(deleteExamFirstCommand.equals(deleteExamFirstCommand));

        DeleteExamCommand deleteExamFirstCommandCopy = new DeleteExamCommand(TITLE_CS1101,
                INDEX_FIRST_EXAM);

        // same values -> return true
        assertTrue(deleteExamFirstCommand.equals(deleteExamFirstCommandCopy));

        // different values -> return false
        assertFalse(deleteExamFirstCommand.equals(deleteExamSecondCommand));

    }

}
