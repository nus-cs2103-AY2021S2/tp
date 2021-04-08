package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.EARLIEST_DATE;
import static seedu.address.testutil.TypicalEvents.LAST_DATE;
import static seedu.address.testutil.TypicalTasks.getTypicalSochedule;
import static seedu.address.testutil.TypicalTasks.getTypicalTasks;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.EventFindSchedulePredicate;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskFindSchedulePredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTaskCommand}.
 */
public class FindScheduleCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSochedule(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalSochedule(), new UserPrefs());
    }

    @Test
    public void execute_validDate_multipleTasksAndNoEventsFound() {
        List<Task> expectedTaskList = getTypicalTasks();
        int expectedNumberOfTasks = expectedTaskList.size();

        String expectedMessage = FindScheduleCommand.MESSAGE_FIND_SCHEDULE_SUCCESS
                + String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, expectedNumberOfTasks)
                + "\n"
                + String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        TaskFindSchedulePredicate taskPredicate = new TaskFindSchedulePredicate(LAST_DATE);
        EventFindSchedulePredicate eventPredicate = new EventFindSchedulePredicate(LAST_DATE);

        FindScheduleCommand findScheduleCommand = new FindScheduleCommand(taskPredicate, eventPredicate);
        expectedModel.updateFilteredTaskList(taskPredicate);
        expectedModel.updateFilteredEventList(eventPredicate);

        assertCommandSuccess(findScheduleCommand, model, expectedMessage, expectedModel);
        assertEquals(expectedTaskList, model.getFilteredTaskList());
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_validDate_noTasksAndEventsFound() {
        String expectedMessage = FindScheduleCommand.MESSAGE_FIND_SCHEDULE_SUCCESS
                + String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, 0)
                + "\n"
                + String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        TaskFindSchedulePredicate taskPredicate = new TaskFindSchedulePredicate(EARLIEST_DATE);
        EventFindSchedulePredicate eventPredicate = new EventFindSchedulePredicate(EARLIEST_DATE);

        FindScheduleCommand findScheduleCommand = new FindScheduleCommand(taskPredicate, eventPredicate);
        expectedModel.updateFilteredTaskList(taskPredicate);
        expectedModel.updateFilteredEventList(eventPredicate);

        assertCommandSuccess(findScheduleCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void equals() {
        TaskFindSchedulePredicate taskPredicateOne = new TaskFindSchedulePredicate(LAST_DATE);
        EventFindSchedulePredicate eventPredicateOne = new EventFindSchedulePredicate(LAST_DATE);
        TaskFindSchedulePredicate taskPredicateTwo = new TaskFindSchedulePredicate(EARLIEST_DATE);
        EventFindSchedulePredicate eventPredicateTwo = new EventFindSchedulePredicate(EARLIEST_DATE);

        FindScheduleCommand findScheduleCommandOne = new FindScheduleCommand(taskPredicateOne, eventPredicateOne);
        FindScheduleCommand findScheduleCommandTwo = new FindScheduleCommand(taskPredicateTwo, eventPredicateTwo);

        // same object -> returns true
        assertTrue(findScheduleCommandOne.equals(findScheduleCommandOne));

        // same values -> returns true
        FindScheduleCommand findScheduleCommandOneCopy = new FindScheduleCommand(taskPredicateOne, eventPredicateOne);
        assertTrue(findScheduleCommandOne.equals(findScheduleCommandOneCopy));

        // different types -> returns false
        assertFalse(findScheduleCommandOne.equals(1));

        // null -> returns false
        assertFalse(findScheduleCommandOne.equals(null));

        // different event -> returns false
        assertFalse(findScheduleCommandOne.equals(findScheduleCommandTwo));
    }

}
