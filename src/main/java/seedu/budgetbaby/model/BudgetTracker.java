package seedu.budgetbaby.model;

import static java.util.Objects.requireNonNull;

import java.time.YearMonth;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.budgetbaby.commons.util.InvalidationListenerManager;
import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.month.Month;
import seedu.budgetbaby.model.month.UniqueMonthList;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * Wraps all data at the budget-tracker level
 */
public class BudgetTracker implements ReadOnlyBudgetTracker {

    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();
    private final UniqueMonthList monthList;
    private Month currentDisplayMonth;

    {
        monthList = new UniqueMonthList();
        currentDisplayMonth = monthList.find(YearMonth.now());
    }

    public BudgetTracker() {
    }

    /**
     * Creates an BudgetTracker using the Months in the {@code toBeCopied}
     */
    public BudgetTracker(ReadOnlyBudgetTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the month list with {@code months}.
     * {@code months} must not contain duplicate months.
     */
    public void setMonthList(List<Month> months) {
        this.monthList.setMonths(months);
        notifyObservers();
    }

    /**
     * Replaces the current display month with {@code month}.
     */
    public void setCurrentDisplayMonth(YearMonth month) {
        this.currentDisplayMonth = monthList.find(month);
        notifyObservers();
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyBudgetTracker newData) {
        requireNonNull(newData);

        setMonthList(newData.getMonthList());
        setCurrentDisplayMonth(newData.getCurrentDisplayMonth().getMonth());
    }

    //// month-level operations

    /**
     * Adds a month to the budget tracker.
     */
    public void addMonth(Month month) {
        monthList.add(month);
        notifyObservers();
    }

    /**
     * Returns a boolean value showing if the month exists.
     */
    public boolean hasMonth(Month month) {
        return monthList.contains(month);
    }

    /**
     * Returns a month representing the given YearMonth.
     */
    public Month findMonth(YearMonth month) {
        return monthList.find(month);
    }

    /**
     * Replaces the given month {@code target} in the list with {@code editedMonth}.
     * {@code target} must exist in the budegt tracker.
     * The month identity of {@code editedRecord} must not be the same as another existing month in the budget tracker.
     */
    public void setMonth(Month target, Month editedMonth) {
        requireNonNull(editedMonth);

        monthList.setMonth(target, editedMonth);
        notifyObservers();
    }

    /**
     * Removes {@code key} from this {@code BudgetTracker}.
     * {@code key} must exist in the budget tracker.
     */
    public void removeMonth(Month key) {
        monthList.remove(key);
        notifyObservers();
    }

    //// financial-record-level operations

    /**
     * Adds a financial record to the budget tracker.
     * Adds a month to the budget tracker.
     */
    public void addFinancialRecord(FinancialRecord r) throws CommandException {
        monthList.addFinancialRecord(r);
        notifyObservers();
    }

    /**
     * Replaces the given financial record {@code target} in the list with {@code editedRecord}.
     * {@code target} must exist in the budegt tracker.
     */
    public void setFinancialRecord(FinancialRecord target, FinancialRecord editedRecord) throws CommandException {
        requireNonNull(editedRecord);
        monthList.setFinancialRecord(target, editedRecord);
        notifyObservers();
    }

    /**
     * Removes {@code key} from this {@code BudgetTracker}.
     * {@code key} must exist in the budget tracker.
     */
    public void removeFinancialRecord(FinancialRecord key) {
        monthList.removeFinancialRecord(key);
        notifyObservers();
    }

    /**
     * Returns an unmodifiable view of the financial record list of the given month.
     */
    ObservableList<FinancialRecord> getFinancialRecordListOfMonth(YearMonth month) {
        currentDisplayMonth = monthList.find(month);
        return this.monthList.getFinancialRecordOfMonth(month);
    }

    //// budget-level operations

    /**
     * Sets the budget for the following months.
     *
     * @param budget The specified budget to be set.
     */
    public void setBudget(Budget budget) {
        monthList.setBudget(budget);
        notifyObservers();
    }

    //// dependency notification methods

    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListenerManager.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListenerManager.removeListener(listener);
    }

    /**
     * Notifies listeners that the budget tracker has been modified.
     */
    public void notifyObservers() {
        invalidationListenerManager.callListeners(this);
    }

    //// util methods

    @Override
    public ObservableList<Month> getMonthList() {
        return monthList.asUnmodifiableObservableList();
    }


    @Override
    public Month getCurrentDisplayMonth() {
        return this.currentDisplayMonth;
    }

    @Override
    public BudgetTracker getDeepClone() {
        BudgetTracker clone = new BudgetTracker();

        List<Month> cloneMthList = monthList.asUnmodifiableObservableList();
        clone.setMonthList(cloneMthList);

        boolean foundCurrentMonth = false;
        for (Month cloneMth : cloneMthList) {
            Month tmpMth = new Month(cloneMth.getFinancialRecords().getDeepClone(),
                cloneMth.getBudget(), cloneMth.getMonth());
            clone.setMonth(cloneMth, tmpMth);
            if (!foundCurrentMonth && tmpMth.equals(currentDisplayMonth)) {
                clone.currentDisplayMonth = tmpMth;
                foundCurrentMonth = true;
            }
        }

        if (!foundCurrentMonth) {
            clone.currentDisplayMonth = currentDisplayMonth;
        }

        return clone;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof BudgetTracker // instanceof handles nulls
            && monthList.equals(((BudgetTracker) other).monthList));
    }

    @Override
    public int hashCode() {
        return monthList.hashCode();
    }

    @Override
    public String toString() {
        return monthList.asUnmodifiableObservableList().size() + " months";
        // TODO: refine later
    }
}

