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
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditEventDescriptor;
import seedu.address.model.EventBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.EventBuilder;



/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalEventBook());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Event editedEvent = new EventBuilder().build();
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(editedEvent).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                new EventBook(model.getEventBook()));
        expectedModel.setEvent(model.getFilteredEventList()
                .stream().filter(event -> event.getIdentifier() == INDEX_FIRST_PERSON.getOneBased())
                .collect(Collectors.toList()).get(0), editedEvent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastEvent = Index.fromOneBased(model.getEventBook().getEventList().size());
        Event lastEvent = model.getEventBook().getEventList().stream()
                .filter(event -> event.getIdentifier() == indexLastEvent.getOneBased()).collect(Collectors.toList())
                .get(0);

        EventBuilder eventInList = new EventBuilder(lastEvent);
        Event editedEvent = eventInList.withName(VALID_NAME_CS2100).withDescription(VALID_DESCRIPTION_CS2100)
                .build();

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_CS2100)
                .withDescription(VALID_DESCRIPTION_CS2100).build();
        EditCommand editCommand = new EditCommand(indexLastEvent, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                new EventBook(model.getEventBook()));
        expectedModel.setEvent(lastEvent, editedEvent);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditEventDescriptor());
        Event editedEvent = model.getEventBook().getEventList().stream()
                .filter(event -> event.getIdentifier() == INDEX_FIRST_PERSON.getOneBased())
                .collect(Collectors.toList()).get(0);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                new EventBook(model.getEventBook()));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEventUnfilteredList_failure() {
        Event firstEvent = model.getEventBook().getEventList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder(firstEvent).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void execute_invalidEventIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_CS2030).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_CS2030);

        // same values -> returns true
        EditEventDescriptor copyDescriptor = new EditEventDescriptor(DESC_CS2030);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_CS2030)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_CS2107)));
    }

}
