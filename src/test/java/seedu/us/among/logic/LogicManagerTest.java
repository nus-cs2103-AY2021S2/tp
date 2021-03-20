package seedu.us.among.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_ENDPOINT_DISPLAYED_INDEX;
import static seedu.us.among.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.us.among.logic.commands.CommandTestUtil.ADDRESS_DESC_RANDOM;
import static seedu.us.among.logic.commands.CommandTestUtil.DATA_DESC_DEFAULT;
import static seedu.us.among.logic.commands.CommandTestUtil.HEADER_DESC_DEFAULT;
import static seedu.us.among.logic.commands.CommandTestUtil.METHOD_DESC_GET;
import static seedu.us.among.testutil.Assert.assertThrows;
import static seedu.us.among.testutil.TypicalEndpoints.GET;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.us.among.logic.commands.AddCommand;
import seedu.us.among.logic.commands.CommandResult;
import seedu.us.among.logic.commands.ListCommand;
import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.logic.endpoint.exceptions.RequestException;
import seedu.us.among.logic.parser.exceptions.ParseException;
import seedu.us.among.model.Model;
import seedu.us.among.model.ModelManager;
import seedu.us.among.model.ReadOnlyEndpointList;
import seedu.us.among.model.UserPrefs;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.storage.JsonEndpointListStorage;
import seedu.us.among.storage.JsonUserPrefsStorage;
import seedu.us.among.storage.StorageManager;
import seedu.us.among.testutil.EndpointBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonEndpointListStorage endpointListStorage = new JsonEndpointListStorage(
                temporaryFolder.resolve("imposter.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(endpointListStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "remove 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_ENDPOINT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand,
                ListCommand.MESSAGE_SUCCESS_WITH_EMPTY_LIST, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonEndpointListIoExceptionThrowingStub
        JsonEndpointListStorage endpointListStorage = new JsonEndpointListIoExceptionThrowingStub(
                temporaryFolder.resolve("ioExceptionEndpointList.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(
                temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(endpointListStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + METHOD_DESC_GET
                + ADDRESS_DESC_RANDOM + DATA_DESC_DEFAULT + HEADER_DESC_DEFAULT;
        Endpoint expectedEndpoint = new EndpointBuilder(GET).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addEndpoint(expectedEndpoint);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }


    @Test
    public void getFilteredEndpointList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredEndpointList().remove(0));
    }

    /**
     * Executes the command and confirms that - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in
     * {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage, Model expectedModel)
            throws CommandException, ParseException, RequestException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the
     * result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the
     * result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the
     * result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getEndpointList(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that - the {@code expectedException} is
     * thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in
     * {@code expectedModel} <br>
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
    private static class JsonEndpointListIoExceptionThrowingStub extends JsonEndpointListStorage {
        private JsonEndpointListIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveEndpointList(ReadOnlyEndpointList endpointList, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
