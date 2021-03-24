package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.booking.commons.core.GuiSettings;
import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.model.BookingSystem;
import seedu.booking.model.Model;
import seedu.booking.model.ReadOnlyBookingSystem;
import seedu.booking.model.ReadOnlyUserPrefs;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Id;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Person;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;
import seedu.booking.testutil.VenueBuilder;

public class CreateVenueCommandTest {

    @Test
    public void constructor_nullVenue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateVenueCommand(null));
    }

    @Test
    public void execute_venueAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingVenueAdded modelStub = new ModelStubAcceptingVenueAdded();
        Venue validVenue = new VenueBuilder().build();

        CommandResult commandResult = new CreateVenueCommand(validVenue).execute(modelStub);

        assertEquals(String.format(CreateVenueCommand.MESSAGE_SUCCESS, validVenue), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validVenue), modelStub.venuesAdded);
    }

    @Test
    public void execute_duplicateVenue_throwsCommandException() {
        Venue validVenue = new VenueBuilder().build();
        CreateVenueCommand createVenueCommand = new CreateVenueCommand(validVenue);
        ModelStub modelStub = new ModelStubWithVenue(validVenue);

        assertThrows(CommandException.class, createVenueCommand.MESSAGE_DUPLICATE_VENUE, ()
            -> createVenueCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Venue hall = new VenueBuilder().withName("Victoria Hall").build();
        Venue field = new VenueBuilder().withName("Town Green").build();
        CreateVenueCommand addHallCommand = new CreateVenueCommand(hall);
        CreateVenueCommand addFieldCommand = new CreateVenueCommand(field);

        // same object -> returns true
        assertTrue(addHallCommand.equals(addHallCommand));

        // same values -> returns true
        CreateVenueCommand addHallCommandCopy = new CreateVenueCommand(hall);
        assertTrue(addHallCommand.equals(addHallCommandCopy));

        // different types -> returns false
        assertFalse(addHallCommand.equals(1));

        // null -> returns false
        assertFalse(addHallCommand.equals(null));

        // different person -> returns false
        assertFalse(addHallCommand.equals(addFieldCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getBookingSystemFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBookingSystemFilePath(Path bookingSystemFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBooking(Booking booking) {

        }

        @Override
        public void setBooking(Booking target, Booking editedBooking) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBookingSystem(ReadOnlyBookingSystem newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyBookingSystem getBookingSystem() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersonWithEmail(Email email) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBooking(Booking booking) {
            return false;
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteVenue(Venue target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasVenueWithVenueName(VenueName venueName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasVenue(Venue venue) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addVenue(Venue venue) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setVenue(Venue target, Venue editedVenue) {

        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Venue> getFilteredVenueList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Booking> getUpcomingBookingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Booking> getFilteredBookingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBooking(Id bookingId) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void updateFilteredBookingList(Predicate<Booking> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredVenueList(Predicate<Venue> predicate) {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that contains a single venue.
     */
    private class ModelStubWithVenue extends ModelStub {
        private final Venue venue;

        ModelStubWithVenue(Venue venue) {
            requireNonNull(venue);
            this.venue = venue;
        }

        @Override
        public boolean hasVenue(Venue venue) {
            requireNonNull(venue);
            return this.venue.isSameVenue(venue);
        }
    }

    /**
     * A Model stub that always accept the venue being added.
     */
    private class ModelStubAcceptingVenueAdded extends ModelStub {
        final ArrayList<Venue> venuesAdded = new ArrayList<>();

        @Override
        public boolean hasVenue(Venue venue) {
            requireNonNull(venue);
            return venuesAdded.stream().anyMatch(venue::isSameVenue);
        }

        @Override
        public void addVenue(Venue venue) {
            requireNonNull(venue);
            venuesAdded.add(venue);
        }

        @Override
        public ReadOnlyBookingSystem getBookingSystem() {
            return new BookingSystem();
        }
    }

}
