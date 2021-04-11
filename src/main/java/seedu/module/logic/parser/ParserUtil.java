package seedu.module.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.module.commons.core.index.Index;
import seedu.module.commons.exceptions.IllegalIntegerException;
import seedu.module.commons.util.StringUtil;
import seedu.module.logic.parser.exceptions.ParseException;
import seedu.module.model.ModuleManager;
import seedu.module.model.tag.Tag;
import seedu.module.model.task.Description;
import seedu.module.model.task.Module;
import seedu.module.model.task.Name;
import seedu.module.model.task.Recurrence;
import seedu.module.model.task.Time;
import seedu.module.model.task.Workload;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not processed as integer).
     * @throws IllegalIntegerException if the specified index is not a positive unsigned integer.
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException, IllegalIntegerException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new IllegalIntegerException(MESSAGE_INVALID_INDEX);
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
     * Parses a {@code String time} into a {@code Time}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static Time parseTime(String deadline) throws ParseException {
        requireNonNull(deadline);
        String trimmedTime = deadline.trim();
        if (!Time.isValidTime(trimmedTime)) {
            throw new ParseException(Time.MESSAGE_CONSTRAINTS);
        }
        return new Time(trimmedTime);
    }

    /**
     * Parses a {@code String description} into an {@code Description}.
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
     * Parses a {@code String module} into an {@code Module} that is supported.
     * Leading and trailing whitespaces are trimmed.
     *
     * @throws ParseException if the given {@code module} is invalid.
     */
    public static Module parseModule(String module) throws ParseException {
        requireNonNull(module);
        String trimmedModule = module.trim();
        if (!Module.isValidModuleFormat(trimmedModule)) {
            throw new ParseException(Module.MESSAGE_CONSTRAINTS);
        }
        if (!ModuleManager.moduleIsValid(trimmedModule)) {
            throw new ParseException(Module.MESSAGE_MODULE_NOT_SUPPORTED);
        }
        return new Module(trimmedModule);
    }

    /**
     * Parses a {@code String workload} into an {@code Workload}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code workload} is invalid.
     */
    public static Workload parseWorkload(String workload) throws ParseException {
        requireNonNull(workload);
        String trimmedWorkload = workload.trim();
        if (!Workload.isValidWorkload(trimmedWorkload)) {
            throw new ParseException(Workload.MESSAGE_CONSTRAINTS);
        }
        return new Workload(trimmedWorkload);
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
        final Set<String> lowerCaseCheck = new HashSet<>();
        for (String tagName : tags) {
            if (lowerCaseCheck.contains(tagName.toLowerCase())) {
                //Skip this tagName
                continue;
            }
            tagSet.add(parseTag(tagName));
            lowerCaseCheck.add(tagName.toLowerCase());
        }
        return tagSet;
    }
    /**
     * Parses a {@code String recurrence} into a {@code Recurrence}.
     * Leading and trailing whitespaces are trimmed.
     *
     * @throws ParseException if the given {@code recurrence} is not valid.
     */
    public static Recurrence parseRecurrence(String recurrence) throws ParseException {
        String trimmedRecurrence = recurrence.trim().toLowerCase();
        if (!Recurrence.isValidRecurrence(trimmedRecurrence)) {
            throw new ParseException(Recurrence.MESSAGE_CONSTRAINTS);
        }
        return new Recurrence(recurrence);
    }
}
