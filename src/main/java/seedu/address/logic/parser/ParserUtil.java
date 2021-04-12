package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    // ========== ERROR MESSAGES ==========

    public static final String MESSAGE_INVALID_NAME = "Invalid name specified.";
    public static final String MESSAGE_INVALID_PHONE = "Invalid phone number specified.";
    public static final String MESSAGE_INVALID_ADDRESS = "Invalid address specified.";
    public static final String MESSAGE_INVALID_EMAIL = "Invalid email address specified.";
    public static final String MESSAGE_INVALID_INGREDIENT = "Invalid ingredient name specified.";
    public static final String MESSAGE_INVALID_DISH = "Invalid dish name specified.";

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_NONNEGATIVE_INT = "Non-negative integer must be specified.";
    public static final String MESSAGE_INVALID_PRICE = "Price must be a non-negative double.";

    public static final String MESSAGE_NO_KEYWORD = "No keyword specified.";
    public static final String MESSAGE_NO_KEYWORDS = "No keywords specified.";
    public static final String MESSAGE_MISMATCHED_LISTS = "Argument lists are different lengths.";

    // ========== REGEXES ==========

    // Name validation: name cannot start with whitespace, or " " can be a valid name.
    public static final String VALID_NAME_REGEX = "[^ ].*";

    // Phone validation: must contain numerical characters only.
    public static final String VALID_PHONE_REGEX = "[0-9]*";

    // Address validation: address cannot start with whitespace, or " " can be a valid address.
    public static final String VALID_ADDRESS_REGEX = "[^ ].*";

    // Ingredient name validation: ingredient name cannot start with whitespace, or " " can be a valid ingredient name.
    public static final String VALID_INGREDIENT_REGEX = "[^ ].*";

    // Dish name validation: dish name cannot start with whitespace, or " " can be a valid dish name.
    public static final String VALID_DISH_REGEX = "[^ ].*";

    // Email address validation: must conform to the form local-part@domain
    // Assumes IP addresses are not used as domain portion
    private static final String SPECIAL_CHARACTERS = "!#$%&'*+/=?`{|}~^.-";
    // Local part contains alphanumeric characters and special characters
    private static final String LOCAL_PART_REGEX = "^[\\w" + SPECIAL_CHARACTERS + "]+";
    // Domain's first character can be word characters, except underscore
    private static final String DOMAIN_FIRST_CHARACTER_REGEX = "[^\\W_]";
    // Domain's middle characters can be alphanumeric characters, period, and hyphen
    private static final String DOMAIN_MIDDLE_REGEX = "[a-zA-Z0-9.-]*";
    // Domain's last character must be word characters, except underscore
    private static final String DOMAIN_LAST_CHARACTER_REGEX = "[^\\W_]$";
    public static final String VALID_EMAIL_REGEX = LOCAL_PART_REGEX + "@"
            + DOMAIN_FIRST_CHARACTER_REGEX + DOMAIN_MIDDLE_REGEX + DOMAIN_LAST_CHARACTER_REGEX;

    // ========== GENERAL ==========

    /**
     * Parse non-negative integer from String.
     * @throws ParseException if specified string is not a positive int.
     */
    public static Integer parseNonNegativeInt(String toParse) throws ParseException {
        String trimmedToParse = toParse.trim();
        if (!StringUtil.isNonNegativeInt(trimmedToParse)) {
            throw new ParseException(MESSAGE_INVALID_NONNEGATIVE_INT);
        }
        return Integer.valueOf(trimmedToParse);
    }

    /**
     * Parses a single keyword.
     * @throws ParseException if specified string is empty.
     */
    public static String parseKeyword(String keyword) throws ParseException {
        String trimmedKeyword = keyword.trim();
        if (trimmedKeyword.isEmpty()) {
            throw new ParseException(MESSAGE_NO_KEYWORD);
        }
        return trimmedKeyword;
    }

    /**
     * Parses space-separated keywords.
     * @throws ParseException if specified string is empty.
     */
    public static List<String> parseKeywords(String keywords) throws ParseException {
        String trimmedKeywords = keywords.trim();
        if (trimmedKeywords.isEmpty()) {
            throw new ParseException(MESSAGE_NO_KEYWORDS);
        }
        return Arrays.asList(trimmedKeywords.split("\\s+"));
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
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
     * Guarantees that two lists have the same length.
     * @param listA The first list in the comparison.
     * @param listB The other list in the comparison.
     * @throws ParseException if the two lists do not have the same length.
     */
    public static void validateListLengths(List listA, List listB) throws ParseException {
        requireNonNull(listA);
        requireNonNull(listB);
        if (listA.size() != listB.size()) {
            throw new ParseException(MESSAGE_MISMATCHED_LISTS);
        }
    }

    /**
     * Removes duplicate items from a list of strings.
     * @param stringList
     * @return A copy of the original list, but with duplicates removed.
     */
    public static List<String> deduplicateStringList(List<String> stringList) {
        HashSet<String> deduplicatedSet = new HashSet<>(stringList);
        List<String> deduplicatedList = new ArrayList<>(deduplicatedSet);
        return deduplicatedList;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    // ========== PERSON ==========

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static String parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!trimmedName.matches(VALID_NAME_REGEX)) {
            throw new ParseException(MESSAGE_INVALID_NAME);
        }
        return trimmedName;
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static String parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!trimmedPhone.matches(VALID_PHONE_REGEX)) {
            throw new ParseException(MESSAGE_INVALID_PHONE);
        }
        return trimmedPhone;
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static String parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!trimmedAddress.matches(VALID_ADDRESS_REGEX)) {
            throw new ParseException(MESSAGE_INVALID_ADDRESS);
        }
        return trimmedAddress;
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static String parseEmail(String email) throws ParseException {
        requireNonNull(email);

        // Convert email to lowercase, just in case
        String trimmedEmail = email.toLowerCase().trim();
        if (!trimmedEmail.matches(VALID_EMAIL_REGEX)) {
            throw new ParseException(MESSAGE_INVALID_EMAIL);
        }
        return trimmedEmail;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static String parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        return trimmedTag;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static List<String> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        return new ArrayList<>(tags);
    }

    // ========== INGREDIENT ==========

    /**
     * Parses a {@code String ingredientName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ingredientName} is invalid.
     */
    public static String parseIngredientName(String ingredientName) throws ParseException {
        requireNonNull(ingredientName);
        String trimmedIngredientName = ingredientName.trim();
        if (!trimmedIngredientName.matches(VALID_INGREDIENT_REGEX)) {
            throw new ParseException(MESSAGE_INVALID_INGREDIENT);
        }
        return trimmedIngredientName;
    }

    // ========== DISH ==========

    /**
     * Parses a {@code String dishName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dishName} is invalid.
     */
    public static String parseDishName(String dishName) throws ParseException {
        requireNonNull(dishName);
        String trimmedDishName = dishName.trim();
        if (!trimmedDishName.matches(VALID_DISH_REGEX)) {
            throw new ParseException(MESSAGE_INVALID_DISH);
        }
        return trimmedDishName;
    }


    /**
     * Parse non-negative double from String.
     * @throws ParseException if specified string is not a positive double.
     */
    public static Double parsePrice(String price) throws ParseException {
        String trimmedToParse = price.trim();
        if (!StringUtil.isNonNegativeDouble(trimmedToParse)) {
            throw new ParseException(MESSAGE_INVALID_PRICE);
        }
        return Double.valueOf(trimmedToParse);
    }


}
