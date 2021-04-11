package seedu.address.ui.testutil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.ui.EventCard.MESSAGE_EVENT_NON_REPEATABLE;
import static seedu.address.ui.EventCard.MESSAGE_EVENT_REPEATABLE;

import guitests.guihandles.ContactCardHandle;
import guitests.guihandles.DeadlineCardHandle;
import guitests.guihandles.EventCardHandle;
import guitests.guihandles.ProjectCardHandle;
import guitests.guihandles.TodayDeadlineCardHandle;
import guitests.guihandles.TodayEventCardHandle;
import guitests.guihandles.TodoCardHandle;
import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.TimeUtil;
import seedu.address.model.contact.Contact;
import seedu.address.model.project.Project;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.CompletableTodo;
import seedu.address.model.task.deadline.DeadlineWithProject;
import seedu.address.model.task.repeatable.Event;
import seedu.address.model.task.repeatable.EventWithProject;

/**
 * @@author {se-edu}-reused
 * Reused with modification from AB4 https://github.com/se-edu/addressbook-level4/
 *
 * A set of assertion methods useful for writing GUI tests.
 */
public class GuiTestAssert {

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedContact}.
     */
    public static void assertCardDisplaysContact(Contact expectedContact, ContactCardHandle actualCard) {
        actualCard.equals(expectedContact);
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedDeadline}.
     */
    public static void assertCardDisplaysCompletableDeadline(
            CompletableDeadline expectedDeadline, DeadlineCardHandle actualCard) {
        assertTrue(actualCard.equals(expectedDeadline));
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedDeadline}.
     */
    public static void assertCardDisplaysTodayDeadline(
            DeadlineWithProject expectedDeadline, TodayDeadlineCardHandle actualCard) {
        assertTrue(actualCard.equals(expectedDeadline));
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedCompletableTodo}.
     */
    public static void assertCardDisplaysCompletableTodo(
            CompletableTodo expectedTodo, TodoCardHandle actualCard) {
        assertTrue(actualCard.equals(expectedTodo));
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedEvent}.
     */
    public static void assertCardDisplaysEvent(Event expectedEvent, EventCardHandle actualCard) {
        assertEquals(expectedEvent.getDescription(), actualCard.getDescription());
        if (expectedEvent.getIsWeekly()) {
            assertEquals(String.format(MESSAGE_EVENT_REPEATABLE,
                    DateUtil.decodeDateIntoDay(expectedEvent.getDate()),
                    DateUtil.decodeDate(expectedEvent.getDate()),
                    TimeUtil.decodeTime(expectedEvent.getTime())),
                    actualCard.getDateTime());
        } else {
            assertEquals(String.format(MESSAGE_EVENT_NON_REPEATABLE,
                    DateUtil.decodeDateIntoDay(expectedEvent.getDate()),
                    DateUtil.decodeDate(expectedEvent.getDate()),
                    TimeUtil.decodeTime(expectedEvent.getTime())),
                    actualCard.getDateTime());
        }
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedEvent}.
     */
    public static void assertCardDisplaysTodayEvent(EventWithProject expectedEvent, TodayEventCardHandle actualCard) {
        assertEquals(expectedEvent.getDescription(), actualCard.getDescription());
        assertEquals(expectedEvent.getProjectName().toString(), actualCard.getProjectName());
        if (expectedEvent.getIsWeekly()) {
            assertEquals(String.format(MESSAGE_EVENT_REPEATABLE,
                    DateUtil.decodeDateIntoDay(expectedEvent.getDate()),
                    DateUtil.decodeDate(expectedEvent.getDate()),
                    TimeUtil.decodeTime(expectedEvent.getTime())),
                    actualCard.getDateTime());
        } else {
            assertEquals(String.format(MESSAGE_EVENT_NON_REPEATABLE,
                    DateUtil.decodeDateIntoDay(expectedEvent.getDate()),
                    DateUtil.decodeDate(expectedEvent.getDate()),
                    TimeUtil.decodeTime(expectedEvent.getTime())),
                    actualCard.getDateTime());
        }
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedProject}.
     */
    public static void assertCardDisplaysProject(Project expectedProject, ProjectCardHandle actualCard) {
        assertEquals(expectedProject.getProjectName().toString(), actualCard.getName());
    }
}
