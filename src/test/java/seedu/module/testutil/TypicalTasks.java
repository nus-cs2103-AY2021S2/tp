package seedu.module.testutil;

import static seedu.module.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.module.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_MODULE_AMY;
import static seedu.module.logic.commands.CommandTestUtil.VALID_MODULE_BOB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.module.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DEADLINE_AMY;
import static seedu.module.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
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

    public static final Task ALICE = new TaskBuilder().withName("Alice Pauline")
            .withDescription("123, Jurong West Ave 6, #08-111").withModule("alice@example.com")
            .withDeadline("94351253")
            .withTags("friends").build();
    public static final Task BENSON = new TaskBuilder().withName("Benson Meier")
            .withDescription("311, Clementi Ave 2, #02-25")
            .withModule("johnd@example.com").withDeadline("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Task CARL = new TaskBuilder().withName("Carl Kurz").withDeadline("95352563")
            .withModule("heinz@example.com").withDescription("wall street").build();
    public static final Task DANIEL = new TaskBuilder().withName("Daniel Meier").withDeadline("87652533")
            .withModule("cornelia@example.com").withDescription("10th street").withTags("friends").build();
    public static final Task ELLE = new TaskBuilder().withName("Elle Meyer").withDeadline("9482224")
            .withModule("werner@example.com").withDescription("michegan ave").build();
    public static final Task FIONA = new TaskBuilder().withName("Fiona Kunz").withDeadline("9482427")
            .withModule("lydia@example.com").withDescription("little tokyo").build();
    public static final Task GEORGE = new TaskBuilder().withName("George Best").withDeadline("9482442")
            .withModule("anna@example.com").withDescription("4th street").build();

    // Manually added
    public static final Task HOON = new TaskBuilder().withName("Hoon Meier").withDeadline("8482424")
            .withModule("stefan@example.com").withDescription("little india").build();
    public static final Task IDA = new TaskBuilder().withName("Ida Mueller").withDeadline("8482131")
            .withModule("hans@example.com").withDescription("chicago ave").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder().withName(VALID_NAME_AMY).withDeadline(VALID_DEADLINE_AMY)
            .withModule(VALID_MODULE_AMY).withDescription(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new TaskBuilder().withName(VALID_NAME_BOB).withDeadline(VALID_DEADLINE_BOB)
            .withModule(VALID_MODULE_BOB).withDescription(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
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
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
