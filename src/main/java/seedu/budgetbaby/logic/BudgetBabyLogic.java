package seedu.budgetbaby.logic;

import java.nio.file.Path;
import java.time.YearMonth;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.budgetbaby.commons.core.GuiSettings;
import seedu.budgetbaby.logic.commands.CommandResult;
import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.logic.statistics.MonthStatistics;
import seedu.budgetbaby.model.ReadOnlyBudgetTracker;
import seedu.budgetbaby.model.month.Month;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * API of the Logic component
 */
public interface BudgetBabyLogic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Sets the current display month to the given {@code month}.
     */
    void setCurrentDisplayMonth(YearMonth month);

    /**
     * Returns the BudgetTracker.
     */
    ReadOnlyBudgetTracker getBudgetTracker();

    /**
     * Returns an unmodifiable view of the filtered list of months
     */
    ObservableList<Month> getFilteredMonthList();

    /**
     * Returns an unmodifiable view of the filtered list of financial records
     * Default showing all financial records of the current month.
     */
    ObservableList<FinancialRecord> getFilteredFinancialRecordList();

    /**
     * Returns a String containing information on past 6 months of budget and expenditure.
     * @return MonthStatistics objects of past 6 months.
     */
    List<MonthStatistics> getPastMonthStatistics();

    /**
     * Returns the user prefs' budget baby file path.
     */
    Path getBudgetBabyFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
