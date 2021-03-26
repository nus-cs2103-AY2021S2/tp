package seedu.iscam.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.commons.util.StringUtil;
import seedu.iscam.logic.commands.EditMeetingCommand;
import seedu.iscam.logic.parser.exceptions.ParseException;
import seedu.iscam.model.client.Email;
import seedu.iscam.model.client.InsurancePlan;
import seedu.iscam.model.client.Phone;
import seedu.iscam.model.commons.Location;
import seedu.iscam.model.commons.Name;
import seedu.iscam.model.meeting.DateTime;
import seedu.iscam.model.meeting.Description;
import seedu.iscam.model.commons.Tag;

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
     * Parses a {@code String iscam} into an {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code iscam} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedLocation = location.trim();
        if (!Location.isValidLocation(trimmedLocation)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedLocation);
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
     * Parses a {@code String plan} into an {@code InsurancePlan}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code plan} is invalid.
     */
    public static InsurancePlan parsePlan(String plan) throws ParseException {
        requireNonNull(plan);
        String trimmedPlan = plan.trim();
        if (!InsurancePlan.isValidPlan(trimmedPlan)) {
            throw new ParseException(InsurancePlan.MESSAGE_CONSTRAINTS);
        }
        return new InsurancePlan(trimmedPlan);
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
     * Parses {@code String desc} into a {@code Description}.
     *
     * @throws ParseException if the given {@code desc} is invalid.
     */
    public static Description parseDescription(String desc) throws ParseException {
        requireNonNull(desc);
        if (!Description.isValidDescription(desc)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(desc);
    }

    /**
     * Parses {@code String dateTimeStr} into a {@code DateTime}.
     *
     * @throws ParseException if the given {@code dateTimeStr} is invalid.
     */
    public static DateTime parseDateTime(String dateTimeStr) throws ParseException {
        requireNonNull(dateTimeStr);
        if (!DateTime.isValidDateTimeStr(dateTimeStr)) {
            throw new ParseException(DateTime.MESSAGE_CONSTRAINTS);
        }
        return new DateTime(dateTimeStr);
    }

    /**
     * Parses {@code String isDone} into a boolean.
     *
     * @throws ParseException if given {@code isDone} is not compliant to what is declared in EditMeetingCommand
     */
    public static boolean parseIsDone(String isDone) throws ParseException {
        requireNonNull(isDone);
        if (!isDone.equals(EditMeetingCommand.PARAMETER_DONE)
                && !isDone.equals(EditMeetingCommand.PARAMETER_NOT_DONE)) {
            throw new ParseException(EditMeetingCommand.MESSAGE_USAGE);
        }
        return isDone.equals(EditMeetingCommand.PARAMETER_DONE);
    }
}
