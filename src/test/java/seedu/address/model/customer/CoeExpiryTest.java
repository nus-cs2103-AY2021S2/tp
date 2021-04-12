package seedu.address.model.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import org.junit.jupiter.api.Test;

class CoeExpiryTest {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("uuuu MM dd")
            .withResolverStyle(ResolverStyle.STRICT);
    private static final String expDate = "2030 05 09";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CoeExpiry(null));
    }

    @Test
    public void constructor_invalidCoeExpiry_throwsIllegalArgumentException() {
        String invalidCoeExpiry = "";
        assertThrows(IllegalArgumentException.class, () -> new CoeExpiry(invalidCoeExpiry));
    }

    @Test
    void isValidCoeExpiry() {
        // null CoeExpiry
        assertThrows(NullPointerException.class, () -> CoeExpiry.isValidCoeExpiry(null));

        // blank CoeExpiry
        assertFalse(CoeExpiry.isValidCoeExpiry("")); // empty string
        assertFalse(CoeExpiry.isValidCoeExpiry(" ")); // spaces only

        // missing CoeExpiry
        assertFalse(CoeExpiry.isValidCoeExpiry("2030 01 ")); // missing date
        assertFalse(CoeExpiry.isValidCoeExpiry("19980505")); // missing spaces in between yyyy mm dd

        // invalid CoeExpiry
        assertFalse(CoeExpiry.isValidCoeExpiry("2030 13 01")); // invalid month
        assertFalse(CoeExpiry.isValidCoeExpiry("2030 10 32")); // invalid date

        // valid CoeExpiry
        assertTrue(CoeExpiry.isValidCoeExpiry("2030 01 10"));
        assertTrue(CoeExpiry.isValidCoeExpiry("2045 05 05"));
    }

    @Test
    void toDate() {
        CoeExpiry ce = new CoeExpiry(expDate);
        LocalDate expectedDate = LocalDate.parse(expDate, DATE_TIME_FORMATTER);
        assertEquals(ce.toDate(), expectedDate);

    }
}
