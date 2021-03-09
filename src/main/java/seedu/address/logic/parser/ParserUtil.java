package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.booking.Venue;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    private static final int DEFAULT_CAPACITY = 0;

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

    public static int parseBookingId(String bookingId) throws ParseException {
        String trimmedIndex = bookingId.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Integer.parseInt(trimmedIndex);
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
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
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
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }


    /**
     * Parses a {@code String booker} into a {@code String trimmedBooker}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static String parseBooker(String booker) {
        requireNonNull(booker);
        String trimmedBooker = booker.trim();
        return trimmedBooker;
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
    public static LocalDateTime parseBookingStart(String bookingStart) {
        requireNonNull(bookingStart);
        String trimmedBookingStart = bookingStart.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(trimmedBookingStart, formatter);
        return dateTime;
    }


    /**
     * Parses a {@code String bookingStart} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static LocalDateTime parseBookingEnd(String bookingEnd) {
        requireNonNull(bookingEnd);
        String trimmedBookingEnd = bookingEnd.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(trimmedBookingEnd, formatter);
        return dateTime;
    }


    /**
     * Parses a {@code String venue} into a {@code Venue}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Venue parseVenue(String venue) throws ParseException {
        requireNonNull(venue);
        String trimmedVenue = venue.trim();
        return new Venue(trimmedVenue, DEFAULT_CAPACITY);
    }

    /**
     * Parses a {@code String capacity} into an integer.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws NumberFormatException if the given {@code capacity} is invalid.
     */
    public static int parseCapacity(String capacity) {
        requireNonNull(capacity);
        String trimmedCapacity = capacity.trim();
        try {
            return Integer.parseInt(trimmedCapacity);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    /**
     * Parses a {@code String name}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static String parseVenueName(String name) {
        requireNonNull(name);
        String trimmedName = name.trim();
        return trimmedName;
    }
}
