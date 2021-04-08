package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_CATEGORY_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_CATEGORY_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_COMPLETED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_PRIORITY_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_PRIORITY_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TAG_DIFFICULT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TAG_IMPORTANT;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Sochedule;
import seedu.address.model.task.Task;


/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final String TODAY_DATE = LocalDate.now().toString();

    public static final String ASSIGNMENT_DATE = LocalDate.now().plusMonths(1).toString();

    public static final String LAB_DATE = LocalDate.now().plusMonths(1).plusDays(2).toString();

    public static final String BREAKFAST_DATE = LocalDate.now().plusMonths(2).plusDays(2).toString();

    public static final String SHOPPING_DATE = LocalDate.now().plusMonths(3).plusDays(1).toString();

    public static final String REVISION_DATE = LocalDate.now().plusMonths(3).plusDays(2).toString();

    public static final String EXERCISE_DATE = LocalDate.now().plusMonths(2).plusDays(1).toString();

    public static final String GAME_DATE = LocalDate.now().plusMonths(5).plusDays(2).toString();

    public static final String LECTURE_DATE = LocalDate.now().plusMonths(4).plusDays(1).toString();

    public static final Task ASSIGNMENT = new TaskBuilder().withName("CS2105 Assignment")
            .withDeadline(ASSIGNMENT_DATE).withCategories("SchoolWork").withPriority("1")
            .build();

    public static final Task LAB = new TaskBuilder().withName("CS2106 Lab")
            .withDeadline(LAB_DATE).withCategories("SchoolWork").withPriority("3")
            .build();

    public static final Task BREAKFAST = new TaskBuilder().withName("Toast Bread")
            .withDeadline(BREAKFAST_DATE).withCategories("Life").withPriority("7")
            .build();

    public static final Task SHOPPING = new TaskBuilder().withName("Buy Clothes")
            .withDeadline(SHOPPING_DATE).withCategories("Life").withPriority("8")
            .build();

    public static final Task REVISION = new TaskBuilder().withName("Revise CS2103")
            .withDeadline(REVISION_DATE).withCategories("Exam").withPriority("2")
            .build();

    public static final Task EXERCISE = new TaskBuilder().withName("Run 10km")
            .withDeadline(EXERCISE_DATE).withCategories("Exercise").withPriority("5")
            .build();

    // Manually added
    public static final Task GAME = new TaskBuilder().withName("Play Mario Kart")
            .withDeadline(GAME_DATE).withCategories("Leisure").withPriority("8")
            .build();

    public static final Task LECTURE = new TaskBuilder().withName("Watch CS2103 Lecture")
            .withDeadline(LECTURE_DATE).withCategories("SchoolWork").withPriority("2")
            .build();

    // With deadline on today
    public static final Task DUE = new TaskBuilder().withName("Due Today")
            .withDeadline(TODAY_DATE).withCategories("Urgent").withPriority("9")
            .build();

    public static final Task ANOTHER_DUE = new TaskBuilder().withName("Also Due Today")
            .withDeadline(TODAY_DATE).withCategories("Urgent").withPriority("8")
            .build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task TASKONE = new TaskBuilder().withName(VALID_TASK_NAME_TASKONE)
            .withDeadline(VALID_TASK_DEADLINE_TASKONE).withPriority(VALID_TASK_PRIORITY_TASKONE)
            .withCategories(VALID_TASK_CATEGORY_HOMEWORK).withTags(VALID_TASK_TAG_IMPORTANT).build();

    public static final Task TASKTWO = new TaskBuilder().withName(VALID_TASK_NAME_TASKTWO)
            .withDeadline(VALID_TASK_DEADLINE_TASKTWO).withPriority(VALID_TASK_PRIORITY_TASKTWO)
            .withCategories(VALID_TASK_CATEGORY_PROJECT)
            .withTags(VALID_TASK_TAG_IMPORTANT, VALID_TASK_TAG_DIFFICULT).build();

    // Manually added - A sample completed Task that mimics TASK ONE but with a different task name.
    public static final Task COMPLETED_TASK = new TaskBuilder().withName(VALID_TASK_NAME_COMPLETED)
            .withDeadline(VALID_TASK_DEADLINE_TASKONE).withPriority(VALID_TASK_PRIORITY_TASKONE)
            .withCategories(VALID_TASK_CATEGORY_HOMEWORK).withTags(VALID_TASK_TAG_IMPORTANT)
            .buildCompletedTask();

    public static final String KEYWORD_MATCHING_ASSIGNMENT = "Assignment"; // A keyword that matches MEIER

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code Sochedule} with all the typical tasks.
     */
    public static Sochedule getTypicalSochedule() {
        Sochedule sochedule = new Sochedule();
        for (Task task : getTypicalTasks()) {
            sochedule.addTask(task);
        }
        return sochedule;
    }

    /**
     * Returns an {@Code Sochedule} with two additional typical tasks on today added.
     */
    public static Sochedule getTypicalSocheduleWithTodayTask() {
        Sochedule sochedule = new Sochedule();
        for (Task task : getTypicalTasks()) {
            sochedule.addTask(task);
        }
        sochedule.addTask(DUE);
        sochedule.addTask(ANOTHER_DUE);
        return sochedule;
    }

    /**
     * Returns an {@code Sochedule} with the first task marked as complete and also all the typical tasks.
     */
    public static Sochedule getTypicalSocheduleWithCompletedTask() {
        Sochedule sochedule = new Sochedule();
        sochedule.addTask(COMPLETED_TASK);
        for (Task task : getTypicalTasks()) {
            sochedule.addTask(task);
        }
        return sochedule;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ASSIGNMENT, LAB, BREAKFAST, SHOPPING, REVISION, EXERCISE));
    }
}
