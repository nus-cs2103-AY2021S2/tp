package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.MASSBLACKLIST_BLACKLIST;
import static seedu.address.logic.commands.CommandTestUtil.MASSBLACKLIST_UNBLACKLIST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_NINTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SEVENTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getBlacklistedTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code MassBlacklistCommand}.
 */
public class MassBlacklistCommandTest {

    private Model unblacklistedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model blacklistedModel = new ModelManager(getBlacklistedTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexRangeBlacklist_success() {
        MassBlacklistCommand massBlacklistCommand = new MassBlacklistCommand(INDEX_FIRST_PERSON,
                INDEX_THIRD_PERSON, MASSBLACKLIST_BLACKLIST);

        String expectedMessage = String.format(MassBlacklistCommand.MESSAGE_MASS_BLACKLIST_SUCCESS,
                INDEX_FIRST_PERSON.getOneBased(), INDEX_THIRD_PERSON.getOneBased());

        ModelManager expectedModel = new ModelManager(unblacklistedModel.getAddressBook(), new UserPrefs());

        expectedModel.massBlacklist(INDEX_FIRST_PERSON.getOneBased(), INDEX_THIRD_PERSON.getOneBased());

        assertCommandSuccess(massBlacklistCommand, unblacklistedModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexRangeUnblacklist_success() {

        MassBlacklistCommand massBlacklistCommand = new MassBlacklistCommand(INDEX_FIRST_PERSON,
                INDEX_THIRD_PERSON, MASSBLACKLIST_UNBLACKLIST);

        String expectedMessage = String.format(MassBlacklistCommand.MESSAGE_MASS_UNBLACKLIST_SUCCESS,
                INDEX_FIRST_PERSON.getOneBased(), INDEX_THIRD_PERSON.getOneBased());

        ModelManager expectedModel = new ModelManager(blacklistedModel.getAddressBook(), new UserPrefs());

        expectedModel.massUnblacklist(INDEX_FIRST_PERSON.getOneBased(), INDEX_THIRD_PERSON.getOneBased());

        assertCommandSuccess(massBlacklistCommand, blacklistedModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStartIndex_throwsCommandException() {
        MassBlacklistCommand massBlacklistCommand = new MassBlacklistCommand(INDEX_SEVENTH_PERSON,
                INDEX_FIRST_PERSON, MASSBLACKLIST_BLACKLIST);

        assertCommandFailure(massBlacklistCommand, unblacklistedModel, Messages.MESSAGE_INVALID_START_INDEX);
    }

    @Test
    public void execute_invalidEndIndex_throwsCommandException() {
        MassBlacklistCommand massBlacklistCommand = new MassBlacklistCommand(INDEX_FIRST_PERSON,
                INDEX_NINTH_PERSON, MASSBLACKLIST_BLACKLIST);

        assertCommandFailure(massBlacklistCommand, unblacklistedModel, Messages.MESSAGE_INVALID_END_INDEX);
    }

    @Test
    public void equals() {
        MassBlacklistCommand firstMassBlacklistCommand = new MassBlacklistCommand(INDEX_FIRST_PERSON,
                INDEX_NINTH_PERSON, MASSBLACKLIST_BLACKLIST);
        MassBlacklistCommand secondMassBlacklistCommand = new MassBlacklistCommand(INDEX_SECOND_PERSON,
                INDEX_SEVENTH_PERSON, MASSBLACKLIST_BLACKLIST);
        MassBlacklistCommand thirdMassBlacklistCommand = new MassBlacklistCommand(INDEX_FIRST_PERSON,
                INDEX_NINTH_PERSON, MASSBLACKLIST_UNBLACKLIST);

        // same object -> return true
        assertTrue(firstMassBlacklistCommand.equals(firstMassBlacklistCommand));

        // same values -> return true
        MassBlacklistCommand firstMassBlacklistCommandCopy = new MassBlacklistCommand(INDEX_FIRST_PERSON,
                INDEX_NINTH_PERSON, MASSBLACKLIST_BLACKLIST);
        assertTrue(firstMassBlacklistCommand.equals(firstMassBlacklistCommandCopy));

        // different types -> return false
        assertFalse(firstMassBlacklistCommand.equals(1));

        // null -> return false
        assertFalse(firstMassBlacklistCommand.equals(null));

        // different index range -> return false
        assertFalse(firstMassBlacklistCommand.equals(secondMassBlacklistCommand));

        // different blacklist keyword -> return false
        assertFalse(firstMassBlacklistCommand.equals(thirdMassBlacklistCommand));
    }
}
