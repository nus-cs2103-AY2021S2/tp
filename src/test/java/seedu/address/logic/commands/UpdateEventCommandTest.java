package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EVENT_1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EVENT_2;
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
import seedu.address.model.project.EventList;
import seedu.address.model.project.Project;
import seedu.address.model.task.repeatable.Event;

public class UpdateEventCommandTest {

    private Model model = new ModelManager(getTypicalColabFolder(), new UserPrefs());

    @Test
    public void execute_unfilteredList_success() {
        UpdateEventCommand.UpdateEventDescriptor descriptor = DESC_EVENT_1;
        UpdateEventCommand updateEventCommand = new UpdateEventCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        Model expectedModel = new ModelManager(new ColabFolder(model.getColabFolder()), new UserPrefs());
        Project projectRelated = expectedModel.getFilteredProjectList().get(0);
        EventList events = projectRelated.getEvents();
        Event eventToUpdate = events.getEvent(0);
        Event updatedEvent = UpdateEventCommand.createUpdatedEvent(eventToUpdate, descriptor);

        events.setEvent(INDEX_FIRST.getZeroBased(), updatedEvent);

        String expectedMessage = String.format(UpdateEventCommand.MESSAGE_UPDATE_EVENT_SUCCESS, updatedEvent);

        assertCommandSuccess(updateEventCommand, model, expectedMessage,
                new ViewProjectAndOverviewUiCommand(INDEX_FIRST), expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST);
        UpdateEventCommand.UpdateEventDescriptor descriptor = DESC_EVENT_1;
        UpdateEventCommand updateEventCommand = new UpdateEventCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        Model expectedModel = new ModelManager(new ColabFolder(model.getColabFolder()), new UserPrefs());
        Project projectRelated = expectedModel.getFilteredProjectList().get(0);
        EventList events = projectRelated.getEvents();
        Event eventToUpdate = events.getEvent(0);
        Event updatedEvent = UpdateEventCommand.createUpdatedEvent(eventToUpdate, descriptor);

        events.setEvent(INDEX_FIRST.getZeroBased(), updatedEvent);

        String expectedMessage = String.format(UpdateEventCommand.MESSAGE_UPDATE_EVENT_SUCCESS, updatedEvent);

        assertCommandSuccess(updateEventCommand, model, expectedMessage,
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

        UpdateEventCommand updateEventCommand = new UpdateEventCommand(outOfBoundIndex,
                INDEX_FIRST, DESC_EVENT_1);

        assertCommandFailure(updateEventCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UpdateEventCommand.UpdateEventDescriptor descriptor = DESC_EVENT_1;
        UpdateEventCommand.UpdateEventDescriptor differentDescriptor = DESC_EVENT_2;

        final UpdateEventCommand standardCommand = new UpdateEventCommand(INDEX_FIRST, INDEX_FIRST, descriptor);

        // same values -> returns true
        assertTrue(standardCommand.equals(new UpdateEventCommand(INDEX_FIRST, INDEX_FIRST, descriptor)));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types ->  returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different project index -> returns false
        assertFalse(standardCommand.equals(new UpdateEventCommand(INDEX_SECOND, INDEX_FIRST, descriptor)));

        // different event index -> returns false
        assertFalse(standardCommand.equals(new UpdateEventCommand(INDEX_FIRST, INDEX_SECOND, descriptor)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new UpdateEventCommand(INDEX_FIRST, INDEX_FIRST, differentDescriptor)));
    }
}
