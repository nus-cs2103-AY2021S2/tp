package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeadlineTime(null));
    }

    @Test
    public void constructor_invalidDeadlineTime_throwsIllegalArgumentException() {
        String invalidDeadlineTimeLowerBound = "23:60";
        String invalidDeadlineTimeUpperBound = "24:00";
        assertThrows(IllegalArgumentException.class, ()
            -> new DeadlineTime(invalidDeadlineTimeLowerBound));
        assertThrows(IllegalArgumentException.class, ()
            -> new DeadlineTime(invalidDeadlineTimeUpperBound));
    }

    @Test
    public void isValidDeadlineTime() {
        // null DeadlineTime
        assertThrows(NullPointerException.class, () -> DeadlineTime.isValidDeadlineTime(null));

        // invalid DeadlineTime
        assertFalse(DeadlineTime.isValidDeadlineTime("")); // empty string
        assertFalse(DeadlineTime.isValidDeadlineTime("12:20pm")); // incorrect format
        assertFalse(DeadlineTime.isValidDeadlineTime("12:20am")); // incorrect format
        assertFalse(DeadlineTime.isValidDeadlineTime("590")); // incorrect format

        // valid DeadlineTime
        assertTrue(DeadlineTime.isValidDeadlineTime("00:00")); // lowest hour
        assertTrue(DeadlineTime.isValidDeadlineTime("20:00")); // lowest min
        assertTrue(DeadlineTime.isValidDeadlineTime("23:22")); // highest hour
        assertTrue(DeadlineTime.isValidDeadlineTime("11:22")); // highest min
        assertTrue(DeadlineTime.isValidDeadlineTime("12:12")); // typical value
        assertTrue(DeadlineTime.isValidDeadlineTime("21:21")); // typical value
    }

    @Test
    public void toStringTest() {
        DeadlineTime toTest = new DeadlineTime("10:20");

        //Different Strings
        assertFalse(toTest.toString().equals(null)); // null
        assertFalse(toTest.toString().equals("")); // empty string
        assertFalse(toTest.toString().equals("random")); // random string

        //Equals to itself
        assertTrue(toTest.toString().equals(toTest.toString()));

        //Equals to other DeadlineDate with same date
        assertTrue(toTest.toString().equals(new DeadlineTime("10:20").toString()));
    }

    @Test
    public void equals() {
        DeadlineTime toTest = new DeadlineTime("11:20");

        //Different object
        assertFalse(toTest.equals(null)); // null
        assertFalse(toTest.equals(new Object())); // object
        assertFalse(toTest.equals("random")); // string
        assertFalse(toTest.equals(1)); // integer
        assertFalse(toTest.equals(new DeadlineDate("11-11-2022"))); // DeadlineDate

        //Different values
        assertFalse(toTest.equals(new DeadlineTime("10:20"))); // Different hour
        assertFalse(toTest.equals(new DeadlineTime("11:10"))); // Different min
        assertFalse(toTest.equals(new DeadlineTime("10:10"))); // All different

        //Equals to itself
        assertTrue(toTest.equals(toTest));

        //Equals to other DeadlineDate with same value
        assertTrue(toTest.equals(new DeadlineTime("11:20")));
    }
}
