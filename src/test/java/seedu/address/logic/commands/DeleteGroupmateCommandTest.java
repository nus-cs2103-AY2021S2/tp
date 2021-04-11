package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_DELETE_GROUPMATE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
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
import seedu.address.model.groupmate.Groupmate;
import seedu.address.model.project.Project;
import seedu.address.testutil.GroupmateBuilder;
import seedu.address.testutil.ProjectBuilder;

public class DeleteGroupmateCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() throws DateConversionException {
        model = new ModelManager(getTypicalColabFolder(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() throws DateConversionException {
        Groupmate contactToDelete = new GroupmateBuilder().withName("a").build();
        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Project editedProject = new ProjectBuilder(projectToEdit).build();
        editedProject.addGroupmate(contactToDelete);

        model.setProject(
                projectToEdit,
                editedProject
        );

        Index firstContactIndex = Index.fromOneBased(1); //groupmate is first in sorted list

        DeleteGroupmateCommand deleteGroupmateCommand = new DeleteGroupmateCommand(INDEX_FIRST, firstContactIndex);

        String expectedMessage = String.format(MESSAGE_DELETE_GROUPMATE_SUCCESS,
                contactToDelete.getName(), projectToEdit.getProjectName());

        ModelManager expectedModel = new ModelManager(getTypicalColabFolder(), new UserPrefs());

        assertCommandSuccess(deleteGroupmateCommand, model, expectedMessage,
                new ViewProjectAndOverviewUiCommand(INDEX_FIRST), expectedModel);
    }

    @Test
    public void execute_invalidProjectIndex_throwsCommandException() {
        Groupmate contactToDelete = new GroupmateBuilder().build();
        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Project editedProject = new ProjectBuilder(projectToEdit).build();
        editedProject.addGroupmate(contactToDelete);

        model.setProject(
                projectToEdit,
                editedProject
        );

        Index lastContactIndex = Index.fromOneBased(model.getFilteredProjectList().get(
                INDEX_FIRST.getZeroBased()).getGroupmates().size());

        DeleteGroupmateCommand deleteGroupmateCommand = new DeleteGroupmateCommand(INDEX_THIRD, lastContactIndex);

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, () -> deleteGroupmateCommand.execute(model)
        );
    }

    @Test
    public void execute_invalidGroupmateIndex_throwsCommandException() {
        Index projectIndex = INDEX_FIRST;
        Index groupmateIndex = Index.fromOneBased(100);

        DeleteGroupmateCommand deleteGroupmateCommand = new DeleteGroupmateCommand(projectIndex, groupmateIndex);

        assertCommandFailure(deleteGroupmateCommand, model, Messages.MESSAGE_INVALID_GROUPMATE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Groupmate contactToDelete = new GroupmateBuilder().build();
        Project project1ToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Project editedProject1 = new ProjectBuilder(project1ToEdit).build();
        editedProject1.addGroupmate(contactToDelete);

        model.setProject(
                project1ToEdit,
                editedProject1
        );

        Project project2ToEdit = model.getFilteredProjectList().get(INDEX_SECOND.getZeroBased());
        Project editedProject2 = new ProjectBuilder(project2ToEdit).build();
        editedProject2.addGroupmate(contactToDelete);


        model.setProject(
                project2ToEdit,
                editedProject2
        );

        Index lastContactFromProject1 = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getGroupmates().size());
        Index lastContactFromProject2 = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_SECOND.getZeroBased()).getGroupmates().size());

        DeleteGroupmateCommand deleteContactFromProject1Command = new DeleteGroupmateCommand(
                INDEX_FIRST, lastContactFromProject1);
        DeleteGroupmateCommand deleteContactFromProject2Command = new DeleteGroupmateCommand(
                INDEX_SECOND, lastContactFromProject2);

        // same object -> returns true
        assertEquals(deleteContactFromProject1Command, deleteContactFromProject1Command);

        // same values -> returns true
        DeleteGroupmateCommand deleteContactFromProject1CommandCopy = new DeleteGroupmateCommand(
                INDEX_FIRST, lastContactFromProject1);
        assertEquals(deleteContactFromProject1Command, deleteContactFromProject1CommandCopy);

        // different types -> returns false
        assertNotEquals(deleteContactFromProject1Command, 1);

        // null -> returns false
        assertNotEquals(deleteContactFromProject1Command, null);

        // different contact -> returns false
        assertNotEquals(deleteContactFromProject1Command, deleteContactFromProject2Command);
    }

}
