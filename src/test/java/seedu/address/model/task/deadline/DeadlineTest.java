package seedu.address.model.task.deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DeadlineBuilder;

public class DeadlineTest {

    private static final Deadline TEST_DEADLINE = new DeadlineBuilder().build();
    private static final Deadline DIFFERENT_DEADLINE = new DeadlineBuilder().withDescription("DIFFERENT").build();

    @Test
    public void constructor_null_throwsNullPointerException() {
        LocalDate validDate = LocalDate.of(2020, 1, 1);
        assertThrows(NullPointerException.class, () -> new Deadline(null, validDate));
        assertThrows(NullPointerException.class, () -> new Deadline("test", null));
        assertThrows(NullPointerException.class, () -> new Deadline(null, validDate, false));
        assertThrows(NullPointerException.class, () -> new Deadline("test", validDate, null));
        assertThrows(NullPointerException.class, () -> new Deadline("test", null, false));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        LocalDate validDate = LocalDate.of(2020, 1, 1);
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDescription, validDate));
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDescription, validDate, false));
        String invalidDescription2 = " ";
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDescription2, validDate));
        assertThrows(IllegalArgumentException.class, () -> new Deadline(invalidDescription2, validDate, false));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> Deadline.isValidDescription(null));

        // invalid description
        assertFalse(Deadline.isValidDescription("")); // empty string
        assertFalse(Deadline.isValidDescription(" ")); // spaces only

        // valid description
        assertTrue(Deadline.isValidDescription("Blk 456, Den Road, #01-355"));
        assertTrue(Deadline.isValidDescription("-")); // one character
        assertTrue(Deadline.isValidDescription("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA"));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Deadline tutorialCopy = new DeadlineBuilder(TEST_DEADLINE).build();
        assertEquals(TEST_DEADLINE, tutorialCopy);

        // same object -> returns true
        assertEquals(TEST_DEADLINE, TEST_DEADLINE);

        // null -> returns false
        assertNotEquals(TEST_DEADLINE, null);

        // different type -> returns false
        assertNotEquals(TEST_DEADLINE, 5);

        // different event -> returns false
        assertNotEquals(DIFFERENT_DEADLINE, TEST_DEADLINE);

        // different name -> returns false
        Deadline editedTutorial = new DeadlineBuilder(TEST_DEADLINE).withDescription("NOT TEST_DEADLINE").build();
        assertNotEquals(editedTutorial, TEST_DEADLINE);

        // different by date -> returns false
        editedTutorial = new DeadlineBuilder(TEST_DEADLINE)
                .withByDate(LocalDate.of(2019, 2, 21)).build();
        assertNotEquals(editedTutorial, TEST_DEADLINE);

    }

    @Test
    public void hashCode_success() {
        Deadline deadline1 = new DeadlineBuilder(TEST_DEADLINE).build();
        Deadline deadline2 = new DeadlineBuilder(DIFFERENT_DEADLINE).build();
        int hashcode1 = deadline1.hashCode();
        int hashcode2 = deadline2.hashCode();
        assertEquals(hashcode1, deadline1.hashCode());
        assertNotEquals(hashcode1, hashcode2);
    }
}
