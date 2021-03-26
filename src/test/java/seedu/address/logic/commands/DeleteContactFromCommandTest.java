package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DeleteContactFromCommand.MESSAGE_DELETE_PROJECT_SUCCESS;
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
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ProjectBuilder;

public class DeleteContactFromCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() throws DateConversionException {
        model = new ModelManager(getTypicalColabFolder(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() throws DateConversionException {
        Person personToDelete = new PersonBuilder().build();
        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Project editedProject = new ProjectBuilder(projectToEdit).build();
        editedProject.addParticipant(personToDelete);

        model.setProject(
                projectToEdit,
                editedProject
        );

        Index lastContactIndex = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getParticipants().size());

        DeleteContactFromCommand deleteContactFromCommand = new DeleteContactFromCommand(INDEX_FIRST, lastContactIndex);

        String expectedMessage = String.format(MESSAGE_DELETE_PROJECT_SUCCESS,
                personToDelete.getName(), projectToEdit.getProjectName());

        ModelManager expectedModel = new ModelManager(getTypicalColabFolder(), new UserPrefs());

        assertCommandSuccess(deleteContactFromCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidProjectIndex_throwsCommandException() {
        Person personToDelete = new PersonBuilder().build();
        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Project editedProject = new ProjectBuilder(projectToEdit).build();
        editedProject.addParticipant(personToDelete);

        model.setProject(
                projectToEdit,
                editedProject
        );

        Index lastContactIndex = Index.fromOneBased(model.getFilteredProjectList().get(
                INDEX_FIRST.getZeroBased()).getParticipants().size());

        DeleteContactFromCommand deleteContactFromCommand = new DeleteContactFromCommand(INDEX_THIRD, lastContactIndex);

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, () -> deleteContactFromCommand.execute(model)
        );
    }

    @Test
    public void equals() {
        Person personToDelete = new PersonBuilder().build();
        Project project1ToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Project editedProject1 = new ProjectBuilder(project1ToEdit).build();
        editedProject1.addParticipant(personToDelete);

        model.setProject(
                project1ToEdit,
                editedProject1
        );

        Project project2ToEdit = model.getFilteredProjectList().get(INDEX_SECOND.getZeroBased());
        Project editedProject2 = new ProjectBuilder(project2ToEdit).build();
        editedProject2.addParticipant(personToDelete);


        model.setProject(
                project2ToEdit,
                editedProject2
        );

        Index lastContactFromProject1 = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getParticipants().size());
        Index lastContactFromProject2 = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_SECOND.getZeroBased()).getParticipants().size());

        DeleteContactFromCommand deleteContactFromProject1Command = new DeleteContactFromCommand(
                INDEX_FIRST, lastContactFromProject1);
        DeleteContactFromCommand deleteContactFromProject2Command = new DeleteContactFromCommand(
                INDEX_SECOND, lastContactFromProject2);

        // same object -> returns true
        assertEquals(deleteContactFromProject1Command, deleteContactFromProject1Command);

        // same values -> returns true
        DeleteContactFromCommand deleteContactFromProject1CommandCopy = new DeleteContactFromCommand(
                INDEX_FIRST, lastContactFromProject1);
        assertEquals(deleteContactFromProject1Command, deleteContactFromProject1CommandCopy);

        // different types -> returns false
        assertNotEquals(deleteContactFromProject1Command, 1);

        // null -> returns false
        assertNotEquals(deleteContactFromProject1Command, null);

        // different person -> returns false
        assertNotEquals(deleteContactFromProject1Command, deleteContactFromProject2Command);
    }

}
