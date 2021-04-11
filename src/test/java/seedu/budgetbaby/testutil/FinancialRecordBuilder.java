package seedu.budgetbaby.testutil;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import seedu.budgetbaby.logic.parser.ParserUtil;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;
import seedu.budgetbaby.model.record.FinancialRecord;
import seedu.budgetbaby.model.util.SampleDataUtil;

/**
 * A utility class to help with building FinancialRecord objects.
 */
public class FinancialRecordBuilder {

    public static final String DEFAULT_DESCRIPTION = "Ramen";
    public static final String DEFAULT_AMOUNT = "12.5";

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
        timestamp = new Date();
        categories = new HashSet<>();
    }

    /**
     * Initializes the FinancialRecordBuilder with the data of {@code financialRecordToCopy}.
     */
    public FinancialRecordBuilder(FinancialRecord financialRecordToCopy) {
        description = financialRecordToCopy.getDescription();
        amount = financialRecordToCopy.getAmount();
        timestamp = financialRecordToCopy.getTimestamp();
        categories = new HashSet<>(financialRecordToCopy.getCategories());
    }

    /**
     * Sets the {@code Description} of the {@code FinancialRecord} that we are building.
     */
    public FinancialRecordBuilder withDescription(String description) {
        this.description = new Description(description);
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
     * Sets the {@code Date} of the {@code FinancialRecord} that we are building.
     */
    public FinancialRecordBuilder withTime(String timestamp) {
        try {
            this.timestamp = ParserUtil.parseDate(timestamp);
        } catch (ParseException e) {
            this.timestamp = new Date();
        }
        return this;
    }

    /**
     * Parses the {@code categories} into a {@code Set<Tag>} and
     * set it to the {@code FinancialRecord} that we are building.
     */
    public FinancialRecordBuilder withCategories(String ... categories) {
        this.categories = SampleDataUtil.getTagSet(categories);
        return this;
    }

    public FinancialRecord build() {
        return new FinancialRecord(description, amount, timestamp, categories);
    }

}
