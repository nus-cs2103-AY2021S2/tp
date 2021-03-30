package seedu.budgetbaby.model.record;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.budgetbaby.logic.parser.YearMonthParser;

/**
 * Represents a Financial Record in the budget tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class FinancialRecord {

    public static final String TIMESTAMP_CONSTRAINTS =
        "Timestamp should follow the format of dd-mm-yyyy. Example: 31-12-2020.";
    private static final String FINANCIAL_RECORD_DETAILS_DELIMITER = " | ";
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    // Data fields
    private final Description description;
    private final Amount amount;
    private final Date timestamp;
    private final Set<Category> categories = new HashSet<>();

    /**
     * Constructs a {@code FinancialRecord}.
     *
     * @param description A valid description.
     * @param amount      A valid amount.
     * @param categories  A valid category.
     */
    public FinancialRecord(Description description, Amount amount, Set<Category> categories) {
        this.description = description;
        this.amount = amount;
        this.timestamp = new Date();
        this.categories.addAll(categories);
    }

    /**
     * Constructs a {@code FinancialRecord}.
     *
     * @param description A valid description.
     * @param amount      A valid amount.
     * @param timestamp   A valid timestamp.
     */
    public FinancialRecord(Description description, Amount amount, Date timestamp, Set<Category> categories) {
        this.description = description;
        this.amount = amount;
        this.timestamp = timestamp;
        this.categories.addAll(categories);
    }

    public Description getDescription() {
        return description;
    }

    public Amount getAmount() {
        return amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getTimestampStr() {
        return formatter.format(timestamp);
    }

    public YearMonth getMonth() {
        return YearMonthParser.getYearMonth(this.timestamp);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    /**
     * Check if {@code test} is of the valid format.
     * Valid format example: 01-01-2021
     */
    public static boolean isValidTimestamp(String test) {
        boolean isValid;
        try {
            formatter.parse(test);
            isValid = true;
        } catch (ParseException e) {
            isValid = false;
        }
        return isValid;
    }

    /**
     * Converts a valid dateStr string to Date.
     */
    public static Date getValidTimeStamp(String dateStr) {
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, amount, timestamp);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTimestampStr())
            .append(FINANCIAL_RECORD_DETAILS_DELIMITER)
            .append(getDescription())
            .append(FINANCIAL_RECORD_DETAILS_DELIMITER)
            .append(getAmount());

        Set<Category> categories = getCategories();
        if (!categories.isEmpty()) {
            builder.append("; Categories: ");
            categories.forEach(builder::append);
        }
        return builder.toString();
    }

}
