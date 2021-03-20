package seedu.booking.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.testutil.Assert.assertThrows;
import static seedu.booking.testutil.TypicalPersons.ALICE;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.person.Person;
import seedu.booking.model.person.exceptions.DuplicatePersonException;
import seedu.booking.model.venue.Venue;
import seedu.booking.testutil.PersonBuilder;

public class BookingSystemTest {

    private final BookingSystem bookingSystem = new BookingSystem();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), bookingSystem.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bookingSystem.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyBookingSystem_replacesData() {
        BookingSystem newData = getTypicalBookingSystem();
        bookingSystem.resetData(newData);
        assertEquals(newData, bookingSystem);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        BookingSystemStub newData = new BookingSystemStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> bookingSystem.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bookingSystem.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInBookingSystem_returnsFalse() {
        assertFalse(bookingSystem.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInBookingSystem_returnsTrue() {
        bookingSystem.addPerson(ALICE);
        assertTrue(bookingSystem.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInBookingSystem_returnsTrue() {
        bookingSystem.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).build();
        assertTrue(bookingSystem.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> bookingSystem.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyBookingSystem whose persons list can violate interface constraints.
     */
    private static class BookingSystemStub implements ReadOnlyBookingSystem {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Venue> venues = FXCollections.observableArrayList();

        BookingSystemStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Booking> getBookingList() {
            return null;
        }

        @Override
        public ObservableList<Venue> getVenueList() {
            return venues;
        }
    }

}
