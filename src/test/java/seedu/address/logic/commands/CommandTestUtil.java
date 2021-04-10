package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;

import java.time.LocalDate;
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
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // Task
    public static final String VALID_TASK_NAME_TASKONE = "Task One";
    public static final String VALID_TASK_NAME_TASKTWO = "Task Two";
    public static final String VALID_TASK_NAME_COMPLETED = "Completed Task";
    public static final String VALID_TASK_DEADLINE_TASKONE = LocalDate.now().plusMonths(3).toString();
    public static final String VALID_TASK_DEADLINE_TASKTWO = LocalDate.now().plusMonths(4).toString();
    public static final String VALID_TASK_PRIORITY_TASKONE = "5";
    public static final String VALID_TASK_PRIORITY_TASKTWO = "6";
    public static final String VALID_TASK_CATEGORY_HOMEWORK = "Homework";
    public static final String VALID_TASK_CATEGORY_PROJECT = "Project";
    public static final String VALID_TASK_TAG_IMPORTANT = "Important";
    public static final String VALID_TASK_TAG_DIFFICULT = "Difficult";
    public static final String INVALID_PAST_DEADLINE = "1999-01-01"; // not allowed in date
    // Task to input to parser
    public static final String NAME_DESC_TASKONE = " " + PREFIX_NAME + VALID_TASK_NAME_TASKONE;
    public static final String NAME_DESC_TASKTWO = " " + PREFIX_NAME + VALID_TASK_NAME_TASKTWO;
    public static final String DEADLINE_DESC_TASKONE = " " + PREFIX_DEADLINE + VALID_TASK_DEADLINE_TASKONE;
    public static final String DEADLINE_DESC_TASKTWO = " " + PREFIX_DEADLINE + VALID_TASK_DEADLINE_TASKTWO;
    public static final String PRIORITY_DESC_TASKONE = " " + PREFIX_PRIORITY + VALID_TASK_PRIORITY_TASKONE;
    public static final String PRIORITY_DESC_TASKTWO = " " + PREFIX_PRIORITY + VALID_TASK_PRIORITY_TASKTWO;
    public static final String CATEGORY_DESC_HOMEWORK = " " + PREFIX_CATEGORY + VALID_TASK_CATEGORY_HOMEWORK;
    public static final String CATEGORY_DESC_PROJECT = " " + PREFIX_CATEGORY + VALID_TASK_CATEGORY_PROJECT;
    public static final String TAG_DESC_IMPORTANT = " " + PREFIX_TAG + VALID_TASK_TAG_IMPORTANT;
    public static final String TAG_DESC_DIFFICULT = " " + PREFIX_TAG + VALID_TASK_TAG_DIFFICULT;
    // Event
    public static final String VALID_EVENT_NAME_EVENTONE = "Coding Interview";
    public static final String VALID_EVENT_NAME_EVENTTWO = "SoC FOP";
    public static final String VALID_EVENT_STARTDATE_EVENTONE = LocalDate.now().plusMonths(1).toString();
    public static final String VALID_EVENT_STARTDATE_EVENTTWO = LocalDate.now().plusMonths(2).toString();
    public static final String VALID_EVENT_STARTTIME_EVENTONE = "13:00";
    public static final String VALID_EVENT_STARTTIME_EVENTTWO = "07:00";
    public static final String VALID_EVENT_ENDDATE_EVENTONE = LocalDate.now().plusMonths(1).toString();
    public static final String VALID_EVENT_ENDDATE_EVENTTWO = LocalDate.now().plusMonths(2).plusDays(2).toString();
    public static final String VALID_EVENT_ENDTIME_EVENTONE = "15:00";
    public static final String VALID_EVENT_ENDTIME_EVENTTWO = "22:00";
    public static final String VALID_EVENT_CATEGORY_WORK = "Work";
    public static final String VALID_EVENT_CATEGORY_SCHOOL = "School";
    public static final String VALID_EVENT_TAG_FINAL = "Final";
    public static final String VALID_EVENT_TAG_FUN = "Fun";
    public static final String INVALID_EVENT_ENDDATE = LocalDate.now().minusMonths(1).toString();
    // Event to input to parser
    public static final String NAME_DESC_EVENTONE = " " + PREFIX_NAME + VALID_EVENT_NAME_EVENTONE;
    public static final String NAME_DESC_EVENTTWO = " " + PREFIX_NAME + VALID_EVENT_NAME_EVENTTWO;
    public static final String STARTDATE_DESC_EVENTONE = " " + PREFIX_STARTDATE + VALID_EVENT_STARTDATE_EVENTONE;
    public static final String STARTDATE_DESC_EVENTTWO = " " + PREFIX_STARTDATE + VALID_EVENT_STARTDATE_EVENTTWO;
    public static final String STARTTIME_DESC_EVENTONE = " " + PREFIX_STARTTIME + VALID_EVENT_STARTTIME_EVENTONE;
    public static final String STARTTIME_DESC_EVENTTWO = " " + PREFIX_STARTTIME + VALID_EVENT_STARTTIME_EVENTTWO;
    public static final String ENDDATE_DESC_EVENTONE = " " + PREFIX_ENDDATE + VALID_EVENT_ENDDATE_EVENTONE;
    public static final String ENDDATE_DESC_EVENTTWO = " " + PREFIX_ENDDATE + VALID_EVENT_ENDDATE_EVENTTWO;
    public static final String ENDTIME_DESC_EVENTONE = " " + PREFIX_ENDTIME + VALID_EVENT_ENDTIME_EVENTONE;
    public static final String ENDTIME_DESC_EVENTTWO = " " + PREFIX_ENDTIME + VALID_EVENT_ENDTIME_EVENTTWO;
    public static final String CATEGORY_DESC_WORK = " " + PREFIX_CATEGORY + VALID_EVENT_CATEGORY_WORK;
    public static final String CATEGORY_DESC_SCHOOL = " " + PREFIX_CATEGORY + VALID_EVENT_CATEGORY_SCHOOL;
    public static final String TAG_DESC_FINAL = " " + PREFIX_TAG + VALID_EVENT_TAG_FINAL;
    public static final String TAG_DESC_FUN = " " + PREFIX_TAG + VALID_EVENT_TAG_FUN;
    // Invalid inputs
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "Invalid&"; // '&' is not allowed in names
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE + "2&20-01-02"; // not allowed in date
    public static final String INVALID_DEADLINE_DESC_0229 = " " + PREFIX_DEADLINE + "2022-02-29"; // not allowed in date
    public static final String INVALID_DEADLINEPAST_DESC = " " + PREFIX_DEADLINE + "1998-01-02"; // not allowed in date
    public static final String INVALID_PRIORITY_DESC = " " + PREFIX_PRIORITY + "15"; // '15' is out of the bound
    public static final String INVALID_CATEGORY_DESC = " " + PREFIX_CATEGORY + "h@mework"; // not allowed in category
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "imp@rtant"; // not allowed in tag
    public static final String INVALID_STARTDATE_DESC = " " + PREFIX_STARTDATE + "2&20-01-02"; // not allowed in date
    public static final String INVALID_STARTDATE_DESC_0229 = " " + PREFIX_STARTDATE + "2022-02-29"; // not such date
    public static final String INVALID_STARTTIME_DESC = " " + PREFIX_STARTTIME + "10:"; // not allowed in time
    public static final String INVALID_ENDDATE_DESC = " " + PREFIX_ENDDATE + "199*-01-02"; // not allowed in date
    public static final String INVALID_ENDDATEPAST_DESC = " " + PREFIX_ENDDATE + "1998-01-02"; // not allowed in date
    public static final String INVALID_ENDTIME_DESC = " " + PREFIX_ENDTIME + "1@:05"; // not allowed in time
    // Dates
    public static final String NO_FREE_TIME_DATE = LocalDate.now().plusMonths(1).plusDays(15).toString();
    public static final String FREE_DATE = LocalDate.now().plusYears(3).plusMonths(2).plusDays(1).toString();
    public static final String EXAMPLE_DATE = LocalDate.now().plusYears(3).plusMonths(2).plusDays(2).toString();
    // Index lists
    public static final List<Index> INDEX_LIST_ONE = Arrays.asList(INDEX_FIRST_TASK);
    public static final List<Index> INDEX_LIST_TWO = Arrays.asList(INDEX_SECOND_TASK);
    public static final List<Index> INDEXES_LIST_ONE = Arrays.asList(INDEX_FIRST_TASK, INDEX_SECOND_TASK);
    public static final List<Index> INDEXES_LIST_TWO = Arrays.asList(INDEX_FOURTH_TASK, INDEX_THIRD_TASK);

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditTaskCommand.EditTaskDescriptor DESC_TASKONE;
    public static final EditTaskCommand.EditTaskDescriptor DESC_TASKTWO;

    static {
        DESC_TASKONE = new EditTaskDescriptorBuilder().withName(VALID_TASK_NAME_TASKONE)
                .withDeadline(VALID_TASK_DEADLINE_TASKONE).withPriority(VALID_TASK_PRIORITY_TASKONE)
                .withCategories(VALID_TASK_CATEGORY_HOMEWORK)
                .withTags(VALID_TASK_TAG_DIFFICULT).build();
        DESC_TASKTWO = new EditTaskDescriptorBuilder().withName(VALID_TASK_NAME_TASKTWO)
                .withDeadline(VALID_TASK_DEADLINE_TASKTWO).withPriority(VALID_TASK_PRIORITY_TASKTWO)
                .withCategories(VALID_TASK_CATEGORY_PROJECT)
                .withTags(VALID_TASK_TAG_IMPORTANT).build();
    }

    public static final EditEventCommand.EditEventDescriptor DESC_EVENTONE;
    public static final EditEventCommand.EditEventDescriptor DESC_EVENTTWO;

    static {
        DESC_EVENTONE = new EditEventDescriptorBuilder().withName(VALID_EVENT_NAME_EVENTONE)
                .withStartDate(VALID_EVENT_STARTDATE_EVENTONE).withStartTime(VALID_EVENT_STARTTIME_EVENTONE)
                .withEndDate(VALID_EVENT_ENDDATE_EVENTONE).withEndTime(VALID_EVENT_ENDTIME_EVENTONE)
                .withCategories(VALID_EVENT_CATEGORY_WORK)
                .withTags(VALID_EVENT_TAG_FINAL).build();
        DESC_EVENTTWO = new EditEventDescriptorBuilder().withName(VALID_EVENT_NAME_EVENTTWO)
                .withStartDate(VALID_EVENT_STARTDATE_EVENTTWO).withStartTime(VALID_EVENT_STARTTIME_EVENTTWO)
                .withEndDate(VALID_EVENT_ENDDATE_EVENTTWO).withEndTime(VALID_EVENT_ENDTIME_EVENTTWO)
                .withCategories(VALID_EVENT_CATEGORY_SCHOOL)
                .withTags(VALID_EVENT_TAG_FINAL).build();
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
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEventList().size());

        Event event = model.getFilteredEventList().get(targetIndex.getZeroBased());
        final String[] splitName = event.getName().fullName.split("\\s+");
        model.updateFilteredEventList(new EventNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredEventList().size());
    }

}
