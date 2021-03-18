package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

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
    private static final String WRONG_PASSWORD = "6767";
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "LockTest");

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            new Authentication(TEST_DATA_FOLDER));

    @Test
    public void execute_newLock_success() throws CommandException {
        LockCommand command = new LockCommand(DEFAULT_PASSWORD);
        command.execute(model);
        assertEquals(DEFAULT_PASSWORD, model.getAuthentication().getPassword());
    }

    @Test
    public void execute_replacePassword_success() throws CommandException, NoSuchFieldException,
            IllegalAccessException {
        Field password = Authentication.class.getDeclaredField("password");
        password.setAccessible(true);
        Authentication authentication = model.getAuthentication();
        password.set(authentication, Optional.of(DEFAULT_PASSWORD));

        LockCommand replaceWithNewPassword = new LockCommand(DEFAULT_PASSWORD, NEW_PASSWORD);
        replaceWithNewPassword.execute(model);
        assertEquals(NEW_PASSWORD, model.getAuthentication().getPassword());
    }

    @Test
    public void execute_useSamePassword_success() throws CommandException {
        LockCommand setOldPassword = new LockCommand(DEFAULT_PASSWORD);
        setOldPassword.execute(model);
        model.getAuthentication().removePassword();
        LockCommand restorePassword = new LockCommand();
        restorePassword.execute(model);
        assertEquals(DEFAULT_PASSWORD, model.getAuthentication().getPassword());
    }

    @Test
    public void execute_equals_success() {
        LockCommand firstCommand = new LockCommand(DEFAULT_PASSWORD, NEW_PASSWORD);
        LockCommand secondCommand = new LockCommand(DEFAULT_PASSWORD, NEW_PASSWORD);
        assertEquals(secondCommand, firstCommand);
    }

    @Test
    public void execute_wrongPassword_throwsCommandException() throws IllegalAccessException, NoSuchFieldException {
        LockCommand command = new LockCommand(WRONG_PASSWORD, NEW_PASSWORD);
        Field password = Authentication.class.getDeclaredField("password");
        password.setAccessible(true);
        Authentication authentication = model.getAuthentication();
        password.set(authentication, Optional.of(DEFAULT_PASSWORD));
        assertCommandFailure(command, model, LockCommand.MESSAGE_ALREADY_LOCKED_INCORRECT_PASSWORD);
    }
}