package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtName;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validNameUnfilteredList_success() throws ParseException {
        Person personToDelete = null;
        Name parsedNameAlice = ParserUtil.parseName("Alice Pauline");
        DeletePersonCommand deleteCommand = new DeletePersonCommand(parsedNameAlice);

        for (Person person : model.getFilteredPersonList()) {
            Name currentName = person.getName();

            if (currentName.equals(parsedNameAlice)) {
                personToDelete = person;
                break;
            }
        }

        String expectedMessage = String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameUnfilteredList_throwsCommandException() {
        Name invalidName = new Name("John");
        DeletePersonCommand deleteCommand = new DeletePersonCommand(invalidName);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validNameFilteredList_success() throws ParseException {
        Name parsedNameAlice = ParserUtil.parseName("Alice Pauline");
        showPersonAtName(model, parsedNameAlice);
        Person personToDelete = null;

        for (Person person : model.getFilteredPersonList()) {
            Name currentName = person.getName();

            if (currentName.equals(parsedNameAlice)) {
                personToDelete = person;
                break;
            }
        }

        DeletePersonCommand deleteCommand = new DeletePersonCommand(parsedNameAlice);
        String expectedMessage = String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameFilteredList_throwsCommandException() throws ParseException {
        Name parsedNameAlice = ParserUtil.parseName("Alice Pauline");
        showPersonAtName(model, parsedNameAlice);

        Name invalidName = ParserUtil.parseName("Benson Meier");
        DeletePersonCommand deleteCommand = new DeletePersonCommand(invalidName);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() throws ParseException {
        Name parsedNameAlice = ParserUtil.parseName("Alice Pauline");
        Name parsedNameBenson = ParserUtil.parseName("Benson Meier");

        DeletePersonCommand deleteFirstCommand = new DeletePersonCommand(parsedNameAlice);
        DeletePersonCommand deleteSecondCommand = new DeletePersonCommand(parsedNameBenson);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePersonCommand deleteFirstCommandCopy = new DeletePersonCommand(parsedNameAlice);
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
