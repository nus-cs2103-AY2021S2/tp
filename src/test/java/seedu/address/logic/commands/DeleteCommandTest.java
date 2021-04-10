package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalCommandAliases.getTypicalAliasMap;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.VALID_INDEXES;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalAliasMap());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = DeleteCommand.buildDeleteIndexCommand(
                Collections.singletonList(INDEX_FIRST_PERSON));

        String expectedMessage = String
                .format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getAliasMap());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexesUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList()
                .get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPersonToDelete = model.getFilteredPersonList()
                .get(INDEX_SECOND_PERSON.getZeroBased());
        DeleteCommand deleteCommand = DeleteCommand.buildDeleteIndexCommand(
                List.of(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON));

        String expectedMessage = String
                .format(DeleteCommand.MESSAGE_DELETE_PERSONS_SUCCESS,
                        personToDelete + "\n" + secondPersonToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getAliasMap());
        expectedModel.deletePerson(personToDelete);
        expectedModel.deletePerson(secondPersonToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = DeleteCommand
                .buildDeleteIndexCommand(Collections.singletonList(outOfBoundIndex));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexesUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Index secondOutOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 2);
        DeleteCommand deleteCommand = DeleteCommand.buildDeleteIndexCommand(
                List.of(outOfBoundIndex, secondOutOfBoundIndex));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = DeleteCommand
                .buildDeleteIndexCommand(Collections.singletonList(INDEX_FIRST_PERSON));

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getAliasMap());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteCommand deleteCommand = DeleteCommand
                .buildDeleteIndexCommand(Collections.singletonList(outOfBoundIndex));

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_deleteSelected_failureNoSelected() {
        assertCommandFailure(DeleteCommand.buildDeleteSelectedCommand(), model,
                DeleteCommand.MESSAGE_NO_SELECTED);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = DeleteCommand.buildDeleteIndexCommand(
                Collections.singletonList(INDEX_FIRST_PERSON));
        DeleteCommand deleteSecondCommand = DeleteCommand.buildDeleteIndexCommand(
                Collections.singletonList(INDEX_SECOND_PERSON));

        // same object -> equals
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same indexes -> equals
        DeleteCommand deleteFirstCommandCopy = DeleteCommand.buildDeleteIndexCommand(
                Collections.singletonList(INDEX_FIRST_PERSON));
        assertEquals(deleteFirstCommandCopy, deleteFirstCommand);
        assertEquals(DeleteCommand.buildDeleteShownCommand(),
                DeleteCommand.buildDeleteShownCommand());

        // same type: shown -> equals
        assertEquals(DeleteCommand.buildDeleteShownCommand(),
                DeleteCommand.buildDeleteShownCommand());

        // same type: selected -> equals
        assertEquals(DeleteCommand.buildDeleteSelectedCommand(),
                DeleteCommand.buildDeleteSelectedCommand());

        // different indexes -> not equals
        assertNotEquals(DeleteCommand
                        .buildDeleteIndexCommand(Collections.singletonList(INDEX_FIRST_PERSON)),
                DeleteCommand.buildDeleteIndexCommand(VALID_INDEXES));

        // different types -> not equals
        assertNotEquals(deleteFirstCommand, 1);

        // null -> not equals
        assertNotEquals(deleteFirstCommand, null);

        // different person -> not equals
        assertNotEquals(deleteSecondCommand, deleteFirstCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
