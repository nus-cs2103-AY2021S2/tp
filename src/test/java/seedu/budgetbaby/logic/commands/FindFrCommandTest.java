package seedu.budgetbaby.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_TYPICAL_BUDGET_TRACKER_FILTER_FOOD;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_TYPICAL_BUDGET_TRACKER_FILTER_LUNCH;
import static seedu.budgetbaby.testutil.TypicalBudgetTracker.getTypicalBudgetTracker;

import org.junit.jupiter.api.Test;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.logic.parser.FindFrCommandParser;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.BudgetBabyModelManager;
import seedu.budgetbaby.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code FindFrCommand}.
 */
public class FindFrCommandTest {
    private BudgetBabyModel model = new BudgetBabyModelManager(getTypicalBudgetTracker(), new UserPrefs());
    private FindFrCommandParser find = new FindFrCommandParser();

    @Test
    public void result_none() throws ParseException, CommandException {
        String expectedOutput = "[]";
        find.parse(" d/Dinner").execute(model);
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void execute_findD() throws ParseException, CommandException {
        find.parse(" d/Lunch").execute(model);
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_BUDGET_TRACKER_FILTER_LUNCH, actualOutput);
    }

    @Test
    public void execute_findA() throws ParseException, CommandException {
        find.parse(" a/6").execute(model);
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_BUDGET_TRACKER_FILTER_LUNCH, actualOutput);
    }

    @Test
    public void execute_findC() throws ParseException, CommandException {
        find.parse(" c/Food").execute(model);
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_BUDGET_TRACKER_FILTER_FOOD, actualOutput);
    }

    @Test
    public void execute_findDA() throws ParseException, CommandException {
        find.parse(" d/Lunch a/6").execute(model);
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_BUDGET_TRACKER_FILTER_LUNCH, actualOutput);
    }

    @Test
    public void execute_findDC() throws ParseException, CommandException {
        find.parse(" d/Lunch c/Food").execute(model);
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_BUDGET_TRACKER_FILTER_LUNCH, actualOutput);
    }

    @Test
    public void execute_findAC() throws ParseException, CommandException {
        find.parse(" a/6 c/Food").execute(model);
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_BUDGET_TRACKER_FILTER_LUNCH, actualOutput);
    }

    @Test
    public void execute_findAll() throws ParseException, CommandException {
        find.parse(" d/Lunch a/6 c/Food").execute(model);
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_BUDGET_TRACKER_FILTER_LUNCH, actualOutput);
    }

}
