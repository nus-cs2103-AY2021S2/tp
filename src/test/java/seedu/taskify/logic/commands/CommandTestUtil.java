package seedu.taskify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taskify.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.taskify.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.taskify.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.taskify.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.taskify.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.taskify.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.taskify.commons.core.index.Index;
import seedu.taskify.logic.commands.exceptions.CommandException;
import seedu.taskify.model.Model;
import seedu.taskify.model.Taskify;
import seedu.taskify.model.task.StatusType;
import seedu.taskify.model.task.Task;
import seedu.taskify.model.task.predicates.NameContainsKeywordsPredicate;
import seedu.taskify.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_DESCRIPTION_AMY = "11111111";
    public static final String VALID_DESCRIPTION_BOB = "22222222";
    public static final String VALID_STATUS_NOT_DONE = "not done";
    public static final String VALID_STATUS_IN_PROGRESS = "in progress";
    public static final String VALID_STATUS_COMPLETED = "completed";
    public static final String VALID_DATE_AMY = "2020-12-25 22:30";
    public static final String VALID_DATE_BOB = "2019-12-25 22:30";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String DESCRIPTION_DESC_AMY = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_AMY;
    public static final String DESCRIPTION_DESC_BOB = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BOB;
    public static final String DATE_DESC_AMY = " " + PREFIX_DATE + VALID_DATE_AMY;
    public static final String DATE_DESC_BOB = " " + PREFIX_DATE + VALID_DATE_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION;
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS; // empty string not allowed for status
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE; // empty string not allowed for dates
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditTaskDescriptor DESC_AMY;
    public static final EditCommand.EditTaskDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditTaskDescriptorBuilder().withName(VALID_NAME_AMY)
                .withDescription(VALID_DESCRIPTION_AMY).withStatus(StatusType.NOT_DONE)
                .withDate(VALID_DATE_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditTaskDescriptorBuilder().withName(VALID_NAME_BOB)
                .withDescription(VALID_DESCRIPTION_BOB).withStatus(StatusType.NOT_DONE)
                .withDate(VALID_DATE_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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
        Taskify expectedTaskify = new Taskify(actualModel.getAddressBook());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTaskify, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitName = task.getName().fullName.split("\\s+");
        model.updateFilteredTaskList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTaskList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the tasks at the given {@code targetIndexes} in the
     * {@code model}'s address book.
     */
    public static void showTasksAtIndexes(Model model, List<Index> targetIndexes) {
        List<String> keywords = new ArrayList<>();
        for (Index targetIndex : targetIndexes) {
            assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());
            Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
            String[] splitName = task.getName().fullName.split("\\s+");
            keywords.add(splitName[0]);
        }
        model.updateFilteredTaskList(new NameContainsKeywordsPredicate(keywords));

        assertEquals(targetIndexes.size(), model.getFilteredTaskList().size());
    }

}
