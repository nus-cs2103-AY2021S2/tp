package seedu.dictionote.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.commons.util.StringUtil;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.logic.parser.exceptions.ParseException;
import seedu.dictionote.model.contact.Address;
import seedu.dictionote.model.contact.Email;
import seedu.dictionote.model.contact.Name;
import seedu.dictionote.model.contact.Phone;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_POSITION = "Position is not a between 1 to 9 (inclusively).";

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
     * Parses a {@code String dictionote} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dictionote} is invalid.
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
     * Parses a {@code String noteContent} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code note} is invalid.
     */
    public static Note parseNote(String noteContent) throws ParseException {
        requireNonNull(noteContent);
        Note note = new Note(noteContent);
        return note;
    }

    /**
     * Parses a {@code String week} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code week} is invalid.
     */
    public static String parseWeek(String week) throws ParseException {
        requireNonNull(week);
        String trimmedWeek = week.trim();
        return trimmedWeek;
    }

    /**
     * Parses a {@code String header} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code header} is invalid.
     */
    public static String parseHeader(String header) throws ParseException {
        requireNonNull(header);
        String trimmedHeader = header.trim();
        return trimmedHeader;
    }

    /**
     * Parses a {@code String maincontent} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code maincontent} is invalid.
     */
    public static String parseMainContent(String maincontent) throws ParseException {
        requireNonNull(maincontent);
        String trimmedMainContent = maincontent.trim();
        return trimmedMainContent;
    }

    /** Parses a {@code String option} into a {@code UiOptionAction}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code option} is invalid.
     */
    public static UiActionOption parseUiActionOption(String option) throws ParseException {
        requireNonNull(option);
        String trimmedOption = option.trim();
        if (!UiActionOption.isValidOption(trimmedOption)) {
            throw new ParseException(UiActionOption.MESSAGE_CONSTRAINTS);
        }
        return UiActionOption.getUiActionOption(trimmedOption);
    }

    /**
     * Parses {@code position} into an {@code int} and returns it. Leading and trailing whitespaces will be trimmed.
     * return 0 if the specified position is invalid (not between 1 to 9).
     */
    public static int parsePosition(String oneBasedIndex) {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            return 0;
        }

        int position = Integer.parseInt(trimmedIndex);

        if (position > 9 || position < 1) {
            return 0;
        }
        return position;
    }
}
