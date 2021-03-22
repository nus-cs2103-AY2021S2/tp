package seedu.budgetbaby.model.month;

import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.commons.util.CollectionUtil.requireAllNonNull;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.budgetbaby.model.budget.Budget;
import seedu.budgetbaby.model.month.exception.MonthMismatchException;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.FinancialRecord;
import seedu.budgetbaby.model.record.FinancialRecordList;

/**
 * Represents a Month in the budget tracker.
 */
public class Month {

    // Data fields
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
    private final YearMonth month;
    private FinancialRecordList records;
    private Budget budget;

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

    public ObservableList<FinancialRecord> getFinancialRecordList() {
        return records.asUnmodifiableObservableList();
    }

    public Budget getBudget() {
        return budget;
    }

    /**
     * Sets the budget for the following months.
     *
     * @param budget The specified budget to be set.
     */
    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public YearMonth getMonth() {
        return month;
    }

    /**
     * Adds a financial record to the month.
     * The timestamp of the financial record must match the month.
     */
    public void addFinancialRecord(FinancialRecord toAdd) {
        requireNonNull(toAdd);
        if (!toAdd.getMonth().equals(this.month)) {
            throw new MonthMismatchException();
        }
        records.add(toAdd);
    }

    public void deleteFinancialRecord(FinancialRecord target) {
        records.remove(target);
    }

    public void setFinancialRecord(FinancialRecord target, FinancialRecord editedRecord) {
        requireAllNonNull(target, editedRecord);

        records.setFinancialRecord(target, editedRecord);
    }

    /**
     * Filters the financial records to a specified category.
     */
    public void filterByCategory(Category category) {
        requireNonNull(category);
        records.filterByCategory(category);
    }

    /**
     * Returns the remaining budget left for the month.
     */
    public Double getRemainingBudget() {
        return budget.getAmount() - records.getTotalExpenses();
    }

    /**
     * Returns total expenses of the month.
     * @return total expenses
     */
    public Double getTotalExpenses() {
        return records.getTotalExpenses();
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
        return month.format(formatter);
    }

}
