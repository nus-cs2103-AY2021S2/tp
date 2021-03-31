package seedu.smartlib.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartlib.commons.core.Messages.MESSAGE_INVALID_READER_DISPLAYED_INDEX;
import static seedu.smartlib.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.smartlib.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.smartlib.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.smartlib.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.smartlib.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.smartlib.testutil.Assert.assertThrows;
import static seedu.smartlib.testutil.TypicalModels.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.smartlib.logic.commands.AddReaderCommand;
import seedu.smartlib.logic.commands.CommandResult;
import seedu.smartlib.logic.commands.ListReaderCommand;
import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.logic.parser.exceptions.ParseException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.ModelManager;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.UserPrefs;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.storage.JsonSmartLibStorage;
import seedu.smartlib.storage.JsonUserPrefsStorage;
import seedu.smartlib.storage.StorageManager;
import seedu.smartlib.testutil.ReaderBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonSmartLibStorage smartLibStorage =
                new JsonSmartLibStorage(temporaryFolder.resolve("smartLib.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(smartLibStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "deletereader 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_READER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListReaderCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListReaderCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonSmartLibIoExceptionThrowingStub
        JsonSmartLibStorage smartLibStorage =
                new JsonSmartLibIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionSmartLib.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(smartLibStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddReaderCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY;
        Reader expectedReader = new ReaderBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addReader(expectedReader);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredReaderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredReaderList().remove(0));
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
        Model expectedModel = new ModelManager(model.getSmartLib(), new UserPrefs());
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
    private static class JsonSmartLibIoExceptionThrowingStub extends JsonSmartLibStorage {
        private JsonSmartLibIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveSmartLib(ReadOnlySmartLib smartLib, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
