package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DATE_TIME_FROM_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DATE_TIME_TO_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_DESCRIPTION_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_TITLE_TWO;
import static seedu.address.testutil.TypicalSchedules.MATHS_HOMEWORK_SCHEDULE;
import static seedu.address.testutil.TypicalSchedules.SCIENCE_HOMEWORK_SCHEDULE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ScheduleBuilder;

public class ScheduleTest {

    @Test
    public void isSameSchedule() {
        // same object -> returns true
        assertTrue(MATHS_HOMEWORK_SCHEDULE.isSameSchedule(MATHS_HOMEWORK_SCHEDULE));

        // null -> returns false
        assertFalse(MATHS_HOMEWORK_SCHEDULE.isSameSchedule(null));

        // same title, all other attributes different -> returns true
        Schedule editedScheduleOne = new ScheduleBuilder(MATHS_HOMEWORK_SCHEDULE)
                .withTimeFrom(VALID_SCHEDULE_DATE_TIME_FROM_TWO).withTimeTo(VALID_SCHEDULE_DATE_TIME_TO_TWO)
                .withDescription(VALID_SCHEDULE_DESCRIPTION_TWO).build();
        assertTrue(MATHS_HOMEWORK_SCHEDULE.isSameSchedule(editedScheduleOne));

        // different title, all other attributes same -> returns false
        editedScheduleOne = new ScheduleBuilder(MATHS_HOMEWORK_SCHEDULE).withTitle(VALID_SCHEDULE_TITLE_TWO).build();
        assertFalse(MATHS_HOMEWORK_SCHEDULE.isSameSchedule(editedScheduleOne));

        // title differs in case, all other attributes same -> returns true
        Schedule editedScheduleTwo = new ScheduleBuilder(SCIENCE_HOMEWORK_SCHEDULE)
                .withTitle(VALID_SCHEDULE_TITLE_TWO.toLowerCase()).build();
        assertTrue(SCIENCE_HOMEWORK_SCHEDULE.isSameSchedule(editedScheduleTwo));

        // title has trailing spaces, all other attributes same -> returns false
        String titleWithTrailingSpaces = VALID_SCHEDULE_TITLE_TWO + " ";
        editedScheduleTwo = new ScheduleBuilder(SCIENCE_HOMEWORK_SCHEDULE).withTitle(titleWithTrailingSpaces).build();
        assertFalse(SCIENCE_HOMEWORK_SCHEDULE.isSameSchedule(editedScheduleTwo));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Schedule scheduleOneCopy = new ScheduleBuilder(MATHS_HOMEWORK_SCHEDULE).build();
        assertTrue(MATHS_HOMEWORK_SCHEDULE.equals(scheduleOneCopy));

        // same object -> returns true
        assertTrue(MATHS_HOMEWORK_SCHEDULE.equals(MATHS_HOMEWORK_SCHEDULE));

        // null -> returns false
        assertFalse(MATHS_HOMEWORK_SCHEDULE.equals(null));

        // different type -> returns false
        assertFalse(MATHS_HOMEWORK_SCHEDULE.equals(5));

        // different person -> returns false
        assertFalse(MATHS_HOMEWORK_SCHEDULE.equals(SCIENCE_HOMEWORK_SCHEDULE));

        // different name -> returns false
        Schedule editedScheduleOne = new ScheduleBuilder(MATHS_HOMEWORK_SCHEDULE)
                .withTitle(VALID_SCHEDULE_TITLE_TWO).build();
        assertFalse(MATHS_HOMEWORK_SCHEDULE.equals(editedScheduleOne));

        // different phone -> returns false
        editedScheduleOne = new ScheduleBuilder(MATHS_HOMEWORK_SCHEDULE)
                .withTimeFrom(VALID_SCHEDULE_DATE_TIME_FROM_TWO).build();
        assertFalse(MATHS_HOMEWORK_SCHEDULE.equals(editedScheduleOne));

        // different email -> returns false
        editedScheduleOne = new ScheduleBuilder(MATHS_HOMEWORK_SCHEDULE)
                .withTimeTo(VALID_SCHEDULE_DATE_TIME_TO_TWO).build();
        assertFalse(MATHS_HOMEWORK_SCHEDULE.equals(editedScheduleOne));

        // different address -> returns false
        editedScheduleOne = new ScheduleBuilder(MATHS_HOMEWORK_SCHEDULE)
                .withDescription(VALID_SCHEDULE_DESCRIPTION_TWO).build();
        assertFalse(MATHS_HOMEWORK_SCHEDULE.equals(editedScheduleOne));
    }
}
