package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_POSITIVE_INT = "Not a valid positive integer.";
    public static final String MESSAGE_INVALID_POSITIVE_DOUBLE = "Not a valid positive double.";
    public static final String MESSAGE_NO_KEYWORD = "No keyword specified.";
    public static final String MESSAGE_NO_KEYWORDS = "No keywords specified.";

    /**
     * Parse non-negative integer from String.
     * @throws ParseException if specified string is not a positive int.
     */
    public static Integer parseNonNegativeInt(String toParse) throws ParseException {
        String trimmedToParse = toParse.trim();
        if (!StringUtil.isNonNegativeInt(trimmedToParse)) {
            throw new ParseException(MESSAGE_INVALID_POSITIVE_INT);
        }
        return Integer.valueOf(trimmedToParse);
    }

    /**
     * Parse non-negative double from String.
     * @throws ParseException if specified string is not a positive double.
     */
    public static Integer parseNonNegativeDouble(String toParse) throws ParseException {
        String trimmedToParse = toParse.trim();
        if (!StringUtil.isNonNegativeDouble(trimmedToParse)) {
            throw new ParseException(MESSAGE_INVALID_POSITIVE_DOUBLE);
        }
        return Integer.valueOf(trimmedToParse);
    }

    /**
     * Parse single keyword
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
     * Parse space separated keywords
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
    public static String parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
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
        String trimmedEmail = email.trim();
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

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
