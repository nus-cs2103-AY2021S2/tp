package seedu.partyplanet.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.commons.util.StringUtil;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.date.Date;
import seedu.partyplanet.model.event.EventDate;
import seedu.partyplanet.model.person.Address;
import seedu.partyplanet.model.person.Birthday;
import seedu.partyplanet.model.person.Email;
import seedu.partyplanet.model.person.Name;
import seedu.partyplanet.model.person.Phone;
import seedu.partyplanet.model.person.Remark;
import seedu.partyplanet.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

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
     * Returns default empty phone if phone is not specified.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (phone == Phone.EMPTY_PHONE_STRING) {
            return Phone.EMPTY_PHONE;
        } else if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Returns default empty address if address is not specified.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (address == Address.EMPTY_ADDRESS_STRING) {
            return Address.EMPTY_ADDRESS;
        } else if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String remark} into a {@code Remark}.
     * Returns default empty remark if remark is not specified.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Remark} is invalid.
     */
    public static Remark parseRemark(String remark) throws ParseException {
        requireNonNull(remark);
        String trimmedRemark = remark.trim();
        if (remark == Remark.EMPTY_REMARK_STRING) {
            return Remark.EMPTY_REMARK;
        } else if (!Remark.isValidRemark(trimmedRemark)) {
            throw new ParseException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(trimmedRemark);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Returns default empty email if email is not specified.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (email == Email.EMPTY_EMAIL_STRING) {
            return Email.EMPTY_EMAIL;
        } else if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String date} into an {@code Date}.
     * Returns default empty date if date is not specified.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static EventDate parseEventDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (date == Date.EMPTY_DATE_STRING) {
            return EventDate.EMPTY_EVENT_DATE;
        }
        try {
            return new EventDate(trimmedDate);
        } catch (DateTimeException err) { // date in wrong format
            throw new ParseException(EventDate.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String birthday} into an {@code Birthday}.
     * Returns default empty birthday if birthday is not specified.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code birthday} is invalid.
     */
    public static Birthday parseBirthday(String birthday) throws ParseException {
        requireNonNull(birthday);
        String trimmedBirthday = birthday.trim();
        if (birthday == Date.EMPTY_DATE_STRING) {
            return Birthday.EMPTY_BIRTHDAY;
        }
        try {
            return new Birthday(trimmedBirthday);
        } catch (DateTimeException err) { // date in wrong format
            throw new ParseException((Birthday.MESSAGE_CONSTRAINTS));
        } catch (IllegalArgumentException err) { // birthday is in the future
            throw new ParseException(Birthday.MESSAGE_DATE_CONSTRAINTS);
        }
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
