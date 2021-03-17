package seedu.cakecollate.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cakecollate.testutil.Assert.assertThrows;

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
    public void equals() {
        LocalDate validDeliveryDate = LocalDate.now().plusDays(5L);
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter format3 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter format4 = DateTimeFormatter.ofPattern("dd MMM yyyy");
        assertEquals(new DeliveryDate(format1.format(validDeliveryDate)),
                new DeliveryDate(format2.format(validDeliveryDate)));
        assertEquals(new DeliveryDate(format2.format(validDeliveryDate)),
                new DeliveryDate(format3.format(validDeliveryDate)));
        assertEquals(new DeliveryDate(format3.format(validDeliveryDate)),
                new DeliveryDate(format4.format(validDeliveryDate)));
    }

    @Test
    public void isValidDeliveryDate() {
        // null delivery date
        assertThrows(NullPointerException.class, () -> DeliveryDate.isValidDeliveryDate(null));

        // invalid addresses
        assertFalse(DeliveryDate.isValidDeliveryDate("01/13/2022")); // invalid month
        assertFalse(DeliveryDate.isValidDeliveryDate("32/01/2022")); // invalid day
        assertFalse(DeliveryDate.isValidDeliveryDate("01/01/202020")); // invalid year
        assertFalse(DeliveryDate.isValidDeliveryDate("01,01,2022")); // invalid delimiter
        assertFalse(DeliveryDate.isValidDeliveryDate("01:01:2022")); // invalid delimiter
        assertFalse(DeliveryDate.isValidDeliveryDate("01/01-2022")); // inconsistent delimiter
        assertFalse(DeliveryDate.isValidDeliveryDate("2022/12/31")); // invalid format
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
