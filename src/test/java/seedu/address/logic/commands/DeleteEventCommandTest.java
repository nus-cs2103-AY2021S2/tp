package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_DELETE_EVENT_SUCCESS;
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
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.task.repeatable.Event;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.ProjectBuilder;

public class DeleteEventCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() throws DateConversionException {
        model = new ModelManager(getTypicalColabFolder(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() throws DateConversionException {
        Event eventToDelete = new EventBuilder().build();
        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Project editedProject = new ProjectBuilder(projectToEdit).build();
        editedProject.addEvent(eventToDelete);

        model.setProject(
                projectToEdit,
                editedProject
        );

        Index lastEventIndex = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getEvents().getEvents().size());

        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(INDEX_FIRST, lastEventIndex);

        String expectedMessage = String.format(MESSAGE_DELETE_EVENT_SUCCESS, lastEventIndex.getOneBased());

        ModelManager expectedModel = new ModelManager(getTypicalColabFolder(), new UserPrefs());

        assertCommandSuccess(deleteEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidProjectIndex_throwsCommandException() {
        Event eventToDelete = new EventBuilder().build();
        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Project editedProject = new ProjectBuilder(projectToEdit).build();
        editedProject.addEvent(eventToDelete);

        model.setProject(
                projectToEdit,
                editedProject
        );

        Index lastEventIndex = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getEvents().getEvents().size());

        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(INDEX_THIRD, lastEventIndex);

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX, () -> deleteEventCommand.execute(model)
        );
    }

    @Test
    public void execute_invalidEventIndex_throwsCommandException() {
        Event eventToDelete = new EventBuilder().build();
        Project projectToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Project editedProject = new ProjectBuilder(projectToEdit).build();
        editedProject.addEvent(eventToDelete);

        model.setProject(
                projectToEdit,
                editedProject
        );

        Index invalidEventIndex = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getEvents().getEvents().size() + 1);

        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(INDEX_FIRST, invalidEventIndex);

        assertThrows(
                CommandException.class,
                Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX, () -> deleteEventCommand.execute(model)
        );
    }

    @Test
    public void equals() {
        Event eventToDelete = new EventBuilder().build();
        Project project1ToEdit = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Project editedProject1 = new ProjectBuilder(project1ToEdit).build();
        editedProject1.addEvent(eventToDelete);

        model.setProject(
                project1ToEdit,
                editedProject1
        );

        Project project2ToEdit = model.getFilteredProjectList().get(INDEX_SECOND.getZeroBased());
        Project editedProject2 = new ProjectBuilder(project2ToEdit).build();
        editedProject2.addEvent(eventToDelete);


        model.setProject(
                project2ToEdit,
                editedProject2
        );

        Index lastEventFromProject1 = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased()).getEvents().getEvents().size());
        Index lastEventFromProject2 = Index.fromOneBased(
                model.getFilteredProjectList().get(INDEX_SECOND.getZeroBased()).getEvents().getEvents().size());

        DeleteEventCommand deleteEventFromProject1Command = new DeleteEventCommand(
                INDEX_FIRST, lastEventFromProject1);
        DeleteEventCommand deleteEventFromProject2Command = new DeleteEventCommand(
                INDEX_SECOND, lastEventFromProject2);

        // same object -> returns true
        assertEquals(deleteEventFromProject1Command, deleteEventFromProject1Command);

        // same values -> returns true
        DeleteEventCommand deleteEventFromProject1CommandCopy = new DeleteEventCommand(
                INDEX_FIRST, lastEventFromProject1);
        assertEquals(deleteEventFromProject1Command, deleteEventFromProject1CommandCopy);

        // different types -> returns false
        assertNotEquals(deleteEventFromProject1Command, 1);

        // null -> returns false
        assertNotEquals(deleteEventFromProject1Command, null);

        // different event -> returns false
        assertNotEquals(deleteEventFromProject1Command, deleteEventFromProject2Command);
    }

}
