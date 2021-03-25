package seedu.address.model.fee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MonthTest {

    @Test
    public void constructor_invalidMonth_throwsIllegalArgumentException() {
        int invalidMonth = 15;
        assertThrows(java.lang.IllegalArgumentException.class, () -> new Month(invalidMonth));
    }

    @Test
    public void equalsMonth() {
        Month firstMonth = new Month(1);
        String FIRST_MONTH_NAME = "January";
        assertEquals(firstMonth, new Month(1));
        assertEquals(firstMonth.toString(), String.valueOf(new Month(1).getMonth()));
        assertEquals(firstMonth.getMonthName(), FIRST_MONTH_NAME);
    }

    @Test
    public void isValidMonth() {
        // Invalid Month: int 0 or below or above 12 should fail
        assertFalse(Month.isValidMonth(-999));
        assertFalse(Month.isValidMonth(0));
        assertFalse(Month.isValidMonth(13));
        assertFalse(Month.isValidMonth(999));

        // Valid Month: Any int between 1 and 12
        assertTrue(Month.isValidMonth(1));
        assertTrue(Month.isValidMonth(12));
    }
}
