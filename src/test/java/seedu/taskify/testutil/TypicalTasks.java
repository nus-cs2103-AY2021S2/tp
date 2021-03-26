package seedu.taskify.testutil;

import static seedu.taskify.logic.commands.CommandTestUtil.VALID_DATE_CS2103T_IP;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_DATE_CS2103T_TP;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2103T_IP;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2103T_TP;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_NAME_CS2103T_IP;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_NAME_CS2103T_TP;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_TAG_CS2103T_TP;
import static seedu.taskify.logic.commands.CommandTestUtil.VALID_TAG_DEBUGGING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.taskify.model.Taskify;
import seedu.taskify.model.task.StatusType;
import seedu.taskify.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task TASK_1 = new TaskBuilder().withName("firstTask")
                 .withStatus(StatusType.NOT_DONE)
                 .withDescription("my first task").withDate("2014-09-13 01:30")
                 .withTags("friends").build();
    public static final Task TASK_2 = new TaskBuilder().withName("secondTask")
            .withStatus(StatusType.NOT_DONE).withDescription("my second task").withDate("2015-01-23 23:30")
                  .withTags("owesMoney", "friends").build();
    public static final Task TASK_3 = new TaskBuilder().withName("thirdTask").withDescription("my third task")
            .withStatus(StatusType.COMPLETED).withDate("1980-12-23 00:12").build();
    public static final Task TASK_4 = new TaskBuilder().withName("fourthTask").withDescription("my fourth task")
            .withStatus(StatusType.IN_PROGRESS).withDate("1995-11-09 15:46").withTags(
                          "friends").build();
    public static final Task TASK_5 = new TaskBuilder().withName("fifthTask").withDescription("my fifth task")
            .withStatus(StatusType.COMPLETED).withDate("1998-07-30 22:13").build();
    public static final Task TASK_6 = new TaskBuilder().withName("sixthTask").withDescription("my sixth task")
            .withStatus(StatusType.NOT_DONE).withDate("1998-07-30 22:13").build();
    public static final Task TASK_7 = new TaskBuilder().withName("seventhTask").withDescription("my seventh task")
            .withStatus(StatusType.IN_PROGRESS).withDate("1998-07-30 22:13").build();

    // Manually added
    public static final Task TASK_8 = new TaskBuilder().withName("eighthTask").withDescription("my eighth task")
            .withStatus(StatusType.NOT_DONE).withDate("1998-07-30 22:13").build();
    public static final Task TASK_9 = new TaskBuilder().withName("ninthTask").withDescription("my ninth task")
            .withStatus(StatusType.NOT_DONE).withDate("1998-07-30 22:13").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task CS2103T_IP = new TaskBuilder().withName(VALID_NAME_CS2103T_IP)
            .withDescription(VALID_DESCRIPTION_CS2103T_IP)
            .withStatus(StatusType.NOT_DONE).withDate(VALID_DATE_CS2103T_IP)
            .withTags(VALID_TAG_CS2103T_TP).build();
    public static final Task BOB = new TaskBuilder().withName(VALID_NAME_CS2103T_TP)
            .withDescription(VALID_DESCRIPTION_CS2103T_TP)
            .withStatus(StatusType.NOT_DONE).withDate(VALID_DATE_CS2103T_TP)
            .withTags(VALID_TAG_DEBUGGING, VALID_TAG_CS2103T_TP).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTasks() {
    } // prevents instantiation

    /**
     * Returns an {@code TaskifyParser} with all the typical tasks.
     */
    public static Taskify getTypicalAddressBook() {
        Taskify ab = new Taskify();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TASK_1, TASK_2, TASK_3, TASK_4, TASK_5, TASK_6, TASK_7));
    }
}
