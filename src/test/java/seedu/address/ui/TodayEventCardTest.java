package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.DateUtil.encodeDate;
import static seedu.address.commons.util.TimeUtil.encodeTime;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysTodayEvent;

import org.junit.jupiter.api.Test;

import guitests.guihandles.TodayEventCardHandle;
import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.commons.exceptions.TimeConversionException;
import seedu.address.model.project.ProjectName;
import seedu.address.model.task.repeatable.Event;
import seedu.address.model.task.repeatable.EventWithProject;
import seedu.address.testutil.EventBuilder;

/**
 * Contains tests for the {@code TodayEventCard}.
 */
public class TodayEventCardTest extends GuiUnitTest {

    @Test
    public void display_success() throws DateConversionException, TimeConversionException {
        // event has no weekly repetition
        Event eventNoWeekly = new EventBuilder().withDescription("Display Test")
                .withDate(encodeDate("12-12-2021")).withTime(encodeTime("1000")).withIsWeekly(false).build();
        EventWithProject eventWithProjectNoWeekly = new EventWithProject(eventNoWeekly, new ProjectName("project"));
        TodayEventCard eventCard = new TodayEventCard(eventWithProjectNoWeekly);
        uiPartExtension.setUiPart(eventCard);
        assertCardDisplay(eventCard, eventWithProjectNoWeekly);

        // event has weekly repetition
        Event eventHasWeekly = new EventBuilder().withDescription("Display Test")
                .withDate(encodeDate("12-12-2021")).withTime(encodeTime("1000")).withIsWeekly(true).build();
        EventWithProject eventWithProjectWeekly = new EventWithProject(eventHasWeekly, new ProjectName("project"));
        eventCard = new TodayEventCard(eventWithProjectWeekly);
        uiPartExtension.setUiPart(eventCard);
        assertCardDisplay(eventCard, eventWithProjectWeekly);
    }

    @Test
    public void equals() {
        Event event = new EventBuilder().build();
        EventWithProject eventWithProject = new EventWithProject(event, new ProjectName("project"));
        TodayEventCard eventCard = new TodayEventCard(eventWithProject);

        // same event, same index -> returns true
        TodayEventCard copy = new TodayEventCard(eventWithProject);
        assertTrue(eventCard.equals(copy));

        // same object -> returns true
        assertTrue(eventCard.equals(eventCard));

        // null -> returns false
        assertFalse(eventCard.equals(null));

        // different types -> returns false
        assertFalse(eventCard.equals(0));
    }

    /**
     * Asserts that {@code eventCard} displays the details of {@code expectedEvent}
     * correctly.
     */
    private void assertCardDisplay(TodayEventCard eventCard, EventWithProject expectedEvent) {
        guiRobot.pauseForHuman();

        TodayEventCardHandle eventCardHandle = new TodayEventCardHandle(eventCard.getRoot());

        // verify event details are displayed correctly
        assertCardDisplaysTodayEvent(expectedEvent, eventCardHandle);
    }
}
