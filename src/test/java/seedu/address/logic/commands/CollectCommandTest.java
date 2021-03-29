package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CollectCommand.
 */
public class CollectCommandTest {

    private static final String SEPARATOR_DEFAULT = CollectCommand.DEFAULT_SEPARATOR;
    private static final String SEPARATOR_OTHER = ", ";

    private Model smallModel = makeSmallModel();
    private Model emptyModel = new ModelManager();

    private Model makeSmallModel() {
        AddressBook ab = new AddressBook();
        ab.addPerson(ALICE);
        ab.addPerson(BENSON);
        ab.addPerson(CARL);
        return new ModelManager(ab, new UserPrefs());
    }

    @Test
    public void execute_emptyModel_throwsCommandException() {
        CollectCommand collectCommand = new CollectCommand(PREFIX_EMAIL, SEPARATOR_DEFAULT);
        assertCommandFailure(collectCommand, emptyModel, CollectCommand.MESSAGE_EMPTY_LIST);
    }

    @Test
    public void execute_collectAddressSuccessful() {
        CollectCommand collectCommand = new CollectCommand(PREFIX_ADDRESS, SEPARATOR_DEFAULT);
        CommandResult expectedResult = new CommandResult(ALICE.getAddress()
                + SEPARATOR_DEFAULT + BENSON.getAddress() + SEPARATOR_DEFAULT
                + CARL.getAddress() + SEPARATOR_DEFAULT);
        assertCommandSuccess(collectCommand, smallModel, expectedResult, smallModel);
    }

    @Test
    public void execute_collectEmailSuccessful() {
        CollectCommand collectCommand = new CollectCommand(PREFIX_EMAIL, SEPARATOR_OTHER);
        CommandResult expectedResult = new CommandResult(ALICE.getEmail()
                + SEPARATOR_OTHER + BENSON.getEmail() + SEPARATOR_OTHER
                + CARL.getEmail() + SEPARATOR_OTHER);
        assertCommandSuccess(collectCommand, smallModel, expectedResult, smallModel);
    }

    @Test
    public void equals() {
        final CollectCommand standardCommand = new CollectCommand(PREFIX_EMAIL, SEPARATOR_DEFAULT);

        // same values -> returns true
        CollectCommand commandWithSameValues = new CollectCommand(PREFIX_EMAIL, SEPARATOR_DEFAULT);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different prefix -> returns false
        assertFalse(standardCommand.equals(new CollectCommand(PREFIX_ADDRESS, SEPARATOR_DEFAULT)));

        // different separator -> returns false
        assertFalse(standardCommand.equals(new CollectCommand(PREFIX_EMAIL, SEPARATOR_OTHER)));
    }
}
