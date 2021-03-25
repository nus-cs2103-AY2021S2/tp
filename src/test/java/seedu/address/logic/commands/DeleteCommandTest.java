package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.address.testutil.TypicalIndexes.IDENTIFIER_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.IDENTIFIER_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Event;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalEventBook());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Event eventToDelete = model.getEventBook().getEventList().get(IDENTIFIER_FIRST_PERSON.getZeroBased());
        Index eventIdentifier = Index.fromOneBased(eventToDelete.getIdentifier());
        DeleteCommand deleteCommand = new DeleteCommand(eventIdentifier);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getEventBook());
        expectedModel.deleteEvent(eventToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getEventBook().getEventList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_IDENTIFIER);
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
        DeleteCommand deleteFirstCommand = new DeleteCommand(IDENTIFIER_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(IDENTIFIER_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(IDENTIFIER_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
