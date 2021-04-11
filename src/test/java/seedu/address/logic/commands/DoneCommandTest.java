package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_FIRST_EVENT;
import static seedu.address.testutil.TypicalIdentifiers.IDENTIFIER_SECOND_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.identifier.Identifier;
import seedu.address.model.EventBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;
/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DoneCommandTest {

    private Model model = new ModelManager(new UserPrefs(), getTypicalEventBook());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Event eventToBeDone = model.getEventBook().getEventList().get(IDENTIFIER_FIRST_EVENT.getZeroBased());
        Identifier eventIdentifier = Identifier.fromIdentifier(eventToBeDone.getIdentifier());
        DoneCommand doneCommand = new DoneCommand(eventIdentifier);

        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_EVENT_SUCCESS, eventToBeDone);

        ModelManager expectedModel = new ModelManager( new UserPrefs(), new EventBook(model.getEventBook()));

        assertCommandSuccess(doneCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Identifier outOfBoundIndex = Identifier.fromIdentifier(model.getEventBook().getEventList().size() + 1);
        DoneCommand doneCommand  = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_IDENTIFIER);
    }

    //    @Test
    //    public void execute_validIndexFilteredList_success() {
    //        showPersonAtIndex(model, IDENTIFIER_FIRST_PERSON);
    //
    //        Person personToDelete = model.getFilteredPersonList().get(IDENTIFIER_FIRST_PERSON.getZeroBased());
    //        DeleteCommand deleteCommand = new DeleteCommand(IDENTIFIER_FIRST_PERSON);
    //
    //        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);
    //
    //        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getEventBook());
    //        expectedModel.deletePerson(personToDelete);
    //        showNoPerson(expectedModel);
    //
    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    //    }

    //    @Test
    //    public void execute_invalidIndexFilteredList_throwsCommandException() {
    //        showPersonAtIndex(model, IDENTIFIER_FIRST_PERSON);
    //
    //        Index outOfBoundIndex = IDENTIFIER_SECOND_PERSON;
    //        // ensures that outOfBoundIndex is still in bounds of address book list
    //        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
    //
    //        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);
    //
    //        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    //    }

    @Test
    public void equals() {
        DoneCommand doneFirstCommand = new DoneCommand(IDENTIFIER_FIRST_EVENT);
        DoneCommand doneSecondCommand = new DoneCommand(IDENTIFIER_SECOND_EVENT);

        // same object -> returns true
        assertTrue(doneFirstCommand.equals(doneFirstCommand));

        // same values -> returns true
        DoneCommand doneFirstCommandCopy = new DoneCommand(IDENTIFIER_FIRST_EVENT);
        assertTrue(doneFirstCommand.equals(doneFirstCommandCopy));

        // different types -> returns false
        assertFalse(doneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(doneFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(doneFirstCommand.equals(doneSecondCommand));
    }

}
