package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for BlacklistCommand.
 */
public class BlacklistCommandTest {

    private Model typicalModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model smallModel = makeSmallModel();

    private Model makeSmallModel() {
        AddressBook ab = new AddressBook();
        ab.addPerson(ALICE);
        ab.addPerson(BENSON);
        ab.addPerson(CARL);
        return new ModelManager(ab, new UserPrefs());
    }

    @Test
    public void execute_unblacklistedToBlacklistedSuccess() {
        Index index = Index.fromOneBased(1);
        BlacklistCommand blacklistCommand = new BlacklistCommand(index);

        Model expectedModel = new ModelManager(new AddressBook(smallModel.getAddressBook()), new UserPrefs());
        List<Person> lastShownList = smallModel.getFilteredPersonList();
        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = personToEdit.toggleBlacklistStatus();
        expectedModel.setPerson(personToEdit, editedPerson);

        String expectedMessage = String.format(BlacklistCommand.MESSAGE_BLACKLIST_SUCCESS, editedPerson);

        assertCommandSuccess(blacklistCommand, smallModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_blacklistedToUnblacklistedSuccess() {
        Index index = Index.fromOneBased(3);
        BlacklistCommand blacklistCommand = new BlacklistCommand(index);

        Model expectedModel = new ModelManager(new AddressBook(smallModel.getAddressBook()), new UserPrefs());
        List<Person> lastShownList = smallModel.getFilteredPersonList();
        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = personToEdit.toggleBlacklistStatus();
        expectedModel.setPerson(personToEdit, editedPerson);

        String expectedMessage = String.format(BlacklistCommand.MESSAGE_UNBLACKLIST_SUCCESS, editedPerson);

        assertCommandSuccess(blacklistCommand, smallModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(typicalModel.getFilteredPersonList().size() + 1);
        BlacklistCommand blacklistCommand = new BlacklistCommand(outOfBoundIndex);

        assertCommandFailure(blacklistCommand, typicalModel,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(typicalModel, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of contacts list
        assertTrue(outOfBoundIndex.getZeroBased() < typicalModel.getAddressBook().getPersonList().size());

        BlacklistCommand blacklistCommand = new BlacklistCommand(outOfBoundIndex);

        assertCommandFailure(blacklistCommand, typicalModel, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final BlacklistCommand standardBlacklistCommand = new BlacklistCommand(INDEX_FIRST_PERSON);

        // same object -> returns true
        assertTrue(standardBlacklistCommand.equals(standardBlacklistCommand));

        // same values -> returns true
        BlacklistCommand commandWithSameValues = new BlacklistCommand(INDEX_FIRST_PERSON);
        assertTrue(standardBlacklistCommand.equals(commandWithSameValues));

        // different object -> returns false
        assertFalse(standardBlacklistCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardBlacklistCommand.equals(new BlacklistCommand(INDEX_SECOND_PERSON)));
    }
}
