package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
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

    public static final Task CS2103 = new TaskBuilder().withName("Software Engineering")
        .withCode("CS2103").withWeightage(0).withPhone("94351253")
        .withRemark("Being a software engineer is fun")
        .withTags("friends").build();
    public static final Task CS2040 = new TaskBuilder().withName("Data Structures and Algorithm")
        .withCode("CS2040").withWeightage(0)
        .withPhone("98765432").withRemark("Favourite pastime: Kattis")
        .withTags("owesMoney", "friends").build();
    public static final Task CS1010E = new TaskBuilder().withName("Programming Methodology I")
        .withCode("CS1010").withWeightage(0).withPhone("95352563")
        .withRemark("PE1").build();
    public static final Task CS2030 = new TaskBuilder().withName("Programming Methodology II")
        .withCode("CS2030").withWeightage(0).withPhone("87652533")
        .withRemark("PE2")
        .withTags("friends").build();
    public static final Task CS3243 = new TaskBuilder().withName("Introduction to Artificial Intelligence")
        .withCode("CS3243").withWeightage(0).withPhone("9482224")
        .build();
    public static final Task CS2100 = new TaskBuilder().withName("Computer Organization")
        .withCode("CS2100").withWeightage(0).withPhone("9482442")
        .build();

    // Manually added
    public static final Task HOON = new TaskBuilder().withName("Hoon Meier").withPhone("8482424")
        .build();
    public static final Task IDA = new TaskBuilder().withName("Ida Mueller").withPhone("8482131")
        .build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
        .withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new TaskBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
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
