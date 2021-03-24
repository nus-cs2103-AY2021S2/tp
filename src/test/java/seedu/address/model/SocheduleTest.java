package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_CATEGORY_WORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDDATE_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDTIME_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTDATE_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTTIME_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TAG_FINAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_TASKONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IMPORTANT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.DATE;
import static seedu.address.testutil.TypicalEvents.EVENTONE;
import static seedu.address.testutil.TypicalEvents.MEETING;
import static seedu.address.testutil.TypicalTasks.ASSIGNMENT;
import static seedu.address.testutil.TypicalTasks.LAB;
import static seedu.address.testutil.TypicalTasks.TASKONE;
import static seedu.address.testutil.TypicalTasks.getTypicalSochedule;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.TaskBuilder;

public class SocheduleTest {
    private final Sochedule sochedule = new Sochedule();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), sochedule.getEventList());
        assertEquals(Collections.emptyList(), sochedule.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sochedule.resetData(null));
        assertThrows(NullPointerException.class, () -> sochedule.resetTaskData(null));
        assertThrows(NullPointerException.class, () -> sochedule.resetEventData(null));
    }

    @Test
    public void resetData_withValidReadOnlySochedule_replacesData() {
        Sochedule newData = getTypicalSochedule();
        sochedule.resetData(newData);
        assertEquals(newData, sochedule);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task taskWithSameIdentity = new TaskBuilder(TASKONE)
                .withDeadline(VALID_DEADLINE_TASKONE).withPriority(VALID_PRIORITY_TASKONE)
                .withCategories(VALID_CATEGORY_HOMEWORK).withTags(VALID_TAG_IMPORTANT)
                .build();
        List<Task> newTasks = Arrays.asList(TASKONE, taskWithSameIdentity);

        // Two different events
        List<Event> newEvents = Arrays.asList(MEETING, DATE);
        SocheduleStub newData = new SocheduleStub(newTasks, newEvents);

        assertThrows(DuplicateTaskException.class, () -> sochedule.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateEvents_throwsDuplicateEventException() {
        // Two different events
        List<Task> newTasks = Arrays.asList(ASSIGNMENT, LAB);

        // Two events with the same identity fields
        Event eventWithSameIdentity = new EventBuilder().withName(VALID_EVENT_NAME_EVENTONE)
                .withStartDate(VALID_EVENT_STARTDATE_EVENTONE).withStartTime(VALID_EVENT_STARTTIME_EVENTONE)
                .withEndDate(VALID_EVENT_ENDDATE_EVENTONE).withEndTime(VALID_EVENT_ENDTIME_EVENTONE)
                .withTags(VALID_EVENT_TAG_FINAL).withCategories(VALID_EVENT_CATEGORY_WORK)
                .build();
        List<Event> newEvents = Arrays.asList(EVENTONE, eventWithSameIdentity);
        SocheduleStub newData = new SocheduleStub(newTasks, newEvents);

        assertThrows(DuplicateEventException.class, () -> sochedule.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sochedule.hasTask(null));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sochedule.hasEvent(null));
    }

    @Test
    public void hasTask_taskNotInSochedule_returnsFalse() {
        assertFalse(sochedule.hasTask(ASSIGNMENT));
    }

    @Test
    public void hasEvent_eventNotInSochedule_returnsFalse() {
        assertFalse(sochedule.hasEvent(MEETING));
    }

    @Test
    public void hasTask_taskInSochedule_returnsTrue() {
        sochedule.addTask(ASSIGNMENT);
        assertTrue(sochedule.hasTask(ASSIGNMENT));
    }

    @Test
    public void hasEvent_eventInSochedule_returnsTrue() {
        sochedule.addEvent(MEETING);
        assertTrue(sochedule.hasEvent(MEETING));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInSochedule_returnsTrue() {
        sochedule.addTask(TASKONE);
        Task taskWithSameIdentity = new TaskBuilder(TASKONE)
                .withDeadline(VALID_DEADLINE_TASKONE).withPriority(VALID_PRIORITY_TASKONE)
                .withCategories(VALID_CATEGORY_HOMEWORK).withTags(VALID_TAG_IMPORTANT)
                .build();
        assertTrue(sochedule.hasTask(taskWithSameIdentity));
    }

    @Test
    public void hasEvent_eventWithSameIdentityFieldsInSochedule_returnsTrue() {
        sochedule.addEvent(EVENTONE);
        Event eventWithSameIdentity = new EventBuilder().withName(VALID_EVENT_NAME_EVENTONE)
                .withStartDate(VALID_EVENT_STARTDATE_EVENTONE).withStartTime(VALID_EVENT_STARTTIME_EVENTONE)
                .withEndDate(VALID_EVENT_ENDDATE_EVENTONE).withEndTime(VALID_EVENT_ENDTIME_EVENTONE)
                .withTags(VALID_EVENT_TAG_FINAL).withCategories(VALID_EVENT_CATEGORY_WORK)
                .build();
        assertTrue(sochedule.hasEvent(eventWithSameIdentity));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> sochedule.getTaskList().remove(0));
    }

    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> sochedule.getEventList().remove(0));
    }

    /**
     * A stub ReadOnlySochedule whose tasks list and event list can violate interface constraints.
     */
    private static class SocheduleStub implements ReadOnlySochedule {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();
        private final ObservableList<Event> events = FXCollections.observableArrayList();

        SocheduleStub(Collection<Task> tasks, Collection<Event> events) {
            this.tasks.setAll(tasks);
            this.events.setAll(events);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }

        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }
    }
}
