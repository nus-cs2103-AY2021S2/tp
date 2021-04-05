package seedu.budgetbaby.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_TYPICAL_BUDGET_TRACKER_ORIGINAL_LIST;
import static seedu.budgetbaby.testutil.TypicalBudgetTracker.getTypicalBudgetTracker;

import org.junit.jupiter.api.Test;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.logic.parser.FindFrCommandParser;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.BudgetBabyModelManager;
import seedu.budgetbaby.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code ResetFilterCommand}.
 */
public class ResetFilterCommandTest {
    private BudgetBabyModel model = new BudgetBabyModelManager(getTypicalBudgetTracker(), new UserPrefs());
    private FindFrCommandParser find = new FindFrCommandParser();
    private ResetFilterCommand reset = new ResetFilterCommand();

    @Test
    public void execute() throws ParseException, CommandException {
        find.parse(" d/Breakfast").execute(model);
        reset.execute(model);
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_BUDGET_TRACKER_ORIGINAL_LIST, actualOutput);
    }

}
