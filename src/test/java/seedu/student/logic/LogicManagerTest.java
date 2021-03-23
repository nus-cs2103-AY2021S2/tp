package seedu.student.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.student.commons.core.Messages.MESSAGE_NONEXISTENT_MATRIC_NUM;
import static seedu.student.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.student.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.student.logic.commands.CommandTestUtil.DETAILS_DESC_AMY;
import static seedu.student.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.student.logic.commands.CommandTestUtil.FACULTY_DESC_AMY;
import static seedu.student.logic.commands.CommandTestUtil.MATRIC_DESC_AMY;
import static seedu.student.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.student.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.student.logic.commands.CommandTestUtil.RESIDENCE_DESC_AMY;
import static seedu.student.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.student.testutil.Assert.assertThrows;
import static seedu.student.testutil.TypicalStudents.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.student.logic.commands.AddCommand;
import seedu.student.logic.commands.CommandResult;
import seedu.student.logic.commands.ListCommand;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.Model;
import seedu.student.model.ModelManager;
import seedu.student.model.ReadOnlyStudentBook;
import seedu.student.model.UserPrefs;
import seedu.student.model.student.Student;
import seedu.student.storage.JsonStudentBookStorage;
import seedu.student.storage.JsonUserPrefsStorage;
import seedu.student.storage.StorageManager;
import seedu.student.testutil.StudentBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonStudentBookStorage addressBookStorage =
                new JsonStudentBookStorage(temporaryFolder.resolve("addressBook.json"));
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
        String deleteCommand = "deleteStud A1231234A";
        assertCommandException(deleteCommand, MESSAGE_NONEXISTENT_MATRIC_NUM);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonStudentBookIoExceptionThrowingStub
        JsonStudentBookStorage addressBookStorage =
                new JsonStudentBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionStudentBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + MATRIC_DESC_AMY + FACULTY_DESC_AMY
                + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + STATUS_DESC_AMY + DETAILS_DESC_AMY
                + RESIDENCE_DESC_AMY;
        Student expectedStudent = new StudentBuilder(AMY).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addStudent(expectedStudent);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredStudentList().remove(0));
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
        Model expectedModel = new ModelManager(model.getStudentBook(), new UserPrefs());
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
    private static class JsonStudentBookIoExceptionThrowingStub extends JsonStudentBookStorage {
        private JsonStudentBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveStudentBook(ReadOnlyStudentBook studentBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
