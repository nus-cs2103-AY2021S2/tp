package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DEADLINE_1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DEADLINE_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProjectAtIndex;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateDeadlineCommand.UpdateDeadlineDescriptor;
import seedu.address.logic.uicommands.ViewProjectAndOverviewUiCommand;
import seedu.address.model.ColabFolder;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.DeadlineList;
import seedu.address.model.project.Project;
import seedu.address.model.task.CompletableDeadline;

public class UpdateDeadlineCommandTest {

    private Model model = new ModelManager(getTypicalColabFolder(), new UserPrefs());

    @Test
    public void execute_unfilteredList_success() {
        UpdateDeadlineDescriptor descriptor = DESC_DEADLINE_1;
        UpdateDeadlineCommand updateDeadlineCommand = new UpdateDeadlineCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        Model expectedModel = new ModelManager(new ColabFolder(model.getColabFolder()), new UserPrefs());
        Project projectRelated = expectedModel.getFilteredProjectList().get(0);
        DeadlineList deadlines = projectRelated.getDeadlines();
        CompletableDeadline deadlineToUpdate = deadlines.getDeadline(0);
        CompletableDeadline updatedDeadline = UpdateDeadlineCommand.createUpdatedDeadline(deadlineToUpdate, descriptor);

        if (deadlines.checkIsDone(INDEX_FIRST.getZeroBased())) {
            updatedDeadline.markAsDone();
        }

        deadlines.setDeadline(INDEX_FIRST.getZeroBased(), updatedDeadline);

        String expectedMessage = String.format(UpdateDeadlineCommand.MESSAGE_UPDATE_DEADLINE_SUCCESS, updatedDeadline);

        assertCommandSuccess(updateDeadlineCommand, model, expectedMessage,
                new ViewProjectAndOverviewUiCommand(INDEX_FIRST), expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST);
        UpdateDeadlineDescriptor descriptor = DESC_DEADLINE_1;
        UpdateDeadlineCommand updateDeadlineCommand = new UpdateDeadlineCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        Model expectedModel = new ModelManager(new ColabFolder(model.getColabFolder()), new UserPrefs());
        Project projectRelated = expectedModel.getFilteredProjectList().get(0);
        DeadlineList deadlines = projectRelated.getDeadlines();
        CompletableDeadline deadlineToUpdate = deadlines.getDeadline(0);
        CompletableDeadline updatedDeadline = UpdateDeadlineCommand.createUpdatedDeadline(deadlineToUpdate, descriptor);

        if (deadlines.checkIsDone(INDEX_FIRST.getZeroBased())) {
            updatedDeadline.markAsDone();
        }

        deadlines.setDeadline(INDEX_FIRST.getZeroBased(), updatedDeadline);

        String expectedMessage = String.format(UpdateDeadlineCommand.MESSAGE_UPDATE_DEADLINE_SUCCESS, updatedDeadline);

        assertCommandSuccess(updateDeadlineCommand, model, expectedMessage,
                new ViewProjectAndOverviewUiCommand(INDEX_FIRST), expectedModel);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of project list
     */
    @Test
    public void execute_invalidProjectIndexFilteredList_failure() {
        showProjectAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of project list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getColabFolder().getProjectsList().size());

        UpdateDeadlineCommand updateDeadlineCommand = new UpdateDeadlineCommand(outOfBoundIndex,
                INDEX_FIRST, DESC_DEADLINE_1);

        assertCommandFailure(updateDeadlineCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UpdateDeadlineCommand.UpdateDeadlineDescriptor descriptor = DESC_DEADLINE_1;
        UpdateDeadlineCommand.UpdateDeadlineDescriptor differentDescriptor = DESC_DEADLINE_2;

        final UpdateDeadlineCommand standardCommand = new UpdateDeadlineCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        // same values -> returns true
        assertTrue(standardCommand.equals(new UpdateDeadlineCommand(INDEX_FIRST, INDEX_FIRST, descriptor)));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types ->  returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different project index -> returns false
        assertFalse(standardCommand.equals(new UpdateDeadlineCommand(INDEX_SECOND, INDEX_FIRST, descriptor)));

        // different deadline index -> returns false
        assertFalse(standardCommand.equals(new UpdateDeadlineCommand(INDEX_FIRST, INDEX_SECOND, descriptor)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new UpdateDeadlineCommand(INDEX_FIRST, INDEX_FIRST, differentDescriptor)));
    }
}
