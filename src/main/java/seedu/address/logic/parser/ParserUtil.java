package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Description;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Title;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Input is not a non-zero unsigned integer";
    public static final String MESSAGE_INVALID_INDEX_1 = "Input for the ";
    public static final String MESSAGE_INVALID_INDEX_2 = " prefix is not a non-zero unsigned integer.";
    private static final String LOCAL_DATE_TIME_CONSTRAINT = "DATE-TIME should be DD/MM/YYYY HHmm";

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
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseGeneralEventIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX_1 + "g/"
                    + MESSAGE_INVALID_INDEX_2);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }


    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseAssignmentIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX_1 + "a/"
                    + MESSAGE_INVALID_INDEX_2);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseExamIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX_1 + "e/"
                    + MESSAGE_INVALID_INDEX_2);
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
     * Parses a {@code String deadline} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static LocalDateTime parseDeadline(String deadline) {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        try {
            return LocalDateTime.parse(trimmedDeadline,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Parses a {@code String date} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * returns null if the given {@code date} is invalid.
     */
    public static LocalDateTime parseEventDate(String date) {
        requireNonNull(date);
        String trimmedDeadline = date.trim();
        try {
            return LocalDateTime.parse(trimmedDeadline,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Parses a {@code String examDateInput} into {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     * returns null if the {@code examDateIput} is of an invalid format.
     */
    public static LocalDateTime parseExamDate(String examDateInput) {
        String trimmedExamDateInput = examDateInput.trim();
        try {
            return LocalDateTime.parse(trimmedExamDateInput, Exam.EXAM_DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Parses a {@code String examDateInput} into {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the {@code examDateIput} is of an invalid format.
     */
    public static Birthday parseBirthday(String birthdayInput, String nameInput) throws ParseException {
        requireNonNull(birthdayInput);
        requireNonNull(nameInput);
        String trimmedBirthdayInput = birthdayInput.trim();
        if (!Birthday.isValidBirthday(trimmedBirthdayInput)) {
            throw new ParseException(Birthday.MESSAGE_CONSTRAINTS);
        }

        return new Birthday(birthdayInput, nameInput);
    }

    /**
     * Parses a {@code String titleInput} into a {@code Title}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Title parseTitle(String titleInput) throws ParseException {
        requireNonNull(titleInput);
        String trimmedTitle = titleInput.trim();
        Title title;
        try {
            title = new Title(trimmedTitle);
        } catch (IllegalArgumentException e) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return title;
    }
}
