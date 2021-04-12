package seedu.partyplanet.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.partyplanet.logic.commands.CommandTestUtil.DESC_CNY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.DESC_EASTER;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_NAME_EASTER;
import static seedu.partyplanet.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.partyplanet.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.partyplanet.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.partyplanet.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.partyplanet.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.commons.core.Messages;
import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.EEditCommand.EditEventDescriptor;
import seedu.partyplanet.model.AddressBook;
import seedu.partyplanet.model.EventBook;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.ModelManager;
import seedu.partyplanet.model.UserPrefs;
import seedu.partyplanet.model.event.Event;
import seedu.partyplanet.testutil.EditEventDescriptorBuilder;
import seedu.partyplanet.testutil.EventBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EEditCommand.
 */
public class EEditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Event editedEvent = new EventBuilder().build();
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        EEditCommand eeditCommand = new EEditCommand(INDEX_FIRST_EVENT, descriptor);

        String expectedMessage = String.format(EEditCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new EventBook(model.getEventBook()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);

        assertCommandSuccess(eeditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastEvent = Index.fromOneBased(model.getFilteredEventList().size());
        Event lastEvent = model.getFilteredEventList().get(indexLastEvent.getZeroBased());

        EventBuilder eventInList = new EventBuilder(lastEvent);
        Event editedEvent = eventInList.withName(VALID_NAME_EASTER).build();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_EASTER).build();
        EEditCommand eeditCommand = new EEditCommand(indexLastEvent, descriptor);

        String expectedMessage = String.format(EEditCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new EventBook(model.getEventBook()), new UserPrefs());
        expectedModel.setEvent(lastEvent, editedEvent);

        assertCommandSuccess(eeditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EEditCommand eeditCommand = new EEditCommand(INDEX_FIRST_EVENT, new EditEventDescriptor());
        Event editedEvent = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());

        String expectedMessage = String.format(EEditCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new EventBook(model.getEventBook()), new UserPrefs());

        assertCommandSuccess(eeditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        Event eventInFilteredList = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        Event editedEvent = new EventBuilder(eventInFilteredList).withName(VALID_NAME_EASTER).build();
        EEditCommand eeditCommand = new EEditCommand(INDEX_FIRST_EVENT,
                new EditEventDescriptorBuilder().withName(VALID_NAME_EASTER).build());

        String expectedMessage = String.format(EEditCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new EventBook(model.getEventBook()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);
        showNoEvent(expectedModel);

        assertCommandSuccess(eeditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEventUnfilteredList_failure() {
        Event firstEvent = model.getFilteredEventList().get(INDEX_FIRST_EVENT.getZeroBased());
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(firstEvent).build();
        EEditCommand eeditCommand = new EEditCommand(INDEX_SECOND_EVENT, descriptor);

        assertCommandFailure(eeditCommand, model, EEditCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_duplicateEventFilteredList_failure() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);

        // edit event in filtered list into a duplicate in address book
        Event eventInList = model.getEventBook().getEventList().get(INDEX_SECOND_EVENT.getZeroBased());
        EEditCommand eeditCommand = new EEditCommand(INDEX_FIRST_EVENT,
                new EditEventDescriptorBuilder(eventInList).build());

        assertCommandFailure(eeditCommand, model, EEditCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_invalidEventIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_EASTER).build();
        EEditCommand eeditCommand = new EEditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(eeditCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidEventIndexFilteredList_failure() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);
        Index outOfBoundIndex = INDEX_SECOND_EVENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getEventBook().getEventList().size());

        EEditCommand eeditCommand = new EEditCommand(outOfBoundIndex,
                new EditEventDescriptorBuilder().withName(VALID_NAME_EASTER).build());

        assertCommandFailure(eeditCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EEditCommand standardCommand = new EEditCommand(INDEX_FIRST_EVENT, DESC_CNY);

        // same values -> returns true
        EditEventDescriptor copyDescriptor = new EditEventDescriptor(DESC_CNY);
        EEditCommand commandWithSameValues = new EEditCommand(INDEX_FIRST_EVENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EEditCommand(INDEX_SECOND_EVENT, DESC_CNY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EEditCommand(INDEX_FIRST_EVENT, DESC_EASTER)));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoEvent(Model model) {
        model.updateFilteredEventList(p -> false);

        assertTrue(model.getFilteredEventList().isEmpty());
    }

}
