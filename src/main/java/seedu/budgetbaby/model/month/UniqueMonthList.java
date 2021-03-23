package seedu.budgetbaby.model.month;

import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.commons.util.CollectionUtil.requireAllNonNull;

import java.time.YearMonth;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.budgetbaby.logic.parser.YearMonthParser;
import seedu.budgetbaby.model.budget.Budget;
import seedu.budgetbaby.model.month.exception.DuplicateMonthException;
import seedu.budgetbaby.model.month.exception.MonthNotFoundException;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * A list of months that enforces uniqueness between its elements and does not allow nulls.
 * A month is considered unique by comparing using {@code Month#isSameMonth(Month)}. As such, adding and updating of
 * months uses Month#isSameMonth(Month) for equality so as to ensure that the month being added or updated is
 * unique in terms of identity in the UniqueMonthList. However, the removal of a month uses Month#equals(Object) so
 * as to ensure that the month with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Month#isSameMonth(Month) (Month)
 */
public class UniqueMonthList implements Iterable<Month> {

    private final ObservableList<Month> internalList = FXCollections.observableArrayList();
    private final ObservableList<Month> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent month as the given argument.
     */
    public boolean contains(Month toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameMonth);
    }

    /**
     * Returns true if the list contains a month representing the given YearMonth.
     */
    public boolean contains(YearMonth yearMonth) {
        requireNonNull(yearMonth);
        return internalList.stream().anyMatch(m -> m.isSameMonth(yearMonth));
    }

    /**
     * Finds the month representing the given YearMonth in the list.
     */
    public Month find(YearMonth yearMonth) {
        requireNonNull(yearMonth);
        if (!contains(yearMonth)) {
            add(yearMonth);
        }
        return internalList.stream()
            .filter(m -> m.isSameMonth(yearMonth))
            .findFirst().get();
    }

    /**
     * Adds a month to the list.
     * The month must not already exist in the list.
     */
    public void add(Month toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMonthException();
        }
        YearMonth prevYearMonth = toAdd.getMonth().minusMonths(1);
        if (contains(prevYearMonth)) {
            Month prevMonth = find(prevYearMonth);
            toAdd.setBudget(prevMonth.getBudget());
        }
        internalList.add(toAdd);
    }

    /**
     * Adds a month representing the given YearMonth to the list.
     * The month must not already exist in the list.
     */
    public void add(YearMonth yearMonth) {
        requireNonNull(yearMonth);
        if (contains(yearMonth)) {
            throw new DuplicateMonthException();
        }
        Month toAdd = new Month(yearMonth);
        YearMonth prevYearMonth = yearMonth.minusMonths(1);
        if (contains(prevYearMonth)) {
            Month prevMonth = find(prevYearMonth);
            toAdd.setBudget(prevMonth.getBudget());
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the month {@code target} in the list with {@code editedMonth}.
     * {@code target} must exist in the list.
     * The month identity of {@code editedMonth} must not be the same as another existing month in the list.
     */
    public void setMonth(Month target, Month editedMonth) {
        requireAllNonNull(target, editedMonth);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MonthNotFoundException();
        }

        if (!target.isSameMonth(editedMonth) && contains(editedMonth)) {
            throw new DuplicateMonthException();
        }

        internalList.set(index, editedMonth);
    }

    /**
     * Removes the equivalent month from the list.
     * The month must exist in the list.
     */
    public void remove(Month toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MonthNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code replacement}.
     * {@code months} must not contain duplicate persons.
     */
    public void setMonths(UniqueMonthList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code months}.
     * {@code months} must not contain duplicate persons.
     */
    public void setMonths(List<Month> months) {
        requireAllNonNull(months);
        if (!monthsAreUnique(months)) {
            throw new DuplicateMonthException();
        }

        internalList.setAll(months);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Month> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /// financial-record

    /**
     * Finds the month which the given financial record belongs to.
     * If the month does not exit in the list, a new month will be added.
     */
    public Month findFinancialRecordMonth(FinancialRecord r) {
        requireNonNull(r);
        YearMonth yearMonth = YearMonthParser.getYearMonth(r.getTimestamp());
        if (!contains(yearMonth)) {
            add(yearMonth);
        }
        return find(yearMonth);
    }

    /**
     * Adds a financial record to the budget tracker.
     * Adds a month to the budget tracker.
     */
    public void addFinancialRecord(FinancialRecord r) {
        requireNonNull(r);
        Month month = findFinancialRecordMonth(r);
        month.addFinancialRecord(r);
    }

    /**
     * Replaces the given financial record {@code target} in the list with {@code editedRecord}.
     * {@code target} must exist in the budget tracker.
     */
    public void setFinancialRecord(FinancialRecord target, FinancialRecord editedRecord) {
        requireNonNull(editedRecord);
        Month targetMonth = findFinancialRecordMonth(target);
        Month editedMonth = findFinancialRecordMonth(editedRecord);
        targetMonth.deleteFinancialRecord(editedRecord);
        editedMonth.addFinancialRecord(editedRecord);
    }

    /**
     * Removes {@code key} from this {@code BudgetTracker}.
     * {@code key} must exist in the budget tracker.
     */
    public void removeFinancialRecord(FinancialRecord key) {
        requireNonNull(key);
        Month month = findFinancialRecordMonth(key);
        month.deleteFinancialRecord(key);
    }

    /**
     * Removes {@code key} from this {@code BudgetTracker}.
     * {@code key} must exist in the budget tracker.
     */
    public void filterByCategory(Category category) {
        requireNonNull(category);
        for (Month m : internalList) {
            m.filterByCategory(category);
        }
    }

    /**
     * Returns a specific month's financial record list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<FinancialRecord> getFinancialRecordOfMonth(YearMonth month) {
        return find(month).getFinancialRecordList();
    }

    /// budget

    /**
     * Sets the budget for the following months.
     *
     * @param budget The specified budget to be set.
     */
    public void setBudget(Budget budget) {
        requireNonNull(budget);
        YearMonth currYearMonth = YearMonth.now();
        Month currentMonth = find(currYearMonth);
        currentMonth.setBudget(budget);
        for (int i = 1; i <= 12; i++) {
            Month month = find(currYearMonth.plusMonths(i));
            month.setBudget(budget);
        }
    }

    @Override
    public Iterator<Month> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueMonthList // instanceof handles nulls
            && internalList.equals(((UniqueMonthList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code months} contains only unique months.
     */
    private boolean monthsAreUnique(List<Month> months) {
        for (int i = 0; i < months.size() - 1; i++) {
            for (int j = i + 1; j < months.size(); j++) {
                if (months.get(i).isSameMonth(months.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
