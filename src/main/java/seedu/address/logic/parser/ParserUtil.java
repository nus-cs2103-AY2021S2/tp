package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTOR_ID;
import static seedu.address.logic.parser.ListType.PERSON_TYPE_LIST;
import static seedu.address.logic.parser.ListType.SESSION_TYPE_LIST;
import static seedu.address.logic.parser.ListType.STUDENT_TYPE_LIST;
import static seedu.address.logic.parser.ListType.TUTOR_TYPE_LIST;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonId;
import seedu.address.model.person.PersonType;
import seedu.address.model.person.Phone;
import seedu.address.model.session.Day;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionId;
import seedu.address.model.session.Subject;
import seedu.address.model.session.Timeslot;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

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
     * Parses a {@code String day} into an {@code Day}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code day} is invalid.
     */
    public static Day parseDay(String day) throws ParseException {
        requireNonNull(day);
        String trimmedDay = day.trim();
        if (!Day.isValidDay(trimmedDay)) {
            throw new ParseException(Day.MESSAGE_CONSTRAINTS);
        }
        return new Day(trimmedDay);
    }

    /**
     * Parses a {@code String subject} into an {@code Subject}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code subject} is invalid.
     */
    public static Subject parseSubject(String subject) throws ParseException {
        requireNonNull(subject);
        String trimmedSubject = subject.trim();
        if (!Subject.isValidSubject(trimmedSubject)) {
            throw new ParseException(Subject.MESSAGE_CONSTRAINTS);
        }
        return new Subject(trimmedSubject);
    }

    /**
     * Parses a {@code String time} into an {@code LocalTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static Timeslot parseTimeslot(String timeslot) throws ParseException {
        requireNonNull(timeslot);
        String trimmedTimeslot = timeslot.trim();
        if (!Timeslot.isValidTimeslot(trimmedTimeslot)) {
            throw new ParseException(Timeslot.MESSAGE_CONSTRAINTS);
        }
        return new Timeslot(timeslot);
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
     Parses a {@code String type} into a {@code PersonType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code type} is invalid.
     */
    public static PersonType parsePersonType(String type) throws ParseException {
        requireNonNull(type);
        type = type.toLowerCase();
        if (!PersonType.isValidPersonType(type)) {
            throw new ParseException(PersonType.MESSAGE_CONSTRAINTS);
        } else {
            return new PersonType(type);
        }
    }

    /**
     Parses a {@code String id} into a {@code PersonId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static PersonId parsePersonId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!PersonId.isValidPersonId(trimmedId)) {
            throw new ParseException(PersonId.MESSAGE_CONSTRAINTS);
        } else {
            return new PersonId(trimmedId);
        }
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
     * Parses a {@code String session ID} into a {@code session ID }.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code session ID} is invalid.
     */
    public static SessionId parseSessionId(String sessionId) throws ParseException {
        requireNonNull(sessionId);
        String trimmedSessionId = sessionId.trim();
        if (!SessionId.isValidSessionId(trimmedSessionId)) {
            throw new ParseException(Session.MESSAGE_CONSTRAINTS);
        }
        return new SessionId(trimmedSessionId);
    }

    /**
     * Parses a {@code String list type}
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static String parseListType(String listType) throws ParseException {
        requireNonNull(listType);
        String trimmedListType = listType.trim();
        if (trimmedListType.equals(PERSON_TYPE_LIST) || trimmedListType.equals(SESSION_TYPE_LIST)
                || trimmedListType.equals(STUDENT_TYPE_LIST) || trimmedListType.equals(TUTOR_TYPE_LIST)) {
            return trimmedListType;
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses a {@code Collection<String> person IDs} into a {@code List<PersonId> }.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code person IDs} is invalid.
     */
    public static Set<PersonId> parseStudentIds(Collection<String> studentIds) throws ParseException {
        requireNonNull(studentIds);
        final Set<PersonId> studentIdsSet = new HashSet<>();
        for (String studentId : studentIds) {
            studentIdsSet.add(parsePersonId(PREFIX_STUDENT_ID + studentId));
        }
        return studentIdsSet;
    }

    /**
     * Parses a {@code String tutor ID} into a {@code person ID }.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tutor ID} is invalid.
     */
    public static PersonId parseTutorId(String tutorId) throws ParseException {
        requireNonNull(tutorId);
        String trimmedTutorId = tutorId.trim();
        return parsePersonId(PREFIX_TUTOR_ID + tutorId);
    }
}
