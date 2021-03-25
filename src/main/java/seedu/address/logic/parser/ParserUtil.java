package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Time;
import seedu.address.model.name.Name;
import seedu.address.model.property.Address;
import seedu.address.model.property.Deadline;
import seedu.address.model.property.PostalCode;
import seedu.address.model.property.Type;
import seedu.address.model.property.client.AskingPrice;
import seedu.address.model.property.client.Contact;
import seedu.address.model.property.client.Email;
import seedu.address.model.property.status.Offer;
import seedu.address.model.remark.Remark;
import seedu.address.model.sort.descriptor.AppointmentSortingKey;
import seedu.address.model.sort.descriptor.PropertySortingKey;
import seedu.address.model.sort.descriptor.SortingOrder;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.DateTimeFormat;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    // =====  General parser methods for shared classes ==========================================================

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
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param name The name string to be parsed.
     * @return A {@code Name}.
     * @throws ParseException If the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        if (name == null) {
            return null;
        }
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String remark} into a {@code Remark}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param remark The remark string to be parsed.
     * @return A {@code Remark}.
     * @throws ParseException If the given {@code remark} is invalid.
     */
    public static Remark parseRemark(String remark) throws ParseException {
        if (remark == null) {
            return null;
        }
        String trimmedRemark = remark.trim();
        if (!Remark.isValidRemark(trimmedRemark)) {
            throw new ParseException(Remark.MESSAGE_CONSTRAINTS);
        }
        return new Remark(trimmedRemark);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException If the given {@code tag} is invalid.
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
     * Parses a {@code String tagsString}, with a comma as the delimiter, into a {@code Set<Tag>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param tagsString The string containing possibly multiple tags to be parsed.
     * @return A {@code Set<Tag>}.
     * @throws ParseException If the given {@code tagsString} is invalid.
     */
    public static Set<Tag> parseTags(String tagsString) throws ParseException {
        Set<Tag> tagSet = new HashSet<>();
        if (tagsString == null || tagsString.isBlank()) {
            return tagSet;
        }
        String trimmedTagsString = tagsString.trim();
        String[] tagList = trimmedTagsString.split(",");
        for (String tag: tagList) {
            tagSet.add(parseTag(tag));
        }
        return tagSet;
    }

    // =====  Parser methods for property attributes =============================================================

    /**
     * Parses a {@code String type} into a {@code Type}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param type The property type string to be parsed.
     * @return A {@code Type}.
     * @throws ParseException If the given {@code type} is invalid.
     */
    public static Type parsePropertyType(String type) throws ParseException {
        requireNonNull(type);
        String trimmedType = type.trim();
        if (!Type.isValidType(trimmedType)) {
            throw new ParseException(Type.MESSAGE_CONSTRAINTS);
        }
        return new Type(trimmedType);
    }

    /**
     * Parses an {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param address The property address string to be parsed.
     * @return An {@code Address}.
     * @throws ParseException If the given {@code address} is invalid.
     */
    public static Address parsePropertyAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String postal} into a {@code Postal}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param postal The property postal string to be parsed.
     * @return A {@code Postal}.
     * @throws ParseException If the given {@code postal} is invalid.
     */
    public static PostalCode parsePropertyPostal(String postal) throws ParseException {
        requireNonNull(postal);
        String trimmedPostal = postal.trim();
        if (!PostalCode.isValidPostal(trimmedPostal)) {
            throw new ParseException(PostalCode.MESSAGE_CONSTRAINTS);
        }
        return new PostalCode(trimmedPostal);
    }

    /**
     * Parses a {@code String deadline} into a {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param deadline The property deadline string to be parsed.
     * @return A {@code Deadline}.
     * @throws ParseException If the given {@code deadline} is invalid.
     */
    public static Deadline parsePropertyDeadline(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        try {
            return new Deadline(LocalDate.parse(trimmedDeadline, DateTimeFormat.INPUT_DATE_FORMAT));
        } catch (DateTimeParseException ex) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
    }

    // =====  Parser methods for client attributes ===============================================================

    /**
     * Parses a {@code String contact} into a {@code Contact}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param contact The client contact string to be parsed.
     * @return A {@code Contact}.
     * @throws ParseException If the given {@code contact} is invalid.
     */
    public static Contact parseClientContact(String contact) throws ParseException {
        if (contact == null) {
            return null;
        }
        String trimmedContact = contact.trim();
        if (!Contact.isValidContact(trimmedContact)) {
            throw new ParseException(Contact.MESSAGE_CONSTRAINTS);
        }
        return new Contact(trimmedContact);
    }

    /**
     * Parses a {@code String email} into a {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param email The client email string to be parsed.
     * @return A {@code Email}.
     * @throws ParseException If the given {@code email} is invalid.
     */
    public static Email parseClientEmail(String email) throws ParseException {
        if (email == null) {
            return null;
        }
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses an {@code String askingPrice} into an {@code AskingPrice}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param askingPrice The client asking price string to be parsed.
     * @return A {@code AskingPrice}.
     * @throws ParseException If the given {@code askingPrice} is invalid.
     */
    public static AskingPrice parseClientAskingPrice(String askingPrice) throws ParseException {
        if (askingPrice == null) {
            return null;
        }
        String trimmedAskingPrice = askingPrice.trim();
        if (!AskingPrice.isValidAskingPrice(trimmedAskingPrice)) {
            throw new ParseException(AskingPrice.MESSAGE_CONSTRAINTS);
        }
        return new AskingPrice(trimmedAskingPrice);
    }

    // =====  Parser methods for appointment attributes ==========================================================

    /**
     * Parses a {@code String date} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param date The appointment meeting date string to be parsed.
     * @return A {@code Date}.
     * @throws ParseException If the given {@code date} is invalid.
     */
    public static Date parseAppointmentDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        try {
            return new Date(LocalDate.parse(trimmedDate, DateTimeFormat.INPUT_DATE_FORMAT));
        } catch (DateTimeParseException ex) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String time} into a {@code Time}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param time The appointment meeting time string to be parsed.
     * @return A {@code Time}.
     * @throws ParseException If the given {@code time} is invalid.
     */
    public static Time parseAppointmentTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        try {
            return new Time(LocalTime.parse(trimmedTime, DateTimeFormat.INPUT_TIME_FORMAT));
        } catch (DateTimeParseException ex) {
            throw new ParseException(Time.MESSAGE_CONSTRAINTS);
        }
    }

    // =====  Parser methods for sorting =========================================================================

    /**
     * Parses {@code String sortingKey} into a {@code AppointmentSortingKey}.
     */
    public static AppointmentSortingKey parseAppointmentSortingKey(String sortingKey) throws ParseException {
        requireNonNull(sortingKey);
        String trimmedSortingKey = sortingKey.trim();
        if (!AppointmentSortingKey.isValidAppointmentSortingKey(trimmedSortingKey)) {
            throw new ParseException(AppointmentSortingKey.MESSAGE_CONSTRAINTS);
        }
        return new AppointmentSortingKey(trimmedSortingKey);
    }

    /**
     * Parses {@code String sortingKey} into a {@code PropertySortingKey}.
     */
    public static PropertySortingKey parsePropertySortingKey(String sortingKey) throws ParseException {
        requireNonNull(sortingKey);
        String trimmedSortingKey = sortingKey.trim();
        if (!PropertySortingKey.isValidPropertySortingKey(trimmedSortingKey)) {
            throw new ParseException(PropertySortingKey.MESSAGE_CONSTRAINTS);
        }
        return new PropertySortingKey(trimmedSortingKey);
    }

    /**
     * Parses {@code String sortingOrder} into a {@code SortingOrder}.
     */
    public static SortingOrder parseSortingOrder(String sortingOrder) throws ParseException {
        requireNonNull(sortingOrder);
        String trimmedSortingOrder = sortingOrder.trim();
        if (!SortingOrder.isValidSortingOrder(trimmedSortingOrder)) {
            throw new ParseException(SortingOrder.MESSAGE_CONSTRAINTS);
        }
        return new SortingOrder(trimmedSortingOrder);
    }

    /**
     * Parses {@code String amount} into an {@code Offer}.
     */
    public static Offer parseOffer(String amount) throws ParseException {
        requireNonNull(amount);
        String trimmedAmount = amount.trim();
        if (!Offer.isValidOffer(trimmedAmount)) {
            throw new ParseException(Offer.MESSAGE_CONSTRAINTS);
        }
        return new Offer(trimmedAmount);
    }
}
