package seedu.address.model.property.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AskingPriceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AskingPrice(null));
    }

    @Test
    public void constructor_invalidAskingPrice_throwsIllegalArgumentException() {
        String invalidAskingPrice = "";
        assertThrows(IllegalArgumentException.class, () -> new AskingPrice(invalidAskingPrice));
    }

    @Test
    public void isValidAskingPrice() {
        // null asking price
        assertThrows(NullPointerException.class, () -> AskingPrice.isValidAskingPrice(null));

        // invalid asking price
        assertFalse(AskingPrice.isValidAskingPrice("")); // empty string
        assertFalse(AskingPrice.isValidAskingPrice(" ")); // spaces only
        assertFalse(AskingPrice.isValidAskingPrice("$01000")); // leading zeros
        assertFalse(AskingPrice.isValidAskingPrice("$00")); // double zeros
        assertFalse(AskingPrice.isValidAskingPrice("$100 000")); // spaces within digits
        assertFalse(AskingPrice.isValidAskingPrice("$1000,000")); // inconsistent commas
        assertFalse(AskingPrice.isValidAskingPrice("$10,00,00")); // inconsistent commas
        assertFalse(AskingPrice.isValidAskingPrice("$1,000000")); // inconsistent commas
        assertFalse(AskingPrice.isValidAskingPrice("$100K")); // alphabets within digits
        assertFalse(AskingPrice.isValidAskingPrice("$100000.")); // ends with a decimal point
        assertFalse(AskingPrice.isValidAskingPrice("$1000.0")); // with cents portion
        assertFalse(AskingPrice.isValidAskingPrice(" $1000000")); // leading space
        assertFalse(AskingPrice.isValidAskingPrice("$1000000 ")); // trailing space

        // valid asking price without dollar sign
        assertTrue(AskingPrice.isValidAskingPrice("1000000")); // without any commas
        assertTrue(AskingPrice.isValidAskingPrice("1,000,000")); // consistent commas
        assertTrue(AskingPrice.isValidAskingPrice("0")); // zero dollars
        assertTrue(AskingPrice.isValidAskingPrice("99999999999")); // large number

        // valid asking price with dollar sign
        assertTrue(AskingPrice.isValidAskingPrice("$1000000")); // without any commas
        assertTrue(AskingPrice.isValidAskingPrice("$1,000,000")); // consistent commas
        assertTrue(AskingPrice.isValidAskingPrice("$0")); // zero dollars
        assertTrue(AskingPrice.isValidAskingPrice("$99,999,999,999")); // large number
    }
}
