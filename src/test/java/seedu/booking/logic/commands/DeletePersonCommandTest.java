package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.logic.commands.CommandShowType.COMMAND_SHOW_PERSONS;
import static seedu.booking.logic.commands.CommandTestUtil.NON_EXISTENT_EMAIL;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.booking.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.booking.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.booking.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import org.junit.jupiter.api.Test;

import seedu.booking.commons.core.Messages;
import seedu.booking.model.Model;
import seedu.booking.model.ModelManager;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeletePersonCommand}.
 */
public class DeletePersonCommandTest {

    private Model model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    public void execute_validEmailUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(personToDelete.getEmail());

        String expectedMessage = String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getBookingSystem(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deletePersonCommand, model, expectedMessage, COMMAND_SHOW_PERSONS, expectedModel);
    }

    @Test
    public void execute_invalidEmailUnfilteredList_throwsCommandException() {
        Email emailNotInSystem = new Email(NON_EXISTENT_EMAIL);
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(emailNotInSystem);

        assertCommandFailure(deletePersonCommand, model, Messages.MESSAGE_NON_EXISTENT_PERSON_EMAIL);
    }

    @Test
    public void execute_validEmailFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(personToDelete.getEmail());

        String expectedMessage = String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getBookingSystem(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deletePersonCommand, model, expectedMessage, COMMAND_SHOW_PERSONS, expectedModel);
    }

    @Test
    public void execute_invalidEmailFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST);

        Email emailNotInSystem = new Email(NON_EXISTENT_EMAIL);

        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(emailNotInSystem);

        assertCommandFailure(deletePersonCommand, model, Messages.MESSAGE_NON_EXISTENT_PERSON_EMAIL);
    }

    @Test
    public void equals() {
        DeletePersonCommand deleteFirstCommand = new DeletePersonCommand(new Email(VALID_EMAIL_AMY));
        DeletePersonCommand deleteSecondCommand = new DeletePersonCommand(new Email(VALID_EMAIL_BOB));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePersonCommand deleteFirstCommandCopy = new DeletePersonCommand(new Email(VALID_EMAIL_AMY));
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
