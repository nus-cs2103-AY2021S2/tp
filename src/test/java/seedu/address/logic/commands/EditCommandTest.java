package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2107;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_FIRST_EVENT;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_SECOND_EVENT;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.identifier.Identifier;
import seedu.address.logic.commands.EditCommand.EditEventDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.TypicalEvents;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(new UserPrefs(), getTypicalEventBook());

    @Test
    public void execute_allFieldsSpecified_success() {
        Event editedEvent = new EventBuilder().withIdentifier(IDENTIFIER_FIRST_EVENT.getValue()).buildWithID();
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        Identifier editedEventIdentifier = Identifier.fromIdentifier(editedEvent.getIdentifier());
        EditCommand editCommand = new EditCommand(editedEventIdentifier, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new UserPrefs(), model.getEventBook());

//        Optional<Event> optFirstEvent = model.getEventByIdentifier(IDENTIFIER_FIRST_EVENT.getValue());
//        assertTrue(optFirstEvent.isPresent());
//        Event firstEvent = optFirstEvent.get();
        expectedModel.setEvent(TypicalEvents.CS2030, editedEvent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Identifier lastEventIdentifier = Identifier.fromIdentifier(model.getFilteredEventList().size());
        Optional<Event> optLastEvent = model.getEventByIdentifier(lastEventIdentifier.getValue());
        assertTrue(optLastEvent.isPresent());
        Event lastEvent = optLastEvent.get();

        EventBuilder eventInBook = new EventBuilder(lastEvent);
        Event editedEvent = eventInBook.withName(VALID_NAME_CS2100)
                .withDescription(VALID_DESCRIPTION_CS2100).buildWithID();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_CS2100)
                .withDescription(VALID_DESCRIPTION_CS2100).build();
        EditCommand editCommand = new EditCommand(lastEventIdentifier, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new UserPrefs(), model.getEventBook());
        expectedModel.setEvent(lastEvent, editedEvent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEvent_failure() {
        Optional<Event> optFirstEvent = model.getEventByIdentifier(IDENTIFIER_FIRST_EVENT.getValue());
        assertTrue(optFirstEvent.isPresent());
        Event firstEvent = optFirstEvent.get();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(firstEvent).build();
        EditCommand editCommand = new EditCommand(IDENTIFIER_SECOND_EVENT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_invalidEventIndexUnfilteredList_failure() {
        Identifier outOfBoundIdentifier = Identifier.fromIdentifier(Event.getLatestIdentifier().getValue() + 1);
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_CS2030).build();
        EditCommand editCommand = new EditCommand(outOfBoundIdentifier, descriptor);

        String expectedMessage = String.format(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_IDENTIFIER,
                outOfBoundIdentifier.getValue());

        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(IDENTIFIER_FIRST_EVENT, DESC_CS2030);

        // same values -> returns true
        EditEventDescriptor copyDescriptor = new EditEventDescriptor(DESC_CS2030);
        EditCommand commandWithSameValues = new EditCommand(IDENTIFIER_FIRST_EVENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(IDENTIFIER_SECOND_EVENT, DESC_CS2030)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(IDENTIFIER_FIRST_EVENT, DESC_CS2107)));
    }
}
