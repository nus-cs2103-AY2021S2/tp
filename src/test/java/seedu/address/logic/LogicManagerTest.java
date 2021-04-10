package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_MEET_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_MEET_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.POSTAL_DESC_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_MEET_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_MEET_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_MAYFAIR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAppointments.MEET_ALEX;
import static seedu.address.testutil.TypicalProperties.MAYFAIR;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddPropertyCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListAllCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.ReadOnlyPropertyBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.property.Property;
import seedu.address.storage.JsonAppointmentBookStorage;
import seedu.address.storage.JsonPropertyBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.PropertyBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonAppointmentBookStorage appointmentBookStorage =
                new JsonAppointmentBookStorage(temporaryFolder.resolve("appointmentBook.json"));
        JsonPropertyBookStorage propertyBookStorage = new JsonPropertyBookStorage(
                temporaryFolder.resolve("propertyBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(appointmentBookStorage, propertyBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteAppointmentCommand = "delete appointment 9";
        assertCommandException(deleteAppointmentCommand, MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListAllCommand.COMMAND_WORD;
        if (!model.hasProperty() && !model.hasAppointment()) {
            assertCommandSuccess(listCommand, ListAllCommand.NO_APPOINTMENTS_AND_PROPERTIES_IN_LIST, model);
        } else if (!model.hasAppointment()) {
            assertCommandSuccess(listCommand, ListAllCommand.NO_APPOINTMENTS_IN_LIST, model);
        } else if (!model.hasProperty()) {
            assertCommandSuccess(listCommand, ListAllCommand.NO_PROPERTIES_IN_LIST, model);
        } else {
            assertCommandSuccess(listCommand, ListAllCommand.MESSAGE_SUCCESS, model);
        }
    }

    @Test
    public void execute_appointmentStorageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAppointmentBookIoExceptionThrowingStub, JsonPropertyBookIoExceptionThrowingStub
        JsonAppointmentBookStorage appointmentBookStorage =
                new JsonAppointmentBookIoExceptionThrowingStub(
                        temporaryFolder.resolve("ioExceptionAppointmentBook.json"));
        JsonPropertyBookStorage propertyBookStorage =
                new JsonPropertyBookIoExceptionThrowingStub(
                        temporaryFolder.resolve("ioExceptionPropertyBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(appointmentBookStorage, propertyBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add appointment command
        String addAppointmentCommand = AddAppointmentCommand.COMMAND_WORD + NAME_DESC_MEET_ALEX + REMARK_DESC_MEET_ALEX
                + DATE_DESC_MEET_ALEX + TIME_DESC_MEET_ALEX;
        Appointment expectedAppointment = new AppointmentBuilder(MEET_ALEX).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addAppointment(expectedAppointment);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addAppointmentCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void execute_propertyStorageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAppointmentBookIoExceptionThrowingStub, JsonPropertyBookIoExceptionThrowingStub
        JsonAppointmentBookStorage appointmentBookStorage =
                new JsonAppointmentBookIoExceptionThrowingStub(
                        temporaryFolder.resolve("ioExceptionAppointmentBook.json"));
        JsonPropertyBookStorage propertyBookStorage =
                new JsonPropertyBookIoExceptionThrowingStub(
                        temporaryFolder.resolve("ioExceptionPropertyBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(appointmentBookStorage, propertyBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add property command
        String addPropertyCommand = AddPropertyCommand.COMMAND_WORD + NAME_DESC_MAYFAIR + TYPE_DESC_MAYFAIR
                + ADDRESS_DESC_MAYFAIR + POSTAL_DESC_MAYFAIR + DEADLINE_DESC_MAYFAIR;
        Property expectedProperty = new PropertyBuilder(MAYFAIR).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addProperty(expectedProperty);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addPropertyCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredAppointmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredAppointmentList().remove(0));
    }

    @Test
    public void getFilteredPropertytList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPropertyList().remove(0));
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
        Model expectedModel = new ModelManager(model.getAppointmentBook(), new UserPrefs());
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
     * A stub class for {@code JsonAppointmentBookStorage} to throw an {@code IOException}
     * when the save method is called.
     */
    private static class JsonAppointmentBookIoExceptionThrowingStub extends JsonAppointmentBookStorage {
        private JsonAppointmentBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class for {@code JsonPropertyBookStorage} to throw an {@code IOException}
     * when the save method is called.
     */
    private static class JsonPropertyBookIoExceptionThrowingStub extends JsonPropertyBookStorage {
        private JsonPropertyBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void savePropertyBook(ReadOnlyPropertyBook propertyBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

}
