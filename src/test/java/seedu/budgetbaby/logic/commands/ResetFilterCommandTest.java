package seedu.budgetbaby.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.budgetbaby.testutil.TypicalBudgetTracker.getTypicalBudgetTracker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.logic.parser.FindFrCommandParser;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.BudgetBabyModelManager;
import seedu.budgetbaby.model.BudgetTracker;
import seedu.budgetbaby.model.UserPrefs;
import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * Contains integration tests (interaction with the Model) for {@code ResetFilterCommand}.
 */
public class ResetFilterCommandTest {
    private BudgetBabyModel model = new BudgetBabyModelManager(getTypicalBudgetTracker(), new UserPrefs());
    private FindFrCommandParser find = new FindFrCommandParser();
    private ResetFilterCommand reset = new ResetFilterCommand();
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private LocalDate currDate = LocalDate.now();

    @Test
    public void execute() throws ParseException, CommandException {
        String expectedOutput = "[" + dtf.format(currDate) + " | Breakfast | 5.00; Categories: "
                + "[Food], " + dtf.format(currDate) + " | Lunch | 6.00; Categories: [Food]]";
        find.parse(" d/Breakfast").execute(model);
        reset.execute(model);
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(expectedOutput, actualOutput);
    }

}
