package seedu.address.model.task.repeatable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.deadline.Deadline;
import seedu.address.testutil.EventBuilder;

public class EventTest {

    private static final Event TUTORIAL = new EventBuilder().withDescription("CS2106 Tutorial")
            .withDate(LocalDate.of(2020, 01, 01)).withTime(LocalTime.of(13, 00))
            .withIsWeekly(true).build();
    private static final Event LAB = new EventBuilder().withDescription("CS2030S Lab")
            .withDate(LocalDate.of(2021, 01, 03)).withTime(LocalTime.of(15, 00))
            .withIsWeekly(false).build();

    private String invalidDescription1 = "";
    private String invalidDescription2 = " ";

    @Test
    public void constructor_null_throwsNullPointerException() {
        LocalDate validDate = LocalDate.of(2020, 1, 1);
        LocalTime validTime = LocalTime.of(17, 30);
        Boolean validIsWeekly = false;

        assertThrows(NullPointerException.class, () -> new Event(null, validDate, validTime, validIsWeekly));
        assertThrows(NullPointerException.class, () -> new Event("test", null,
                validTime, validIsWeekly));
        assertThrows(NullPointerException.class, () -> new Event("test", validDate,
                null, validIsWeekly));
        assertThrows(NullPointerException.class, () -> new Event("test", validDate,
                validTime, null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        LocalDate validDate = LocalDate.of(2020, 1, 1);
        LocalTime validTime = LocalTime.of(17, 30);
        Boolean validIsWeekly = false;

        assertThrows(IllegalArgumentException.class, () -> new Event(invalidDescription1, validDate,
                validTime, validIsWeekly));

        assertThrows(IllegalArgumentException.class, () -> new Event(invalidDescription2, validDate,
                validTime, validIsWeekly));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Deadline.isValidDescription(null));

        // invalid description
        assertFalse(Deadline.isValidDescription(invalidDescription1)); // empty string
        assertFalse(Deadline.isValidDescription(invalidDescription2)); // spaces only

        // valid description
        assertTrue(Deadline.isValidDescription("Tutorial, CS2106, #01-355"));
        assertTrue(Deadline.isValidDescription("-")); // one character
        assertTrue(Deadline.isValidDescription("Lab; Com1; 123456; SINGAPORE"));
    }

    @Test
    public void getIsWeekly_success() {
        assertEquals(true, TUTORIAL.getIsWeekly());
        assertEquals(false, LAB.getIsWeekly());
    }

    @Test
    public void equals() {
        // same values -> returns true
        Event tutorialCopy = new EventBuilder(TUTORIAL).build();
        assertEquals(TUTORIAL, tutorialCopy);

        // same object -> returns true
        assertEquals(TUTORIAL, TUTORIAL);

        // null -> returns false
        assertNotEquals(TUTORIAL, null);

        // different type -> returns false
        assertNotEquals(TUTORIAL, 5);

        // different event -> returns false
        assertNotEquals(LAB, TUTORIAL);

        // different name -> returns false
        Event editedTutorial = new EventBuilder(TUTORIAL).withDescription("NOT TUTORIAL").build();
        assertNotEquals(editedTutorial, TUTORIAL);

        // different date -> returns false
        editedTutorial = new EventBuilder(TUTORIAL).withDate(LocalDate.of(2019, 01, 01)).build();
        assertNotEquals(editedTutorial, TUTORIAL);

        // different time -> returns false
        editedTutorial = new EventBuilder(TUTORIAL).withTime(LocalTime.of(19, 00)).build();
        assertNotEquals(editedTutorial, TUTORIAL);

        // different isWeekly -> returns false
        editedTutorial = new EventBuilder(TUTORIAL).withIsWeekly(false).build();
        assertNotEquals(editedTutorial, TUTORIAL);
    }

    @Test
    public void hashCode_success() {
        Event event1 = new EventBuilder(TUTORIAL).build();
        int hashcode1 = event1.hashCode();

        // invoked on the same object: _must_ be equal
        assertEquals(hashcode1, event1.hashCode());

        Event event2 = new EventBuilder(TUTORIAL).build();

        // objects are equal according to equals(): _must_ be equal
        assertEquals(hashcode1, event2.hashCode());

        Event event3 = new EventBuilder(LAB).build();
        int hashcode3 = event3.hashCode();

        // objects are unequal according to equals(): _should_ be distinct
        assertNotEquals(hashcode1, hashcode3);
    }
}
