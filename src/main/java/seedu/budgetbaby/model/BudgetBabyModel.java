package seedu.budgetbaby.model;

import java.nio.file.Path;
import java.time.YearMonth;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.budgetbaby.commons.core.GuiSettings;
import seedu.budgetbaby.model.budget.Budget;
import seedu.budgetbaby.model.month.Month;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * The API of the Model component.
 */
public interface BudgetBabyModel {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Month> PREDICATE_SHOW_ALL_RECORDS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' budget tracker file path.
     */
    Path getBudgetBabyFilePath();

    /**
     * Sets the user prefs' budget tracker file path.
     */
    void setBudgetBabyFilePath(Path budgetBabyFilePath);

    //=========== BudgetTracker ================================================================================

    /**
     * Replaces budget tracker data with the data in {@code budgetTracker}.
     */
    void setBudgetTracker(ReadOnlyBudgetTracker budgetTracker);

    /**
     * Returns the BudgetTracker
     */
    ReadOnlyBudgetTracker getBudgetTracker();

    /**
     * Adds the given month.
     */
    void addMonth(Month month);

    /**
     * Deletes the given month.
     * The month must exist in the address book.
     */
    void deleteMonth(Month target);

    /**
     * Replaces the given month{@code target} with {@code editedMonth}.
     * {@code target} must exist in the budget tracker.
     */
    void setMonth(Month target, Month editedMonth);

    /**
     * Sets the current display month to the given month{@code month}.
     * If the month does not exist in the budget tracker,
     * create a month representing the given month{@code month} and add to the
     * budget tracker.
     */
    void setCurrentDisplayMonth(YearMonth month);

    /**
     * Returns an unmodifiable view of the filtered month list
     */
    ObservableList<Month> getFilteredMonthList();

    /**
     * Updates the filter of the filtered month list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMonthList(Predicate<Month> predicate);

    /**
     * Returns an unmodifiable view of the filtered financial record list
     */
    ObservableList<FinancialRecord> getFilteredFinancialRecordList();

    /**
     * Updates the filter of the filtered financial record list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFinancialRecordList(Predicate<FinancialRecord> predicate);

    /**
     * Deletes the given financial record.
     * The financial record must exist in the budegt tracker.
     */
    void deleteFinancialRecord(FinancialRecord target);

    /**
     * Adds the given financial record.
     */
    void addFinancialRecord(FinancialRecord record);

    /**
     * Replaces the given financial record {@code target} with {@code editedRecord}.
     * {@code target} must exist in the address book.
     */
    void setFinancialRecord(FinancialRecord target, FinancialRecord editedRecord);

    void filterByCategory(Category category);

    /**
     * Sets the budget for the following months.
     *
     * @param budget The specified budget to be set.
     */
    void setBudget(Budget budget);

}
