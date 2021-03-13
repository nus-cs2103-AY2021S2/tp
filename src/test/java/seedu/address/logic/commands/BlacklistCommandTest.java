package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for BlacklistCommand.
 */
public class BlacklistCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        BlacklistCommand blacklistCommand = new BlacklistCommand(outOfBoundIndex);

        assertCommandFailure(blacklistCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of contacts list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        BlacklistCommand blacklistCommand = new BlacklistCommand(outOfBoundIndex);

        assertCommandFailure(blacklistCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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
