package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Phone;
import seedu.address.model.booking.TenantName;
import seedu.address.model.residence.ResidenceAddress;
import seedu.address.model.residence.ResidenceName;
import seedu.address.model.tag.CleanStatusTag;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "The date is not in the expected format: DD-MM-YYYY";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
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
     * Parses a {@code String name} into a {@code ResidenceName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ResidenceName parseResidenceName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!ResidenceName.isValidResidenceName(trimmedName)) {
            throw new ParseException(ResidenceName.MESSAGE_CONSTRAINTS);
        }
        return new ResidenceName(trimmedName);
    }

    /**
     * Parses a {@code String name} into a {@code TenantName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static TenantName parseTenantName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!ResidenceName.isValidResidenceName(trimmedName)) {
            throw new ParseException(TenantName.MESSAGE_CONSTRAINTS);
        }
        return new TenantName(trimmedName);
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
     * Parses a {@code String address} into an {@code ResidenceAddress}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static ResidenceAddress parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!ResidenceAddress.isValidResidenceAddress(trimmedAddress)) {
            throw new ParseException(ResidenceAddress.MESSAGE_CONSTRAINTS);
        }
        return new ResidenceAddress(trimmedAddress);
    }

    /**
     * Parses {@code String start} and {@code String end} into a {@code Booking} together with it's
     * {@code tenantName} and {@code phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code start} and {@code end} are invalid.
     */
    public static Booking parseBooking(TenantName tenantName, Phone phone,
                                       String start, String end) throws ParseException {
        requireNonNull(start);
        requireNonNull(end);

        LocalDate startTime = parseDate(start.trim());
        LocalDate endTime = parseDate(end.trim());
        if (!Booking.isValidBookingTime(startTime, endTime)) {
            throw new ParseException(Booking.MESSAGE_CONSTRAINTS);
        }

        return new Booking(tenantName, phone, startTime, endTime);
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        try {
            return LocalDate.parse(date.trim(), DateTimeFormatter.ofPattern("dd-MM-uuuu"));
        } catch (Exception e) {
            throw new ParseException(MESSAGE_INVALID_DATE_FORMAT);
        }
    }

    /**
     * Parses a {@code String cleanStatus} into a {@code CleanStatusTag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code cleanStatus} is invalid.
     */
    public static CleanStatusTag parseCleanStatusTag(String cleanStatus) throws ParseException {
        requireNonNull(cleanStatus);
        String trimmedTag = cleanStatus.trim();
        if (!CleanStatusTag.isValidCleanStatusTag(trimmedTag)) {
            throw new ParseException(CleanStatusTag.MESSAGE_CONSTRAINTS);
        }
        return new CleanStatusTag(trimmedTag);
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
}
