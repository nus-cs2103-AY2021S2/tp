package seedu.booking.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.booking.commons.exceptions.IllegalValueException;
import seedu.booking.model.BookingSystem;
import seedu.booking.model.ReadOnlyBookingSystem;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.person.Person;
import seedu.booking.model.venue.Venue;

/**
 * An Immutable BookingSystem that is serializable to JSON format.
 */
@JsonRootName(value = "bookingsystem")
class JsonSerializableBookingSystem {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_VENUE = "Venues list contains duplicate venue(s).";
    public static final String MESSAGE_DUPLICATE_BOOKING = "Bookings list contains duplicate booking(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedVenue> venues = new ArrayList<>();
    private final List<JsonAdaptedBooking> bookings = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableBookingSystem} with the given persons.
     */
    @JsonCreator
    public JsonSerializableBookingSystem(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                         @JsonProperty("venues") List<JsonAdaptedVenue> venues,
                                         @JsonProperty("bookings") List<JsonAdaptedBooking> bookings) {
        this.persons.addAll(persons);
        this.venues.addAll(venues);
        this.bookings.addAll(bookings);
    }

    /**
     * Converts a given {@code ReadOnlyBookingSystem} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableBookingSystem}.
     */
    public JsonSerializableBookingSystem(ReadOnlyBookingSystem source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        venues.addAll(source.getVenueList().stream().map(JsonAdaptedVenue::new).collect(Collectors.toList()));
        bookings.addAll(source.getBookingList().stream().map(JsonAdaptedBooking::new).collect(Collectors.toList()));
    }

    /**
     * Converts this booking system into the model's {@code BookingSystem} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public BookingSystem toModelType() throws IllegalValueException {
        BookingSystem bookingSystem = new BookingSystem();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (bookingSystem.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            bookingSystem.addPerson(person);
        }

        for (JsonAdaptedVenue jsonAdaptedVenue : venues) {
            Venue venue = jsonAdaptedVenue.toModelType();
            if (bookingSystem.hasVenue(venue)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_VENUE);
            }
            bookingSystem.addVenue(venue);
        }

        for (JsonAdaptedBooking jsonAdaptedBooking : bookings) {
            Booking booking = jsonAdaptedBooking.toModelType();
            if (bookingSystem.hasBooking(booking)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_VENUE);
            }
            bookingSystem.addBooking(booking);
        }

        return bookingSystem;
    }

}
