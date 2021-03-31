package seedu.budgetbaby.model;

import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.YearMonth;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.budgetbaby.commons.core.GuiSettings;
import seedu.budgetbaby.commons.core.LogsCenter;
import seedu.budgetbaby.model.budget.Budget;
import seedu.budgetbaby.model.month.Month;
import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * Represents the in-memory model of the budget tracker data.
 */
public class BudgetBabyModelManager implements BudgetBabyModel {
    private static final Logger logger = LogsCenter.getLogger(BudgetBabyModelManager.class);

    private final BudgetTracker budgetTracker;
    private final UserPrefs userPrefs;
    private final FilteredList<Month> filteredMonths;
    private FilteredList<FinancialRecord> filteredFinancialRecords;

    /**
     * Initializes a ModelManager with the given budgetTracker and userPrefs.
     */
    public BudgetBabyModelManager(ReadOnlyBudgetTracker budgetTracker, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(budgetTracker, userPrefs);

        logger.fine("Initializing with budget tracker: " + budgetTracker + " and user prefs " + userPrefs);

        this.budgetTracker = new BudgetTracker(budgetTracker);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredMonths = new FilteredList<>(this.budgetTracker.getMonthList());
        updateFilteredMonthList(month -> month.isSameMonth(YearMonth.now()));
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
        updateFilteredMonthList(m -> m.isSameMonth(month));
        filteredFinancialRecords = new FilteredList<>(
            this.budgetTracker.getFinancialRecordListOfMonth(month));
    }

    @Override
    public void deleteFinancialRecord(FinancialRecord target) {
        budgetTracker.removeFinancialRecord(target);
    }

    @Override
    public void addFinancialRecord(FinancialRecord record) {
        FinancialRecord toAdd = record;
        budgetTracker.addFinancialRecord(record);
        if (!record.getMonth().equals(filteredMonths.get(0).getMonth())) {
            setCurrentDisplayMonth(record.getMonth());
        }
    }

    @Override
    public void setFinancialRecord(FinancialRecord target, FinancialRecord editedRecord) {
        requireAllNonNull(target, editedRecord);
        budgetTracker.setFinancialRecord(target, editedRecord);
        if (!editedRecord.getMonth().equals(filteredMonths.get(0))) {
            setCurrentDisplayMonth(editedRecord.getMonth());
        }
    }

    @Override
    public boolean findFinancialRecord(Description description, Amount amount, Set<Category> categories) {
        assert true;

        boolean result;

        Predicate<FinancialRecord> findD = fr -> fr.getDescription().description.equals(description.description);

        Predicate<FinancialRecord> findA = fr -> fr.getAmount().getValue().equals(amount.getValue());

        Predicate<FinancialRecord> findC = fr -> fr.getCategories().containsAll(categories);

        Predicate<FinancialRecord> findDA = fr -> fr.getDescription().description.equals(description.description)
            && fr.getAmount().getValue().equals(amount.getValue());

        Predicate<FinancialRecord> findDC = fr -> fr.getDescription().description.equals(description.description)
            && fr.getCategories().containsAll(categories);

        Predicate<FinancialRecord> findAC = fr -> fr.getAmount().getValue().equals(amount.getValue())
            && fr.getCategories().containsAll(categories);

        Predicate<FinancialRecord> findAll = fr -> fr.getDescription().description.equals(description.description)
            && fr.getAmount().getValue().equals(amount.getValue())
            && fr.getCategories().containsAll(categories);

        if (description == null) {
            if (amount == null && categories != null) {
                result = updateFilteredFinancialRecordList(findC);
            } else if (amount != null && categories == null) {
                result = updateFilteredFinancialRecordList(findA);
            } else {
                result = updateFilteredFinancialRecordList(findAC);
            }
        } else if (amount == null) {
            if (categories == null) {
                result = updateFilteredFinancialRecordList(findD);
            } else {
                result = updateFilteredFinancialRecordList(findDC);
            }
        } else if (categories == null) {
            result = updateFilteredFinancialRecordList(findDA);
        } else {
            result = updateFilteredFinancialRecordList(findAll);
        }

        return result;
    }

    @Override
    public void resetFilter() {
        assert true;
        updateFilteredFinancialRecordList(fr -> true);
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
    public boolean updateFilteredFinancialRecordList(Predicate<FinancialRecord> predicate) {
        requireNonNull(predicate);
        FilteredList<FinancialRecord> tempFilteredList = new FilteredList<>(filteredFinancialRecords);
        tempFilteredList.setPredicate(predicate);
        if (!tempFilteredList.isEmpty()) {
            filteredFinancialRecords.setPredicate(predicate);
            return true;
        } else {
            return false;
        }
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
