package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.util.OperationFlag;
import seedu.address.model.person.DeadlineDate;
import seedu.address.model.person.DeadlineTime;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Notes;
import seedu.address.model.person.TaskName;
import seedu.address.model.person.Weightage;
import seedu.address.model.tag.PriorityTag;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    public static final String MESSAGE_INVALID_WEIGHTAGE = "Weightage is not a positive percentage value.";


    public static final String MESSAGE_INVALID_ARGS_LENGTH = "Invalid number of arguments provided.";

    public static final String MESSAGE_INVALID_NUMBER_OF_DAYS = "Number of days is not a positive integer.";

    public static final String MESSAGE_INVALID_NUMBER_OF_WEEKS = "Number of weeks is not a positive integer.";



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
     * Parses {@code String operationFlag} into an {@code OperationFlag} and returns it. Leading and trailing
     * whitespaces will be trimmed.
     *
     * @throws ParseException if given {@code operationFlag} is invalid.
     */
    public static OperationFlag parseOperationFlag(String operationFlag) throws ParseException {
        String trimmedOperationFlag = operationFlag.trim();
        if (!OperationFlag.isValidOperationType(trimmedOperationFlag)) {
            throw new ParseException(OperationFlag.MESSAGE_CONSTRAINTS);
        }
        return new OperationFlag(trimmedOperationFlag);
    }

    /**
     * Parses a {@code String name} into a {@code TaskName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static TaskName parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!TaskName.isValidName(trimmedName)) {
            throw new ParseException(TaskName.MESSAGE_CONSTRAINTS);
        }
        return new TaskName(trimmedName);
    }

    /**
     * Parses a {@code String code} into a {@code ModuleCode}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code code} is invalid.
     */
    public static ModuleCode parseCode(String code) throws ParseException {
        requireNonNull(code);
        String trimmedModuleCode = code.trim();
        if (!ModuleCode.isValidModuleCode(trimmedModuleCode)) {
            throw new ParseException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(trimmedModuleCode);
    }

    /**
     * Parses a {@code String deadlineDate} into a {@code DeadlineDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadlineDate} is invalid.
     */
    public static DeadlineDate parseDeadlineDate(String deadlineDate) throws ParseException {
        requireNonNull(deadlineDate);
        String trimmedDeadlineDate = deadlineDate.trim();
        if (!DeadlineDate.isValidDeadlineDate(trimmedDeadlineDate)) {
            throw new ParseException(DeadlineDate.MESSAGE_CONSTRAINTS);
        }
        return new DeadlineDate(trimmedDeadlineDate);
    }

    /**
     * Parses a {@code String deadlineTime} into a {@code DeadlineTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadlineTime} is invalid.
     */
    public static DeadlineTime parseDeadlineTime(String deadlineTime) throws ParseException {
        requireNonNull(deadlineTime);
        String trimmedDeadlineTime = deadlineTime.trim();
        if (!DeadlineTime.isValidDeadlineTime(trimmedDeadlineTime)) {
            throw new ParseException(DeadlineTime.MESSAGE_CONSTRAINTS);
        }
        return new DeadlineTime(trimmedDeadlineTime);
    }

    /**
     * Parses a {@code String weightage} into a {@code Weightage}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Weightage parseWeightage(String weightage) throws ParseException {
        requireNonNull(weightage);
        String trimmedWeightage = weightage.trim();
        if (!StringUtil.isPositivePercentageValue(trimmedWeightage)) {
            throw new ParseException(MESSAGE_INVALID_WEIGHTAGE);
        }
        Integer intWeightage = StringUtil.convertPercentageToInteger(trimmedWeightage);
        if (!Weightage.isValidWeightage(intWeightage)) {
            throw new ParseException(Weightage.MESSAGE_CONSTRAINTS);
        }
        return new Weightage(intWeightage);
    }

    /**
     * Parses a {@code String notes} into a {@code Notes}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static Notes parseNotes(String notes) {
        requireNonNull(notes);
        String trimmedRemark = notes.trim();
        return new Notes(trimmedRemark);
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
     * method to parse and return priorityTag
     * @param priorityTag String value of ptag
     * @return correct string value of ptag
     */
    public static PriorityTag parsePriorityTag(String priorityTag) throws ParseException {
        requireNonNull(priorityTag);
        String trimmedTag = priorityTag.trim();
        if (trimmedTag.equals("LOW")
                || trimmedTag.equals("MEDIUM")
                || trimmedTag.equals("HIGH")) {
            return new PriorityTag(trimmedTag);
        } else {
            throw new ParseException(PriorityTag.MESSAGE_CONSTRAINTS);
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
     * Parses a {@code String numberOfDays} into a long.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static long parseNumberOfDays(String numberOfDays) throws ParseException {
        requireNonNull(numberOfDays);
        String trimmedNumberOfDays = numberOfDays.trim();
        if (!StringUtil.isNonZeroUnsignedLong(trimmedNumberOfDays)) {
            throw new ParseException(MESSAGE_INVALID_NUMBER_OF_DAYS);
        }
        long longNumberOfDays = Long.parseLong(trimmedNumberOfDays);
        return longNumberOfDays;
    }

    /**
     * Parses a {@code String numberOfWeeks} into a long.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static long parseNumberOfWeeks(String numberOfWeeks) throws ParseException {
        requireNonNull(numberOfWeeks);
        String trimmedNumberOfWeeks = numberOfWeeks.trim();
        if (!StringUtil.isNonZeroUnsignedLong(trimmedNumberOfWeeks)) {
            throw new ParseException(MESSAGE_INVALID_NUMBER_OF_WEEKS);
        }
        long longNumberOfWeeks = Long.parseLong(trimmedNumberOfWeeks);
        return longNumberOfWeeks;
    }

}
