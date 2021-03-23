package seedu.budgetbaby.model;

import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.YearMonth;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.budgetbaby.commons.core.GuiSettings;
import seedu.budgetbaby.commons.core.LogsCenter;
import seedu.budgetbaby.model.budget.Budget;
import seedu.budgetbaby.model.month.Month;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * Represents the in-memory model of the address book data.
 */
public class BudgetBabyModelManager implements BudgetBabyModel {
    private static final Logger logger = LogsCenter.getLogger(BudgetBabyModelManager.class);

    private final BudgetTracker budgetTracker;
    private final UserPrefs userPrefs;
    private final FilteredList<Month> filteredMonths;
    private final Month currentDisplayMonth;
    private final FilteredList<FinancialRecord> filteredFinancialRecords;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public BudgetBabyModelManager(ReadOnlyBudgetTracker budgetTracker, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(budgetTracker, userPrefs);

        logger.fine("Initializing with budget tracker: " + budgetTracker + " and user prefs " + userPrefs);

        this.budgetTracker = new BudgetTracker(budgetTracker);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredMonths = new FilteredList<>(this.budgetTracker.getMonthList());
        currentDisplayMonth = this.budgetTracker.getCurrentDisplayMonth();
        filteredFinancialRecords = new FilteredList<>(
            this.budgetTracker.getFinancialRecordListOfMonth(YearMonth.now()));
    }

    public BudgetBabyModelManager() {
        this(new BudgetTracker(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getBudgetBabyFilePath() {
        return userPrefs.getBudgetBabyFilePath();
    }

    @Override
    public void setBudgetBabyFilePath(Path budgetBabyFilePath) {
        requireNonNull(budgetBabyFilePath);
        userPrefs.setBudgetBabyFilePath(budgetBabyFilePath);
    }

    //=========== BudgetTracker ================================================================================

    @Override
    public void setBudgetTracker(ReadOnlyBudgetTracker budgetTracker) {
        this.budgetTracker.resetData(budgetTracker);
    }

    @Override
    public ReadOnlyBudgetTracker getBudgetTracker() {
        return budgetTracker;
    }

    @Override
    public void deleteMonth(Month target) {
        budgetTracker.removeMonth(target);
    }

    @Override
    public void addMonth(Month month) {
        budgetTracker.addMonth(month);
        updateFilteredMonthList(PREDICATE_SHOW_ALL_RECORDS);
    }

    @Override
    public void setMonth(Month target, Month editedMonth) {
        requireAllNonNull(target, editedMonth);

        budgetTracker.setMonth(target, editedMonth);
    }

    @Override
    public void setCurrentDisplayMonth(YearMonth month) {
        budgetTracker.setCurrentDisplayMonth(month);
    };

    @Override
    public Month getCurrentDisplayMonth() {
        return currentDisplayMonth;
    }

    @Override
    public void deleteFinancialRecord(FinancialRecord target) {
        budgetTracker.removeFinancialRecord(target);
    }

    @Override
    public void addFinancialRecord(FinancialRecord record) {
        budgetTracker.addFinancialRecord(record);
    }

    @Override
    public void setFinancialRecord(FinancialRecord target, FinancialRecord editedRecord) {
        requireAllNonNull(target, editedRecord);
        budgetTracker.setFinancialRecord(target, editedRecord);
    }

    @Override
    public void filterByCategory(Category category) {
        requireAllNonNull(category);
        budgetTracker.filterByCategory(category);
    }

    @Override
    public void setBudget(Budget budget) {
        budgetTracker.setBudget(budget);
    }

    //=========== Filtered List Accessors =============================================================

    @Override
    public ObservableList<Month> getFilteredMonthList() {
        return filteredMonths;
    }

    @Override
    public void updateFilteredMonthList(Predicate<Month> predicate) {
        requireNonNull(predicate);
        filteredMonths.setPredicate(predicate);
    }

    @Override
    public ObservableList<FinancialRecord> getFilteredFinancialRecordList() {
        return filteredFinancialRecords;
    }

    @Override
    public void updateFilteredFinancialRecordList(Predicate<FinancialRecord> predicate) {
        requireNonNull(predicate);
        filteredFinancialRecords.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof BudgetBabyModelManager)) {
            return false;
        }

        // state check
        BudgetBabyModelManager other = (BudgetBabyModelManager) obj;
        return budgetTracker.equals(other.budgetTracker)
            && userPrefs.equals(other.userPrefs)
            && filteredMonths.equals(other.filteredMonths);
    }

}
