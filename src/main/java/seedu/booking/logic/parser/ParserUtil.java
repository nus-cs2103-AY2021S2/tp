package seedu.booking.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.booking.commons.core.index.Index;
import seedu.booking.commons.util.StringUtil;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.Id;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Name;
import seedu.booking.model.person.Person;
import seedu.booking.model.person.Phone;
import seedu.booking.model.venue.Capacity;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String DEFAULT_DESCRIPTION = "No description provided.";
    private static final Capacity DEFAULT_CAPACITY = new Capacity(10);

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String bookingId} into a {@code int}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Id parseBookingId(String bookingId) throws ParseException {
        String trimmedIndex = bookingId.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return new Id(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }


    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String booker} into a {@code String trimmedBooker}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static Person parseBooker(String booker) {
        requireNonNull(booker);
        String trimmedBooker = booker.trim();
        return new Person(new Name(trimmedBooker));
    }


    /**
     * Parses a {@code String description} into a {@code String description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static Description parseBookingDescription(String description) {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String description} into a {@code String description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static String parseDescription(String description) {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        return trimmedDescription;
    }


    /**
     * Parses a {@code String bookingStart} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static StartTime parseBookingStart(String bookingStart) {
        requireNonNull(bookingStart);
        String trimmedBookingStart = bookingStart.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(trimmedBookingStart, formatter);
        return new StartTime(dateTime);
    }


    /**
     * Parses a {@code String bookingStart} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static EndTime parseBookingEnd(String bookingEnd) {
        requireNonNull(bookingEnd);
        String trimmedBookingEnd = bookingEnd.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(trimmedBookingEnd, formatter);
        return new EndTime(dateTime);
    }


    /**
     * Parses a {@code String venue} into a {@code Venue}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Venue parseVenue(String venue) throws ParseException {
        requireNonNull(venue);
        String trimmedVenue = venue.trim();
        return new Venue(new VenueName(trimmedVenue), DEFAULT_CAPACITY, DEFAULT_DESCRIPTION);
    }

    /**
     * Parses a {@code String capacity} into an integer.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code capacity} is invalid.
     */
    public static Capacity parseCapacity(String capacity) throws ParseException {
        requireNonNull(capacity);
        String trimmedCapacity = capacity.trim();
        Integer formattedCapacity = Integer.parseInt(trimmedCapacity);
        if (!Capacity.isValidCapacity(formattedCapacity)) {
            throw new ParseException(Capacity.MESSAGE_CONSTRAINTS);
        }
        try {
            assert Capacity.isValidCapacity(Integer.parseInt(trimmedCapacity));
            return new Capacity(formattedCapacity);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    /**
     * Parses a {@code String name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static VenueName parseVenueName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!VenueName.isValidName(trimmedName)) {
            throw new ParseException(VenueName.MESSAGE_CONSTRAINTS);
        }
        return new VenueName(trimmedName);
    }
}
