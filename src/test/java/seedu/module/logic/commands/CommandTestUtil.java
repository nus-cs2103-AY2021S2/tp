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

    public static final String VALID_NAME_AMY = "Lab";
    public static final String VALID_NAME_BOB = "Preview";
    public static final String VALID_DEADLINE_AMY = "2021-01-30 12:00";
    public static final String VALID_DEADLINE_BOB = "2021-02-02 23:59";
    public static final String VALID_MODULE_AMY = "CS2106";
    public static final String VALID_MODULE_BOB = "CS3244";
    public static final String VALID_DESCRIPTION_AMY = "Finish this ASAP.";
    public static final String VALID_DESCRIPTION_BOB = "Need to figure out the concept.";
    public static final String VALID_WORKLOAD_1 = "1";
    public static final String VALID_WORKLOAD_2 = "2";
    public static final String VALID_TAG_HIGH = "priorityHigh";
    public static final String VALID_TAG_LOW = "priorityLow";

    public static final String NAME_DESC_AMY = " " + PREFIX_TASK_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_TASK_NAME + VALID_NAME_BOB;
    public static final String DEADLINE_DESC_AMY = " " + PREFIX_DEADLINE + VALID_DEADLINE_AMY;
    public static final String DEADLINE_DESC_BOB = " " + PREFIX_DEADLINE + VALID_DEADLINE_BOB;
    public static final String MODULE_DESC_AMY = " " + PREFIX_MODULE + VALID_MODULE_AMY;
    public static final String MODULE_DESC_BOB = " " + PREFIX_MODULE + VALID_MODULE_BOB;
    public static final String DESCRIPTION_DESC_AMY = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_AMY;
    public static final String DESCRIPTION_DESC_BOB = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BOB;
    public static final String WORKLOAD_DESC_1 = " " + PREFIX_WORKLOAD + VALID_WORKLOAD_1;
    public static final String WORKLOAD_DESC_2 = " " + PREFIX_WORKLOAD + VALID_WORKLOAD_2;
    public static final String TAG_DESC_LOW = " " + PREFIX_TAG + VALID_TAG_LOW;
    public static final String TAG_DESC_HIGH = " " + PREFIX_TAG + VALID_TAG_HIGH;

    public static final String INVALID_NAME_DESC = " " + PREFIX_TASK_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE + "911a"; // 'a' not allowed in deadlines
    public static final String INVALID_MODULE_DESC = " " + PREFIX_MODULE + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION; // descriptions should not be empty
    public static final String INVALID_WORKLOAD_DESC = " " + PREFIX_WORKLOAD + "4"; // workload should be in range 1-3
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditTaskDescriptor DESC_AMY;
    public static final EditCommand.EditTaskDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditTaskDescriptorBuilder().withName(VALID_NAME_AMY)
                .withDeadline(VALID_DEADLINE_AMY).withModule(VALID_MODULE_AMY).withDescription(VALID_DESCRIPTION_AMY)
                .withWorkload(VALID_WORKLOAD_1).withTags(VALID_TAG_LOW).build();
        DESC_BOB = new EditTaskDescriptorBuilder().withName(VALID_NAME_BOB)
                .withDeadline(VALID_DEADLINE_BOB).withModule(VALID_MODULE_BOB).withDescription(VALID_DESCRIPTION_BOB)
                .withWorkload(VALID_WORKLOAD_2).withTags(VALID_TAG_HIGH, VALID_TAG_LOW).build();
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
