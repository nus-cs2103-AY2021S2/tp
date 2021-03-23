package seedu.budgetbaby.model.record;

import java.time.YearMonth;
import java.util.Collections;git
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.budgetbaby.logic.parser.TimestampParser;

/**
 * Represents a Financial Record in the budget tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class FinancialRecord {

    private static final String FINANCIAL_RECORD_DETAILS_DELIMITER = " | ";

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

    public YearMonth getMonth() {
        return TimestampParser.getYearMonth(this.timestamp);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Category> getTags() {
        return Collections.unmodifiableSet(categories);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, amount, timestamp);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTimestamp())
            .append(FINANCIAL_RECORD_DETAILS_DELIMITER)
            .append(getDescription())
            .append(FINANCIAL_RECORD_DETAILS_DELIMITER)
            .append(getAmount())
            .append(FINANCIAL_RECORD_DETAILS_DELIMITER)
            .append(getTimestamp());

        Set<Category> categories = getTags();
        if (!categories.isEmpty()) {
            builder.append("; Categories: ");
            categories.forEach(builder::append);
        }
        return builder.toString();
    }

}
