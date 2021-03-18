package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.Authentication;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code LockCommand}.
 */
public class UnlockCommandTest {

    private static final String DEFAULT_PASSWORD = "1234";
    private static final String INCORRECT_PASSWORD = "5678";
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "LockTest");

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            new Authentication(TEST_DATA_FOLDER));

    @Test
    public void execute_unlock_success() throws CommandException, NoSuchPaddingException, NoSuchAlgorithmException,
            IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        model.getAuthentication().setPassword(Optional.of(DEFAULT_PASSWORD));
        UnlockCommand command = new UnlockCommand(DEFAULT_PASSWORD);
        command.execute(model);
        assertEquals("", model.getAuthentication().getPassword());
    }

    @Test
    public void execute_unlockWrongPassword_throwsCommandException() throws NoSuchPaddingException,
            NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        model.getAuthentication().setPassword(Optional.of(DEFAULT_PASSWORD));
        UnlockCommand command = new UnlockCommand(INCORRECT_PASSWORD);
        assertCommandFailure(command, model, UnlockCommand.MESSAGE_INCORRECT_PASSWORD);
    }

    @Test
    public void execute_equals_success() {
        UnlockCommand firstCommand = new UnlockCommand(DEFAULT_PASSWORD);
        UnlockCommand secondCommand = new UnlockCommand(DEFAULT_PASSWORD);
        assertEquals(firstCommand, secondCommand);
    }

    @Test
    public void execute_unlockAlreadyUnlocked_throwsCommandException() {
        model.getAuthentication().removePassword();
        UnlockCommand command = new UnlockCommand(DEFAULT_PASSWORD);
        assertCommandFailure(command, model, UnlockCommand.MESSAGE_ALREADY_UNLOCKED);
    }
}
