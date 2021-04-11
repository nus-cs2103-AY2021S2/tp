package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.JOB_TITLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.AMY;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EmailCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUniqueAliasMap;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonAliasMapStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonAliasMapStorage aliasMapStorage =
                new JsonAliasMapStorage(temporaryFolder.resolve("aliasMap.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage, aliasMapStorage);
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
        assertCommandException(deleteCommand, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub and JsonAliasMapIoExceptionThrowingStub
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        JsonAliasMapStorage aliasMapStorage =
                new JsonAliasMapIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAliasMap.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage, aliasMapStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + COMPANY_DESC_AMY + JOB_TITLE_DESC_AMY + ADDRESS_DESC_AMY + REMARK_DESC_AMY;
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedPerson);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
    }

    @Test
    public void getAutocompleteCommands_returnEmptyList_whiteSpaceParameter() {
        List<String> whiteSpaceCommandList = logic.getAutocompleteCommands(" ");
        List<String> emptyCommandList = new ArrayList<>();
        assertEquals(whiteSpaceCommandList, emptyCommandList);
    }

    @Test
    public void getAutocompleteCommands_nullParameterReturnsSameAsEmptyParameter() {
        List<String> nullCommandList = logic.getAutocompleteCommands(null);
        List<String> emptyCommandList = logic.getAutocompleteCommands("");
        assertEquals(nullCommandList, emptyCommandList);
    }

    @Test
    public void getAutocompleteCommands_existingParameter() {
        List<String> commandList = new ArrayList<>();
        commandList.add(EditCommand.COMMAND_WORD);
        commandList.add(ExitCommand.COMMAND_WORD);
        commandList.add(EmailCommand.COMMAND_WORD);
        Collections.sort(commandList);

        List<String> testList = logic.getAutocompleteCommands("e");
        assertEquals(commandList, testList);
        List<String> testList1 = logic.getAutocompleteCommands("ad");

        List<String> commandList1 = new ArrayList<>();
        commandList1.add(AddCommand.COMMAND_WORD);
        assertEquals(commandList1, testList1);

    }

    @Test
    public void getAutocompleteCommands_testLexicographical() {
        ObservableList<String> testList = logic.getAutocompleteCommands("");
        for (int i = 0; i < testList.size() - 1; i++) {
            assertTrue(testList.get(i).compareTo(testList.get(i + 1)) <= 0);
        }
    }

    @Test
    public void getAutocompleteFlags_invalidCommand() {
        // null
        List<String> whiteSpaceTest = logic.getAutocompleteFlags(" ");
        List<String> emptyTest = logic.getAutocompleteFlags("");
        List<String> spellingErrorTest = logic.getAutocompleteFlags("addd");
        List<String> unhandledCommandTest = logic.getAutocompleteFlags(FilterCommand.COMMAND_WORD);

        List<String> emptyList = new ArrayList<>();
        assertEquals(whiteSpaceTest, emptyList);
        assertEquals(emptyTest, emptyList);
        assertEquals(spellingErrorTest, emptyList);
        assertEquals(unhandledCommandTest, emptyList);
    }

    @Test
    public void getAutocompleteFlags_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> logic.getAutocompleteFlags(null));
    }

    @Test
    public void filterExistingFlags_emptyAndWhiteSpace() {
        String emptyString = "";
        String whiteSpace = " ";

        List<String> addFlags = logic.getAutocompleteFlags(AddCommand.COMMAND_WORD);
        List<String> editFlags = logic.getAutocompleteFlags(AddCommand.COMMAND_WORD);

        assertEquals(addFlags, logic.filterExistingFlags(emptyString, AddCommand.COMMAND_WORD));
        assertEquals(editFlags, logic.filterExistingFlags(emptyString, EditCommand.COMMAND_WORD));
        assertEquals(addFlags, logic.filterExistingFlags(whiteSpace, AddCommand.COMMAND_WORD));
        assertEquals(editFlags, logic.filterExistingFlags(whiteSpace, EditCommand.COMMAND_WORD));
    }

    @Test
    public void filterExistingFlags_validCommand() {
        List<String> editFlags = logic.getAutocompleteFlags(EditCommand.COMMAND_WORD);
        List<String> addFlags = logic.getAutocompleteFlags(AddCommand.COMMAND_WORD);

        String editString = "edit 3 -n John Doe -e john@doe.com";
        String addString = "add -n Jane Doe -p 99998888 -e jane@doe.com -a National University of Singapore";

        editFlags.remove(CliSyntax.PREFIX_NAME.getPrefix());
        editFlags.remove(CliSyntax.PREFIX_EMAIL.getPrefix());
        assertEquals(editFlags, logic.filterExistingFlags(editString, EditCommand.COMMAND_WORD));

        addFlags.remove(CliSyntax.PREFIX_NAME.getPrefix());
        addFlags.remove(CliSyntax.PREFIX_PHONE.getPrefix());
        addFlags.remove(CliSyntax.PREFIX_EMAIL.getPrefix());
        addFlags.remove(CliSyntax.PREFIX_ADDRESS.getPrefix());
        assertEquals(addFlags, logic.filterExistingFlags(addString, AddCommand.COMMAND_WORD));
    }

    @Test
    public void filterExistingFlags_invalidCommand() {
        String validString = "edit 3 -n John Doe -e john@doe.com";
        String invalidString = "delete 3";
        String invalidCommand = DeleteCommand.COMMAND_WORD;

        List<String> emptyFlags = new ArrayList<>();

        assertEquals(emptyFlags, logic.filterExistingFlags(validString, invalidCommand));
        assertEquals(emptyFlags, logic.filterExistingFlags(invalidString, invalidCommand));
    }

    @Test
    public void isAutocompleteFlag_validStrings() {
        String addString = "add ";
        String editString = "edit ";

        assertTrue(logic.isAutocompleteFlag(addString));
        assertTrue(logic.isAutocompleteFlag(editString));
    }

    @Test
    public void isAutocompleteFlag_invalidStrings() {
        String addString = "add";
        String editString = "edit";
        String unrecognizedCommand = "delete";
        String typo = "edi";

        assertFalse(logic.isAutocompleteFlag(addString));
        assertFalse(logic.isAutocompleteFlag(editString));
        assertFalse(logic.isAutocompleteFlag(unrecognizedCommand));
        assertFalse(logic.isAutocompleteFlag(typo));
    }

    @Test
    public void isAutocompleteFlag_nullParameter() {
        assertFalse(logic.isAutocompleteFlag(null));
    }

    @Test
    public void getAvailableFlags_validCommandStrings() {
        String expectedAdd = "add ";
        String expectedEdit = "edit 3";

        List<String> addFlags = logic.getAutocompleteFlags(AddCommand.COMMAND_WORD);
        List<String> editFlags = logic.getAutocompleteFlags(EditCommand.COMMAND_WORD);


        assertEquals(addFlags, logic.getAvailableFlags(expectedAdd));
        assertEquals(editFlags, logic.getAvailableFlags(expectedEdit));
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
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getAliasMap());
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
    private static class JsonAddressBookIoExceptionThrowingStub extends JsonAddressBookStorage {
        private JsonAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonAliasMapIoExceptionThrowingStub extends JsonAliasMapStorage {
        private JsonAliasMapIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAliasMap(ReadOnlyUniqueAliasMap aliasMap, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
