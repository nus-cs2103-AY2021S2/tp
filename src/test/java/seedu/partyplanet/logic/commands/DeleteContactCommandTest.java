package seedu.partyplanet.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.partyplanet.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.partyplanet.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.partyplanet.logic.commands.CommandTestUtil.showPersonAtMultipleIndex;
import static seedu.partyplanet.testutil.Assert.assertThrows;
import static seedu.partyplanet.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.partyplanet.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.partyplanet.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.commons.core.Messages;
import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.ModelManager;
import seedu.partyplanet.model.UserPrefs;
import seedu.partyplanet.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteContactCommand}.
 */
public class DeleteContactCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());

    @Test
    public void execute_validSingleIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteContactCommand(List.of(INDEX_FIRST_PERSON), List.of());

        String expectedMessage = String.format(DeleteContactCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                personToDelete.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validSingleIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteContactCommand(List.of(INDEX_FIRST_PERSON), List.of());

        String expectedMessage = String.format(DeleteContactCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                personToDelete.getName());

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMultipleIndexUnfilteredList_success() {

        List<Person> personsToDelete = new ArrayList<>();
        personsToDelete.add(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));
        personsToDelete.add(model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()));

        DeleteCommand deleteCommand = new DeleteContactCommand(
                List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON), List.of());

        String expectedMessage = String.format(DeleteContactCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                displayPersons(personsToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        for (Person p : personsToDelete) {
            expectedModel.deletePerson(p);
        }

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validMultipleIndexFilteredList_success() {
        showPersonAtMultipleIndex(model, INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);

        List<Person> personsToDelete = new ArrayList<>();
        personsToDelete.add(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));
        personsToDelete.add(model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()));

        DeleteCommand deleteCommand = new DeleteContactCommand(
                List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON), List.of());

        String expectedMessage = String.format(DeleteContactCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                displayPersons(personsToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        for (Person p : personsToDelete) {
            expectedModel.deletePerson(p);
        }
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allInvalidIndexUnfilteredList_throwsCommandException() {

        DeleteCommand deleteCommand = new DeleteContactCommand(List.of(Index.fromZeroBased(100)), new ArrayList<>());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());

        assertThrows(CommandException.class,
                Messages.MESSAGE_NONE_INDEX_VALID, () -> deleteCommand.execute(expectedModel));
    }

    @Test
    public void execute_bothValidInvalidIndexUnfilteredList_success() {

        List<Person> personsToDelete = new ArrayList<>();
        personsToDelete.add(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));
        personsToDelete.add(model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()));

        DeleteCommand deleteCommand = new DeleteContactCommand(
                List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON), List.of("invalid", "-1"));

        String expectedMessage = String.format(DeleteContactCommand.MESSAGE_DELETE_PERSON_SUCCESS
                + "\n" + DeleteContactCommand.MESSAGE_INVALID_PERSON_INDEX,
                displayPersons(personsToDelete), "invalid, -1");

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());
        for (Person p : personsToDelete) {
            expectedModel.deletePerson(p);
        }

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteContactCommand(List.of(INDEX_FIRST_PERSON), List.of());
        DeleteCommand deleteSecondCommand = new DeleteContactCommand(List.of(INDEX_SECOND_PERSON), List.of());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteContactCommand(List.of(INDEX_FIRST_PERSON), List.of());
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

    /**
     * Returns list of persons in the form "a, b, c,..."
     */
    private String displayPersons(List<Person> deletedPersons) {
        return deletedPersons.stream()
                .map(p -> p.getName().toString())
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }
}
