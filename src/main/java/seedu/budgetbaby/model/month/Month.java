package seedu.budgetbaby.model.month;

import seedu.budgetbaby.abmodel.person.Person;
import seedu.budgetbaby.model.budget.Budget;
import seedu.budgetbaby.model.month.exception.DuplicateMonthException;
import seedu.budgetbaby.model.month.exception.MonthMismatchException;
import seedu.budgetbaby.model.record.FinancialRecord;
import seedu.budgetbaby.model.record.FinancialRecordList;

import java.time.Year;
import java.time.YearMonth;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Month in the budget tracker.
 */
public class Month {

    // Data fields
    private FinancialRecordList records;
    private Budget budget;
    private final YearMonth month;

    /**
     * Constructs a {@code Month}.
     *
     * @param records   A list of financial records.
     * @param budget    A valid budget.
     * @param yearMonth A valid year month.
     */
    public Month(FinancialRecordList records, Budget budget, YearMonth yearMonth) {
        this.records = records;
        this.budget = budget;
        this.month = yearMonth;
    }

    /**
     * Constructs a new {@code Month} with no records and default budget.
     *
     * @param yearMonth A valid year month.
     */
    public Month(YearMonth yearMonth) {
        this.records = new FinancialRecordList();
        this.budget = new Budget();
        this.month = yearMonth;
    }

    public FinancialRecordList getFinancialRecords() {
        return records;
    }

    public Budget getBudget() {
        return budget;
    }

    public YearMonth getMonth() {
        return month;
    }

    /**
     * Adds a financial record to the month.
     * The timestamp of the financial record must match the month.
     */
    public void add(FinancialRecord toAdd) {
        requireNonNull(toAdd);
        if (!toAdd.getMonth().equals(this.month)) {
            throw new MonthMismatchException();
        }
        records.add(toAdd);
    }

    /**
     * Returns true if both months have the same.
     * This defines a weaker notion of equality between two months.
     */
    public boolean isSameMonth(Month otherMonth) {
        if (otherMonth == this) {
            return true;
        }

        return otherMonth != null
            && otherMonth.getMonth().equals(getMonth());
    }

    /**
     * Returns true if the month object is representing the YearMonth object.
     */
    public boolean isSameMonth(YearMonth yearMonth) {
        return yearMonth != null
            && getMonth().equals(yearMonth);
    }

    /**
     * Returns true if both months have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Month)) {
            return false;
        }

        Month otherMonth = (Month) other;
        return otherMonth.getFinancialRecords().equals(getFinancialRecords())
            && otherMonth.getBudget().equals(getBudget())
            && otherMonth.getMonth().equals(getMonth());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(records, budget, month);
    }

    @Override
    public String toString() {
        return month.toString();
    }

}
