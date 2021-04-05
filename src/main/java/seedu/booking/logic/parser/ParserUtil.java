package seedu.booking.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.booking.commons.core.index.Index;
import seedu.booking.commons.util.StringUtil;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.Tag;
import seedu.booking.model.booking.Description;
import seedu.booking.model.booking.EndTime;
import seedu.booking.model.booking.Id;
import seedu.booking.model.booking.StartTime;
import seedu.booking.model.booking.VenueNameContainsKeywordsPredicate;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.EmailMatchesKeywordPredicate;
import seedu.booking.model.person.Name;
import seedu.booking.model.person.NameContainsKeywordsPredicate;
import seedu.booking.model.person.PersonTagContainsKeywordsPredicate;
import seedu.booking.model.person.Phone;
import seedu.booking.model.person.PhoneMatchesKeywordPredicate;
import seedu.booking.model.venue.Capacity;
import seedu.booking.model.venue.CapacityMatchesKeywordPredicate;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueDescContainsKeywordsPredicate;
import seedu.booking.model.venue.VenueName;
import seedu.booking.model.venue.VenueTagContainsKeywordsPredicate;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String DEFAULT_DESCRIPTION = "No description provided.";
    private static final Capacity DEFAULT_CAPACITY = new Capacity(10);

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
     * Parses a {@code String bookingId} into a {@code int}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Id parseBookingId(String bookingId) throws ParseException {
        String trimmedIndex = bookingId.trim();
        //if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
        //    throw new ParseException(MESSAGE_INVALID_INDEX);
        //}
        return new Id(trimmedIndex);
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
     * Parses a {@code String phoneKeyword} into a {@code PhoneContainsKeywordsPredicate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phoneKeyword} is invalid.
     */
    public static PhoneMatchesKeywordPredicate parsePhoneContainsKeywordsPredicate(String phoneKeyword)
            throws ParseException {
        requireNonNull(phoneKeyword);
        String trimmedPhoneKeyword = phoneKeyword.trim();
        if (!Phone.isValidPhone(trimmedPhoneKeyword)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new PhoneMatchesKeywordPredicate(trimmedPhoneKeyword);
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
     * Parses a {@code String emailKeyword} into an {@code EmailContainsKeywordsPredicate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code emailKeyword} is invalid.
     */
    public static EmailMatchesKeywordPredicate parseEmailContainsKeywordsPredicate(String emailKeyword)
            throws ParseException {
        requireNonNull(emailKeyword);
        String trimmedEmailKeyword = emailKeyword.trim();
        if (!Email.isValidEmail(trimmedEmailKeyword)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new EmailMatchesKeywordPredicate(trimmedEmailKeyword);
    }

    /**
     * Parses a {@code String description} into a {@code String description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static Description parseBookingDescription(String description) {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String description} into a {@code String description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static String parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return trimmedDescription;
    }

    /**
     * Parses a {@code String keywords} into a {@code VenueDescContainsKeywordsPredicate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static VenueDescContainsKeywordsPredicate parseVenueDescContainsKeywordsPredicate(String descKeywords) {
        requireNonNull(descKeywords);
        String trimmedDescKeywords = descKeywords.trim();
        String[] splitDescKeywords = trimmedDescKeywords.split("\\s+");
        return new VenueDescContainsKeywordsPredicate(Arrays.asList(splitDescKeywords));
    }


    /**
     * Parses a {@code String bookingStart} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static StartTime parseBookingStart(String bookingStart) {
        requireNonNull(bookingStart);
        String trimmedBookingStart = bookingStart.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(trimmedBookingStart, formatter);
        return new StartTime(dateTime);
    }


    /**
     * Parses a {@code String bookingStart} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static EndTime parseBookingEnd(String bookingEnd) {
        requireNonNull(bookingEnd);
        String trimmedBookingEnd = bookingEnd.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(trimmedBookingEnd, formatter);
        return new EndTime(dateTime);
    }


    /**
     * Parses a {@code String venue} into a {@code Venue}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code venue} is invalid.
     */
    public static Venue parseVenue(String venue) throws ParseException {
        requireNonNull(venue);
        String trimmedVenue = venue.trim();
        if (!VenueName.isValidVenueName(trimmedVenue)) {
            throw new ParseException(VenueName.MESSAGE_CONSTRAINTS);
        }
        return new Venue(new VenueName(trimmedVenue), DEFAULT_CAPACITY, DEFAULT_DESCRIPTION, new HashSet<>());
    }

    /**
     * Parses a {@code String capacity} into an integer.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code capacity} is invalid.
     */
    public static Capacity parseCapacity(String capacity) throws ParseException, NumberFormatException {
        requireNonNull(capacity);
        String trimmedCapacity = capacity.trim();
        try {
            Integer formattedCapacity = Integer.parseInt(trimmedCapacity);
            if (!Capacity.isValidCapacity(formattedCapacity)) {
                throw new ParseException(Capacity.MESSAGE_CONSTRAINTS);
            }
            assert Capacity.isValidCapacity(Integer.parseInt(trimmedCapacity));
            return new Capacity(formattedCapacity);
        } catch (NumberFormatException e) {
            throw new ParseException(Capacity.MESSAGE_INVALID);
        }
    }

    /**
     * Parses a {@code String capacityKeyword} into a {@code CapacityContainsKeywordsPredicate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code capacity} is invalid.
     */
    public static CapacityMatchesKeywordPredicate parseCapacityContainsKeywordsPredicate(String capacityKeyword)
            throws ParseException, NumberFormatException {
        requireNonNull(capacityKeyword);
        String trimmedCapacityKeyword = capacityKeyword.trim();
        try {
            Integer formattedCapacityKeyword = Integer.parseInt(trimmedCapacityKeyword);
            if (!Capacity.isValidCapacity(formattedCapacityKeyword)) {
                throw new ParseException(Capacity.MESSAGE_CONSTRAINTS);
            }
            assert Capacity.isValidCapacity(Integer.parseInt(trimmedCapacityKeyword));
            return new CapacityMatchesKeywordPredicate(formattedCapacityKeyword);
        } catch (NumberFormatException e) {
            throw new ParseException(Capacity.MESSAGE_INVALID);
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

    /**
     * Parses a {@code String tagKeyword} into a {@code PersonTagContainsKeywordsPredicate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tagKeyword} is invalid.
     */
    public static PersonTagContainsKeywordsPredicate parsePersonTagContainsKeywordsPredicate(String tagKeyword)
            throws ParseException {
        requireNonNull(tagKeyword);
        String trimmedTagKeyword = tagKeyword.trim();
        if (!Tag.isValidTagName(trimmedTagKeyword)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new PersonTagContainsKeywordsPredicate(trimmedTagKeyword);
    }

    /**
     * Parses a {@code String tagKeyword} into a {@code VenueTagContainsKeywordsPredicate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tagKeyword} is invalid.
     */
    public static VenueTagContainsKeywordsPredicate parseVenueTagContainsKeywordsPredicate(String tagKeyword)
            throws ParseException {
        requireNonNull(tagKeyword);
        String trimmedTagKeyword = tagKeyword.trim();
        if (!Tag.isValidTagName(trimmedTagKeyword)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new VenueTagContainsKeywordsPredicate(trimmedTagKeyword);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} for commands with multi-step prompts.
     */
    public static Set<Tag> parseTagsForPromptCommands(String str) throws ParseException {
        requireNonNull(str);
        final Set<Tag> tagSet = new HashSet<>();
        if (!str.equals("")) {
            String[] tags = str.split(",");
            for (String tag : tags) {
                if (!Tag.isValidTagName(tag.trim())) {
                    throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
                }
                tagSet.add(new Tag(tag.trim()));
            }
        }
        return tagSet;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    public static Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses a {@code String name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static VenueName parseVenueName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!VenueName.isValidVenueName(trimmedName)) {
            throw new ParseException(VenueName.MESSAGE_CONSTRAINTS);
        }
        return new VenueName(trimmedName);
    }

    /**
     * Parses a {@code String venueKeywords} into a {@code VenueNameContainsKeywordsPredicate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code venueKeywords} is invalid.
     */
    public static VenueNameContainsKeywordsPredicate parseVenueNameContainsKeywordsPredicate(String venueKeywords)
            throws ParseException {
        requireNonNull(venueKeywords);
        String trimmedVenueKeywords = venueKeywords.trim();
        if (!VenueName.isValidVenueName(trimmedVenueKeywords)) {
            throw new ParseException(VenueName.MESSAGE_CONSTRAINTS);
        }
        String[] splitVenueKeywords = trimmedVenueKeywords.split("\\s+");
        return new VenueNameContainsKeywordsPredicate(Arrays.asList(splitVenueKeywords));
    }

    /**
     * Parses a {@code String nameKeywords} into a {@code NameContainsKeywordsPredicate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nameKeywords} is invalid.
     */
    public static NameContainsKeywordsPredicate parseNameContainsKeywordsPredicate(String nameKeywords)
            throws ParseException {
        requireNonNull(nameKeywords);
        String trimmedNameKeywords = nameKeywords.trim();
        if (!Name.isValidName(trimmedNameKeywords)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        String[] splitNameKeywords = trimmedNameKeywords.split("\\s+");
        return new NameContainsKeywordsPredicate(Arrays.asList(splitNameKeywords));
    }
}
