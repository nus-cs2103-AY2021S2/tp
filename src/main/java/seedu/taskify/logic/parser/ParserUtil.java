package seedu.taskify.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.taskify.commons.util.StringUtil.extractStringArguments;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.taskify.commons.core.index.Index;
import seedu.taskify.commons.util.StringUtil;
import seedu.taskify.logic.parser.exceptions.ParseException;
import seedu.taskify.model.tag.Tag;
import seedu.taskify.model.task.Address;
import seedu.taskify.model.task.Date;
import seedu.taskify.model.task.Description;
import seedu.taskify.model.task.Name;
import seedu.taskify.model.task.Status;
import seedu.taskify.model.task.StatusType;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_AT_LEAST_ONE_INVALID_INDEX = "At least one Index is not a non-zero unsigned " +
            "integer.";
    public static final String MESSAGE_PARSE_MULTIPLE_INDEX_ON_SINGLE_INDEX = "The string passed to ParserUtil" +
            ".parseMultipleIndex() contains only one argument";

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
     * Parses {@code oneBasedIndexes} into a list of {@code Index} and returns it.
     * @param oneBasedIndexes user's input excluding the command word
     * @return a List<Index> representing all valid indexes in {@code oneBasedIndexes}
     * @throws ParseException if at least one argument in {@code argumentInput} is an invalid index.
     */
    public static List<Index> parseMultipleIndex(String oneBasedIndexes) throws ParseException {
        String[] arguments = extractStringArguments(oneBasedIndexes);
        boolean hasOnlyOneArgument = arguments.length == 1;

        if (hasOnlyOneArgument) {
            // throw new AssertionError instead?
            throw new ParseException(MESSAGE_PARSE_MULTIPLE_INDEX_ON_SINGLE_INDEX);
        }

        List<Index> parsedIndexes = new ArrayList<>();
        for (String argument : arguments) {
            boolean isValidIndex = StringUtil.isNonZeroUnsignedInteger(argument);
            if (!isValidIndex) { /* Not necessary, but reduces coupling with TaskifyParser since TaskifyParser
            depends on hasMultipleValidIndex() to check if all indexes are valid in the user's input */
                throw new ParseException(MESSAGE_AT_LEAST_ONE_INVALID_INDEX);
                // throw new AssertionError instead?
            }
            parsedIndexes.add(Index.fromOneBased(Integer.parseInt(argument)));
        }
        return parsedIndexes;
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
     * Parses a {@code String description} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
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
     * Parses a {@code String status} into a {@code Status}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatusString = status.trim().toLowerCase();
        if (!Status.isValidStatus(trimmedStatusString)) {
            throw new ParseException(Status.MESSAGE_CONSTRAINTS);
        }
        StatusType statusType = Status.getStatusType(trimmedStatusString);
        return new Status(statusType);
    }

    /**
     * Parses a {@code String date} into an {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedDate);
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
