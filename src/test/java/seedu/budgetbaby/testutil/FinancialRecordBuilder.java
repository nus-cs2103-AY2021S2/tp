package seedu.budgetbaby.testutil;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;
import seedu.budgetbaby.model.record.FinancialRecord;
import seedu.budgetbaby.model.util.SampleDataUtil;

/**
 * A utility class to help with building FinancialRecord objects.
 */

public class FinancialRecordBuilder {

    public static final String DEFAULT_DESCRIPTION = "Lunch";
    public static final String DEFAULT_AMOUNT = "10.0";
    public static final String DEFAULT_TIMESTAMP = "01-01-2021";

    private Description description;
    private Amount amount;
    private Date timestamp;
    private Set<Category> categories;

    /**
     * Creates a {@code FinancialRecordBuilder} with the default details.
     */
    public FinancialRecordBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        amount = new Amount(DEFAULT_AMOUNT);
        timestamp = FinancialRecord.getValidTimeStamp(DEFAULT_TIMESTAMP);
        categories = new HashSet<>();
    }

    /**
     * Initializes the FinancialRecordBuilder with the data of {@code frToCopy}.
     */
    public FinancialRecordBuilder(FinancialRecord frToCopy) {
        description = frToCopy.getDescription();
        amount = frToCopy.getAmount();
        timestamp = frToCopy.getTimestamp();
        categories = new HashSet<>(frToCopy.getCategories());
    }

    /**
     * Sets the {@code Description} of the {@code FinancialRecord} that we are building.
     */
    public FinancialRecordBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Parses the {@code categories} into a {@code Set<Category>} and
     * set it to the {@code FinancialRecord} that we are building.
     */
    public FinancialRecordBuilder withCategories(String... categories) {
        this.categories = SampleDataUtil.getCategorySet(categories);
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code FinancialRecord} that we are building.
     */
    public FinancialRecordBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code FinancialRecord} that we are building.
     */
    public FinancialRecordBuilder withTimestamp(String timestamp) {
        this.timestamp = FinancialRecord.getValidTimeStamp(timestamp);
        return this;
    }

    public FinancialRecord build() {
        return new FinancialRecord(description, amount, timestamp, categories);
    }

}
