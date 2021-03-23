package seedu.booking.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.booking.commons.exceptions.IllegalValueException;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.Id;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Person;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;

public class JsonAdaptedBooking {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Booking's %s field is missing!";

    private final String bookerEmail;
    private final String venueName;
    private final String description;
    private final String bookingStart;
    private final String bookingEnd;
    private final String id;


    /**
     * Constructs a {@code JsonAdaptedBooking} with the given booking details.
     */
    @JsonCreator
    public JsonAdaptedBooking(@JsonProperty("bookerEmail") String bookerEmail,
          @JsonProperty("venueName") String venueName, @JsonProperty("description") String description,
          @JsonProperty("bookingStart") String bookingStart, @JsonProperty("bookingEnd") String bookingEnd,
          @JsonProperty("id") String id) {
        this.bookerEmail = bookerEmail;
        this.venueName = venueName;
        this.description = description;
        this.bookingStart = bookingStart;
        this.bookingEnd = bookingEnd;
        this.id = id;
    }

    /**
     * Converts a given {@code Booking} into this class for Jackson use.
     */
    public JsonAdaptedBooking(Booking source) {
        bookerEmail = source.getBookerEmail().value;
        venueName = source.getVenueName().venueName;
        description = source.getDescription().value;
        bookingStart = source.getBookingStart().value.toString();
        bookingEnd = source.getBookingEnd().value.toString();
        id = String.valueOf(source.getId().value);
    }


    /**
     * Converts this Jackson-friendly adapted booking object into the model's {@code Booking} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted booking.
     */
    public Booking toModelType() throws IllegalValueException {

        if (bookerEmail == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Person.class.getSimpleName()));
        }

        final Email modelBooker = new Email(bookerEmail);

        if (venueName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Venue.class.getSimpleName()));
        }

        final VenueName modelVenue = new VenueName(venueName);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName()));
        }

        final Description modelDescription = new Description(description);

        if (bookingStart == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }

        //Build formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        //Parse String to LocalDateTime
        final StartTime modelBookingStart = new StartTime(LocalDateTime.parse(bookingStart, formatter));


        if (bookingEnd == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }

        // Parse String to LocalDateTime
        final EndTime modelBookingEnd = new EndTime(LocalDateTime.parse(bookingEnd, formatter));

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Integer.class.getSimpleName()));
        }

        final Id modelId = new Id(Integer.parseInt(id));

        return new Booking(modelBooker, modelVenue, modelDescription, modelBookingStart, modelBookingEnd, modelId);
    }

}
