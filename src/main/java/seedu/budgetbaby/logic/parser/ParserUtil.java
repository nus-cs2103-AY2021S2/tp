package seedu.budgetbaby.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.YearMonth;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import seedu.budgetbaby.commons.core.index.Index;
import seedu.budgetbaby.commons.util.StringUtil;
import seedu.budgetbaby.logic.commands.SetBudgetCommand;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.month.Month;
import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;
import seedu.budgetbaby.model.record.FinancialRecord;

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
     * Parses a {@code String monthStr} into a {@code YearMonth}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code monthStr} is invalid.
     */
    public static YearMonth parseYearMonth(String yearMonthStr) throws ParseException {
        requireNonNull(yearMonthStr);
        String trimmedMonthStr = yearMonthStr.trim();
        if (!Month.isValidMonthStr(trimmedMonthStr)) {
            throw new ParseException(Month.MESSAGE_CONSTRAINTS);
        }
        return YearMonthParser.getYearMonth(trimmedMonthStr);
    }

    /**
     * Parses a {@code String dateStr} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code monthStr} is invalid.
     */
    public static Date parseDate(String dateStr) throws ParseException {
        requireNonNull(dateStr);
        String trimmedDateStr = dateStr.trim();
        if (!FinancialRecord.isValidTimestamp(dateStr)) {
            throw new ParseException(FinancialRecord.TIMESTAMP_CONSTRAINTS);
        }
        return FinancialRecord.getValidTimeStamp(trimmedDateStr);
    }

    /**
     * Parses a {@code String name} into a {@code Description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(description)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String amount} into a {@code Amount}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code amount} is invalid.
     */
    public static Amount parseAmount(String amount) throws ParseException {
        requireNonNull(amount);
        String trimmedAmount = amount.trim();
        if (!Amount.isValidAmount(amount)) {
            throw new ParseException(Amount.MESSAGE_CONSTRAINTS);
        }
        return new Amount(trimmedAmount);
    }

    /**
     * Parses a String representation of a budget amount to a double.
     * Leading and trailing whitespaces will be trimmed
     *
     * @throws ParseException if the given budget amount is negative.
     */
    public static double parseBudgetAmount(String amount) throws ParseException {
        try {
            requireNonNull(amount);
            String trimmedAmount = amount.trim();
            double budgetAmount = Double.parseDouble(trimmedAmount);
            if (budgetAmount < 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetBudgetCommand.MESSAGE_USAGE));
            }
            return budgetAmount;
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetBudgetCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Category parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Category.isValidTagName(trimmedTag)) {
            throw new ParseException(Category.MESSAGE_CONSTRAINTS);
        }
        return new Category(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Category> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Category> categorySet = new HashSet<>();
        for (String tagName : tags) {
            categorySet.add(parseTag(tagName));
        }
        return categorySet;
    }
}
