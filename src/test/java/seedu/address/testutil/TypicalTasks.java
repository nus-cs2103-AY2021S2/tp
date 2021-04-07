package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_CATEGORY_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_CATEGORY_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_PRIORITY_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_PRIORITY_TASKTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TAG_DIFFICULT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TAG_IMPORTANT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Sochedule;
import seedu.address.model.task.Task;


/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task ASSIGNMENT = new TaskBuilder().withName("CS2105 Assignment")
            .withDeadline("2022-01-01").withCategories("SchoolWork").withPriority("1")
            .build();
    public static final Task LAB = new TaskBuilder().withName("CS2106 Lab")
            .withDeadline("2022-01-02").withCategories("SchoolWork").withPriority("3")
            .build();
    public static final Task BREAKFAST = new TaskBuilder().withName("Toast Bread")
            .withDeadline("2022-03-01").withCategories("Life").withPriority("7")
            .build();
    public static final Task SHOPPING = new TaskBuilder().withName("Buy Clothes")
            .withDeadline("2022-03-02").withCategories("Life").withPriority("8")
            .build();
    public static final Task REVISION = new TaskBuilder().withName("Revise CS2103")
            .withDeadline("2022-04-02").withCategories("Exam").withPriority("2")
            .build();
    public static final Task EXERCISE = new TaskBuilder().withName("Run 10km")
            .withDeadline("2022-02-02").withCategories("Exercise").withPriority("5")
            .build();

    // Manually added
    public static final Task GAME = new TaskBuilder().withName("Play Mario Kart")
            .withDeadline("2022-05-02").withCategories("Leisure").withPriority("8")
            .build();
    public static final Task LECTURE = new TaskBuilder().withName("Watch CS2103 Lecture")
            .withDeadline("2022-04-02").withCategories("SchoolWork").withPriority("2")
            .build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task TASKONE = new TaskBuilder().withName(VALID_TASK_NAME_TASKONE)
            .withDeadline(VALID_TASK_DEADLINE_TASKONE).withPriority(VALID_TASK_PRIORITY_TASKONE)
            .withCategories(VALID_TASK_CATEGORY_HOMEWORK).withTags(VALID_TASK_TAG_IMPORTANT).build();
    public static final Task TASKTWO = new TaskBuilder().withName(VALID_TASK_NAME_TASKTWO)
            .withDeadline(VALID_TASK_DEADLINE_TASKTWO).withPriority(VALID_TASK_PRIORITY_TASKTWO)
            .withCategories(VALID_TASK_CATEGORY_PROJECT)
            .withTags(VALID_TASK_TAG_IMPORTANT, VALID_TASK_TAG_DIFFICULT).build();
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

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ASSIGNMENT, LAB, BREAKFAST, SHOPPING, REVISION, EXERCISE));
    }
}
