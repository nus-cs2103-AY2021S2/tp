package seedu.cakecollate.model.orderitem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CostTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cost(null));
    }

    @Test
    public void constructor_invalidCost_throwsIllegalArgumentException() {
        String invalidCost = "";
        assertThrows(IllegalArgumentException.class, () -> new Cost(invalidCost));
    }

    @Test
    public void isValidCost() {
        // null cost
        assertThrows(NullPointerException.class, () -> Cost.isValidCost(null));

        // invalid cost
        assertFalse(Cost.isValidCost("")); // empty string
        assertFalse(Cost.isValidCost(" ")); // spaces only
        assertFalse(Cost.isValidCost(".")); // decimal only
        assertFalse(Cost.isValidCost(".5")); // decimal point with no preceding digit
        assertFalse(Cost.isValidCost("5.")); // decimal point with no succeeding digit
        assertFalse(Cost.isValidCost("5.6 ")); //empty space at end
        assertFalse(Cost.isValidCost(" 5.6")); //empty space at start
        assertFalse(Cost.isValidCost(" 5.6 ")); //empty space at start and end
        assertFalse(Cost.isValidCost("5 .6")); //empty space between digits
        assertFalse(Cost.isValidCost("fifty")); //alphabets
        assertFalse(Cost.isValidCost("$3.0")); // special character;
        // valid cost
        assertTrue(Cost.isValidCost("50.20")); //typical cost
        assertTrue(Cost.isValidCost("5")); //integer
    }

    @Test
    public void toString_typicalFloatValue_valueAsString() {
        assertEquals("20.55", new Cost("20.55").toString());
    }

    @Test
    public void toString_lastDigitZero_stringWithoutZeroAtEnd() {
        assertEquals("20.5", new Cost("20.50").toString());
    }

    @Test
    public void equal() {
        Cost costOne = new Cost("20.50");
        Cost costTwo = new Cost("20.5");
        Cost costThree = new Cost("54");

        assertEquals(costOne, costTwo); //costs are equivalent
        assertNotEquals(costOne, costThree);
    }
}
