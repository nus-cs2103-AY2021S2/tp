package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.plan.Description;
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
     * Parses a {@code String description} into an {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parsePlan(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String semNumber} into an {@code Integer}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code semNumber} is invalid.
     */
    public static int parsePlanNumber(String planNumber) throws ParseException {
        requireNonNull(planNumber);
        String trimmedPlanNumber = planNumber.trim();
        int planNum = 0;
        try {
            planNum = Integer.parseInt(trimmedPlanNumber);
        } catch (NumberFormatException error) {
            throw new ParseException("Invalid Plan Number");
        }
        return planNum;
    }

    /**
     * Parses a {@code String description} into an {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parsePlan(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String semNumber} into an {@code Integer}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code semNumber} is invalid.
     */
    public static int parsePlanNumber(String planNumber) throws ParseException {
        requireNonNull(planNumber);
        String trimmedPlanNumber = planNumber.trim();
        int planNum = 0;
        try {
            planNum = Integer.parseInt(trimmedPlanNumber);
        } catch(NumberFormatException error) {
            throw new ParseException("Invalid Plan Number");
        }
        return planNum;
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
     * Parses a {@code String semNumber} into an {@code Integer}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code semNumber} is invalid.
     */
    public static int parseSemesterNumber(String semesterNumber) throws ParseException {
        requireNonNull(semesterNumber);
        String trimmedDescription = semesterNumber.trim();
        int semesterNum = 0;
        try {
            semesterNum = Integer.parseInt(trimmedDescription);
<<<<<<< HEAD
        } catch(NumberFormatException error) {
=======
        } catch (NumberFormatException error) {
>>>>>>> 3d49dd485089143070617a7d9f2fcd597e1d9183
            throw new ParseException("Invalid Semester Number");
        }
        return semesterNum;
    }
<<<<<<< HEAD

    public static String parseModuleCode(String moduleCode) throws ParseException{
        requireNonNull(moduleCode);
        String code = moduleCode.trim();
        return code;
    }
}
=======
}
>>>>>>> 3d49dd485089143070617a7d9f2fcd597e1d9183
