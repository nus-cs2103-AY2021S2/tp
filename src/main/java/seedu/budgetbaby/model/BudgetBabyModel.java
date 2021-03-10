package seedu.budgetbaby.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.budgetbaby.commons.core.GuiSettings;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * The API of the Model component.
 */
public interface BudgetBabyModel {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<FinancialRecord> PREDICATE_SHOW_ALL_RECORDS = unused -> true;

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
     * Deletes the given person.
     * The person must exist in the address book.
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
}
