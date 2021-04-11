package seedu.booking.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.booking.commons.exceptions.IllegalValueException;
import seedu.booking.model.Tag;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;

public class JsonAdaptedBooking {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Booking's %s field is missing!";

    private final String bookerEmail;
    private final String venueName;
    private final String description;
    private final String bookingStart;
    private final String bookingEnd;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedBooking} with the given booking details.
     */
    @JsonCreator
    public JsonAdaptedBooking(@JsonProperty("bookerEmail") String bookerEmail,
          @JsonProperty("venueName") String venueName, @JsonProperty("description") String description,
          @JsonProperty("bookingStart") String bookingStart, @JsonProperty("bookingEnd") String bookingEnd,
          @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.bookerEmail = bookerEmail;
        this.venueName = venueName;
        this.description = description;
        this.bookingStart = bookingStart;
        this.bookingEnd = bookingEnd;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }

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
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }


    /**
     * Converts this Jackson-friendly adapted booking object into the model's {@code Booking} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted booking.
     */
    public Booking toModelType() throws IllegalValueException {

        if (bookerEmail == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
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
                    StartTime.class.getSimpleName()));
        }

        if (bookingEnd == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EndTime.class.getSimpleName()));
        }

        final StartTime modelBookingStart;
        final EndTime modelBookingEnd;

        //Build formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        try {
            //Parse String to LocalDateTime
            modelBookingStart = new StartTime(LocalDateTime.parse(bookingStart, formatter));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Start time should follow yyyy-MM-dd HH:mm.");
        }

        try {
            // Parse String to LocalDateTime
            modelBookingEnd = new EndTime(LocalDateTime.parse(bookingEnd, formatter));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("End time should follow yyyy-MM-dd HH:mm.");
        }




        final List<Tag> bookingTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            bookingTags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(bookingTags);

        return new Booking(modelBooker, modelVenue, modelDescription, modelBookingStart, modelBookingEnd,
                modelTags);
    }

}
