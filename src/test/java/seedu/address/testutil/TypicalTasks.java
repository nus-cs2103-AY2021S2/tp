package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
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
            .withPhone("94351253")
            .withTags("priorities").build();
    public static final Task BENSON = new TaskBuilder().withTitle("Build a gaming PC")
            .withDescription("buy: coffee, 3080, 40-inch monitor")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("findMoney", "priorities").build();
    public static final Task CARL = new TaskBuilder().withTitle("Countdown to finals").withPhone("95352563")
            .withEmail("heinz@example.com").withDescription("aiken doeet").build();
    public static final Task DANIEL = new TaskBuilder().withTitle("Demo our amazing product").withPhone("87652533")
            .withEmail("cornelia@example.com").withDescription("Number 1 for real").withTags("fact").build();
    public static final Task ELLE = new TaskBuilder().withTitle("Ensure that I eat lunch").withPhone("9482224")
            .withEmail("werner@example.com").withDescription("budget 3$").build();
    public static final Task FIONA = new TaskBuilder().withTitle("Final project consult again").withPhone("9482427")
            .withEmail("lydia@example.com").withDescription("at COM2").build();
    public static final Task GEORGE = new TaskBuilder().withTitle("Get ready for 2101 presentation")
            .withPhone("9482442").withEmail("anna@example.com").withDescription("remember to shave").build();

    // Manually added
    public static final Task HOON = new TaskBuilder().withTitle("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withDescription("little india").build();
    public static final Task IDA = new TaskBuilder().withTitle("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withDescription("chicago ave").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder().withTitle(VALID_TITLE_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withDescription(VALID_DESCRIPTION_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new TaskBuilder().withTitle(VALID_TITLE_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withDescription(VALID_DESCRIPTION_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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
