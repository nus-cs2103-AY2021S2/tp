package seedu.budgetbaby.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.YearMonth;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.budgetbaby.abmodel.person.Address;
import seedu.budgetbaby.abmodel.person.Email;
import seedu.budgetbaby.abmodel.person.Name;
import seedu.budgetbaby.abmodel.person.Phone;
import seedu.budgetbaby.commons.core.index.Index;
import seedu.budgetbaby.commons.util.StringUtil;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.month.Month;
import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;

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
        requireNonNull(amount);
        String trimmedAmount = amount.trim();
        double budgetAmount = Double.parseDouble(trimmedAmount);
        if (budgetAmount < 0) {
            throw new ParseException("Budget amount cannot be negative!");
        }
        return budgetAmount;
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
