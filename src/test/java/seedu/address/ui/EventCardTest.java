package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.DateUtil.encodeDate;
import static seedu.address.commons.util.TimeUtil.encodeTime;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysEvent;

import org.junit.jupiter.api.Test;

import guitests.guihandles.EventCardHandle;
import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.commons.exceptions.TimeConversionException;
import seedu.address.model.task.repeatable.Event;
import seedu.address.testutil.EventBuilder;

/**
 * Contains tests for the {@code EventCard}.
 */
public class EventCardTest extends GuiUnitTest {

    @Test
    public void display_success() throws DateConversionException, TimeConversionException {
        // event has no weekly repetition
        Event eventNoWeekly = new EventBuilder().withDescription("Display Test")
                .withDate(encodeDate("12-12-2021")).withTime(encodeTime("1000")).withIsWeekly(false).build();
        EventCard eventCard = new EventCard(eventNoWeekly, 1);
        uiPartExtension.setUiPart(eventCard);
        assertCardDisplay(eventCard, eventNoWeekly, 1);

        // event has weekly repetition
        Event eventHasWeekly = new EventBuilder().withDescription("Display Test")
                .withDate(encodeDate("12-12-2021")).withTime(encodeTime("1000")).withIsWeekly(true).build();
        eventCard = new EventCard(eventHasWeekly, 2);
        uiPartExtension.setUiPart(eventCard);
        assertCardDisplay(eventCard, eventHasWeekly, 2);
    }

    @Test
    public void equals() {
        Event event = new EventBuilder().build();
        EventCard eventCard = new EventCard(event, 0);

        // same event, same index -> returns true
        EventCard copy = new EventCard(event, 0);
        assertTrue(eventCard.equals(copy));

        // same object -> returns true
        assertTrue(eventCard.equals(eventCard));

        // null -> returns false
        assertFalse(eventCard.equals(null));

        // different types -> returns false
        assertFalse(eventCard.equals(0));

        // different event, same index -> returns false
        Event differentEvent = new EventBuilder().withDescription("differentDescription").build();
        assertFalse(eventCard.equals(new EventCard(differentEvent, 0)));

        // same event, different index -> returns false
        assertFalse(eventCard.equals(new EventCard(event, 1)));
    }

    /**
     * Asserts that {@code eventCard} displays the details of {@code expectedEvent}
     * correctly and matches {@code expectedId}.
     */
    private void assertCardDisplay(EventCard eventCard, Event expectedEvent, int expectedId) {
        guiRobot.pauseForHuman();

        EventCardHandle eventCardHandle = new EventCardHandle(eventCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", eventCardHandle.getId());

        // verify event details are displayed correctly
        assertCardDisplaysEvent(expectedEvent, eventCardHandle);
    }
}
