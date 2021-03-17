package seedu.address.ui.testutil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.util.DateUtil.decodeDate;

import java.util.List;
import java.util.stream.Collectors;

import guitests.guihandles.CompletableDeadlineCardHandle;
import guitests.guihandles.CompletableTodoCardHandle;
import guitests.guihandles.PersonCardHandle;
import guitests.guihandles.PersonListPanelHandle;
import guitests.guihandles.ResultDisplayHandle;
import seedu.address.model.person.Person;
import seedu.address.model.task.CompletableDeadline;
import seedu.address.model.task.CompletableTodo;
import seedu.address.ui.CompletableDeadlineCard;

/**
 * A set of assertion methods useful for writing GUI tests.
 */
public class GuiTestAssert {
    /**
     * Asserts that {@code actualCard} displays the same values as {@code expectedCard}.
     */
    public static void assertCardEquals(PersonCardHandle expectedCard, PersonCardHandle actualCard) {
        assertEquals(expectedCard.getId(), actualCard.getId());
        assertEquals(expectedCard.getAddress(), actualCard.getAddress());
        assertEquals(expectedCard.getEmail(), actualCard.getEmail());
        assertEquals(expectedCard.getName(), actualCard.getName());
        assertEquals(expectedCard.getPhone(), actualCard.getPhone());
        assertEquals(expectedCard.getTags(), actualCard.getTags());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedPerson}.
     */
    public static void assertCardDisplaysPerson(Person expectedPerson, PersonCardHandle actualCard) {
        assertEquals(expectedPerson.getName().fullName, actualCard.getName());
        assertEquals(expectedPerson.getPhone().value, actualCard.getPhone());
        assertEquals(expectedPerson.getEmail().value, actualCard.getEmail());
        assertEquals(expectedPerson.getAddress().value, actualCard.getAddress());
        assertEquals(expectedPerson.getTags().stream().map(tag -> tag.tagName).sorted().collect(Collectors.toList()),
                actualCard.getTags());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedCompletableDeadline}.
     */
    public static void assertCardDisplaysCompletableDeadline(
            CompletableDeadline expectedDeadline, CompletableDeadlineCardHandle actualCard) {
        assertEquals(expectedDeadline.getDescription(), actualCard.getDescription());
        assertEquals(decodeDate(expectedDeadline.getBy()), actualCard.getDate());
        String expectedCompletedText = CompletableDeadlineCard
                .getTextToDisplay(expectedDeadline.getIsDone());
        assertEquals(expectedCompletedText, actualCard.getCompleted());
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedCompletableTodo}.
     */
    public static void assertCardDisplaysCompletableTodo(
            CompletableTodo expectedDeadline, CompletableTodoCardHandle actualCard) {
        assertEquals(expectedDeadline.getDescription(), actualCard.getDescription());
        String expectedCompletedText = CompletableDeadlineCard
                .getTextToDisplay(expectedDeadline.getIsDone());
        assertEquals(expectedCompletedText, actualCard.getCompleted());
    }

    /**
     * Asserts that the list in {@code personListPanelHandle} displays the details of {@code persons} correctly and
     * in the correct order.
     */
    public static void assertListMatching(PersonListPanelHandle personListPanelHandle, Person... persons) {
        for (int i = 0; i < persons.length; i++) {
            personListPanelHandle.navigateToCard(i);
            assertCardDisplaysPerson(persons[i], personListPanelHandle.getPersonCardHandle(i));
        }
    }

    /**
     * Asserts that the list in {@code personListPanelHandle} displays the details of {@code persons} correctly and
     * in the correct order.
     */
    public static void assertListMatching(PersonListPanelHandle personListPanelHandle, List<Person> persons) {
        assertListMatching(personListPanelHandle, persons.toArray(new Person[0]));
    }

    /**
     * Asserts the size of the list in {@code personListPanelHandle} equals to {@code size}.
     */
    public static void assertListSize(PersonListPanelHandle personListPanelHandle, int size) {
        int numberOfPeople = personListPanelHandle.getListSize();
        assertEquals(size, numberOfPeople);
    }

    /**
     * Asserts the message shown in {@code resultDisplayHandle} equals to {@code expected}.
     */
    public static void assertResultMessage(ResultDisplayHandle resultDisplayHandle, String expected) {
        assertEquals(expected, resultDisplayHandle.getText());
    }
}
