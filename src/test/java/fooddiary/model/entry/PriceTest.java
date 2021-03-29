package fooddiary.model.entry;

import static fooddiary.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PriceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Price(null));
    }

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        String invalidPrice = "";
        assertThrows(IllegalArgumentException.class, () -> new Price(invalidPrice));
    }

    @Test
    public void isValidPrice() {
        // null price
        assertThrows(NullPointerException.class, () -> Price.isValidPrice(null));

        // invalid price
        assertFalse(Price.isValidPrice("")); // empty string
        assertFalse(Price.isValidPrice(" ")); // spaces only
        assertFalse(Price.isValidPrice("1234")); // more than 3 numbers
        assertFalse(Price.isValidPrice("price")); // non-numeric
        assertFalse(Price.isValidPrice("9011p041")); // alphabets within digits
        assertFalse(Price.isValidPrice("5 ")); // spaces within digits
        assertFalse(Price.isValidPrice("1000")); // price higher than 999
        assertFalse(Price.isValidPrice("-1")); // price lower than 0
        assertFalse(Price.isValidPrice("12-")); //dash without upper bound
        assertFalse(Price.isValidPrice("-12")); //dash without lower bound
        assertFalse(Price.isValidPrice("-1-12")); //lower bound less than 0
        assertFalse(Price.isValidPrice("12-1000")); //upper bound more than 999
        assertFalse(Price.isValidPrice("36-12")); //lower bound more than upper bound
        assertFalse(Price.isValidPrice("12-12")); //lower bound same as upper bound

        // valid price
        assertTrue(Price.isValidPrice("4")); // exactly 1 number
        assertTrue(Price.isValidPrice("555"));
        assertTrue(Price.isValidPrice("0"));
        assertTrue(Price.isValidPrice("4-7"));
        assertTrue(Price.isValidPrice("15-16"));
        assertTrue(Price.isValidPrice("100-999"));

    }
}
