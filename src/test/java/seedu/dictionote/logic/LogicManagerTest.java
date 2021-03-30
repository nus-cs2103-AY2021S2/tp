package seedu.dictionote.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX;
import static seedu.dictionote.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.dictionote.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.dictionote.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.dictionote.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.dictionote.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.dictionote.testutil.Assert.assertThrows;
import static seedu.dictionote.testutil.TypicalContacts.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.dictionote.logic.commands.AddContactCommand;
import seedu.dictionote.logic.commands.CommandResult;
import seedu.dictionote.logic.commands.ListContactCommand;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.logic.parser.exceptions.ParseException;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.ModelManager;
import seedu.dictionote.model.ReadOnlyContactsList;
import seedu.dictionote.model.UserPrefs;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.storage.JsonContactsListStorage;
import seedu.dictionote.storage.JsonDefinitionBookStorage;
import seedu.dictionote.storage.JsonDictionaryStorage;
import seedu.dictionote.storage.JsonNoteBookStorage;
import seedu.dictionote.storage.JsonUserPrefsStorage;
import seedu.dictionote.storage.StorageManager;
import seedu.dictionote.testutil.ContactBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonContactsListStorage addressBookStorage =
                new JsonContactsListStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonNoteBookStorage noteBookStorage =
                new JsonNoteBookStorage(temporaryFolder.resolve("notebook.json"));
        JsonDictionaryStorage dictionaryStorage =
                new JsonDictionaryStorage(temporaryFolder.resolve("dictionary.json"));
        JsonDefinitionBookStorage definitionBookStorage =
                new JsonDefinitionBookStorage(temporaryFolder.resolve("definition.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage,
                noteBookStorage, dictionaryStorage, definitionBookStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteContactCommand = "deletecontact 9";
        assertCommandException(deleteContactCommand, MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListContactCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListContactCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonContactsListStorage addressBookStorage =
                new JsonContactsListIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        JsonNoteBookStorage noteBookStorage =
                new JsonNoteBookStorage(temporaryFolder.resolve("notebook.json"));
        JsonDictionaryStorage dictionaryStorage =
                new JsonDictionaryStorage(temporaryFolder.resolve("dictionary.json"));
        JsonDefinitionBookStorage definitionBookStorage =
                new JsonDefinitionBookStorage(temporaryFolder.resolve("definition.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage,
                noteBookStorage, dictionaryStorage, definitionBookStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddContactCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY;
        Contact expectedContact = new ContactBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addContact(expectedContact);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredContactList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredContactList().remove(0));
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
        Model expectedModel = new ModelManager(model.getContactsList(), new UserPrefs(),
                model.getNoteBook(), model.getDictionary(), model.getDefinitionBook());
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
    private static class JsonContactsListIoExceptionThrowingStub extends JsonContactsListStorage {
        private JsonContactsListIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveContactsList(ReadOnlyContactsList addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
