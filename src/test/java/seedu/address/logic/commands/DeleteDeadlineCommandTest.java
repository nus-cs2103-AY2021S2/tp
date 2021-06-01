package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_DELETE_DEADLINE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalColabFolder.getTypicalColabFolder;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.uicommands.ViewProjectAndOverviewUiCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.testutil.DeadlineBuilder;
import seedu.address.testutil.ProjectBuilder;

public class DeleteDeadlineCommandTest {

    private Model model;
    private Deadline deadlineToDelete;
    private Project projectToEdit;
    private Project editedProject;

    @BeforeEach
    public void setUp() throws DateConversionException {
        model = new ModelManager(getTypicalColabFolder(), new UserPrefs());
        deadlineToDelete = new DeadlineBuilder().build();
        projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        editedProject = new ProjectBuilder(projectToEdit).build();
        editedProject.addDeadline(deadlineToDelete);

        model.setProject(
                projectToEdit,
                editedProject
        );
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Index firstDeadlineIndex = Index.fromOneBased(1); // deadline is added to front since list is sorted

        DeleteDeadlineCommand deleteDeadlineCommand = new DeleteDeadlineCommand(INDEX_FIRST, firstDeadlineIndex);

        String expectedMessage = String.format(MESSAGE_DELETE_DEADLINE_SUCCESS, firstDeadlineIndex.getOneBased(),
                projectToEdit.getProjectName());

        ModelManager expectedModel = new ModelManager(getTypicalColabFolder(), new UserPrefs());

        assertCommandSuccess(deleteDeadlineCommand, model, expectedMessage,
                new ViewProjectAndOverviewUiCommand(INDEX_FIRST), expectedModel);
    }

    @Test
    public void execute_invalidProjectIndex_throwsCommandException() {
        Index lastDeadlineIndex = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getDeadlines().size());

        DeleteDeadlineCommand deleteDeadlineCommand = new DeleteDeadlineCommand(INDEX_THIRD, lastDeadlineIndex);

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, () -> deleteDeadlineCommand.execute(model)
        );
    }

    @Test
    public void execute_invalidDeadlineIndex_throwsCommandException() {
        Index invalidDeadlineIndex = Index.fromOneBased(
                model.getFilteredProjectList().get(
                        INDEX_FIRST.getZeroBased()).getDeadlines().getSortedDeadlineList().size() + 1);

        DeleteDeadlineCommand deleteDeadlineCommand = new DeleteDeadlineCommand(INDEX_FIRST, invalidDeadlineIndex);

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_DEADLINE_DISPLAYED_INDEX, () -> deleteDeadlineCommand.execute(model)
        );
    }

    @Test
    public void equals() {
        Project project2ToEdit = model.getFilteredProjectList().get(INDEX_SECOND.getZeroBased());
        Project editedProject2 = new ProjectBuilder(project2ToEdit).build();
        editedProject2.addDeadline(deadlineToDelete);


        model.setProject(
                project2ToEdit,
                editedProject2
        );

        Index lastDeadlineFromProject1 = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getDeadlines().size());
        Index lastDeadlineFromProject2 = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_SECOND.getZeroBased()).getDeadlines().size());

        DeleteDeadlineCommand deleteDeadlineFromProject1Command = new DeleteDeadlineCommand(
                INDEX_FIRST, lastDeadlineFromProject1);
        DeleteDeadlineCommand deleteDeadlineFromProject2Command = new DeleteDeadlineCommand(
                INDEX_SECOND, lastDeadlineFromProject2);

        // same object -> returns true
        assertEquals(deleteDeadlineFromProject1Command, deleteDeadlineFromProject1Command);

        // same values -> returns true
        DeleteDeadlineCommand deleteDeadlineFromProject1CommandCopy = new DeleteDeadlineCommand(
                INDEX_FIRST, lastDeadlineFromProject1);
        assertEquals(deleteDeadlineFromProject1Command, deleteDeadlineFromProject1CommandCopy);

        // different types -> returns false
        assertNotEquals(deleteDeadlineFromProject1Command, 1);

        // null -> returns false
        assertNotEquals(deleteDeadlineFromProject1Command, null);

        // different deadline -> returns false
        assertNotEquals(deleteDeadlineFromProject1Command, deleteDeadlineFromProject2Command);
    }

}
