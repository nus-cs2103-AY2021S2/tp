package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_ENDDATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDDATE_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTDATE_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTTIME_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.address.logic.commands.EditEventCommand.MESSAGE_EDIT_TO_EXPIRED_EVENT;
import static seedu.address.testutil.TypicalEvents.getTypicalSochedule;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Sochedule;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.EventBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditEventCommand.
 */
public class EditEventCommandTest {

    private Model model = new ModelManager(getTypicalSochedule(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Event editedEvent = new EventBuilder().build();
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, descriptor);

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new Sochedule(model.getSochedule()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastEvent = Index.fromOneBased(model.getFilteredEventList().size());
        Event lastEvent = model.getFilteredEventList().get(indexLastEvent.getZeroBased());

        EventBuilder eventInList = new EventBuilder(lastEvent);
        Event editedEvent = eventInList.withName(VALID_EVENT_NAME_EVENTTWO)
                .withStartDate(VALID_EVENT_STARTDATE_EVENTTWO).withStartTime(VALID_EVENT_STARTTIME_EVENTTWO).build();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_EVENT_NAME_EVENTTWO)
                .withStartDate(VALID_EVENT_STARTDATE_EVENTTWO).withStartTime(VALID_EVENT_STARTTIME_EVENTTWO).build();
        EditEventCommand editEventCommand = new EditEventCommand(indexLastEvent, descriptor);

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new Sochedule(model.getSochedule()), new UserPrefs());
        expectedModel.setEvent(lastEvent, editedEvent);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Event eventInFilteredList = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        Event editedEvent = new EventBuilder(eventInFilteredList).withName(VALID_EVENT_NAME_EVENTTWO).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT,
                new EditEventDescriptorBuilder().withName(VALID_EVENT_NAME_EVENTTWO).build());

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new Sochedule(model.getSochedule()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEventUnfilteredList_failure() {
        Event firstEvent = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(firstEvent).build();
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_SECOND_EVENT, descriptor);

        assertCommandFailure(editEventCommand, model, EditEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_duplicateEventFilteredList_failure() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        // edit event in filtered list into a duplicate in sochedule
        Event eventInList = model.getSochedule().getEventList().get(INDEX_SECOND_EVENT.getZeroBased());
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT,
                new EditEventDescriptorBuilder(eventInList).build());

        assertCommandFailure(editEventCommand, model, EditEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_invalidEventIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_EVENT_NAME_EVENTTWO).build();
        EditEventCommand editEventCommand = new EditEventCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of sochedule
     */
    @Test
    public void execute_invalidEventIndexFilteredList_failure() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);
        Index outOfBoundIndex = INDEX_SECOND_EVENT;
        // ensures that outOfBoundIndex is still in bounds of sochedule list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSochedule().getEventList().size());

        EditEventCommand editEventCommand = new EditEventCommand(outOfBoundIndex,
                new EditEventDescriptorBuilder().withName(VALID_EVENT_NAME_EVENTTWO).build());

        assertCommandFailure(editEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_eventEndDateTimeBeforeThanStartDateTime_failure() {
        Index indexLastEvent = Index.fromOneBased(model.getFilteredEventList().size());

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withEndDate(VALID_EVENT_ENDDATE_EVENTONE).build();
        EditEventCommand editEventCommand = new EditEventCommand(indexLastEvent, descriptor);

        assertCommandFailure(editEventCommand, model, Messages.MESSAGE_END_DATETIME_BEFORE_START_DATETIME);
    }

    @Test
    public void execute_editEventEndDateTimeToExpiredEndDateTime_failure() {
        Index indexLastEvent = Index.fromOneBased(model.getFilteredEventList().size());

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withStartDate(INVALID_EVENT_ENDDATE).withEndDate(INVALID_EVENT_ENDDATE).build();
        EditEventCommand editEventCommand = new EditEventCommand(indexLastEvent, descriptor);

        assertCommandFailure(editEventCommand, model, MESSAGE_EDIT_TO_EXPIRED_EVENT);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT, new EditEventDescriptor());

        assertCommandFailure(editEventCommand, model, EditEventCommand.MESSAGE_NO_CHANGE);
    }

    @Test
    public void equals() {
        final EditEventCommand standardCommand = new EditEventCommand(INDEX_FIRST_EVENT, DESC_EVENTONE);

        // same values -> returns true
        EditEventDescriptor copyDescriptor = new EditEventDescriptor(DESC_EVENTONE);
        EditEventCommand commandWithSameValues = new EditEventCommand(INDEX_FIRST_EVENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_SECOND_EVENT, DESC_EVENTONE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_FIRST_EVENT, DESC_EVENTTWO)));
    }

}
