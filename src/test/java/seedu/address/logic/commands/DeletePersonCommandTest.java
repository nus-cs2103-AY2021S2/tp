package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersonIds.FIRST_PERSON_ID;
import static seedu.address.testutil.TypicalPersonIds.SECOND_PERSON_ID;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonId;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeletePersonCommand}.
 */
public class DeletePersonCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validPersonIdFilteredList_success() {
        Optional<Person> personToDelete = model.getFilteredPersonList().stream()
                .filter(x-> x.getPersonId().equals(FIRST_PERSON_ID)).findAny();

        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(FIRST_PERSON_ID);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        String expectedMessage = String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete.get());

        Optional<Person> expectedPersonToDelete = expectedModel.getFilteredPersonList().stream()
                .filter(x-> x.getPersonId().equals(FIRST_PERSON_ID)).findAny();

        expectedModel.deletePerson(expectedPersonToDelete.get());

        assertCommandSuccess(deletePersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIdUnfilteredList_throwsCommandException() {
        int outOfBoundPersonIdNumber = model.getUnfilteredPersonList().size() + 1;
        PersonId outOfBoundPersonId = new PersonId("s/" + outOfBoundPersonIdNumber);
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(outOfBoundPersonId);

        assertCommandFailure(deletePersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }



    @Test
    public void equals() {
        DeletePersonCommand deleteFirstPersonCommand = new DeletePersonCommand(FIRST_PERSON_ID);
        DeletePersonCommand deleteSecondPersonCommand = new DeletePersonCommand(SECOND_PERSON_ID);

        // same object -> returns true
        assertTrue(deleteFirstPersonCommand.equals(deleteFirstPersonCommand));

        // same values -> returns true
        DeletePersonCommand deleteFirstPersonCommandCopy = new DeletePersonCommand(FIRST_PERSON_ID);
        assertTrue(deleteFirstPersonCommand.equals(deleteFirstPersonCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstPersonCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstPersonCommand.equals(null));

        // different session -> returns false
        assertFalse(deleteFirstPersonCommand.equals(deleteSecondPersonCommand));
    }

}
