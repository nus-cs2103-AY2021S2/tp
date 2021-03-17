package seedu.module.testutil;

import static seedu.module.logic.commands.CommandTestUtil.VALID_DEADLINE_LAB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DEADLINE_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DESCRIPTION_LAB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_MODULE_LAB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_MODULE_PRACTICAL;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_PRIORITY_HIGH;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_PRIORITY_LOW;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TASK_NAME_LAB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TASK_NAME_PRACTICAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.module.model.ModuleBook;
import seedu.module.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final String NOT_DONE_STRING = String.valueOf(Boolean.FALSE);

    public static final Task QUIZ = new TaskBuilder().withName("Quiz")
            .withDescription("About Artificial Intelligence.").withModule("CS3243")
            .withDeadline("2021-02-01 12:00").withDoneStatus(NOT_DONE_STRING)
            .withTags("medianPriority").build();
    public static final Task MIDTERM = new TaskBuilder().withName("Midterm")
            .withDescription("Not include CSP.")
            .withModule("CS3243").withDeadline("2021-03-06 08:30").withDoneStatus(NOT_DONE_STRING)
            .withTags("highPriority").build();
    public static final Task TP = new TaskBuilder().withName("TP")
            .withDeadline("2021-03-14 14:00")
            .withModule("CS2103T").withDescription("Wrap up v1.2.").withDoneStatus(NOT_DONE_STRING).build();
    public static final Task TUTORIAL = new TaskBuilder().withName("Tutorial")
            .withDeadline("2021-03-02 23:59")
            .withModule("ST2131").withDoneStatus(NOT_DONE_STRING).withDescription("Try to get full mark.")
            .withTags("easy").build();
    public static final Task PROJECT = new TaskBuilder().withName("Project")
            .withDeadline("2021-02-28 23:59").withDoneStatus(NOT_DONE_STRING)
            .withModule("CS3243").withDescription("This is really challenging.").build();
    public static final Task PAQ = new TaskBuilder().withName("PAQ")
            .withDeadline("2021-03-05 23:59").withDoneStatus(NOT_DONE_STRING)
            .withModule("IS1103").withDescription("Strange questions.").build();
    public static final Task OP = new TaskBuilder().withName("OP 2")
            .withDeadline("2021-04-02 23:59").withDoneStatus(NOT_DONE_STRING)
            .withModule("CS2101").withDescription("Need to divide ourselves into two groups.").build();
    public static final Task REVIEW = new TaskBuilder().withName("Review").withModule("CS3244")
            .withDescription("ML FTW").withDeadline("2021-03-02").build();

    // Manually added
    public static final Task MISSION = new TaskBuilder().withName("Mission")
            .withDeadline("2021-03-05 23:59").withDoneStatus(NOT_DONE_STRING)
            .withModule("IS1103").withDescription("Strange questions.").build();
    public static final Task FINAL = new TaskBuilder().withName("Final")
            .withDeadline("2021-04-29 23:59").withDoneStatus(NOT_DONE_STRING)
            .withModule("ST2131").withDescription("Let's go!").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task LAB = new TaskBuilder()
            .withName(VALID_TASK_NAME_LAB)
            .withDeadline(VALID_DEADLINE_LAB)
            .withModule(VALID_MODULE_LAB)
            .withDescription(VALID_DESCRIPTION_LAB)
            .withTags(VALID_TAG_PRIORITY_LOW).build();
    public static final Task PRACTICAL = new TaskBuilder()
            .withName(VALID_TASK_NAME_PRACTICAL)
            .withDeadline(VALID_DEADLINE_PRACTICAL)
            .withModule(VALID_MODULE_PRACTICAL)
            .withDescription(VALID_DESCRIPTION_PRACTICAL)
            .withTags(VALID_TAG_PRIORITY_HIGH, VALID_TAG_PRIORITY_LOW)
            .build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns an {@code ModuleBook} with all the typical tasks.
     */
    public static ModuleBook getTypicalModuleBook() {
        ModuleBook ab = new ModuleBook();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    /**
     * Returns an {@code ModuleBook} with all the typical tasks.
     */
    public static ModuleBook getSortedTypicalModuleBook() {
        ModuleBook ab = new ModuleBook();
        ab.addTask(QUIZ); // 02-01
        ab.addTask(PROJECT); // 02-28
        ab.addTask(TUTORIAL); // 03-02
        ab.addTask(PAQ); // 03-05
        ab.addTask(MIDTERM); // 03-06
        ab.addTask(TP); // 03-14
        ab.addTask(OP); // 04-02
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(QUIZ, MIDTERM, TP, TUTORIAL, PROJECT, PAQ, OP));
    }
}
