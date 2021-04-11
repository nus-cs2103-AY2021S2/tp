package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_NINTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SEVENTH_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code MassDeleteCommand}.
 */
public class MassDeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexRange_success() {
        MassDeleteCommand massDeleteCommand = new MassDeleteCommand(INDEX_FIRST_PERSON,
                INDEX_SEVENTH_PERSON);

        String expectedMessage = String.format(MassDeleteCommand.MESSAGE_MASS_DELETE_PERSON_SUCCESS,
                INDEX_FIRST_PERSON.getOneBased(), INDEX_SEVENTH_PERSON.getOneBased());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.massDelete(INDEX_FIRST_PERSON.getOneBased(),
                INDEX_SEVENTH_PERSON.getOneBased());

        assertCommandSuccess(massDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStartIndex_throwsCommandException() {
        MassDeleteCommand massDeleteCommand = new MassDeleteCommand(INDEX_SEVENTH_PERSON,
                INDEX_SECOND_PERSON);

        assertCommandFailure(massDeleteCommand, model, Messages.MESSAGE_INVALID_START_INDEX);
    }

    @Test
    public void execute_invalidEndIndex_throwsCommandException() {
        MassDeleteCommand massDeleteCommand = new MassDeleteCommand(INDEX_FIRST_PERSON,
                INDEX_NINTH_PERSON);

        assertCommandFailure(massDeleteCommand, model, Messages.MESSAGE_INVALID_END_INDEX);
    }

    @Test
    public void equals() {
        MassDeleteCommand firstMassDeleteCommand = new MassDeleteCommand(INDEX_FIRST_PERSON, INDEX_NINTH_PERSON);
        MassDeleteCommand secondMassDeleteCommand = new MassDeleteCommand(INDEX_SECOND_PERSON, INDEX_SEVENTH_PERSON);

        // same object -> return true
        assertTrue(firstMassDeleteCommand.equals(firstMassDeleteCommand));

        // same values -> return true
        MassDeleteCommand firstMassDeleteCommandCopy = new MassDeleteCommand(INDEX_FIRST_PERSON, INDEX_NINTH_PERSON);
        assertTrue(firstMassDeleteCommand.equals(firstMassDeleteCommandCopy));

        // different types -> return false
        assertFalse(firstMassDeleteCommand.equals(1));

        // null -> return false
        assertFalse(firstMassDeleteCommand.equals(null));

        // different index range -> return false
        assertFalse(firstMassDeleteCommand.equals(secondMassDeleteCommand));
    }
}
