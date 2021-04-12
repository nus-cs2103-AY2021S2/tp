package seedu.budgetbaby.model;

import java.nio.file.Path;
import java.time.YearMonth;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.budgetbaby.commons.core.GuiSettings;
import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.month.Month;
import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;
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

    void findMonth(YearMonth month);

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
     * Returns the full month list currently stored in the application.
     *
     * @return full month list.
     */
    ObservableList<Month> getFullMonthList();

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
    boolean updateFilteredFinancialRecordList(Predicate<FinancialRecord> predicate);

    /**
     * Deletes the given financial record.
     * The financial record must exist in the budegt tracker.
     */
    void deleteFinancialRecord(FinancialRecord target);

    /**
     * Adds the given financial record.
     */
    void addFinancialRecord(FinancialRecord record) throws CommandException;

    /**
     * Replaces the given financial record {@code target} with {@code editedRecord}.
     * {@code target} must exist in the address book.
     */
    void setFinancialRecord(FinancialRecord target, FinancialRecord editedRecord) throws CommandException;

    /**
     * Iterates through the financial records and searches for the {@code description} {@code amount} {@code category}.
     * {@code description} {@code amount} {@code category} must exist in the budget tracker.
     */
    boolean findFinancialRecord(Description description, Amount amount, Set<Category> categories);

    /**
     * Resets filters on financial records to display original list
     */
    void resetFilter();

    /**
     * Sets the budget for the following months.
     *
     * @param budget The specified budget to be set.
     */
    void setBudget(Budget budget);

    /**
     * Returns true if the model has previous budget tracker states to restore.
     */
    boolean canUndoBudgetTracker();

    /**
     * Returns true if the model has undone budget tracker states to restore.
     */
    boolean canRedoBudgetTracker();

    /**
     * Restores the model's budget tracker to its previous state.
     */
    void undoBudgetTracker();

    /**
     * Restores the model's budget tracker to its previously undone state.
     */
    void redoBudgetTracker();

    /**
     * Saves the current budget tracker state for undo/redo.
     */
    void commitBudgetTracker();
}
