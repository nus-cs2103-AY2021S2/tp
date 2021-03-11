package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
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

    public static final Task ALICE = new TaskBuilder().withName("Alice Pauline")
        .withCode("CS2103").withPhone("94351253")
        .withEmail("alice@example.com").withRemark("Likes skiing.")
        .withTags("friends").build();
    public static final Task BENSON = new TaskBuilder().withName("Benson Meier")
        .withCode("CS2040").withEmail("johnd@example.com")
        .withPhone("98765432").withRemark("Favourite pastime: Eating")
        .withTags("owesMoney", "friends").build();
    public static final Task CARL = new TaskBuilder().withName("Carl Kurz")
        .withCode("CS1010").withPhone("95352563")
        .withEmail("heinz@example.com").build();
    public static final Task DANIEL = new TaskBuilder().withName("Daniel Meier")
        .withCode("CS2030").withPhone("87652533")
        .withEmail("cornelia@example.com").withTags("friends").build();
    public static final Task ELLE = new TaskBuilder().withName("Elle Meyer")
        .withCode("CS3240").withPhone("9482224")
        .withEmail("werner@example.com").build();
    public static final Task FIONA = new TaskBuilder().withName("Fiona Kunz")
        .withCode("CS2103").withPhone("9482427")
        .withEmail("lydia@example.com").build();
    public static final Task GEORGE = new TaskBuilder().withName("George Best")
        .withCode("CS2010").withPhone("9482442")
        .withEmail("anna@example.com").build();

    // Manually added
    public static final Task HOON = new TaskBuilder().withName("Hoon Meier").withPhone("8482424")
        .withEmail("stefan@example.com").build();
    public static final Task IDA = new TaskBuilder().withName("Ida Mueller").withPhone("8482131")
        .withEmail("hans@example.com").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
        .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new TaskBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
        .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
