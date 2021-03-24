package seedu.iscam.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX;
import static seedu.iscam.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.iscam.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.iscam.logic.commands.CommandTestUtil.LOCATION_DESC_AMY;
import static seedu.iscam.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.iscam.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.iscam.logic.commands.CommandTestUtil.PLAN_DESC_AMY;
import static seedu.iscam.testutil.Assert.assertThrows;
import static seedu.iscam.testutil.TypicalClients.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.iscam.logic.commands.AddCommand;
import seedu.iscam.logic.commands.CommandResult;
import seedu.iscam.logic.commands.ListCommand;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.logic.parser.exceptions.ParseException;
import seedu.iscam.model.Model;
import seedu.iscam.model.ModelManager;
import seedu.iscam.model.ReadOnlyClientBook;
import seedu.iscam.model.UserPrefs;
import seedu.iscam.model.client.Client;
import seedu.iscam.storage.JsonClientBookStorage;
import seedu.iscam.storage.JsonUserPrefsStorage;
import seedu.iscam.storage.StorageManager;
import seedu.iscam.testutil.ClientBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonClientBookStorage addressBookStorage =
                new JsonClientBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonClientBookStorage addressBookStorage =
                new JsonClientBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + LOCATION_DESC_AMY + PLAN_DESC_AMY;
        Client expectedClient = new ClientBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addClient(expectedClient);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredClientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredClientList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getClientBook(), model.getMeetingBook(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonClientBookIoExceptionThrowingStub extends JsonClientBookStorage {
        private JsonClientBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveClientBook(ReadOnlyClientBook addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
