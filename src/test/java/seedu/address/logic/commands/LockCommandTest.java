package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
public class LockCommandTest {

    private static final String DEFAULT_PASSWORD = "1234";
    private static final String NEW_PASSWORD = "5678";
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "LockTest");

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new Authentication(TEST_DATA_FOLDER));

    @Test
    public void execute_newLock_success() throws CommandException {
        LockCommand command = new LockCommand(DEFAULT_PASSWORD);
        command.execute(model);
        assertEquals(DEFAULT_PASSWORD, model.getAuthentication().getPassword());
    }

    @Test
    public void execute_replacePassword_success() throws CommandException {
        LockCommand setOldPassword = new LockCommand(DEFAULT_PASSWORD);
        setOldPassword.execute(model);
        LockCommand replaceWithNewPassword = new LockCommand(DEFAULT_PASSWORD, NEW_PASSWORD);
        replaceWithNewPassword.execute(model);
        assertEquals(NEW_PASSWORD, model.getAuthentication().getPassword());
    }

    @Test
    public void execute_useSamePassword_success() throws CommandException, NoSuchPaddingException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        LockCommand setOldPassword = new LockCommand(DEFAULT_PASSWORD);
        setOldPassword.execute(model);
        model.getAuthentication().removePassword();
        LockCommand restorePassword = new LockCommand();
        restorePassword.execute(model);
        assertEquals(DEFAULT_PASSWORD, model.getAuthentication().getPassword());
    }








}
