package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.Alias;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.issue.Category;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Timestamp;
import seedu.address.model.resident.Email;
import seedu.address.model.resident.Name;
import seedu.address.model.resident.Phone;
import seedu.address.model.resident.Room;
import seedu.address.model.resident.Year;
import seedu.address.model.room.IsOccupied;
import seedu.address.model.room.RoomType;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX_RANGE = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_INDEX_TYPE = "Index is not an integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException, IllegalArgumentException {
        String trimmedIndex = oneBasedIndex.trim();

        // If its flat out NOT a number, throw illegal arg ex
        if (!StringUtil.isInteger(trimmedIndex)) {
            throw new IllegalArgumentException(MESSAGE_INVALID_INDEX_TYPE);
        }

        // If its <= 0, throw parse ex
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX_RANGE);
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
     * Parses a {@code String year} into an {@code Year}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code year} is invalid.
     */
    public static Year parseYear(String year) throws ParseException {
        requireNonNull(year);
        String trimmedYear = year.trim();
        if (!Year.isValidYear(trimmedYear)) {
            throw new ParseException(Year.MESSAGE_CONSTRAINTS);
        }
        return new Year(trimmedYear);
    }

    /**
     * Parses a {@code String room} into an {@code Room}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code room} is invalid.
     */
    public static Room parseRoom(String room) throws ParseException {
        requireNonNull(room);
        String trimmedRoom = room.trim();
        if (!Room.isValidRoom(trimmedRoom)) {
            throw new ParseException(Room.MESSAGE_CONSTRAINTS);
        }
        return new Room(trimmedRoom);
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
     * Parses a {@code String roomNumber} into a {@code RoomNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code roomNumber} is invalid.
     */
    public static seedu.address.model.room.RoomNumber parseRoomNumber(String roomNumber) throws ParseException {
        requireNonNull(roomNumber);
        String trimmedRoomNumber = roomNumber.trim();
        if (!seedu.address.model.room.RoomNumber.isValidRoomNumber(trimmedRoomNumber)) {
            throw new ParseException(seedu.address.model.room.RoomNumber.MESSAGE_CONSTRAINTS);
        }
        return new seedu.address.model.room.RoomNumber(trimmedRoomNumber);
    }

    /**
     * Parses a {@code String roomType} into a {@code RoomType}.
     * Leading and trailing whitespaces will be trimmed, and text will be converted to uppercase.
     *
     * @throws ParseException if the given {@code roomType} is invalid.
     */
    public static RoomType parseRoomType(String roomType) throws ParseException {
        requireNonNull(roomType);
        String trimmedRoomType = roomType.toUpperCase().trim();
        if (!RoomType.isValidRoomType(trimmedRoomType)) {
            throw new ParseException(RoomType.MESSAGE_CONSTRAINTS);
        }
        return new RoomType(trimmedRoomType);
    }

    /**
     * Parses a {@code String roomOccupancyStatus} into a {@code IsOccupied}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code roomOccupancyStatus} is invalid.
     */
    public static IsOccupied parseRoomOccupancyStatus(String roomOccupancyStatus) throws ParseException {
        requireNonNull(roomOccupancyStatus);
        String trimmedRoomOccupancyStatus = roomOccupancyStatus.trim();
        if (!IsOccupied.isValidOccupancyStatus(trimmedRoomOccupancyStatus)) {
            throw new ParseException(IsOccupied.MESSAGE_CONSTRAINTS);
        }
        return new IsOccupied(trimmedRoomOccupancyStatus);
    }

    //==========Issue Parsing Method========================================================

    /**
     * Parses a {@code String roomNumber} into a {@code RoomNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code roomNumber} is invalid.
     */
    public static seedu.address.model.issue.RoomNumber parseIssueRoomNumber(String roomNumber) throws ParseException {
        requireNonNull(roomNumber);
        String trimmedRoomNumber = roomNumber.trim();
        if (!seedu.address.model.issue.RoomNumber.isValidRoomNumber(trimmedRoomNumber)) {
            throw new ParseException(seedu.address.model.issue.RoomNumber.MESSAGE_CONSTRAINTS);
        }
        return new seedu.address.model.issue.RoomNumber(trimmedRoomNumber);
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
     * Parses a {@code String timestamp} into a {@code Timestamp}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code timestamp} is invalid.
     */
    public static Timestamp parseTimestamp(String timestamp) throws ParseException {
        requireNonNull(timestamp);
        String trimmedTimestamp = timestamp.trim();
        if (!Timestamp.isValidTimestamp(trimmedTimestamp)) {
            throw new ParseException(Timestamp.MESSAGE_CONSTRAINTS);
        }
        return new Timestamp(trimmedTimestamp);
    }

    /**
     * Parses a {@code String status} into a {@code Status}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code status} is invalid.
     */
    public static Status parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        if (!Status.isValidStatus(trimmedStatus)) {
            throw new ParseException(Status.MESSAGE_CONSTRAINTS);
        }
        return new Status(trimmedStatus);
    }

    /**
     * Parses a {@code String category} into a {@code Category}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code category} is invalid.
     */
    public static Category parseCategory(String category) throws ParseException {
        if (category == null) {
            return null;
        }

        String trimmedCategory = category.trim();
        if (!Category.isValidCategory(trimmedCategory)) {
            throw new ParseException(Category.MESSAGE_CONSTRAINTS);
        }
        return new Category(trimmedCategory);
    }

    //==========Alias Parsing Method========================================================

    /**
     * Parses a {@code String aliasName} and {@code String command} into a {@code Alias}.
     * @param aliasName name of the alias
     * @param command content of the command
     * @throws ParseException if the inputs are invalid
     */
    public static Alias parseAlias(String aliasName, String command) throws ParseException {
        if (!Alias.isValidName(aliasName)) {
            throw new ParseException(Alias.MESSAGE_NAME_CONSTRAINTS);
        }
        if (!Alias.isValidCommand(command)) {
            throw new ParseException(Alias.MESSAGE_COMMAND_CONSTRAINTS);
        }
        return new Alias(aliasName, command);
    }
}
