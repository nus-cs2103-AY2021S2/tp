package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_FILE;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.TimeUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Debt;
import seedu.address.model.person.Email;
import seedu.address.model.person.Event;
import seedu.address.model.person.Goal;
import seedu.address.model.person.Goal.Frequency;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_INDEXES = "One of of the indexes is not a non-zero unsigned integer.";

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
     * Parses a {@code String birthday} into a {@code Birthday}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code birthday} is invalid.
     */
    public static Birthday parseBirthday(String birthday) throws ParseException {
        requireNonNull(birthday);
        String trimmedBirthday = birthday.trim();
        LocalDate birthdayDate = DateUtil.fromDateInput(trimmedBirthday);
        return new Birthday(birthdayDate);
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
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses a {@code String indexes} into a {@code List<Index>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static List<Index> parseIndexes(String indexes) throws ParseException {
        requireNonNull(indexes);
        String[] indexStringArray = indexes.trim().split(" ");
        List<Index> indexArray = new ArrayList<>();

        try {
            for (String s : indexStringArray) {
                indexArray.add(parseIndex(s));
            }
        } catch (ParseException e) {
            throw new ParseException(MESSAGE_INVALID_INDEXES);
        }
        return indexArray;
    }

    /**
     * Parses a {@code String} into a {@code Path}
     *
     * @throws ParseException if the given {@code pathStr} is invalid
     */
    public static Path parseFilePath(String pathStr) throws ParseException {
        requireNonNull(pathStr);

        if (!FileUtil.isValidPath(pathStr)) {
            throw new ParseException(String.format(MESSAGE_INVALID_FILE, pathStr));
        }

        return Path.of(pathStr);
    }

    /**
     * Parses a {@code String} into a {@code LocalDate}
     *
     * @throws ParseException if the given {@code String} is invalid
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        return DateUtil.fromDateInput(trimmedDate);
    }

    /**
     * Parses a {@code String} into a {@code LocalTime}
     *
     * @throws ParseException if the given {@code String} is invalid
     */
    public static LocalTime parseTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        return TimeUtil.fromTimeInput(trimmedTime);
    }

    /**
     * Parses a {@code String} as an {@code Event} description
     */
    public static String parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Event.isValidDescription(trimmedDescription)) {
            throw new ParseException(Event.DESCRIPTION_MESSAGE_CONSTRAINTS);
        }
        return trimmedDescription;
    }

    /**
     * Parses a {@code String} as a positive {@code Debt}
     */
    public static Debt parsePositiveDebt(String debt) throws ParseException {
        requireNonNull(debt);
        String trimmedDebt = debt.trim();
        if (!Debt.isValidDebt(trimmedDebt)) {
            throw new ParseException(Debt.MESSAGE_CONSTRAINTS);
        }
        return new Debt(debt);
    }
    /**
     * Parses a {@code String} into a {@code Frequency}
     *
     * @throws ParseException if the given {@code String} is an invalid frequency.
     */
    public static Frequency parseFrequency(String frequencyString) throws ParseException {
        return Goal.parseFrequency(frequencyString);
    }
}
