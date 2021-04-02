package seedu.address.logic.commands.gradecommands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.grade.Grade;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;
import static seedu.address.testutil.TypicalBudgets.getTypicalBudgetBook;
import static seedu.address.testutil.TypicalGrades.getTypicalGradeBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalReminders.getTypicalReminderTracker;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleTracker;
import static seedu.address.testutil.TypicalTutors.getTypicalTutorBook;

public class DeleteGradeCommandTest {

    private Model model = new ModelManager(getTypicalTutorBook(), new UserPrefs(), getTypicalAppointmentBook(),
            getTypicalBudgetBook(), getTypicalGradeBook(), getTypicalScheduleTracker(), getTypicalReminderTracker());


    @Test
    public void execute_validIndexUnfilteredList_throwCommandException() {
        Grade gradeToDelete =
                model.getFilteredGradeList().get(INDEX_FIRST_PERSON.getZeroBased());

    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Grade gradeToDelete = model.getFilteredGradeList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteGradeCommand deleteGradeCommand = new DeleteGradeCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteGradeCommand.MESSAGE_DELETE_GRADE_SUCCESS, gradeToDelete);

        ModelManager expectedModel = new ModelManager(model.getTutorBook(), new UserPrefs(),
                model.getAppointmentBook(), model.getBudgetBook(),
                model.getGradeBook(), model.getScheduleTracker(), model.getReminderTracker());
        expectedModel.deleteGrade(gradeToDelete);

        assertCommandSuccess(deleteGradeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGradeList().size() + 1);
        DeleteGradeCommand deleteGradeCommand = new DeleteGradeCommand(outOfBoundIndex);

        assertGradeCommandFailure(deleteGradeCommand, model, Messages.MESSAGE_INVALID_GRADE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteGradeCommand deleteFirstCommand = new DeleteGradeCommand(INDEX_FIRST_PERSON);
        DeleteGradeCommand deleteSecondCommand = new DeleteGradeCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteGradeCommand deleteFirstCommandCopy = new DeleteGradeCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different grade -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
