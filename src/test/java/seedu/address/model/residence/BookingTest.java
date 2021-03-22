package seedu.address.model.residence;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

import java.time.LocalDate;

public class BookingTest {

    Name validName = new Name("John");
    Phone validPhone = new Phone("91234567");
    LocalDate validStart = LocalDate.of(2021, 3, 22);
    LocalDate validEnd = LocalDate.of(2021, 3, 25);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Booking(null, null, null, null));
        assertThrows(NullPointerException.class, () -> new Booking(validName, null, null, null));
        assertThrows(NullPointerException.class, () -> new Booking(validName, validPhone, null, null));
        assertThrows(NullPointerException.class, () -> new Booking(validName, validPhone, validStart, null));
        assertThrows(NullPointerException.class, () -> new Booking(validName, validPhone, null, validEnd));
        assertThrows(NullPointerException.class, () -> new Booking(validName, null, validStart, null));
        assertThrows(NullPointerException.class, () -> new Booking(validName, null, validStart, validEnd));
        assertThrows(NullPointerException.class, () -> new Booking(validName, null, null, validEnd));
        assertThrows(NullPointerException.class, () -> new Booking(null, validPhone, null, null));
        assertThrows(NullPointerException.class, () -> new Booking(null, validPhone, validStart, null));
        assertThrows(NullPointerException.class, () -> new Booking(null, validPhone, validStart, validEnd));
        assertThrows(NullPointerException.class, () -> new Booking(null, validPhone, null, validEnd));
        assertThrows(NullPointerException.class, () -> new Booking(null, null, validStart, null));
        assertThrows(NullPointerException.class, () -> new Booking(null, null, validStart, validEnd));
        assertThrows(NullPointerException.class, () -> new Booking(null, null, null, validEnd));
    }

    @Test
    public void constructor_invalidBooking_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Booking(new Name(""), validPhone, validStart, validEnd));
        assertThrows(
                IllegalArgumentException.class, () -> new Booking(validName, new Phone("12"), validStart, validEnd));
    }

    @Test
    public void isValidBooking() {

        // null booking
        assertThrows(NullPointerException.class, () -> Booking.isValidBooking(null));

        /*// invalid names
        assertFalse(Booking.isValidBooking(new Booking(new Name(""), validPhone, validStart, validEnd))); // empty string
        assertFalse(Booking.isValidBooking(new Booking(new Name(" "), validPhone, validStart, validEnd))); // spaces only

        // invalid phones
        assertFalse(Booking.isValidBooking(new Booking(validName, new Phone("91"), validStart, validEnd))); // empty string
        assertFalse(Booking.isValidBooking(new Booking(validName, new Phone("abc123"), validStart, validEnd))); // spaces only*/

        // invalid dates
        assertFalse(Booking.isValidBooking(new Booking(validName, validPhone, validEnd, validStart))); // start date after end date

        // valid bookings
        assertTrue(Booking.isValidBooking(new Booking(validName, validPhone, validStart, validEnd)));
    }
}