package seedu.iscam.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.iscam.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.iscam.model.meeting.CompletionStatus.ARGUMENT_COMPLETE;
import static seedu.iscam.model.meeting.CompletionStatus.ARGUMENT_INCOMPLETE;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.iscam.commons.core.index.Index;
import seedu.iscam.commons.util.StringUtil;
import seedu.iscam.logic.parser.exceptions.ParseException;
import seedu.iscam.logic.parser.exceptions.ParseFormatException;
import seedu.iscam.logic.parser.exceptions.ParseIndexException;
import seedu.iscam.model.client.Email;
import seedu.iscam.model.client.Image;
import seedu.iscam.model.client.InsurancePlan;
import seedu.iscam.model.client.Phone;
import seedu.iscam.model.commons.Location;
import seedu.iscam.model.commons.Name;
import seedu.iscam.model.commons.Tag;
import seedu.iscam.model.meeting.CompletionStatus;
import seedu.iscam.model.meeting.DateTime;
import seedu.iscam.model.meeting.Description;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index must be a non-zero positive integer.";
    public static final String MESSAGE_EMPTY_INDEX = "Index field is empty.";
    public static final String MESSAGE_INDEX_TOO_LARGE = "Index specified is too big! Please input a "
            + "number smaller than 2147483647.";


    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseIndexException if the specified index is invalid (not non-zero unsigned integer).
     * @throws ParseIndexException if the specified index is bigger than the maximum holding value of an int.
     * @throws ParseException if {@code oneBasedIndex} is an empty string (index field is empty).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        // Remove the trailing white spaces at the beginning and ending of oneBasedIndex.
        String trimmedIndex = oneBasedIndex.trim();

        // Split the trimmedIndex by space to detect the presence of additional parameters.
        String[] params = trimmedIndex.split(" ", 2);

        if (trimmedIndex.length() == 0) {
            // Throw ParseException if oneBasedIndex is an empty string.
            throw new ParseException(MESSAGE_EMPTY_INDEX);

        } else if (params.length > 1) {
            // Throw ParseFormatException if there are parameters more than required parameters.
            throw new ParseFormatException(MESSAGE_INVALID_COMMAND_FORMAT);

        } else if (!StringUtil.isSmallerThanIntegerMaxValue(trimmedIndex)) {
            // Throw ParseIndexException if the index specified is larger than Integer.MAX_VALUE.
            throw new ParseIndexException(MESSAGE_INDEX_TOO_LARGE);

        } else if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            // Throw ParseIndexException if the index is invalid.
            throw new ParseIndexException(MESSAGE_INVALID_INDEX);
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
            throw new ParseFormatException(Name.MESSAGE_TYPE_CONSTRAINTS);
        }
        if (!Name.isValidLength(trimmedName)) {
            throw new ParseFormatException(Name.MESSAGE_LENGTH_CONSTRAINTS);
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
        if (!Phone.isValidPhoneLength(trimmedPhone)) {
            throw new ParseFormatException(Phone.MESSAGE_LENGTH_CONSTRAINTS);
        }
        if (!Phone.isValidNumbersOnly(trimmedPhone)) {
            throw new ParseFormatException(Phone.MESSAGE_INPUT_CONSTRAINTS);
        }
        if (!Phone.isValidPhoneNumber(trimmedPhone)) {
            throw new ParseFormatException(Phone.MESSAGE_STARTING_DIGIT_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String location} into an {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedLocation = location.trim();
        if (!Location.isValidLocation(trimmedLocation)) {
            throw new ParseFormatException(Location.MESSAGE_CONSTRAINTS);
        }
        if (!Location.isValidLength(trimmedLocation)) {
            throw new ParseFormatException(Location.MESSAGE_LENGTH_CONSTRAINTS);
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
            throw new ParseFormatException(Email.MESSAGE_CONSTRAINTS);
        }
        if (!Email.isValidLength(trimmedEmail)) {
            throw new ParseFormatException(Email.MESSAGE_LENGTH_CONSTRAINTS);
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
            throw new ParseFormatException(InsurancePlan.MESSAGE_CONSTRAINTS);
        }
        if (!InsurancePlan.isValidLength(trimmedPlan)) {
            throw new ParseFormatException(InsurancePlan.MESSAGE_LENGTH_CONSTRAINTS);
        }
        return new InsurancePlan(trimmedPlan);
    }

    /**
     * Parses {@code Collection<InsurancePlan> plans} into a {@code Set<InsurancePlan>}.
     */
    public static Set<InsurancePlan> parsePlans(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<InsurancePlan> planSet = new HashSet<>();
        for (String planName : tags) {
            planSet.add(parsePlan(planName));
        }
        return planSet;
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
            throw new ParseFormatException(Tag.MESSAGE_CONSTRAINTS);
        }
        if (!Tag.isValidLength(trimmedTag)) {
            throw new ParseFormatException(Tag.MESSAGE_LENGTH_CONSTRAINTS);
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
            throw new ParseFormatException(Description.MESSAGE_CONSTRAINTS);
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
        if (!DateTime.isStringValidFormat(dateTimeStr)) {
            throw new ParseFormatException(DateTime.MESSAGE_INVALID_FORMAT);
        } else if (!DateTime.isStringValidDateTime(dateTimeStr)) {
            throw new ParseFormatException(DateTime.MESSAGE_IN_PAST);
        }
        return new DateTime(dateTimeStr);
    }

    /**
     * Parses {@code String status} into a CompletionStatus.
     *
     * @throws ParseException if given {@code status} is not compliant to what is declared in EditMeetingCommand
     */
    public static CompletionStatus parseCompletionStatus(String status) throws ParseException {
        requireNonNull(status);
        if (!status.equals(ARGUMENT_COMPLETE)
                && !status.equals(ARGUMENT_INCOMPLETE)) {
            throw new ParseFormatException(CompletionStatus.MESSAGE_CONSTRAINTS);
        }
        return new CompletionStatus(status);
    }

    /**
     * Parses {@code String imageRes} into a boolean.
     */
    public static Image parseImageRes(String imageRes) throws ParseException {
        requireNonNull(imageRes);
        if (!Image.isValidImageRes(imageRes)) {
            throw new ParseFormatException(Image.MESSAGE_CONSTRAINTS);
        }
        return new Image(imageRes);
    }
}
