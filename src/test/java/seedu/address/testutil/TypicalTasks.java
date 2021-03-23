package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskTracker;
import seedu.address.model.person.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task CS2103 = new TaskBuilder().withName("Week 10 Quiz")
        .withCode("CS2103").withDeadlineDate("10-10-2020").withDeadlineTime("10:10")
        .withStatus("Unfinished").withWeightage(0).withRemark("Being a software engineer is fun")
        .withTags("friends").build();
    public static final Task CS2040 = new TaskBuilder().withName("Take Home lab 1")
        .withCode("CS2040").withDeadlineDate("10-10-2020").withDeadlineTime("10:10")
        .withStatus("Unfinished").withWeightage(0)
        .withRemark("Favourite pastime: Kattis")
        .withTags("owesMoney", "friends").build();
    public static final Task CS1010E = new TaskBuilder().withName("Tutorial 6")
        .withCode("CS1010").withDeadlineDate("10-10-2020").withDeadlineTime("10:10")
        .withStatus("Unfinished").withWeightage(0)
        .withRemark("PE1").build();
    public static final Task CS2030 = new TaskBuilder().withName("Tutorial 5")
        .withCode("CS2030").withDeadlineDate("10-10-2020").withDeadlineTime("10:10")
        .withStatus("Unfinished").withWeightage(0)
        .withRemark("PE2")
        .withTags("friends").build();
    public static final Task CS3243 = new TaskBuilder().withName("Weekly Readings")
        .withCode("CS3243").withDeadlineDate("10-10-2020").withDeadlineTime("10:10")
        .withStatus("Unfinished").withWeightage(0)
        .build();
    public static final Task CS2100 = new TaskBuilder().withName("Write Tests")
        .withCode("CS2100").withDeadlineDate("10-10-2020").withDeadlineTime("10:10")
        .withStatus("Unfinished").withWeightage(0)
        .build();

    // Manually added
    public static final Task HOON = new TaskBuilder().withName("Hoon Meier")
        .build();
    public static final Task IDA = new TaskBuilder().withName("Ida Mueller")
        .build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder().withName(VALID_NAME_AMY)
        .withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new TaskBuilder().withName(VALID_NAME_BOB)
        .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTasks() {
    } // prevents instantiation

    /**
     * Returns an {@code TaskTracker} with all the typical tasks.
     */
    public static TaskTracker getTypicalTaskTracker() {
        TaskTracker ab = new TaskTracker();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(CS2103, CS2040, CS1010E, CS2030, CS3243, CS2100));
    }
}
