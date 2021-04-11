package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRINGSCHEDULE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRINGSCHEDULE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static seedu.address.model.tag.UniqueTagListTestUtil.VALID_TAG_FRIEND;
import static seedu.address.model.tag.UniqueTagListTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Planner;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task ALICE = new TaskBuilder().withTitle("Assignment 79")
            .withDescription("Build the next Google").withRecurringSchedule("[10/06/2021][Mon][biweekly]")
            .withStatus("not done").withDuration("12:30-13:30")
            .withTags("priorities").build();
    public static final Task BENSON = new TaskBuilder().withTitle("Build a gaming PC")
            .withDescription("buy: coffee, 3080, 40-inch monitor")
            .withDate("12/12/2021").withStatus("not done").withTags("findMoney", "priorities").build();
    public static final Task CARL = new TaskBuilder().withTitle("Countdown to finals")
            .withDuration("12:30-13:30").withRecurringSchedule("[10/06/2021][Wed][weekly]")
            .withDescription("aiken doeet").withStatus("done").build();
    public static final Task DANIEL = new TaskBuilder().withTitle("Demo our amazing product")
            .withDuration("12:30-13:30").withRecurringSchedule("[05/06/2021][Thu][biweekly]").withStatus("done")
            .withDescription("Number 1 for real").withTags("fact").build();
    public static final Task ELLE = new TaskBuilder().withTitle("Ensure that I eat lunch").withDate("20/10/2021")
            .withDescription("budget 3$").withStatus("done").build();
    public static final Task FIONA = new TaskBuilder().withTitle("Final project consult again")
            .withDuration("12:30-13:30").withDescription("at COM2").withStatus("done").build();
    public static final Task GEORGE = new TaskBuilder().withTitle("Get ready for 2101 presentation")
            .withRecurringSchedule("[18/06/2021][thu][weekly]")
            .withStatus("done").withDescription("remember to shave").build();

    // Manually added
    public static final Task HOON = new TaskBuilder().withTitle("Hoon Meier")
            .withDuration("12:30-13:30").withRecurringSchedule("[25/06/2021][Sat][biweekly]")
            .withDescription("little india").withStatus("done").build();
    public static final Task IDA = new TaskBuilder().withTitle("Ida Mueller").withDate("05/05/2021")
            .withDescription("chicago ave").withStatus("done").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task AMY = new TaskBuilder().withTitle(VALID_TITLE_AMY).withDate(VALID_DATE_AMY)
            .withDuration(VALID_DURATION_AMY).withRecurringSchedule(VALID_RECURRINGSCHEDULE_AMY)
            .withDescription(VALID_DESCRIPTION_AMY).withStatus(VALID_STATUS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new TaskBuilder().withTitle(VALID_TITLE_BOB).withDate(VALID_DATE_BOB)
            .withDuration(VALID_DURATION_BOB).withRecurringSchedule(VALID_RECURRINGSCHEDULE_BOB)
            .withDescription(VALID_DESCRIPTION_BOB).withStatus(VALID_STATUS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    public static final Task AMY_NO_DATE = new TaskBuilder().withTitle(VALID_TITLE_AMY)
            .withDuration(VALID_DURATION_AMY).withRecurringSchedule(VALID_RECURRINGSCHEDULE_AMY)
            .withDescription(VALID_DESCRIPTION_AMY).withStatus(VALID_STATUS_AMY).withTags(VALID_TAG_FRIEND).build();

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
            ab.addTagsIfAbsent(task.getTags());
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    /**
     * Tags that are in the list of typical tasks. Duplicate tags are included.
     * Resulting list is not in order.
     *
     * @return List of typical tags that are in the typical tasks.
     */
    public static List<Tag> getTypicalTags() {
        List<Tag> typicalTags = new ArrayList<>();
        for (Task task : getTypicalTasks()) {
            typicalTags.addAll(task.getTags());
        }
        return typicalTags;
    }
}
