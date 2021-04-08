package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSchedules.MATHS_HOMEWORK_SCHEDULE;
import static seedu.address.testutil.TypicalSchedules.getTypicalScheduleTracker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.schedule.exceptions.DuplicateScheduleException;

public class ScheduleTrackerTest {

    private final ScheduleTracker scheduleTracker = new ScheduleTracker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), scheduleTracker.getScheduleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduleTracker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        ScheduleTracker newData = getTypicalScheduleTracker();
        scheduleTracker.resetData(newData);
        assertEquals(newData, scheduleTracker);
    }

    @Test
    public void resetData_withDuplicateSchedules_throwsDuplicateScheduleException() {
        List<Schedule> newSchedules = Arrays.asList(MATHS_HOMEWORK_SCHEDULE, MATHS_HOMEWORK_SCHEDULE);
        ScheduleTrackerStub newData = new ScheduleTrackerStub(newSchedules);

        assertThrows(DuplicateScheduleException.class, () -> scheduleTracker.resetData(newData));
    }

    @Test
    public void hasSchedule_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduleTracker.hasSchedule(null));
    }

    @Test
    public void hasSchedule_scheduleNotInScheduleTracker_returnsFalse() {
        assertFalse(scheduleTracker.hasSchedule(MATHS_HOMEWORK_SCHEDULE));
    }

    @Test
    public void hasSchedule_scheduleInScheduleTracker_returnsTrue() {
        scheduleTracker.addSchedule(MATHS_HOMEWORK_SCHEDULE);
        assertTrue(scheduleTracker.hasSchedule(MATHS_HOMEWORK_SCHEDULE));
    }

    @Test
    public void getScheduleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> scheduleTracker.getScheduleList().remove(0));
    }

    /**
     * A stub ReadOnlyScheduleTracker whose schedule list can violate interface constraints.
     */
    private static class ScheduleTrackerStub implements ReadOnlyScheduleTracker {
        private final ObservableList<Schedule> schedules = FXCollections.observableArrayList();

        ScheduleTrackerStub(Collection<Schedule> schedules) {
            this.schedules.setAll(schedules);
        }

        @Override
        public ObservableList<Schedule> getScheduleList() {
            return schedules;
        }
    }

}
