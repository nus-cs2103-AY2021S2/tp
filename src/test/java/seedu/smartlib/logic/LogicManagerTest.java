package seedu.smartlib.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.smartlib.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.smartlib.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.smartlib.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.smartlib.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.smartlib.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.smartlib.testutil.Assert.assertThrows;
import static seedu.smartlib.testutil.TypicalModels.ALICE;
import static seedu.smartlib.testutil.TypicalModels.AMY;
import static seedu.smartlib.testutil.TypicalModels.RECORD_A;
import static seedu.smartlib.testutil.TypicalModels.SECRET;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.smartlib.commons.core.GuiSettings;
import seedu.smartlib.logic.commands.AddReaderCommand;
import seedu.smartlib.logic.commands.CommandResult;
import seedu.smartlib.logic.commands.DeleteReaderCommand;
import seedu.smartlib.logic.commands.ListReaderCommand;
import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.logic.parser.exceptions.ParseException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.ModelManager;
import seedu.smartlib.model.ReadOnlySmartLib;
import seedu.smartlib.model.SmartLib;
import seedu.smartlib.model.UserPrefs;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.Record;
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
        assertCommandException(deleteCommand, DeleteReaderCommand.INVALID_COMMAND_FORMAT
                + DeleteReaderCommand.MESSAGE_USAGE);
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
    public void getSmartLib() {
        // EP: empty SmartLib
        SmartLib smartLib = new SmartLib();
        assertEquals(smartLib, logic.getSmartLib());

        // EP: SmartLib with a reader
        model.addReader(ALICE);
        smartLib.addReader(ALICE);
        assertEquals(smartLib, logic.getSmartLib());
        model.deleteReader(ALICE);
        smartLib.removeReader(ALICE);

        // EP: SmartLib with a book
        model.addBook(SECRET);
        smartLib.addBook(SECRET);
        assertEquals(smartLib, logic.getSmartLib());
        model.deleteBook(SECRET);
        smartLib.removeBook(SECRET);

        // EP: SmartLib with a record
        model.addRecord(RECORD_A);
        smartLib.addRecord(RECORD_A);
        assertEquals(smartLib, logic.getSmartLib());
    }

    @Test
    public void getFilteredReaderList() {
        // EP: empty reader list
        ArrayList<Reader> al = new ArrayList<>();
        assertEquals(al, logic.getFilteredReaderList());

        // EP: non-empty reader list
        model.addReader(ALICE);
        al.add(ALICE);
        assertEquals(al, logic.getFilteredReaderList());
        model.deleteReader(ALICE);
    }

    @Test
    public void getFilteredReaderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredReaderList().remove(0));
    }

    @Test
    public void getFilteredBookList() {
        // EP: empty book list
        ArrayList<Book> al = new ArrayList<>();
        assertEquals(al, logic.getFilteredBookList());

        // EP: non-empty book list
        model.addBook(SECRET);
        al.add(SECRET);
        assertEquals(al, logic.getFilteredBookList());
        model.deleteBook(SECRET);
    }

    @Test
    public void getFilteredBookList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredBookList().remove(0));
    }

    @Test
    public void getFilteredRecordList() {
        // EP: empty record list
        ArrayList<Record> al = new ArrayList<>();
        assertEquals(al, logic.getFilteredRecordList());

        // EP: non-empty record list
        model.addRecord(RECORD_A);
        al.add(RECORD_A);
        assertEquals(al, logic.getFilteredRecordList());
    }

    @Test
    public void getFilteredRecordList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredRecordList().remove(0));
    }

    @Test
    public void getSmartLibFilePath() {
        // EP: default file path
        assertEquals(new UserPrefs().getSmartLibFilePath(), logic.getSmartLibFilePath());

        // EP: non-default file path
        model.setSmartLibFilePath(Paths.get("smartLib/file/path"));
        assertEquals(Paths.get("smartLib/file/path"), logic.getSmartLibFilePath());
    }

    @Test
    public void getGuiSettings() {
        // EP: default GUI settings
        assertEquals(new UserPrefs().getGuiSettings(), logic.getGuiSettings());

        // EP: non-default GUI settings
        model.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        assertEquals(new GuiSettings(1, 2, 3, 4),
                logic.getGuiSettings());
    }

    @Test
    public void setGuiSettings() {
        logic.setGuiSettings(new GuiSettings(1, 1, 1, 1));
        assertEquals(new GuiSettings(1, 1, 1, 1),
                logic.getGuiSettings());
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
