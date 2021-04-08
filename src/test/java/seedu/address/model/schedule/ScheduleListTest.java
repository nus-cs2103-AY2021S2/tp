package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSchedules.MATHS_HOMEWORK_SCHEDULE;
import static seedu.address.testutil.TypicalSchedules.SCIENCE_HOMEWORK_SCHEDULE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.schedule.exceptions.DuplicateScheduleException;
import seedu.address.model.schedule.exceptions.ScheduleNotFoundException;

public class ScheduleListTest {

    private final ScheduleList scheduleList = new ScheduleList();

    @Test
    public void contains_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduleList.contains(null));
    }

    @Test
    public void contains_scheduleNotInList_returnsFalse() {
        assertFalse(scheduleList.contains(MATHS_HOMEWORK_SCHEDULE));
    }

    @Test
    public void contains_scheduleInList_returnsTrue() {
        scheduleList.add(MATHS_HOMEWORK_SCHEDULE);
        assertTrue(scheduleList.contains(MATHS_HOMEWORK_SCHEDULE));
    }

    @Test
    public void add_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduleList.add(null));
    }

    @Test
    public void add_duplicateSchedule_throwsDuplicateScheduleException() {
        scheduleList.add(MATHS_HOMEWORK_SCHEDULE);
        assertThrows(DuplicateScheduleException.class, () -> scheduleList.add(MATHS_HOMEWORK_SCHEDULE));
    }

    @Test
    public void setSchedule_nullTargetSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduleList.setSchedule(null, MATHS_HOMEWORK_SCHEDULE));
    }

    @Test
    public void setSchedule_nullEditedSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduleList.setSchedule(MATHS_HOMEWORK_SCHEDULE, null));
    }

    @Test
    public void setSchedule_targetScheduleNotInList_throwsScheduleNotFoundException() {
        assertThrows(ScheduleNotFoundException.class, () -> scheduleList.setSchedule(MATHS_HOMEWORK_SCHEDULE, MATHS_HOMEWORK_SCHEDULE));
    }

    @Test
    public void setSchedule_editedScheduleIsSameSchedule_success() {
        scheduleList.add(MATHS_HOMEWORK_SCHEDULE);
        scheduleList.setSchedule(MATHS_HOMEWORK_SCHEDULE, MATHS_HOMEWORK_SCHEDULE);
        ScheduleList expectedScheduleList = new ScheduleList();
        expectedScheduleList.add(MATHS_HOMEWORK_SCHEDULE);
        assertEquals(expectedScheduleList, scheduleList);
    }

    @Test
    public void setSchedule_editedScheduleIsDifferent_success() {
        scheduleList.add(MATHS_HOMEWORK_SCHEDULE);
        scheduleList.setSchedule(MATHS_HOMEWORK_SCHEDULE, SCIENCE_HOMEWORK_SCHEDULE);
        ScheduleList expectedScheduleList = new ScheduleList();
        expectedScheduleList.add(SCIENCE_HOMEWORK_SCHEDULE);
        assertEquals(expectedScheduleList, scheduleList);
    }

    @Test
    public void setSchedule_duplicateScheduleList_throwsDuplicateScheduleException() {
        scheduleList.add(MATHS_HOMEWORK_SCHEDULE);
        scheduleList.add(SCIENCE_HOMEWORK_SCHEDULE);
        assertThrows(DuplicateScheduleException.class, () -> scheduleList.setSchedule(MATHS_HOMEWORK_SCHEDULE, SCIENCE_HOMEWORK_SCHEDULE));
    }

    @Test
    public void remove_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduleList.remove(null));
    }

    @Test
    public void remove_scheduleDoesNotExist_throwsScheduleNotFoundException() {
        assertThrows(ScheduleNotFoundException.class, () -> scheduleList.remove(MATHS_HOMEWORK_SCHEDULE));
    }

    @Test
    public void remove_existingSchedule_removesSchedule() {
        scheduleList.add(MATHS_HOMEWORK_SCHEDULE);
        scheduleList.remove(MATHS_HOMEWORK_SCHEDULE);
        ScheduleList expectedScheduleList = new ScheduleList();
        assertEquals(expectedScheduleList, scheduleList);
    }

    @Test
    public void setSchedules_nullScheduleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduleList.setSchedules((ScheduleList) null));
    }

    @Test
    public void setSchedules_scheduleList_replacesOwnListWithProvidedScheduleList() {
        scheduleList.add(MATHS_HOMEWORK_SCHEDULE);
        ScheduleList expectedScheduleList = new ScheduleList();
        expectedScheduleList.add(SCIENCE_HOMEWORK_SCHEDULE);
        scheduleList.setSchedules(expectedScheduleList);
        assertEquals(expectedScheduleList, scheduleList);
    }

    @Test
    public void setSchedules_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduleList.setSchedules((List<Schedule>) null));
    }

    @Test
    public void setSchedules_list_replacesOwnListWithProvidedList() {
        scheduleList.add(MATHS_HOMEWORK_SCHEDULE);
        List<Schedule> anotherScheduleList = Collections.singletonList(SCIENCE_HOMEWORK_SCHEDULE);
        scheduleList.setSchedules(anotherScheduleList);
        ScheduleList expectedScheduleList = new ScheduleList();
        expectedScheduleList.add(SCIENCE_HOMEWORK_SCHEDULE);
        assertEquals(expectedScheduleList, scheduleList);
    }

    @Test
    public void setSchedules_listWithDuplicateSchedules_throwsDuplicateScheduleException() {
        List<Schedule> listWithDuplicateSchedules = Arrays.asList(MATHS_HOMEWORK_SCHEDULE, MATHS_HOMEWORK_SCHEDULE);
        assertThrows(DuplicateScheduleException.class, () -> scheduleList.setSchedules(listWithDuplicateSchedules));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> scheduleList.asUnmodifiableObservableList().remove(0));
    }
}
