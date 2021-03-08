package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;



public class DeliveryDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeliveryDate(null));
    }

    @Test
    public void constructor_invalidDeliveryDate_throwsIllegalArgumentException() {
        String invalidDeliveryDate = "2021/01";
        assertThrows(IllegalArgumentException.class, () -> new DeliveryDate(invalidDeliveryDate));
    }

    @Test
    public void isValidDeliveryDate() {
        // null delivery date
        assertThrows(NullPointerException.class, () -> DeliveryDate.isValidDeliveryDate(null));

        // invalid addresses
        assertFalse(DeliveryDate.isValidDeliveryDate("01/13/2022")); // invalid month
        assertFalse(DeliveryDate.isValidDeliveryDate("32/01/2022")); // invalid day
        assertFalse(DeliveryDate.isValidDeliveryDate("01/01/202020")); // invalid year
        // less than 3 working days
        assertFalse(DeliveryDate.isValidDeliveryDate(LocalDate.now().plusDays(2L).toString()));

        // valid addresses
        assertTrue(DeliveryDate.isValidDeliveryDate("02/02/2022"));
        assertTrue(DeliveryDate.isValidDeliveryDate("02-02-2022"));
        assertTrue(DeliveryDate.isValidDeliveryDate("02.02.2022"));
        assertTrue(DeliveryDate.isValidDeliveryDate("02 Feb 2022"));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("[dd/MM/yyyy]");
        // 3 working days from current date
        assertTrue(DeliveryDate.isValidDeliveryDate(dateFormat.format(LocalDate.now().plusDays(3L))));
    }
}
