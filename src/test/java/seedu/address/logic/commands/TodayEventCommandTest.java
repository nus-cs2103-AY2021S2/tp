package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.ANOTHER_HAPPENING;
import static seedu.address.testutil.TypicalEvents.HAPPENING;
import static seedu.address.testutil.TypicalEvents.getTypicalSochedule;
import static seedu.address.testutil.TypicalEvents.getTypicalSocheduleWithTodayEvent;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.EventCoversTodayPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for TodayEventCommand.
 */
public class TodayEventCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSochedule(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalSochedule(), new UserPrefs());
    }

    @Test
    public void execute_noEventToday_noEventFound() {
        String expectedMessage = String.format(TodayEventCommand.MESSAGE_TODAY_EVENT_SUCCESS
                + MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        EventCoversTodayPredicate predicate = new EventCoversTodayPredicate();
        TodayEventCommand command = new TodayEventCommand();
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredEventList());
    }

    @Test
    public void execute_multipleEventsToday_multipleEventsFound() {
        String expectedMessage = String.format(TodayEventCommand.MESSAGE_TODAY_EVENT_SUCCESS
                + MESSAGE_EVENTS_LISTED_OVERVIEW, 2);
        EventCoversTodayPredicate predicate = new EventCoversTodayPredicate();
        TodayEventCommand command = new TodayEventCommand();
        updateModelWithTodayEvent();
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HAPPENING, ANOTHER_HAPPENING), model.getFilteredEventList());
    }

    private void updateModelWithTodayEvent() {
        model = new ModelManager(getTypicalSocheduleWithTodayEvent(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalSocheduleWithTodayEvent(), new UserPrefs());
    }
}
