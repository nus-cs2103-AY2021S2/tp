package seedu.booking.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static seedu.booking.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
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
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.Id;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Person;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;
import seedu.booking.testutil.BookingBuilder;
import seedu.booking.testutil.PersonBuilder;
import seedu.booking.testutil.VenueBuilder;

class CreateBookingCommandTest {

    @Test
    public void constructor_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateBookingCommand(null));
    }

    @Test
    public void execute_bookingAcceptedByModel_addSuccessful() throws Exception {
        CreateBookingCommandTest.ModelStubAcceptingBookingAdded modelStub = new CreateBookingCommandTest.ModelStubAcceptingBookingAdded();
        Booking validBooking = new BookingBuilder().build();

        CommandResult commandResult = new CreateBookingCommand(validBooking).execute(modelStub);

        assertEquals(String.format(CreateBookingCommand.MESSAGE_SUCCESS, validBooking), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validBooking), modelStub.bookingsAdded);
    }

    @Test
    public void execute_duplicateBooking_throwsCommandException() {
        Booking validBooking = new BookingBuilder().build();
        CreateBookingCommand createBookingCommand = new CreateBookingCommand(validBooking);
        CreateBookingCommandTest.ModelStub modelStub = new CreateBookingCommandTest.ModelStubWithBooking(validBooking);

        assertThrows(CommandException.class, createBookingCommand.MESSAGE_DUPLICATE_BOOKING, ()
                -> createBookingCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Venue hall = new VenueBuilder().withName("Victoria Hall").build();
        Person tom = new PersonBuilder().withName("Tom Holland").withEmail("SpiderMan@gmail.com")
                .withPhone("11111111").build();
        Venue field = new VenueBuilder().withName("Town Green").build();
        Booking bookHall = new BookingBuilder().withVenue(hall.getVenueName()).withBooker(tom.getEmail())
                .withDescription(new Description("VIP"))
                .withBookingStart(new StartTime(
                        LocalDateTime.of(2021, 2, 2, 7, 0, 0)))
                .withBookingEnd(new EndTime(
                        LocalDateTime.of(2021, 2, 2, 8, 0, 0)))
                .build();
        Booking bookField = new BookingBuilder().withVenue(field.getVenueName()).withBooker(tom.getEmail())
                .withDescription(new Description("VIP"))
                .withBookingStart(new StartTime(
                        LocalDateTime.of(2021, 2, 2, 7, 0, 0)))
                .withBookingEnd(new EndTime(
                        LocalDateTime.of(2021, 2, 2, 8, 0, 0)))
                .build();
        CreateBookingCommand addHallCommand = new CreateBookingCommand(bookHall);
        CreateBookingCommand addFieldCommand = new CreateBookingCommand(bookField);

        // same object -> returns true
        assertTrue(addHallCommand.equals(addHallCommand));

        // same values -> returns true
        CreateBookingCommand addHallCommandCopy = new CreateBookingCommand(bookHall);
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
            throw new AssertionError("This method should not be called.");
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
            return true;
        }

        @Override
        public boolean hasBooking(Booking booking) {
            throw new AssertionError("This method should not be called.");
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
            return true;
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
    private class ModelStubWithBooking extends CreateBookingCommandTest.ModelStub {
        private final Booking booking;

        ModelStubWithBooking(Booking booking) {
            requireNonNull(booking);
            this.booking = booking;
        }

        @Override
        public boolean hasBooking(Booking booking) {
            requireNonNull(booking);
            return this.booking.isSameBooking(booking);
        }
    }

    /**
     * A Model stub that always accept the venue being added.
     */
    private class ModelStubAcceptingBookingAdded extends CreateBookingCommandTest.ModelStub {
        final ArrayList<Booking> bookingsAdded = new ArrayList<>();

        @Override
        public boolean hasBooking(Booking booking) {
            requireNonNull(booking);
            return bookingsAdded.stream().anyMatch(booking::isSameBooking);
        }

        @Override
        public void addBooking(Booking booking) {
            requireNonNull(booking);
            bookingsAdded.add(booking);
        }

        @Override
        public ReadOnlyBookingSystem getBookingSystem() {
            return new BookingSystem();
        }
    }
}