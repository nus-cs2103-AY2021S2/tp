package seedu.budgetbaby.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.logic.parser.FindFrCommandParser;
import seedu.budgetbaby.logic.parser.ResetFilterCommandParser;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.*;
import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;
import seedu.budgetbaby.model.record.FinancialRecord;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Contains integration tests (interaction with the Model) for {@code ResetFilterCommand}.
 */
public class ResetFilterCommandTest {
    private BudgetBabyModel model = new BudgetBabyModelManager(new BudgetTracker(), new UserPrefs());
    private FindFrCommandParser find = new FindFrCommandParser();
    private ResetFilterCommandParser reset = new ResetFilterCommandParser();

    private final Description description = new Description("Breakfast");
    private final Amount amount = new Amount("5");
    private final Set<Category> categories = new HashSet<>(Arrays.asList(new Category("Food")));

    private final Description description2 = new Description("Lunch");
    private final Amount amount2 = new Amount("6");
    private final Set<Category> categories2 = new HashSet<>(Arrays.asList(new Category("Food")));

    @Test
    public void execute() throws ParseException, CommandException {
        model.addFinancialRecord(new FinancialRecord(description, amount, categories));
        model.addFinancialRecord(new FinancialRecord(description2, amount2, categories2));
        String expectedOutput = "[01-04-2021 | Breakfast | 5.0; Categories: [Food], 01-04-2021 | Lunch | 6.0; Categories: [Food]]";
        find.parse(" d/Breakfast").execute(model);
        reset.parse("").execute(model);
        assertEquals(expectedOutput, model.getFilteredFinancialRecordList().toString());
    }

}
