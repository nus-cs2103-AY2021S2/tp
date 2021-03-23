package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.diet.DietPlanList;
import seedu.address.model.food.Food;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.user.Age;
import seedu.address.model.user.Bmi;
import seedu.address.model.user.Gender;
import seedu.address.model.user.IdealWeight;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

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
     * Parses a {@code String date} into a {@code Date}
     *
     * @param date date string
     * @return a DateTime object
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(trimmedDate, formatter);
        } catch (DateTimeParseException de) {
            throw new ParseException(MESSAGE_INVALID_DATETIME_FORMAT);
        }
        return localDate;
    }

    /**
     * Parses a {@code String value} into a {@code Double}
     *
     * @param doubleValue double string
     * @return a Double value
     * @throws ParseException if the given {@code value} is invalid.
     */
    public static Double parseDouble(String doubleValue) throws ParseException {
        requireNonNull(doubleValue);
        String trimmedValue = doubleValue.trim();
        if (!trimmedValue.matches(Food.VALIDATION_POSITIVE_DOUBLE_REGEX)) {
            throw new ParseException(Food.MESSAGE_DIGIT_CONSTRAINTS);
        }
        return Double.valueOf(trimmedValue);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static String parseFoodName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!trimmedName.matches(Food.VALIDATION_CHAR_REGEX)
                || !trimmedName.matches(Food.VALIDATION_WHITESPACE_REGEX)) {
            throw new ParseException(Food.MESSAGE_CONSTRAINTS);
        }
        return trimmedName;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static String parseFoodItemName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!trimmedName.matches(Food.VALIDATION_CHAR_REGEX_IMPORT)
                || !trimmedName.matches(Food.VALIDATION_WHITESPACE_REGEX)) {
            throw new ParseException(Food.MESSAGE_CONSTRAINTS);
        }
        return trimmedName;
    }

    /**
     * Parses a {@code String ageString} into an Integer.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static int parseAge(String ageString) throws ParseException {
        requireNonNull(ageString);
        String trimmedAge = ageString.trim();
        if (!Age.isValidAge(trimmedAge)) {
            throw new ParseException(Age.MESSAGE_CONSTRAINTS);
        }

        return Integer.parseInt(trimmedAge);
    }

    /**
     * Parses a {@code String weightHeightString} into a Double.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static double parseWeightAndHeight(String weightHeightString) throws ParseException {
        requireNonNull(weightHeightString);
        String trimmedWeightHeight = weightHeightString.trim();
        if (!Bmi.isValidWeightOrHeight(trimmedWeightHeight)) {
            throw new ParseException(Bmi.MESSAGE_CONSTRAINTS);
        }

        return Double.parseDouble(trimmedWeightHeight);
    }

    /**
     * Parses a {@code String gender}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static String parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (!Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }

        return trimmedGender;
    }

    /**
     * Parses a {@code String idealWeightString} into a Double.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static double parseIdealWeight(String idealWeightString) throws ParseException {
        requireNonNull(idealWeightString);
        String trimmedWeight = idealWeightString.trim();
        if (!IdealWeight.isValidIdealWeight(trimmedWeight)) {
            throw new ParseException(IdealWeight.MESSAGE_CONSTRAINTS);
        }

        return Double.parseDouble(trimmedWeight);
    }

    /**
     * Parses a {@code String indexString} into an Integer.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static int parsePlan(String indexString) throws ParseException {
        requireNonNull(indexString);
        String trimmedIndex = indexString.trim();
        if (!DietPlanList.isValidIndex(trimmedIndex)) {
            throw new ParseException(DietPlanList.MESSAGE_CONSTRAINTS);
        }

        return Integer.parseInt(trimmedIndex);
    }

    // *********************************************************************************

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
}
