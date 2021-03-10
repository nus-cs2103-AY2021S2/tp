package seedu.budgetbaby.model.record;

import java.util.Date;
import java.util.Objects;

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
    //    private final Set<Tag> tags = new HashSet<>();

    /**
     * Constructs a {@code FinancialRecord}.
     *
     * @param description A valid description.
     * @param amount      A valid amount.
     */
    public FinancialRecord(Description description, Amount amount) {
        //        requireNonNull(tagName);
        //        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.description = description;
        this.amount = amount;
        this.timestamp = new Date();
        //        this.tags.addAll(tags);
    }

    /**
     * Constructs a {@code FinancialRecord}.
     *
     * @param description A valid description.
     * @param amount      A valid amount.
     * @param timestamp   A valid timestamp.
     */
    public FinancialRecord(Description description, Amount amount, Date timestamp) {
        //        requireNonNull(tagName);
        //        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.description = description;
        this.amount = amount;
        this.timestamp = timestamp;
        //        this.tags.addAll(tags);
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

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    //    public Set<Tag> getTags() {
    //        return Collections.unmodifiableSet(tags);
    //    }
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
            .append(getAmount());

        //        Set<Tag> tags = getTags();
        //        if (!tags.isEmpty()) {
        //            builder.append("; Tags: ");
        //            tags.forEach(builder::append);
        //        }
        return builder.toString();
    }

}
