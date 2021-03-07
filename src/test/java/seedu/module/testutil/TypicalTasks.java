package seedu.module.testutil;

import static seedu.module.logic.commands.CommandTestUtil.VALID_DEADLINE_AMY;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_MODULE_AMY;
import static seedu.module.logic.commands.CommandTestUtil.VALID_MODULE_BOB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.module.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.module.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.module.model.ModuleBook;
import seedu.module.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task QUIZ = new TaskBuilder().withName("Quiz")
            .withDescription("About Artificial Intelligence.").withModule("CS3243")
            .withDeadline("2021-02-01 12:00")
            .withTags("medianPriority").build();
    public static final Task MIDTERM = new TaskBuilder().withName("Midterm")
            .withDescription("Not include CSP.")
            .withModule("CS3243").withDeadline("2021-03-06 08:30")
            .withTags("highPriority").build();
    public static final Task TP = new TaskBuilder().withName("TP")
            .withDeadline("2021-03-14 14:00")
            .withModule("CS2103T").withDescription("Wrap up v1.2.").build();
    public static final Task TUTORIAL = new TaskBuilder().withName("Tutorial")
            .withDeadline("2021-03-02 23:59")
            .withModule("ST2131").withDescription("Try to get full mark.").withTags("easy").build();
    public static final Task PROJECT = new TaskBuilder().withName("Project")
            .withDeadline("2021-02-28 23:59")
            .withModule("CS3243").withDescription("This is really challenging.").build();
    public static final Task PAQ = new TaskBuilder().withName("PAQ")
            .withDeadline("2021-03-05 23:59")
            .withModule("IS1103").withDescription("Strange questions.").build();
    public static final Task OP = new TaskBuilder().withName("OP 2")
            .withDeadline("2021-04-02 23:59")
            .withModule("CS2101").withDescription("Need to divide ourselves into two groups.").build();

    // Manually added
    public static final Task MISSION = new TaskBuilder().withName("Mission")
            .withDeadline("2021-03-05 23:59")
            .withModule("IS1103").withDescription("Strange questions.").build();
    public static final Task FINAL = new TaskBuilder().withName("Final")
            .withDeadline("2021-04-29 23:59")
            .withModule("ST2131").withDescription("Let's go!").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder().withName(VALID_NAME_AMY).withDeadline(VALID_DEADLINE_AMY)
            .withModule(VALID_MODULE_AMY).withDescription(VALID_DESCRIPTION_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new TaskBuilder().withName(VALID_NAME_BOB).withDeadline(VALID_DEADLINE_BOB)
            .withModule(VALID_MODULE_BOB).withDescription(VALID_DESCRIPTION_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

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

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(QUIZ, MIDTERM, TP, TUTORIAL, PROJECT, PAQ, OP));
    }
}
