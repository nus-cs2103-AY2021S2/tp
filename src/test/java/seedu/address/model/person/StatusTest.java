package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void toStringTest() {
        Status toTest = new Status();
        Status toTestDone = new Status("Finished");
        Status toTestUndone = new Status("Unfinished");

        //Different Strings
        assertFalse(toTest.toString().equals(null)); // null
        assertFalse(toTest.toString().equals("")); // empty string
        assertFalse(toTest.toString().equals("random")); // random string
        assertFalse(toTestDone.toString().equals(null)); // null
        assertFalse(toTestDone.toString().equals("")); // empty string
        assertFalse(toTestDone.toString().equals("random")); // random string
        assertFalse(toTestUndone.toString().equals(null)); // null
        assertFalse(toTestUndone.toString().equals("")); // empty string
        assertFalse(toTestUndone.toString().equals("random")); // random string

        //Equals to itself
        assertTrue(toTest.toString().equals(toTest.toString()));
        assertTrue(toTestDone.toString().equals(toTestDone.toString()));
        assertTrue(toTestUndone.toString().equals(toTestUndone.toString()));

        //Equals to other DeadlineDate with same date
        assertTrue(toTest.toString().equals(new Status().toString()));
        assertTrue(toTestDone.toString().equals(new Status("Finished").toString()));
        assertTrue(toTestUndone.toString().equals(new Status("Unfinished").toString()));
    }

    @Test
    public void equals() {
        Status toTest = new Status();

        //Different object
        assertFalse(toTest.equals(null)); // null
        assertFalse(toTest.equals(new Object())); // object
        assertFalse(toTest.equals("random")); // string
        assertFalse(toTest.equals(1)); // integer
        assertFalse(toTest.equals(new DeadlineDate("11-11-2022"))); // DeadlineDate

        //Different value
        assertFalse(toTest.equals(new Status("Finished"))); // Different hour

        //Equals to itself
        assertTrue(toTest.equals(toTest));

        //Equals to other DeadlineDate with same value
        assertTrue(toTest.equals(new Status()));
    }
}
