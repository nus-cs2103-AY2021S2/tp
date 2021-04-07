package seedu.address.logic.parser;


import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.common.Category;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.common.Tag;
import seedu.address.model.event.EventComparator;
import seedu.address.model.event.Time;
import seedu.address.model.task.Priority;
import seedu.address.model.task.TaskComparator;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class SocheduleParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero positive integer.\n";
    public static final String MESSAGE_INVALID_INDEXES =
            "Some of the given index(es) are not non-zero positive integers.\n";
    public static final String MESSAGE_DUPLICATE_INDEXES = "Some of the given index(es) contain duplicates.\n";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        requireNonNull(oneBasedIndex);
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a list of {@code oneBasedIndex} into an list of {@code Index} and returns the parsed list of Index.
     * @throws ParseException if any of the specified indexes is invalid or there is any duplicate index.
     */
    public static List<Index> parseIndexes(List<String> oneBasedIndexes) throws ParseException {
        requireNonNull(oneBasedIndexes);
        List<Index> parsedIndexes = new ArrayList<>();

        for (String oneBasedIndex : oneBasedIndexes) {
            try {
                parsedIndexes.add(parseIndex(oneBasedIndex));
            } catch (ParseException pe) {
                throw new ParseException(MESSAGE_INVALID_INDEXES);
            }
        }

        if (areIndexesDuplicate(parsedIndexes)) {
            throw new ParseException(MESSAGE_DUPLICATE_INDEXES);
        }

        return parsedIndexes;
    }

    private static boolean areIndexesDuplicate(List<Index> parsedIndexes) {
        HashSet<Index> indexSet = new HashSet<>(parsedIndexes);
        return indexSet.size() != parsedIndexes.size();
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
        return Optional.of(parseTags(tagSet));
    }

    /**
     * Parses {@code Collection<String> categories} into a {@code Set<Category>} if {@code categories} is non-empty.
     * If {@code categories} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Category>} containing zero categories.
     */
    public static Optional<Set<Category>> parseCategoriesForEdit(Collection<String> categories) throws ParseException {
        assert categories != null;

        if (categories.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> categorySet = categories.size() == 1 && categories.contains("")
                ? Collections.emptySet() : categories;
        return Optional.of(parseCategories(categorySet));
    }

    /**
     * Parses a {@code String date} into a {@code date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (!Date.isValidDateFormat(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS_FORMAT);
        }
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedDate);
    }

    /**
     * Parses a {@code String time} into a {@code time}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static Time parseTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        if (!Time.isValidTime(trimmedTime)) {
            throw new ParseException(Time.MESSAGE_CONSTRAINTS);
        }
        return new Time(trimmedTime);
    }

    /**
     * Parses a {@code String priority} into a {@code priority}.
     * Leading and trailing whitespaces will be trimmed.
     *
     */
    public static Priority parsePriority(String priority) throws ParseException {
        requireNonNull(priority);
        String trimmedPriority = priority.trim();
        if (!Priority.isValidPriority(trimmedPriority)) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS);
        }
        return new Priority(trimmedPriority);
    }

    /**
     * Parses a {@code String category} into a {@code category}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code category} is invalid.
     */
    public static Category parseCategory(String category) throws ParseException {
        requireNonNull(category);
        String trimmedCategory = category.trim();
        if (!Category.isValidCategoryName(trimmedCategory)) {
            throw new ParseException(Category.MESSAGE_CONSTRAINTS);
        }
        return new Category(trimmedCategory);
    }

    /**
     * Parses {@code Collection<String> categories} into a {@code Set<Category>}.
     */
    public static Set<Category> parseCategories(Collection<String> categories) throws ParseException {
        requireNonNull(categories);
        final Set<Category> categorySet = new HashSet<>();
        for (String categoryName : categories) {
            categorySet.add(parseCategory(categoryName));
        }
        return categorySet;
    }

    /**
     * Parses {@code String comparingVar} into a {@code String comparingVar}.
     */
    public static String parseTaskComparingVar(String comparingVar) throws ParseException {
        requireNonNull(comparingVar);
        String trimmedVar = comparingVar.trim();
        if (!TaskComparator.isValidComparingVar(trimmedVar)) {
            throw new ParseException(TaskComparator.MESSAGE_CONSTRAINTS);
        }
        return trimmedVar;
    }

    /**
     * Parses {@code String comparingVar} into a {@code String comparingVar}.
     */
    public static String parseEventComparingVar(String comparingVar) throws ParseException {
        requireNonNull(comparingVar);
        String trimmedVar = comparingVar.trim();
        if (!EventComparator.isValidComparingVar(trimmedVar)) {
            throw new ParseException(EventComparator.MESSAGE_CONSTRAINTS);
        }
        return trimmedVar;
    }
}
