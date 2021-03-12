package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.appointment.exceptions.NegativeOrZeroDurationException;
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
    public static final String MESSAGE_INVALID_DATE_TIME_FORMAT = "Input format for date time parameters "
            + "must be: YYYY-MM-DD HH:mm";
    public static final String MESSAGE_INVALID_DURATION_FORMAT = "Input format for duration must be: "
            + "[%d UNIT...] where units are { H:hours, M:minutes }\n"
            + "Examples of duration inputs:\n"
            + "2H 30M - returns a duration of 2 hours, 30 minutes and 30 seconds\n"
            + "150M   - returns a duration of 150 minutes";

    private static final String PREFIX_DURATION_PARSE_SEQUENCE = "PT";
    private static final String REMOVE_WHITESPACE_REGEX = "\\s+";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
     * Parses a {@code String dateTime} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateTime} does not
     * conform to the expected date time format.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();

        try {
            return LocalDateTime.parse(trimmedDateTime, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_DATE_TIME_FORMAT);
        }
    }

    /**
     * Parses a {@code String duration} into a {@code Duration}.
     * All whitespaces will be removed.
     *
     * @throws ParseException if the given {@code duration} does not
     * conform to the expected duration format.
     */
    public static Duration parseDuration(String duration) throws ParseException {
        requireNonNull(duration);

        try {
            return Duration.parse(PREFIX_DURATION_PARSE_SEQUENCE + duration.replaceAll(
                    REMOVE_WHITESPACE_REGEX, ""));
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_DURATION_FORMAT);
        }
    }

    /**
     * Parses a {@code String start} and {@code String end} into a {@code Timeslot}.
     *
     * @throws ParseException for the following scenarios:
     * - the given {@code start} or {@code end} does not conform to the expected date
     * time format
     * - the {@code LocalDateTime} represented by {@code end} is not strictly after
     * {@code start}
     */
    public static Timeslot parseTimeslotByEnd(String start, String end) throws ParseException {
        try {
            return new Timeslot(parseDateTime(start), parseDateTime(end));
        } catch (NegativeOrZeroDurationException e) {
            throw new ParseException(Timeslot.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String start} and {@code String duration} into a {@code Timeslot}.
     *
     * @throws ParseException for the following scenarios:
     * - the given {@code start} does not conform to the expected date time format
     * - the given {@code duration} does not conform to the expected duration format
     * - the given {@code Duration} represented by {@code duration} is negative.
     */
    public static Timeslot parseTimeslotByDuration(String start, String duration) throws ParseException {
        try {
            return new Timeslot(parseDateTime(start), parseDuration(duration));
        } catch (NegativeOrZeroDurationException e) {
            throw new ParseException(Timeslot.MESSAGE_CONSTRAINTS);
        }
    }
}
