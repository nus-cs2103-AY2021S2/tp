package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPlanAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PLAN;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PLAN;
import static seedu.address.testutil.TypicalPlans.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.Semester;

public class DeleteSemesterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validSemester_success() {
//        Plan planToDeleteFrom = model.getFilteredPlanList().get(INDEX_FIRST_PLAN.getZeroBased());
        Semester semesterToDelete = new Semester(1);
        model.addSemester(INDEX_FIRST_PLAN.getZeroBased(), semesterToDelete);
        DeleteSemesterCommand deleteCommand = new DeleteSemesterCommand(INDEX_FIRST_PLAN,
                semesterToDelete.getSemNumber());

        String expectedMessage = String.format(DeleteSemesterCommand.MESSAGE_DELETE_SEMESTER_SUCCESS,
                INDEX_FIRST_PLAN.getOneBased(), semesterToDelete.toString());

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidSemester_throwsCommandException() {
        DeleteSemesterCommand deleteCommand = new DeleteSemesterCommand(INDEX_FIRST_PLAN, 99);
        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_SEM_NUMBER);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPlanList().size() + 1);
        deleteCommand = new DeleteSemesterCommand(outOfBoundIndex, 1);
        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PLAN_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteSemesterCommand deleteFirstSemesterCommand = new DeleteSemesterCommand(INDEX_FIRST_PLAN, 1);
        DeleteSemesterCommand deleteSecondSemesterCommand = new DeleteSemesterCommand(INDEX_FIRST_PLAN, 2);

        // same object -> returns true
        assertTrue(deleteFirstSemesterCommand.equals(deleteFirstSemesterCommand));

        // same values -> returns true
        DeleteSemesterCommand deleteFirstSemesterCommandCopy = new DeleteSemesterCommand(INDEX_FIRST_PLAN, 1);
        assertTrue(deleteFirstSemesterCommand.equals(deleteFirstSemesterCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstSemesterCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstSemesterCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstSemesterCommand.equals(deleteSecondSemesterCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPlan(Model model) {
        model.updateFilteredPlanList(p -> false);

        assertTrue(model.getFilteredPlanList().isEmpty());
    }
}
