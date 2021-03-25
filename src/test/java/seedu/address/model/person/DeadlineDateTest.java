package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeadlineDate(null));
    }

    @Test
    public void constructor_invalidDeadlineDate_throwsIllegalArgumentException() {
        String invalidDeadlineDateLowerBound = "12-12-2019";
        String invalidDeadlineDateUpperBound = "01-01-2100";
        String invalidDeadlineDateLowerBoundSecond = "00-02-2020";
        String invalidDeadlineDateUpperBoundSecond = "32-12-2099";
        String invalidDeadlineDateLowerBoundThird = "12-00-2020";
        String invalidDeadlineDateUpperBoundThird = "01-13-2099";
        assertThrows(IllegalArgumentException.class, () ->
            new DeadlineDate(invalidDeadlineDateLowerBound));
        assertThrows(IllegalArgumentException.class, () ->
            new DeadlineDate(invalidDeadlineDateUpperBound));
        assertThrows(IllegalArgumentException.class, () ->
                new DeadlineDate(invalidDeadlineDateLowerBoundSecond));
        assertThrows(IllegalArgumentException.class, () ->
                new DeadlineDate(invalidDeadlineDateUpperBoundSecond));
        assertThrows(IllegalArgumentException.class, () ->
                new DeadlineDate(invalidDeadlineDateLowerBoundThird));
        assertThrows(IllegalArgumentException.class, () ->
                new DeadlineDate(invalidDeadlineDateUpperBoundThird));
    }

    @Test
    public void isValidDeadlineDate() {
        // null DeadlineDate
        assertThrows(NullPointerException.class, () -> DeadlineDate.isValidDeadlineDate(null));

        // invalid DeadlineDate
        assertFalse(DeadlineDate.isValidDeadlineDate("")); // empty string
        assertFalse(DeadlineDate.isValidDeadlineDate("12:22:2020")); // incorrect format MM:DD:YYYY
        assertFalse(DeadlineDate.isValidDeadlineDate("22:12:2020")); // incorrect format DD:MM:YYYY
        assertFalse(DeadlineDate.isValidDeadlineDate("2020:12:02")); // incorrect format YYYY:DD:MM
        assertFalse(DeadlineDate.isValidDeadlineDate("12/22/2020")); // incorrect format MM/DD/YYYY
        assertFalse(DeadlineDate.isValidDeadlineDate("22/12/2020")); // incorrect format DD/MM/YYYY
        assertFalse(DeadlineDate.isValidDeadlineDate("2020/12/02")); // incorrect format YYYY/DD/MM

        // valid DeadlineDate
        assertTrue(DeadlineDate.isValidDeadlineDate("11-11-2020")); // min year
        assertTrue(DeadlineDate.isValidDeadlineDate("11-01-2026")); // min month
        assertTrue(DeadlineDate.isValidDeadlineDate("01-10-2029")); // min date
        assertTrue(DeadlineDate.isValidDeadlineDate("12-02-2099")); // max year
        assertTrue(DeadlineDate.isValidDeadlineDate("01-12-2029")); // max month
        assertTrue(DeadlineDate.isValidDeadlineDate("31-10-2020")); // max day
        assertTrue(DeadlineDate.isValidDeadlineDate("29-02-2024")); // leap day
        assertTrue(DeadlineDate.isValidDeadlineDate("10-11-2021")); // typical value
        assertTrue(DeadlineDate.isValidDeadlineDate("25-09-2032")); // typical value
    }

    @Test
    public void toStringTest() {
        DeadlineDate toTest = new DeadlineDate("11-11-2020");

        //Different Strings
        assertFalse(toTest.toString().equals(null)); // null
        assertFalse(toTest.toString().equals("")); // empty string
        assertFalse(toTest.toString().equals("random")); // random string

        //Equals to itself
        assertTrue(toTest.toString().equals(toTest.toString()));

        //Equals to other DeadlineDate with same date
        assertTrue(toTest.toString().equals(new DeadlineDate("11-11-2020").toString()));
    }

    @Test
    public void equals() {
        DeadlineDate toTest = new DeadlineDate("11-11-2020");

        //Different object
        assertFalse(toTest.equals(null)); // null
        assertFalse(toTest.equals(new Object())); // object
        assertFalse(toTest.equals("random")); // string
        assertFalse(toTest.equals(1)); // integer
        assertFalse(toTest.equals(new DeadlineTime("11:11"))); // DeadlineTime

        //Different values
        assertFalse(toTest.equals(new DeadlineDate("10-11-2020"))); // Different date
        assertFalse(toTest.equals(new DeadlineDate("11-10-2020"))); // Different month
        assertFalse(toTest.equals(new DeadlineDate("11-11-2022"))); // Different year
        assertFalse(toTest.equals(new DeadlineDate("01-01-2022"))); // All different

        //Equals to itself
        assertTrue(toTest.equals(toTest));

        //Equals to other DeadlineDate with same date
        assertTrue(toTest.equals(new DeadlineDate("11-11-2020")));
    }
}
