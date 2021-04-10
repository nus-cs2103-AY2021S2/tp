package seedu.address.model.schedule;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.schedule.TypicalSchedules.LONG_SCHEDULABLE;
import static seedu.address.model.schedule.TypicalSchedules.LONG_SCHEDULABLE_PART_ONE;
import static seedu.address.model.schedule.TypicalSchedules.LONG_SCHEDULABLE_PART_THREE;
import static seedu.address.model.schedule.TypicalSchedules.LONG_SCHEDULABLE_PART_TWO;
import static seedu.address.model.schedule.TypicalSchedules.SCHEDULE_THREE;
import static seedu.address.model.schedule.TypicalSchedules.SCHEDULE_TWO;
import static seedu.address.model.schedule.TypicalSchedules.SHORT_SCHEDULE_ONE;

public class SchedulableUtilTest {

    // if the schedule is within one day, there should be no splitting.
    @Test
    public void splitSchedulableByDay_lessThanOneDayLongSchedulable_success() {
        List<? extends Schedulable> splittedSchedules =
                SchedulableUtil.splitSchedulableByDay(SHORT_SCHEDULE_ONE);
        assertTrue(SchedulableTestUtil.checkEquals(splittedSchedules, List.of(SHORT_SCHEDULE_ONE)));
    }

    //Boundary Case Analysis: if the schedule is from 00:00 to 00:00 the next day, the resulting schedulable should be
    // 00:00 - 23:59.99999 on the same day.
    @Test
    public void splitSchedulableByDay_exactlylOneDayLongSchedulable_success() {
        List<? extends Schedulable> splittedSchedules =
                SchedulableUtil.splitSchedulableByDay(SCHEDULE_TWO);
        assertTrue(SchedulableTestUtil.checkEquals(splittedSchedules, List.of(SCHEDULE_THREE)));
    }

    //Boundary Case Analysis for the starrTime, as well as check that a schedulable is split
    //into multiple days.
    @Test
    public void splitScheduleByDay_multipleDays() {
        List<? extends Schedulable> splittedSchedules =
                SchedulableUtil.splitSchedulableByDay(LONG_SCHEDULABLE);
        assertTrue(SchedulableTestUtil.checkEquals(splittedSchedules, List.of(
                LONG_SCHEDULABLE_PART_ONE,
                LONG_SCHEDULABLE_PART_TWO,
                LONG_SCHEDULABLE_PART_THREE)));
    }


}
