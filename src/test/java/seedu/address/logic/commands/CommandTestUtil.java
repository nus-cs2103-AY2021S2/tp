package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Sochedule;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventNameContainsKeywordsPredicate;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    // for logic in sochedule
    public static final String VALID_TASK_NAME = "Sample Task";
    public static final String VALID_TASK_DEADLINE = "2022-01-01";
    public static final String VALID_PRIORITY = "5";
    public static final String TASK_NAME_SAMPLE = " " + PREFIX_NAME + VALID_TASK_NAME;
    public static final String TASK_DEADLINE_SAMPLE = " " + PREFIX_DEADLINE + VALID_TASK_DEADLINE;
    public static final String PRIORITY_SAMPLE = " " + PREFIX_PRIORITY + VALID_PRIORITY;

    public static final String VALID_EVENT_NAME_INTERVIEW = "Coding Interview";
    public static final String VALID_EVENT_STARTDATE_INTERVIEW = "2022-03-22";
    public static final String VALID_EVENT_STARTTIME_INTERVIEW = "13:00";
    public static final String VALID_EVENT_ENDDATE_INTERVIEW = "2022-03-22";
    public static final String VALID_EVENT_ENDTIME_INTERVIEW = "15:00";
    public static final String VALID_EVENT_TAG_INTERVIEW = "Final";
    public static final String VALID_EVENT_CATEGORY_INTERVIEW = "Work";
    public static final String VALID_EVENT_NAME_ORIENTATION = "SoC FOP";
    public static final String VALID_EVENT_STARTDATE_ORIENTATION = "2022-07-24";
    public static final String VALID_EVENT_STARTTIME_ORIENTATION = "07:00";
    public static final String VALID_EVENT_ENDDATE_ORIENTATION = "2022-07-31";
    public static final String VALID_EVENT_ENDTIME_ORIENTATION = "22:00";
    public static final String VALID_EVENT_TAG_ORIENTATION = "Fun";
    public static final String VALID_EVENT_CATEGORY_ORIENTATION = "School";


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
     * - the sochedule, filtered list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        Sochedule expectedSochedule = new Sochedule(actualModel.getSochedule());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedSochedule, actualModel.getSochedule());
        assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s sochedule.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitName = task.getName().fullName.split("\\s+");
        model.updateFilteredTaskList(new TaskNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTaskList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the event at the given {@code targetIndex} in the
     * {@code model}'s sochedule.
     */
    public static void showEventAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Event event = model.getFilteredEventList().get(targetIndex.getZeroBased());
        final String[] splitName = event.getName().fullName.split("\\s+");
        model.updateFilteredEventList(new EventNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTaskList().size());
    }

}
