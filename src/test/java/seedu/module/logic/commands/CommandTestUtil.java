package seedu.module.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.module.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.module.logic.parser.CliSyntax.PREFIX_WORKLOAD;
import static seedu.module.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.module.commons.core.index.Index;
import seedu.module.logic.commands.exceptions.CommandException;
import seedu.module.model.Model;
import seedu.module.model.ModuleBook;
import seedu.module.model.task.NameContainsKeywordsPredicate;
import seedu.module.model.task.Task;
import seedu.module.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_TASK_NAME_LAB = "Lab";
    public static final String VALID_TASK_NAME_PRACTICAL = "Practical";
    public static final String VALID_DEADLINE_LAB = "2021-01-30 12:00";
    public static final String VALID_DEADLINE_PRACTICAL = "2021-02-02";
    public static final String VALID_MODULE_LAB = "CS2106";
    public static final String VALID_MODULE_PRACTICAL = "CS3244";
    public static final String VALID_DESCRIPTION_LAB = "Finish this ASAP.";
    public static final String VALID_DESCRIPTION_PRACTICAL = "Need to figure out the concept.";
    public static final String VALID_TAG_PRIORITY_HIGH = "priorityHigh";
    public static final String VALID_TAG_PRIORITY_LOW = "priorityLow";
    public static final String VALID_WORKLOAD_1 = "1";
    public static final String VALID_WORKLOAD_2 = "2";

    public static final String TASK_NAME_DESC_LAB = " " + PREFIX_TASK_NAME + VALID_TASK_NAME_LAB;
    public static final String TASK_NAME_DESC_PRACTICAL = " " + PREFIX_TASK_NAME + VALID_TASK_NAME_PRACTICAL;
    public static final String DEADLINE_DESC_LAB = " " + PREFIX_DEADLINE + VALID_DEADLINE_LAB;
    public static final String DEADLINE_DESC_PRACTICAL = " " + PREFIX_DEADLINE + VALID_DEADLINE_PRACTICAL;
    public static final String MODULE_DESC_LAB = " " + PREFIX_MODULE + VALID_MODULE_LAB;
    public static final String MODULE_DESC_PRACTICAL = " " + PREFIX_MODULE + VALID_MODULE_PRACTICAL;
    public static final String DESCRIPTION_DESC_LAB = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_LAB;
    public static final String DESCRIPTION_DESC_PRACTICAL = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_PRACTICAL;
    public static final String TAG_DESC_LOW = " " + PREFIX_TAG + VALID_TAG_PRIORITY_LOW;
    public static final String TAG_DESC_HIGH = " " + PREFIX_TAG + VALID_TAG_PRIORITY_HIGH;
    public static final String WORKLOAD_DESC_1 = " " + PREFIX_WORKLOAD + VALID_WORKLOAD_1;
    public static final String WORKLOAD_DESC_2 = " " + PREFIX_WORKLOAD + VALID_WORKLOAD_2;

    public static final String INVALID_TASK_NAME_DESC = " " + PREFIX_TASK_NAME + "   "; // '&' not allowed in tasks
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE + "911a"; // 'a' not allowed in deadlines
    public static final String INVALID_MODULE_DESC = " " + PREFIX_MODULE + "CS!2040"; // ! not allowed in modules
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION; // descriptions should not be empty
    public static final String INVALID_WORKLOAD_DESC = " " + PREFIX_WORKLOAD + "4"; // workload should be in range 1-3
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditTaskDescriptor DESC_LAB;
    public static final EditCommand.EditTaskDescriptor DESC_PRACTICAL;

    static {
        DESC_LAB = new EditTaskDescriptorBuilder()
                .withName(VALID_TASK_NAME_LAB)
                .withDeadline(VALID_DEADLINE_LAB)
                .withModule(VALID_MODULE_LAB)
                .withDescription(VALID_DESCRIPTION_LAB)
                .withWorkload(VALID_WORKLOAD_1)
                .withTags(VALID_TAG_PRIORITY_LOW).build();
        DESC_PRACTICAL = new EditTaskDescriptorBuilder()
                .withName(VALID_TASK_NAME_PRACTICAL)
                .withDeadline(VALID_DEADLINE_PRACTICAL)
                .withModule(VALID_MODULE_PRACTICAL)
                .withDescription(VALID_DESCRIPTION_PRACTICAL)
                .withWorkload(VALID_WORKLOAD_2)
                .withTags(VALID_TAG_PRIORITY_HIGH, VALID_TAG_PRIORITY_LOW).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel); //execute command on actual model
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
     * - the module book, filtered task list and selected task in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ModuleBook expectedModuleBook = new ModuleBook(actualModel.getModuleBook());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedModuleBook, actualModel.getModuleBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s module book.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitName = task.getName().fullName.split("\\s+");
        model.updateFilteredTaskList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTaskList().size());
    }

}
