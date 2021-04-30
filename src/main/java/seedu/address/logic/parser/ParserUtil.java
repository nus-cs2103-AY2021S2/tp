package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS_STRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME_STRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE_STRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_STRING;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.TripDay;
import seedu.address.model.TripTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.passenger.Address;
import seedu.address.model.person.passenger.Price;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_NO_ARGS = "No indexes were provided.";

    /**
     * Prevents ParserUtil from being instantiated.
     */
    private ParserUtil() {}

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
     * Parses a {@code String tripDay} into a {@code TripDay}.
     * Leading and trailing whitespaces will be trimmed and converted to uppercase for case-insensitive enum
     * instantiation of {@code DayOfWeek}.
     * @throws ParseException if the given {@code tripDay} is invalid.
     */
    public static TripDay parseTripDay(String tripDay) throws ParseException {
        requireNonNull(tripDay);
        String trimmedTripDay = tripDay.trim().toUpperCase();
        DayOfWeek day;
        try {
            day = DayOfWeek.valueOf(trimmedTripDay);
        } catch (IllegalArgumentException e) {
            throw new ParseException(TripDay.MESSAGE_CONSTRAINTS);
        }
        return new TripDay(day);
    }

    /**
     * Parses a {@code String tripTime} into a {@code TripTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tripTime} is invalid.
     */
    public static TripTime parseTripTime(String tripTime) throws ParseException {
        requireNonNull(tripTime);
        String trimmedTripTime = tripTime.trim();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
        LocalTime parsedTimeObject;
        try {
            parsedTimeObject = LocalTime.parse(trimmedTripTime, timeFormat);
        } catch (DateTimeParseException e) {
            throw new ParseException(TripTime.MESSAGE_CONSTRAINTS);
        }

        return new TripTime(parsedTimeObject);
    }

    /**
     * Parses a {@code String price} into a {@code Price}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code price} is invalid.
     */
    public static Price parsePrice(String price) throws ParseException {
        requireNonNull(price);
        String trimmedPrice = price.trim();
        if (!Price.isValidPrice(price)) {
            throw new ParseException(Price.MESSAGE_CONSTRAINTS);
        }

        return new Price(Double.parseDouble(trimmedPrice));
    }

    /**
     * Parses {@code Collection<String> indices} into a {@code Set<Index>}.
     */
    public static Set<Index> parseIndices(Collection<String> indices) throws ParseException {
        requireNonNull(indices);
        final Set<Index> indicesSet = new HashSet<>();
        for (String index : indices) {
            if (index.isBlank() || !index.chars().allMatch(Character::isDigit)) {
                throw new ParseException(MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);
            }
            indicesSet.add(parseIndex(index));
        }
        return indicesSet;
    }

    /**
     * Parses {@code oneBasedIndexes} into an {@code List<Index>} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static List<Index> parseDeleteIndex(String oneBasedIndexes) throws ParseException {
        String[] arguments = oneBasedIndexes.split("\\s+");
        List<Index> indexes = new ArrayList<>();

        for (String s : arguments) {
            if (s.length() > 0) {
                Index index = ParserUtil.parseIndex(s.trim());
                indexes.add(index);
            }
        }

        if (indexes.size() > 0) {
            return indexes;
        } else {
            throw new ParseException(MESSAGE_NO_ARGS);
        }
    }

    /**
     * Parses the keywords for each of the predicates. If parsing fails, the keyword is dropped as it isn't a valid
     * search term.
     * @param keywords search terms to be parsed.
     * @param prefix prefix to operate on.
     * @return list of valid keywords to search on for corresponding prefix.
     */
    public static List<String> parseAttributePredicateKeywords(List<String> keywords, String prefix) {
        List<String> outputList = new ArrayList<>();

        for (String s : keywords) {
            try {
                switch (prefix) {
                case PREFIX_NAME_STRING:
                    outputList.add(ParserUtil.parseName(s).toString());
                    break;
                case PREFIX_ADDRESS_STRING:
                    outputList.add(ParserUtil.parseAddress(s).toString());
                    break;
                case PREFIX_PHONE_STRING:
                    outputList.add(ParserUtil.parsePhone(s).toString());
                    break;
                case PREFIX_TAG_STRING:
                    outputList.add(ParserUtil.parseTag(s).toString());
                    break;
                default:
                    break;
                }

            } catch (ParseException ignored) {
                // keyword is dropped as it isn't a valid term determined by parser
                // exception dropped as an invalid argument for 1 of the prefixes does not necessarily apply to another
            }
        }

        return outputList;
    }
}
