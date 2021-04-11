package seedu.budgetbaby.testutil;

import java.time.YearMonth;
import java.util.Date;

import seedu.budgetbaby.logic.parser.YearMonthParser;
import seedu.budgetbaby.model.Budget;
import seedu.budgetbaby.model.month.Month;
import seedu.budgetbaby.model.record.FinancialRecordList;

/**
 * A utility class to help with building Month objects.
 */

public class MonthBuilder {
    public static final Double DEFAULT_BUDGET = Budget.DEFAULT_BUDGET;
    public static final FinancialRecordList DEFAULT_RECORDS = new FinancialRecordList();
    public static final YearMonth DEFAULT_MONTH = YearMonthParser.getYearMonth(new Date());

    private YearMonth month;
    private FinancialRecordList records;
    private Budget budget;

    /**
     * Creates a {@code MonthBuilder} with the default details.
     */
    public MonthBuilder() {
        month = DEFAULT_MONTH;
        records = DEFAULT_RECORDS;
        budget = new Budget(DEFAULT_BUDGET);
    }

    /**
     * Initializes the MonthBuilder with the data of {@code monthToCopy}.
     */
    public MonthBuilder(Month monthToCopy) {
        month = monthToCopy.getMonth();
        records = monthToCopy.getFinancialRecords();
        budget = monthToCopy.getBudget();
    }

    /**
     * Sets the {@code Month} of the {@code Month} that we are building.
     */
    public MonthBuilder withMonth(YearMonth month) {
        this.month = month;
        return this;
    }

    /**
     * Sets the {@code Month} of the {@code Month} that we are building.
     */
    public MonthBuilder withRecords(FinancialRecordList records) {
        this.records = records;
        return this;
    }

    /**
     * Sets the {@code Budget} of the {@code Month} that we are building.
     */
    public MonthBuilder withBudget(Double budget) {
        this.budget = new Budget(budget);
        return this;
    }

    public Month build() {
        return new Month(records, budget, month);
    }

}
