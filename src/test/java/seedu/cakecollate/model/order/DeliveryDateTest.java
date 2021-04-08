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
    public void isValidFormat() {
        // null delivery date
        assertThrows(NullPointerException.class, () -> DeliveryDate.isValidFormat(null));

        // invalid delivery dates
        assertFalse(DeliveryDate.isValidFormat("01/13/2022")); // invalid month
        assertFalse(DeliveryDate.isValidFormat("32/01/2022")); // invalid day
        assertFalse(DeliveryDate.isValidFormat("01/01/202020")); // invalid year
        assertFalse(DeliveryDate.isValidFormat("01,01,2022")); // invalid delimiter
        assertFalse(DeliveryDate.isValidFormat("01:01:2022")); // invalid delimiter
        assertFalse(DeliveryDate.isValidFormat("01/01-2022")); // inconsistent delimiter
        assertFalse(DeliveryDate.isValidFormat("2022/12/31")); // invalid format
        assertFalse(DeliveryDate.isValidFormat("29 Feb 2022")); // non-leap year
        assertFalse(DeliveryDate.isValidFormat(LocalDate.now().toString())); // Invalid in-build LocalDate format

        // valid delivery dates
        assertTrue(DeliveryDate.isValidFormat("02/02/2022"));
        assertTrue(DeliveryDate.isValidFormat("02-02-2022"));
        assertTrue(DeliveryDate.isValidFormat("02.02.2022"));
        assertTrue(DeliveryDate.isValidFormat("02 Feb 2022"));
        assertTrue(DeliveryDate.isValidFormat("29/02/2024"));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("[dd/MM/yyyy]");
        // 3 working days from current date
        assertTrue(DeliveryDate.isValidFormat(dateFormat.format(LocalDate.now().plusDays(3L))));
    }

    @Test
    public void isXDaysLater() {
        String yesterday = DateTimeFormatter.ofPattern("[dd/MM/yyyy]").format(LocalDate.now().plusDays(-1L));
        String today = DateTimeFormatter.ofPattern("[dd/MM/yyyy]").format(LocalDate.now());
        String tomorrow = DateTimeFormatter.ofPattern("[dd/MM/yyyy]").format(LocalDate.now().plusDays(1L));
        // isValidFormat should be called before isXDaysLater
        assertFalse(DeliveryDate.isValidFormat("2020/13/40") && DeliveryDate.isXDaysLater(today, 0L));

        // date is in the past
        assertFalse(DeliveryDate.isValidFormat(yesterday) && DeliveryDate.isXDaysLater(yesterday, 0L));

        // date is today or in the future
        assertTrue(DeliveryDate.isValidFormat(today) && DeliveryDate.isXDaysLater(today, 0L));
        assertTrue(DeliveryDate.isValidFormat(tomorrow) && DeliveryDate.isXDaysLater(tomorrow, 0L));
        assertTrue(DeliveryDate.isValidFormat(tomorrow) && DeliveryDate.isXDaysLater(tomorrow, 1L));
    }
}
