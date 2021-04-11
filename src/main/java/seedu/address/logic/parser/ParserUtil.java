package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.ContactEmail;
import seedu.address.model.contact.ContactName;
import seedu.address.model.contact.ContactPhone;
import seedu.address.model.entry.EntryDate;
import seedu.address.model.entry.EntryName;
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

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(String.format("Index given: %s\n%s", trimmedIndex, MESSAGE_INVALID_INDEX));
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    //Contact methods--------------------------------------------
    /**
     * Parses a {@code String name} into a {@code ContactName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static ContactName parseContactName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!ContactName.isValidName(trimmedName)) {
            throw new ParseException(String.format("Name given: %s\n%s", trimmedName, ContactName.MESSAGE_CONSTRAINTS));
        }
        return new ContactName(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code ContactPhone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static ContactPhone parseContactPhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!ContactPhone.isValidPhone(trimmedPhone)) {
            throw new ParseException(String
                    .format("Phone number given: %s\n%s", trimmedPhone, ContactPhone.MESSAGE_CONSTRAINTS));
        }
        return new ContactPhone(trimmedPhone);
    }

    /**
     * Parses a {@code String email} into an {@code ContactEmail}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static ContactEmail parseContactEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!ContactEmail.isValidEmail(trimmedEmail)) {
            throw new ParseException(String
                    .format("Email given: %s\n%s", trimmedEmail, ContactEmail.MESSAGE_CONSTRAINTS));
        }
        return new ContactEmail(trimmedEmail);
    }


    //to be deleted-----------------------------------
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
            throw new ParseException(String.format("Name given: %s\n%s", trimmedName, Name.MESSAGE_CONSTRAINTS));
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
            throw new ParseException(String.format("Phone number given: %s\n%s",
                    trimmedPhone, Phone.MESSAGE_CONSTRAINTS));
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
            throw new ParseException(String.format("Email given: %s\n%s", trimmedEmail, Email.MESSAGE_CONSTRAINTS));
        }
        return new Email(trimmedEmail);
    }
    //---------------------------------------------------------

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim().substring(0, 6);
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(String.format("Tag given: %s\n%s", trimmedTag, Tag.MESSAGE_CONSTRAINTS));
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
     * Parses a {@code String entryName} into a {@code EntryName}.
     */
    public static EntryName parseEntryName(String entryName) throws ParseException {
        requireNonNull(entryName);
        String trimmedEntryName = entryName.trim();
        if (!EntryName.isValidName(trimmedEntryName)) {
            throw new ParseException(String.format("Name given: %s\n%s", trimmedEntryName, EntryName.NAME_CONSTRAINTS));
        }
        return new EntryName(trimmedEntryName);
    }

    /**
     * Parses a {@code String entryDate} into a {@code EntryDate}
     */
    public static EntryDate parseEntryDate(String entryDate) throws ParseException {
        requireNonNull(entryDate);
        String trimmedEntryDate = entryDate.trim();
        if (!EntryDate.isValidDate(trimmedEntryDate)) {
            throw new ParseException(String.format("Date given: %s\n%s", trimmedEntryDate, EntryDate.DATE_CONSTRAINTS));
        }
        return new EntryDate(trimmedEntryDate);
    }
}
