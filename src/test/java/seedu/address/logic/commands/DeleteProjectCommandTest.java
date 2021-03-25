package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProjectAtIndex;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;

public class DeleteProjectCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() throws DateConversionException {
        model = new ModelManager(getTypicalColabFolder(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Project projectToDelete = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteProjectCommand.MESSAGE_DELETE_PROJECT_SUCCESS, projectToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalColabFolder(), new UserPrefs());
        expectedModel.deleteProject(projectToDelete);

        assertCommandSuccess(deleteProjectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(outOfBoundIndex);

        assertCommandFailure(deleteProjectCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST);

        Project projectToDelete = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteProjectCommand.MESSAGE_DELETE_PROJECT_SUCCESS, projectToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalColabFolder(), new UserPrefs());
        expectedModel.deleteProject(projectToDelete);
        showNoProject(expectedModel);

        assertCommandSuccess(deleteProjectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showProjectAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of project list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getColabFolder().getProjectsList().size());

        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(outOfBoundIndex);

        assertCommandFailure(deleteProjectCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteProjectCommand deleteFirstProjectCommand = new DeleteProjectCommand(INDEX_FIRST);
        DeleteProjectCommand deleteSecondProjectCommand = new DeleteProjectCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstProjectCommand.equals(deleteFirstProjectCommand));

        // same values -> returns true
        DeleteProjectCommand deleteFirstProjectCommandCopy = new DeleteProjectCommand(INDEX_FIRST);
        assertTrue(deleteFirstProjectCommand.equals(deleteFirstProjectCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstProjectCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstProjectCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstProjectCommand.equals(deleteSecondProjectCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoProject(Model model) {
        model.updateFilteredProjectList(p -> false);

        assertTrue(model.getFilteredProjectList().isEmpty());
    }
}
