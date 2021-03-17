package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.DateUtil.encodeDate;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysEvent;

import org.junit.jupiter.api.Test;

import guitests.guihandles.EventCardHandle;
import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.model.task.Interval;
import seedu.address.model.task.repeatable.Event;
import seedu.address.testutil.EventBuilder;

/**
 * Contains tests for the {@code EventCard}.
 */
public class EventCardTest extends GuiUnitTest {

    @Test
    public void display() throws DateConversionException {
        // event has no interval
        Event eventNoInterval = new EventBuilder().withDescription("Display Test")
                .withInterval(Interval.NONE).withAtDate(encodeDate("12-12-2021")).build();
        EventCard eventCard = new EventCard(eventNoInterval, 1);
        uiPartExtension.setUiPart(eventCard);
        assertCardDisplay(eventCard, eventNoInterval, 1);

        // event has interval
        Event eventHasInterval = new EventBuilder().withDescription("Display Test")
                .withInterval(Interval.WEEKLY).withAtDate(encodeDate("12-12-2021")).build();
        eventCard = new EventCard(eventHasInterval, 2);
        uiPartExtension.setUiPart(eventCard);
        assertCardDisplay(eventCard, eventHasInterval, 2);
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
     * Asserts that {@code completedEventCard} displays the details of {@code completedEvent}
     * correctly and matches {@code expectedId}.
     */
    private void assertCardDisplay(EventCard eventCard, Event expectedEvent, int expectedId) {
        guiRobot.pauseForHuman();

        EventCardHandle completableEventCardHandle = new EventCardHandle(eventCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", completableEventCardHandle.getId());

        // verify person details are displayed correctly
        assertCardDisplaysEvent(expectedEvent, completableEventCardHandle);
    }
}
