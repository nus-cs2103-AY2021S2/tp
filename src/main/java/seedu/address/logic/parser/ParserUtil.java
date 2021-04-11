package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.date.Description;
import seedu.address.model.date.Details;
import seedu.address.model.lesson.Day;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.Time;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.School;
import seedu.address.model.person.level.Level;
import seedu.address.model.subject.Subject;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    // public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_INDEX = "Invalid index! \n%1$s";

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
        return new Name(trimmedName.toUpperCase(Locale.ROOT));
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
     * Parses a {@code String school} into a {@code School}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code school} is invalid.
     */
    public static Optional<School> parseSchool(String school) throws ParseException {
        requireNonNull(school);
        String trimmedSchool = school.trim();
        if (!School.isValidSchool(trimmedSchool)) {
            throw new ParseException(School.MESSAGE_CONSTRAINTS);
        }
        return Optional.of(new School(trimmedSchool));
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Optional<Address> parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return Optional.of(new Address(trimmedAddress));
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Optional<Email> parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return Optional.of(new Email(trimmedEmail));
    }

    /**
     * Parses a {@code String guardianName} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Optional<Name> parseGuardianName(String guardianName) throws ParseException {
        requireNonNull(guardianName);
        String trimmedName = guardianName.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return Optional.of(new Name(trimmedName));
    }

    /**
     * Parses a {@code String guardianPhone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Optional<Phone> parseGuardianPhone(String guardianPhone) throws ParseException {
        requireNonNull(guardianPhone);
        String trimmedPhone = guardianPhone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return Optional.of(new Phone(trimmedPhone));
    }

    /**
     * Parses a {@code String level} into a {@code Level}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code level} is invalid.
     */
    public static Optional<Level> parseLevel(String level) throws ParseException {
        requireNonNull(level);
        String trimmedLevel = level.trim();
        if (!Level.isValidLevel(trimmedLevel)) {
            throw new ParseException(Level.MESSAGE_CONSTRAINTS);
        }
        return Optional.of(new Level(trimmedLevel));
    }

    /**
     * Parses a {@code String subject} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code subject} is invalid.
     */
    public static Subject parseSubject(String subject) throws ParseException {
        requireNonNull(subject);
        String trimmedTag = subject.trim().toLowerCase(Locale.ROOT);
        if (!Subject.isValidSubjectName(trimmedTag)) {
            throw new ParseException(Subject.MESSAGE_CONSTRAINTS);
        }
        return new Subject(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> subjects} into a {@code Set<Tag>}.
     */
    public static Set<Subject> parseSubjects(Collection<String> subjects) throws ParseException {
        requireNonNull(subjects);
        final Set<Subject> subjectSet = new HashSet<>();
        for (String tagName : subjects) {
            subjectSet.add(parseSubject(tagName));
        }
        return subjectSet;
    }

    /**
     * Parses a {@code String lesson} into a {@code Lesson}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code lesson} is invalid.
     */
    public static Lesson parseLesson(String lesson) throws ParseException {
        requireNonNull(lesson);
        String trimmedLesson = lesson.trim();
        String[] details = trimmedLesson.split(" ");

        if (!Lesson.isValidLesson(details)) {
            throw new ParseException(Lesson.MESSAGE_CONSTRAINTS);
        }
        if (!Day.isValidDay(details[Lesson.INDEX_OF_DAY])) {
            throw new ParseException(Day.MESSAGE_CONSTRAINTS);
        }
        if (!Time.isValidTime(details[Lesson.INDEX_OF_TIME])) {
            throw new ParseException(Time.MESSAGE_CONSTRAINTS);
        }
        return new Lesson(trimmedLesson);
    }

    /**
     * Parses {@code Collection<String> lessons} into a {@code Set<Lesson>}.
     */
    public static Set<Lesson> parseLessons(Collection<String> lessons) throws ParseException {
        requireNonNull(lessons);
        final Set<Lesson> lessonSet = new HashSet<>();
        for (String lesson : lessons) {
            lessonSet.add(parseLesson(lesson));
        }
        return lessonSet;
    }

    /**
     * Parses a {@code String details} into a {@code Details}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code details} is invalid.
     */
    public static Details parseDetails(String details) throws ParseException {
        requireNonNull(details);
        String trimmedDetails = details.trim();
        if (!Details.isValidDetails(trimmedDetails)) {
            throw new ParseException(Details.MESSAGE_CONSTRAINTS);
        } else {
            return new Details(trimmedDetails);
        }
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

}
