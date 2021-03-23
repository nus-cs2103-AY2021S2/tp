package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskTracker;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Task;
import seedu.address.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_CODE_AMY = "CS2103";
    public static final String VALID_CODE_BOB = "CS2040";
    public static final String VALID_WEIGHTAGE_AMY = "25%";
    public static final String VALID_WEIGHTAGE_BOB = "50%";
    public static final String VALID_DATE_AMY = "10-10-2020";
    public static final String VALID_DATE_BOB = "03-05-2022";
    public static final String VALID_TIME_AMY = "10:10";
    public static final String VALID_TIME_BOB = "23:59";
    public static final String VALID_REMARK_AMY = "Being a software engineer is fun";
    public static final String VALID_REMARK_BOB = "Favourite pastime: Kattis";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String CODE_DESC_AMY = " " + PREFIX_CODE + VALID_CODE_AMY;
    public static final String CODE_DESC_BOB = " " + PREFIX_CODE + VALID_CODE_BOB;
    public static final String WEIGHTAGE_DESC_AMY = " " + PREFIX_WEIGHTAGE + VALID_WEIGHTAGE_AMY;
    public static final String WEIGHTAGE_DESC_BOB = " " + PREFIX_WEIGHTAGE + VALID_WEIGHTAGE_BOB;
    public static final String DATE_DESC_AMY = " " + PREFIX_DEADLINE_DATE + VALID_DATE_AMY;
    public static final String DATE_DESC_BOB = " " + PREFIX_DEADLINE_DATE + VALID_DATE_BOB;
    public static final String TIME_DESC_AMY = " " + PREFIX_DEADLINE_TIME + VALID_TIME_AMY;
    public static final String TIME_DESC_BOB = " " + PREFIX_DEADLINE_TIME + VALID_TIME_BOB;
    public static final String REMARK_DESC_AMY = " " + PREFIX_REMARK + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + PREFIX_REMARK + VALID_REMARK_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_CODE_DESC = " " + PREFIX_CODE + "CT2340"; // 'CT' not allowed in code
    // non-digits not allowed in weightage
    public static final String INVALID_WEIGHTAGE_DESC_NAN = " " + PREFIX_WEIGHTAGE + "asd%";
    public static final String INVALID_WEIGHTAGE_DESC_OOB = " " + PREFIX_WEIGHTAGE + "101%"; // out of bounds
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditTaskDescriptor DESC_AMY;
    public static final EditCommand.EditTaskDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditTaskDescriptorBuilder().withName(VALID_NAME_AMY)
            .withCode(VALID_CODE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditTaskDescriptorBuilder().withName(VALID_NAME_BOB)
            .withCode(VALID_CODE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered task list and selected task in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TaskTracker expectedTaskTracker = new TaskTracker(actualModel.getTaskTracker());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTaskTracker, actualModel.getTaskTracker());
        assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitName = task.getModuleName().fullName.split("\\s+");
        model.updateFilteredTaskList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTaskList().size());
    }

}
