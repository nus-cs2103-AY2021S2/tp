package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.DateUtil.encodeDate;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysCompletableDeadline;

import org.junit.jupiter.api.Test;

import guitests.guihandles.DeadlineCardHandle;
import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.testutil.DeadlineBuilder;

/**
 * Contains tests for the {@code DeadlineCard}.
 */
public class DeadlineCardTest extends GuiUnitTest {

    private static final boolean DONE = true;
    private static final boolean NOT_DONE = false;

    @Test
    public void display_success() throws DateConversionException {
        // deadline is done
        CompletableDeadline deadlineIsDone = new DeadlineBuilder().withDescription("Display Test")
                .withByDate(encodeDate("18-03-2021")).withIsDone(DONE).build();
        DeadlineCard deadlineCard = new DeadlineCard(deadlineIsDone, 1);
        uiPartExtension.setUiPart(deadlineCard);
        assertCardDisplay(deadlineCard, deadlineIsDone, 1);

        // deadline is not done
        CompletableDeadline deadlineIsNotDone = new DeadlineBuilder().withDescription("Display Test")
                .withByDate(encodeDate("18-03-2021")).withIsDone(NOT_DONE).build();
        deadlineCard = new DeadlineCard(deadlineIsNotDone, 2);
        uiPartExtension.setUiPart(deadlineCard);
        assertCardDisplay(deadlineCard, deadlineIsNotDone, 2);
    }

    @Test
    public void equals() {
        CompletableDeadline deadline = new DeadlineBuilder().build();
        DeadlineCard deadlineCard = new DeadlineCard(deadline, 0);

        // same deadline, same index -> returns true
        DeadlineCard copy = new DeadlineCard(deadline, 0);
        assertTrue(deadlineCard.equals(copy));

        // same object -> returns true
        assertTrue(deadlineCard.equals(deadlineCard));

        // null -> returns false
        assertFalse(deadlineCard.equals(null));

        // different types -> returns false
        assertFalse(deadlineCard.equals(0));

        // different deadline, same index -> returns false
        CompletableDeadline differentDeadline = new DeadlineBuilder().withDescription("differentDescription").build();
        assertFalse(deadlineCard.equals(new DeadlineCard(differentDeadline, 0)));

        // same deadline, different index -> returns false
        assertFalse(deadlineCard.equals(new DeadlineCard(deadline, 1)));
    }

    /**
     * Asserts that {@code deadlineCard} displays the details of {@code expectedDeadline}
     * correctly and matches {@code expectedId}.
     */
    private void assertCardDisplay(
            DeadlineCard deadlineCard, CompletableDeadline expectedDeadline, int expectedId) {
        guiRobot.pauseForHuman();

        DeadlineCardHandle deadlineCardHandle =
                new DeadlineCardHandle(deadlineCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", deadlineCardHandle.getId());

        // verify contact details are displayed correctly
        assertCardDisplaysCompletableDeadline(expectedDeadline, deadlineCardHandle);
    }
}
