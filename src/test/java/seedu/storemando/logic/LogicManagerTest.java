package seedu.storemando.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX;
import static seedu.storemando.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.storemando.logic.commands.CommandTestUtil.EXPIRYDATE_DESC_CHEESE;
import static seedu.storemando.logic.commands.CommandTestUtil.LOCATION_DESC_CHEESE;
import static seedu.storemando.logic.commands.CommandTestUtil.NAME_DESC_CHEESE;
import static seedu.storemando.logic.commands.CommandTestUtil.QUANTITY_DESC_CHEESE;
import static seedu.storemando.testutil.Assert.assertThrows;
import static seedu.storemando.testutil.TypicalItems.CHEESE;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.storemando.commons.core.GuiSettings;
import seedu.storemando.logic.commands.AddCommand;
import seedu.storemando.logic.commands.CommandResult;
import seedu.storemando.logic.commands.ListCommand;
import seedu.storemando.logic.commands.exceptions.CommandException;
import seedu.storemando.logic.parser.exceptions.ParseException;
import seedu.storemando.model.Model;
import seedu.storemando.model.ModelManager;
import seedu.storemando.model.ReadOnlyStoreMando;
import seedu.storemando.model.UserPrefs;
import seedu.storemando.model.item.Item;
import seedu.storemando.storage.JsonStoreMandoStorage;
import seedu.storemando.storage.JsonUserPrefsStorage;
import seedu.storemando.storage.StorageManager;
import seedu.storemando.testutil.ItemBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private final Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonStoreMandoStorage storeMandoStorage =
            new JsonStoreMandoStorage(temporaryFolder.resolve("storeMando.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(storeMandoStorage, userPrefsStorage);
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
        assertCommandException(deleteCommand, MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonStoreMandoIoExceptionThrowingStub
        JsonStoreMandoStorage storeMandoStorage =
            new JsonStoreMandoIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionStoreMando.json"));
        JsonUserPrefsStorage userPrefsStorage =
            new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(storeMandoStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_CHEESE + QUANTITY_DESC_CHEESE + EXPIRYDATE_DESC_CHEESE
            + LOCATION_DESC_CHEESE;
        Item expectedItem = new ItemBuilder(CHEESE).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addItem(expectedItem);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getLocationList_modifyList_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> logic.getLocationList().remove(0));
    }

    @Test
    public void getItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getItemList().remove(0));
    }

    @Test
    public void getFilteredItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredItemList().remove(0));
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> logic.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        logic.setGuiSettings(guiSettings);
        assertEquals(guiSettings, logic.getGuiSettings());
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
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
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage) {
        Model expectedModel = new ModelManager(model.getStoreMando(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
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
    private static class JsonStoreMandoIoExceptionThrowingStub extends JsonStoreMandoStorage {
        private JsonStoreMandoIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveStoreMando(ReadOnlyStoreMando storeMando, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
