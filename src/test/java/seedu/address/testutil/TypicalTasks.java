package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTTIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTTIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Planner;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task ALICE = new TaskBuilder().withTitle("Assignment 79")
            .withDescription("Build the next Google").withEmail("alice@example.com")
            .withDeadline("94351253").withStatus("done").withStartTime("1230")
            .withTags("priorities").build();
    public static final Task BENSON = new TaskBuilder().withTitle("Build a gaming PC")
            .withDescription("buy: coffee, 3080, 40-inch monitor")
            .withEmail("johnd@example.com").withDeadline("98765432").withStartTime("1230")
            .withStatus("not done").withTags("findMoney", "priorities").build();
    public static final Task CARL = new TaskBuilder().withTitle("Countdown to finals").withDeadline("95352563")
            .withStartTime("1230").withEmail("heinz@example.com").withDescription("aiken doeet")
            .withStatus("done").build();
    public static final Task DANIEL = new TaskBuilder().withTitle("Demo our amazing product").withDeadline("87652533")
            .withStartTime("1230").withEmail("cornelia@example.com").withStatus("done")
            .withDescription("Number 1 for real").withTags("fact").build();
    public static final Task ELLE = new TaskBuilder().withTitle("Ensure that I eat lunch").withDeadline("9482224")
            .withStartTime("1230").withEmail("werner@example.com").withDescription("budget 3$")
            .withStatus("done").build();
    public static final Task FIONA = new TaskBuilder().withTitle("Final project consult again").withDeadline("9482427")
            .withStartTime("1230").withEmail("lydia@example.com").withDescription("at COM2")
            .withStatus("done").build();
    public static final Task GEORGE = new TaskBuilder().withTitle("Get ready for 2101 presentation")
            .withStartTime("1230").withDeadline("9482442").withEmail("anna@example.com").withStatus("done")
            .withDescription("remember to shave").build();

    // Manually added
    public static final Task HOON = new TaskBuilder().withTitle("Hoon Meier").withDeadline("8482424")
            .withStartTime("1230").withEmail("stefan@example.com").withDescription("little india")
            .withStatus("done").build();
    public static final Task IDA = new TaskBuilder().withTitle("Ida Mueller").withDeadline("8482131")
            .withStartTime("1230").withEmail("hans@example.com").withDescription("chicago ave")
            .withStatus("done").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder().withTitle(VALID_TITLE_AMY).withDeadline(VALID_DEADLINE_AMY)
            .withStartTime(VALID_STARTTIME_AMY).withEmail(VALID_EMAIL_AMY).withDescription(VALID_DESCRIPTION_AMY)
            .withStatus(VALID_STATUS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new TaskBuilder().withTitle(VALID_TITLE_BOB).withDeadline(VALID_DEADLINE_BOB)
            .withStartTime(VALID_STARTTIME_BOB).withEmail(VALID_EMAIL_BOB).withDescription(VALID_DESCRIPTION_BOB)
            .withStatus(VALID_STATUS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalTasks() {
    } // prevents instantiation

    /**
     * Returns an {@code Planner} with all the typical tasks.
     */
    public static Planner getTypicalPlanner() {
        Planner ab = new Planner();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
