package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPlans.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.plan.Plan;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeletePlanCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Plan planToDelete = model.getFilteredPlanList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePlanCommand deletePlanCommand = new DeletePlanCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeletePlanCommand.MESSAGE_DELETE_PERSON_SUCCESS, planToDelete);

        ModelManager expectedModel = new ModelManager(model.getPlans(), new UserPrefs());
        expectedModel.deletePlan(planToDelete);

        assertCommandSuccess(deletePlanCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPlanList().size() + 1);
        DeletePlanCommand deletePlanCommand = new DeletePlanCommand(outOfBoundIndex);

        assertCommandFailure(deletePlanCommand, model, Messages.MESSAGE_INVALID_PLAN_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeletePlanCommand deleteFirstCommand = new DeletePlanCommand(INDEX_FIRST_PERSON);
        DeletePlanCommand deleteSecondCommand = new DeletePlanCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePlanCommand deleteFirstCommandCopy = new DeletePlanCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different plan -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPlanList(p -> false);

        assertTrue(model.getFilteredPlanList().isEmpty());
    }
}
