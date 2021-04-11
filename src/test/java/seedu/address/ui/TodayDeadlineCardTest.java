package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.DateUtil.encodeDate;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysTodayDeadline;

import org.junit.jupiter.api.Test;

import guitests.guihandles.TodayDeadlineCardHandle;
import seedu.address.commons.exceptions.DateConversionException;
import seedu.address.model.project.ProjectName;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.deadline.DeadlineWithProject;
import seedu.address.testutil.DeadlineBuilder;

/**
 * Contains tests for the {@code TodayDeadlineCard}.
 */
public class TodayDeadlineCardTest extends GuiUnitTest {

    private static final boolean DONE = true;
    private static final boolean NOT_DONE = false;

    @Test
    public void display_success() throws DateConversionException {
        // deadline is done
        CompletableDeadline deadlineIsDone = new DeadlineBuilder().withDescription("Display Test")
                .withByDate(encodeDate("18-03-2021")).withIsDone(DONE).build();
        DeadlineWithProject todayDeadlineIsDone = new DeadlineWithProject(deadlineIsDone, new ProjectName("project"));
        TodayDeadlineCard deadlineCard = new TodayDeadlineCard(todayDeadlineIsDone);
        uiPartExtension.setUiPart(deadlineCard);
        assertCardDisplay(deadlineCard, todayDeadlineIsDone);

        // deadline is not done
        CompletableDeadline deadlineIsNotDone = new DeadlineBuilder().withDescription("Display Test")
                .withByDate(encodeDate("18-03-2021")).withIsDone(NOT_DONE).build();
        DeadlineWithProject todayDeadlineIsNotDone =
                new DeadlineWithProject(deadlineIsNotDone, new ProjectName("project"));
        deadlineCard = new TodayDeadlineCard(todayDeadlineIsNotDone);
        uiPartExtension.setUiPart(deadlineCard);
        assertCardDisplay(deadlineCard, todayDeadlineIsNotDone);
    }

    @Test
    public void equals() {
        CompletableDeadline deadline = new DeadlineBuilder().build();
        DeadlineWithProject deadlineWithProject = new DeadlineWithProject(deadline, new ProjectName("project"));
        TodayDeadlineCard deadlineCard = new TodayDeadlineCard(deadlineWithProject);

        // same deadline, same index -> returns true
        TodayDeadlineCard copy = new TodayDeadlineCard(deadlineWithProject);
        assertTrue(deadlineCard.equals(copy));

        // same object -> returns true
        assertTrue(deadlineCard.equals(deadlineCard));

        // null -> returns false
        assertFalse(deadlineCard.equals(null));

        // different types -> returns false
        assertFalse(deadlineCard.equals(0));
    }

    /**
     * Asserts that {@code deadlineCard} displays the details of {@code expectedDeadline}
     * correctly.
     */
    private void assertCardDisplay(TodayDeadlineCard deadlineCard, DeadlineWithProject expectedDeadline) {
        guiRobot.pauseForHuman();

        TodayDeadlineCardHandle deadlineCardHandle =
                new TodayDeadlineCardHandle(deadlineCard.getRoot());

        // verify deadline details are displayed correctly
        assertCardDisplaysTodayDeadline(expectedDeadline, deadlineCardHandle);
    }
}
