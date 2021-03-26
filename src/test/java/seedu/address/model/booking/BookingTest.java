package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class BookingTest {

    private Name validName = new Name("John");
    private Phone validPhone = new Phone("91234567");
    private LocalDate validStart = LocalDate.of(2021, 3, 22);
    private LocalDate validEnd = LocalDate.of(2021, 3, 25);

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
        assertThrows(NullPointerException.class, () -> Booking.isValidBookingTime(null, null));

        // invalid dates - start date after end date
        assertFalse(Booking.isValidBookingTime(validEnd, validStart));

        // valid bookings
        assertTrue(Booking.isValidBookingTime(validStart, validEnd));
    }
}
