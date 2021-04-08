package seedu.booking.model.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.testutil.Assert.assertThrows;
import static seedu.booking.testutil.TypicalBookings.BOOKING1;
import static seedu.booking.testutil.TypicalBookings.BOOKING2;
import static seedu.booking.testutil.TypicalBookings.BOOKING3;
import static seedu.booking.testutil.TypicalBookings.BOOKING5;
import static seedu.booking.testutil.TypicalVenues.VENUE1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.booking.model.booking.exceptions.BookingNotFoundException;
import seedu.booking.model.booking.exceptions.DuplicateBookingException;
import seedu.booking.model.booking.exceptions.OverlappingBookingException;
import seedu.booking.testutil.BookingBuilder;

public class NonOverlappingBookingListTest {

    private final NonOverlappingBookingList nonOverlappingBookingList = new NonOverlappingBookingList();

    @Test
    public void contains_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingBookingList.contains(null));
    }

    @Test
    public void contains_bookingNotInList_returnsFalse() {
        assertFalse(nonOverlappingBookingList.contains(BOOKING1));
    }

    @Test
    public void contains_bookingInList_returnsTrue() {
        nonOverlappingBookingList.add(BOOKING1);
        assertTrue(nonOverlappingBookingList.contains(BOOKING1));
    }

    @Test
    public void contains_bookingWithSameIdentityFieldsInList_returnsTrue() {
        nonOverlappingBookingList.add(BOOKING1);
        Booking editedBooking1 = new BookingBuilder(BOOKING1).withVenue(VENUE1.getVenueName().venueName)
                .build();
        assertTrue(nonOverlappingBookingList.contains(editedBooking1));
    }

    @Test
    public void add_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingBookingList.add(null));
    }

    @Test
    public void add_duplicateBooking_throwsDuplicateBookingException() {
        nonOverlappingBookingList.add(BOOKING1);
        assertThrows(DuplicateBookingException.class, () -> nonOverlappingBookingList.add(BOOKING1));
    }

    @Test
    public void add_overlappingBooking_throwsOverlappingBookingException() {
        nonOverlappingBookingList.add(BOOKING1);
        assertThrows(OverlappingBookingException.class, () -> nonOverlappingBookingList.add(BOOKING5));
    }

    @Test
    public void setBooking_nullTargetBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingBookingList.setBooking(null, BOOKING1));
    }

    @Test
    public void setBooking_nullEditedBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingBookingList.setBooking(BOOKING1, null));
    }

    @Test
    public void setBooking_targetBookingNotInList_throwsBookingNotFoundException() {
        assertThrows(BookingNotFoundException.class, () -> nonOverlappingBookingList.setBooking(BOOKING1, BOOKING1));
    }

    @Test
    public void setBooking_targetBookingOverlapping_throwsOverlappingBookingException() {
        nonOverlappingBookingList.add(BOOKING1);
        nonOverlappingBookingList.add(BOOKING2);
        assertThrows(OverlappingBookingException.class, () -> nonOverlappingBookingList.setBooking(BOOKING2, BOOKING5));
    }

    @Test
    public void setBooking_editedBookingIsSameBooking_success() {
        nonOverlappingBookingList.add(BOOKING1);
        nonOverlappingBookingList.setBooking(BOOKING1, BOOKING1);
        NonOverlappingBookingList expectedNonOverlappingBookingList = new NonOverlappingBookingList();
        expectedNonOverlappingBookingList.add(BOOKING1);
        assertEquals(expectedNonOverlappingBookingList, nonOverlappingBookingList);
    }


    @Test
    public void setBooking_editedBookingHasSameIdentity_success() {
        nonOverlappingBookingList.add(BOOKING1);
        Booking editedBooking1 = new BookingBuilder(BOOKING1)
                .withVenue(VENUE1.getVenueName().venueName).build();
        nonOverlappingBookingList.setBooking(BOOKING1, editedBooking1);
        NonOverlappingBookingList expectedNonOverlappingBookingList = new NonOverlappingBookingList();
        expectedNonOverlappingBookingList.add(editedBooking1);
        assertEquals(expectedNonOverlappingBookingList, nonOverlappingBookingList);
    }

    @Test
    public void setBooking_editedBookingHasDifferentIdentity_success() {
        nonOverlappingBookingList.add(BOOKING1);
        nonOverlappingBookingList.setBooking(BOOKING1, BOOKING3);
        NonOverlappingBookingList expectedNonOverlappingBookingList = new NonOverlappingBookingList();
        expectedNonOverlappingBookingList.add(BOOKING3);
        assertEquals(expectedNonOverlappingBookingList, nonOverlappingBookingList);
    }


    @Test
    public void setBooking_editedBookingHasNonUniqueIdentity_throwsDuplicateBookingException() {
        nonOverlappingBookingList.add(BOOKING1);
        nonOverlappingBookingList.add(BOOKING3);
        assertThrows(DuplicateBookingException.class, () -> nonOverlappingBookingList.setBooking(BOOKING1, BOOKING3));
    }

    @Test
    public void remove_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingBookingList.remove(null));
    }


    @Test
    public void remove_nonexistentBooking_throwsBookingNotFoundException() {
        nonOverlappingBookingList.add(BOOKING1);
        assertThrows(BookingNotFoundException.class, () -> nonOverlappingBookingList.remove(BOOKING2));
    }

    @Test
    public void remove_emptyBookingList_throwsBookingNotFoundException() {
        assertThrows(BookingNotFoundException.class, () -> nonOverlappingBookingList.remove(BOOKING1));
    }

    @Test
    public void setBookings_nullNonOverlappingBookingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingBookingList
                .setBookings((NonOverlappingBookingList) null));
    }

    @Test
    public void setBookings_nonOverlappingBookingList_replacesOwnListWithProvidedNonOverlappingBookingList() {
        nonOverlappingBookingList.add(BOOKING1);
        NonOverlappingBookingList expectedNonOverlappingBookingList = new NonOverlappingBookingList();
        expectedNonOverlappingBookingList.add(BOOKING2);
        nonOverlappingBookingList.setBookings(expectedNonOverlappingBookingList);
        assertEquals(expectedNonOverlappingBookingList, nonOverlappingBookingList);
    }

    @Test
    public void setBooking_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonOverlappingBookingList
                .setBookings((List<Booking>) null));
    }

    @Test
    public void setBookings_list_replacesOwnListWithProvidedList() {
        nonOverlappingBookingList.add(BOOKING1);
        List<Booking> bookingList = Collections.singletonList(BOOKING3);
        nonOverlappingBookingList.setBookings(bookingList);
        NonOverlappingBookingList expectedNonOverlappingBookingList = new NonOverlappingBookingList();
        expectedNonOverlappingBookingList.add(BOOKING3);
        assertEquals(expectedNonOverlappingBookingList, nonOverlappingBookingList);
    }

    /*@Test
    public void setBookings_listWithDuplicateBookings_throwsDuplicateBookingException() {
        List<Booking> listWithDuplicateBookings = Arrays.asList(BOOKING1, BOOKING1);
        assertThrows(DuplicateBookingException.class,
         () -> nonOverlappingBookingList.setBookings(listWithDuplicateBookings));
    }*/

    @Test
    public void setBookings_listWithOverlappingBookingTiming_throwsOverlappingBookingException() {
        List<Booking> listWithOverlappingBookings = Arrays.asList(BOOKING1, BOOKING5);
        assertThrows(OverlappingBookingException.class, () -> nonOverlappingBookingList
                .setBookings(listWithOverlappingBookings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> nonOverlappingBookingList.asUnmodifiableObservableList().remove(0));
    }
}
