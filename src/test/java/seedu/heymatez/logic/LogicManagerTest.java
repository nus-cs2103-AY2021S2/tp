package seedu.heymatez.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.heymatez.commons.core.Messages.MESSAGE_EMPTY_PERSON_LIST;
import static seedu.heymatez.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.heymatez.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.heymatez.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.heymatez.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.heymatez.testutil.Assert.assertThrows;
import static seedu.heymatez.testutil.TypicalPersons.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.heymatez.logic.commands.AddMemberCommand;
import seedu.heymatez.logic.commands.CommandResult;
import seedu.heymatez.logic.commands.ViewMembersCommand;
import seedu.heymatez.logic.commands.exceptions.CommandException;
import seedu.heymatez.logic.parser.exceptions.ParseException;
import seedu.heymatez.model.Model;
import seedu.heymatez.model.ModelManager;
import seedu.heymatez.model.ReadOnlyHeyMatez;
import seedu.heymatez.model.UserPrefs;
import seedu.heymatez.model.person.Person;
import seedu.heymatez.storage.JsonHeyMatezStorage;
import seedu.heymatez.storage.JsonUserPrefsStorage;
import seedu.heymatez.storage.StorageManager;
import seedu.heymatez.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonHeyMatezStorage heyMatezStorage =
                new JsonHeyMatezStorage(temporaryFolder.resolve("heymatez.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(heyMatezStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ViewMembersCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, MESSAGE_EMPTY_PERSON_LIST, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonHeyMatezIoExceptionThrowingStub
        JsonHeyMatezStorage heyMatezStorage =
                new JsonHeyMatezIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionHeyMatez.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(heyMatezStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddMemberCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY;
        Person expectedPerson = new PersonBuilder(AMY).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedPerson);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
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
        Model expectedModel = new ModelManager(model.getHeyMatez(), new UserPrefs());
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
    private static class JsonHeyMatezIoExceptionThrowingStub extends JsonHeyMatezStorage {
        private JsonHeyMatezIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveHeyMatez(ReadOnlyHeyMatez heyMatez, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
