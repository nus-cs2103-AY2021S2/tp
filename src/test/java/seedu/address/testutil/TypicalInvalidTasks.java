package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRINGSCHEDULE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRINGSCHEDULE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOB;

import seedu.address.model.task.Task;

/**
 * A utility class containing typical invalid Task object to be used for testing.
 */
public class TypicalInvalidTasks {

    public static final Task DEADLINE_WITH_DURATION_TASK = new TaskBuilder().withTitle(VALID_TITLE_AMY)
            .withDeadline(VALID_DEADLINE_AMY).withDuration(VALID_DURATION_AMY)
            .withDescription(VALID_DESCRIPTION_AMY).withStatus(VALID_STATUS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Task DEADLINE_WITH_RECURRING_SCHEDULE = new TaskBuilder().withTitle(VALID_TITLE_BOB)
            .withDeadline(VALID_DEADLINE_BOB).withRecurringSchedule(VALID_RECURRINGSCHEDULE_BOB)
            .withDescription(VALID_DESCRIPTION_BOB).withStatus(VALID_STATUS_BOB).withTags(VALID_TAG_FRIEND).build();
    public static final Task DEADLINE_WITH_RECURRING_SCHEDULE_AND_DURATION = new TaskBuilder()
            .withTitle(VALID_TITLE_AMY).withDeadline(VALID_DEADLINE_AMY)
            .withDuration(VALID_DURATION_AMY).withRecurringSchedule(VALID_RECURRINGSCHEDULE_AMY)
            .withDescription(VALID_DESCRIPTION_AMY).withStatus(VALID_STATUS_AMY).withTags(VALID_TAG_FRIEND).build();
}
