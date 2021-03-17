package seedu.module.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.module.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.module.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.module.logic.commands.CommandTestUtil.DEADLINE_DESC_LAB;
import static seedu.module.logic.commands.CommandTestUtil.DESCRIPTION_DESC_LAB;
import static seedu.module.logic.commands.CommandTestUtil.MODULE_DESC_LAB;
import static seedu.module.logic.commands.CommandTestUtil.TASK_NAME_DESC_LAB;
import static seedu.module.logic.commands.CommandTestUtil.WORKLOAD_DESC_1;
import static seedu.module.testutil.Assert.assertThrows;
import static seedu.module.testutil.TypicalTasks.LAB;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.module.logic.commands.AddCommand;
import seedu.module.logic.commands.CommandResult;
import seedu.module.logic.commands.ListCommand;
import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.logic.parser.exceptions.ParseException;
import seedu.module.model.Model;
import seedu.module.model.ModelManager;
import seedu.module.model.ReadOnlyModuleBook;
import seedu.module.model.UserPrefs;
import seedu.module.model.task.Task;
import seedu.module.storage.JsonModuleBookStorage;
import seedu.module.storage.JsonUserPrefsStorage;
import seedu.module.storage.StorageManager;
import seedu.module.testutil.TaskBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonModuleBookStorage moduleBookStorage =
                new JsonModuleBookStorage(temporaryFolder.resolve("moduleBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(moduleBookStorage, userPrefsStorage);
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
        assertCommandException(deleteCommand, MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonModuleBookIoExceptionThrowingStub
        JsonModuleBookStorage moduleBookStorage =
                new JsonModuleBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionModuleBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(moduleBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + TASK_NAME_DESC_LAB + DEADLINE_DESC_LAB + MODULE_DESC_LAB
                + DESCRIPTION_DESC_LAB + WORKLOAD_DESC_1;
        Task expectedTask = new TaskBuilder(LAB).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addTask(expectedTask);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredTaskList().remove(0));
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
        Model expectedModel = new ModelManager(model.getModuleBook(), new UserPrefs());
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
    private static class JsonModuleBookIoExceptionThrowingStub extends JsonModuleBookStorage {
        private JsonModuleBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveModuleBook(ReadOnlyModuleBook moduleBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
