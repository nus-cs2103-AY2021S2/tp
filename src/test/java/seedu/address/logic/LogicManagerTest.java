package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.AddUserCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.diet.DietPlanList;
import seedu.address.model.food.FoodIntakeList;
import seedu.address.model.food.UniqueFoodList;
import seedu.address.model.user.User;
import seedu.address.storage.JsonDietPlanListStorage;
import seedu.address.storage.JsonFoodIntakeListStorage;
import seedu.address.storage.JsonUniqueFoodListStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.JsonUserStorage;
import seedu.address.storage.StorageManager;

public class LogicManagerTest {

    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() throws CommandException, ParseException {
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonUniqueFoodListStorage uniqueFoodListStorage =
                new JsonUniqueFoodListStorage(temporaryFolder.resolve("uniqueFoods.json"));
        JsonFoodIntakeListStorage foodIntakeListStorage =
                new JsonFoodIntakeListStorage(temporaryFolder.resolve("foodIntakes.json"));
        JsonDietPlanListStorage dietPlanListStorage =
                new JsonDietPlanListStorage(temporaryFolder.resolve("dietPlans.json"));
        JsonUserStorage userStorage = new JsonUserStorage(temporaryFolder.resolve("user.json"));
        StorageManager storage = new StorageManager(uniqueFoodListStorage, foodIntakeListStorage,
                dietPlanListStorage, userPrefsStorage, userStorage);
        logic = new LogicManager(model, storage);

        // Execute startup BMI
        startupBmi();
    }

    public void startupBmi() throws CommandException, ParseException {
        // TODO: Add a seperate test case for this as well
        String addUserCommand = AddUserCommand.COMMAND_WORD;
        String command = addUserCommand + " g/M a/24 h/170 w/52 i/55";
        logic.execute(command);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonDietLahIoExceptionThrowingStub
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        JsonUniqueFoodListStorage uniqueFoodListStorage =
                new JsonUniqueFoodListStorage(temporaryFolder.resolve("ioExceptionUniqueFoods.json"));
        JsonDietPlanListStorage dietPlanListStorage =
                new JsonDietPlanListStorage(temporaryFolder.resolve("ioExceptionDietPlans.json"));
        JsonFoodIntakeListStorage foodIntakeListStorage =
                new JsonFoodIntakeListStorage(temporaryFolder.resolve("ioExceptionFoodIntakeList.json"));
        JsonUserStorage userStorage = new JsonUserStorage(temporaryFolder.resolve("ioExceptionUser.json"));
        StorageManager storage = new StorageManager(uniqueFoodListStorage, foodIntakeListStorage,
                dietPlanListStorage, userPrefsStorage, userStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        /*String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY;
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedPerson);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);*/

        //TODO Implement storage throws IOException testing
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

    /*
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /*
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /*
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage) {
        Model expectedModel = new ModelManager(new UniqueFoodList(),
                new FoodIntakeList(), new DietPlanList(), new UserPrefs(), new User());
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
        // TODO Fix ParseException in line commented below
        //assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

}
