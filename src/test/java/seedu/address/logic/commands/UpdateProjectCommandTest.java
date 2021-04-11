package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showProjectAtIndex;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.uicommands.ViewProjectAndOverviewUiCommand;
import seedu.address.model.ColabFolder;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;

public class UpdateProjectCommandTest {

    private Model model = new ModelManager(getTypicalColabFolder(), new UserPrefs());

    @Test
    public void execute_unfilteredList_success() {
        Project updatedProject = UpdateProjectCommand.createUpdatedProject(
                model.getFilteredProjectList().get(0), new ProjectName("new project"));

        UpdateProjectCommand updateProjectCommand = new UpdateProjectCommand(INDEX_FIRST,
                new ProjectName("new project"));

        String expectedMessage = String.format(UpdateProjectCommand.MESSAGE_UPDATE_PROJECT_SUCCESS,
                updatedProject.getProjectName());

        Model expectedModel = new ModelManager(new ColabFolder(model.getColabFolder()), new UserPrefs());
        expectedModel.setProject(model.getFilteredProjectList().get(0), updatedProject);

        assertCommandSuccess(updateProjectCommand, model, expectedMessage,
                new ViewProjectAndOverviewUiCommand(INDEX_FIRST), expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST);
        Project updatedProject = UpdateProjectCommand.createUpdatedProject(
                model.getFilteredProjectList().get(0), new ProjectName("new project"));

        UpdateProjectCommand updateProjectCommand = new UpdateProjectCommand(INDEX_FIRST,
                new ProjectName("new project"));

        String expectedMessage = String.format(UpdateProjectCommand.MESSAGE_UPDATE_PROJECT_SUCCESS,
                updatedProject.getProjectName());

        Model expectedModel = new ModelManager(new ColabFolder(model.getColabFolder()), new UserPrefs());
        expectedModel.setProject(model.getFilteredProjectList().get(0), updatedProject);

        assertCommandSuccess(updateProjectCommand, model, expectedMessage,
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
        // ensures that outOfBoundIndex is still in bounds of contact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getColabFolder().getProjectsList().size());

        UpdateProjectCommand updateProjectCommand = new UpdateProjectCommand(outOfBoundIndex,
                new ProjectName("new project"));

        assertCommandFailure(updateProjectCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ProjectName name = new ProjectName("new project");
        ProjectName differentName = new ProjectName("different project");

        final UpdateProjectCommand standardCommand = new UpdateProjectCommand(INDEX_FIRST, name);

        // same values -> returns true
        assertTrue(standardCommand.equals(new UpdateProjectCommand(INDEX_FIRST, name)));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types ->  returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new UpdateProjectCommand(INDEX_SECOND, name)));

        // different name -> returns false
        assertFalse(standardCommand.equals(new UpdateProjectCommand(INDEX_FIRST, differentName)));
    }
}
