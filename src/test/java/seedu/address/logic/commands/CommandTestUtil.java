package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRINGSCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Planner;
import seedu.address.model.task.Task;
import seedu.address.model.task.TitleContainsKeywordsPredicate;
import seedu.address.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_TITLE_AMY = "Amy Bee";
    public static final String VALID_TITLE_BOB = "Bob Choo";
    public static final String VALID_DEADLINE_AMY = "12 Oct 2012";
    public static final String VALID_DEADLINE_BOB = "14 Oct 2012";
    public static final String VALID_RECURRINGSCHEDULE_AMY = "[10 Mar 2021][Mon][biweekly]";
    public static final String VALID_RECURRINGSCHEDULE_BOB = "[08 Mar 2021][Tue][weekly]";
    public static final String VALID_DESCRIPTION_AMY = "Block 312, Amy Street 1";
    public static final String VALID_DESCRIPTION_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_STATUS_AMY = "done";
    public static final String VALID_STATUS_BOB = "not done";
    public static final String VALID_STATUS_INDEX = "1000";
    public static final String VALID_STARTTIME_AMY = "1230";
    public static final String VALID_STARTTIME_BOB = "1230";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String TITLE_DESC_AMY = " " + PREFIX_TITLE + VALID_TITLE_AMY;
    public static final String TITLE_DESC_BOB = " " + PREFIX_TITLE + VALID_TITLE_BOB;
    public static final String STARTTIME_DESC_AMY = " " + PREFIX_STARTTIME + VALID_STARTTIME_AMY;
    public static final String STARTTIME_DESC_BOB = " " + PREFIX_STARTTIME + VALID_STARTTIME_BOB;
    public static final String DEADLINE_DESC_AMY = " " + PREFIX_DEADLINE + VALID_DEADLINE_AMY;
    public static final String DEADLINE_DESC_BOB = " " + PREFIX_DEADLINE + VALID_DEADLINE_BOB;
    public static final String RECURRINGSCHEDULE_DESC_AMY = " " + PREFIX_RECURRINGSCHEDULE
            + VALID_RECURRINGSCHEDULE_AMY;
    public static final String RECURRINGSCHEDULE_DESC_BOB = " " + PREFIX_RECURRINGSCHEDULE
            + VALID_RECURRINGSCHEDULE_BOB;
    public static final String DESCRIPTION_DESC_AMY = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_AMY;
    public static final String DESCRIPTION_DESC_BOB = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BOB;
    public static final String STATUS_DESC_AMY = " " + PREFIX_STATUS + VALID_STATUS_AMY;
    public static final String STATUS_DESC_BOB = " " + PREFIX_STATUS + VALID_STATUS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + "James&"; // '&' not allowed in titles
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE; // ' ' not allowed in deadlines
    public static final String INVALID_STARTTIME_DESC = " " + PREFIX_STARTTIME; // ' ' not allowed in deadlines
    public static final String INVALID_RECURRINGSCHEDULE_DESC = " " + PREFIX_RECURRINGSCHEDULE
             + "10 Mar2021Monbiweekly"; // missing '[]' symbol within the field
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_STATUS_INDEX = " " + "2147483648"; // Integer Overflow

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditTaskDescriptor DESC_AMY;
    public static final EditCommand.EditTaskDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditTaskDescriptorBuilder().withTitle(VALID_TITLE_AMY).withStartTime(VALID_STARTTIME_AMY)
                .withDeadline(VALID_DEADLINE_AMY).withRecurringSchedule(VALID_RECURRINGSCHEDULE_AMY)
                .withDescription(VALID_DESCRIPTION_AMY).withStatus(VALID_STATUS_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditTaskDescriptorBuilder().withTitle(VALID_TITLE_BOB).withStartTime(VALID_STARTTIME_BOB)
                .withDeadline(VALID_DEADLINE_BOB).withRecurringSchedule(VALID_RECURRINGSCHEDULE_BOB)
                .withDescription(VALID_DESCRIPTION_BOB).withStatus(VALID_STATUS_BOB)
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
     * - the planner, filtered task list and selected task in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Planner expectedPlanner = new Planner(actualModel.getPlanner());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPlanner, actualModel.getPlanner());
        assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s planner.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitTitle = task.getTitle().fullTitle.split("\\s+");
        model.updateFilteredTaskList(new TitleContainsKeywordsPredicate(Arrays.asList(splitTitle[0])));

        assertEquals(1, model.getFilteredTaskList().size());
    }

}
