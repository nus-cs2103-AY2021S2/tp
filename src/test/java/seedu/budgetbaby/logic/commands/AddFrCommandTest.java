package seedu.budgetbaby.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_TYPICAL_ADD;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_TYPICAL_ADD_TIMESTAMP;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_TYPICAL_ADD_CATEGORY;

import org.junit.jupiter.api.Test;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.logic.parser.AddFrCommandParser;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.BudgetBabyModelManager;
import seedu.budgetbaby.model.BudgetTracker;
import seedu.budgetbaby.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code AddFrCommand}.
 */
public class AddFrCommandTest {
    private BudgetBabyModel model = new BudgetBabyModelManager(new BudgetTracker(),
        new UserPrefs());
    private AddFrCommandParser add = new AddFrCommandParser();

    @Test
    public void execute_addLunch() throws ParseException, CommandException {
        System.out.println(model.getFilteredFinancialRecordList().toString());
        add.parse(" d/Dinner a/10.5").execute(model);
        System.out.println(model.getFilteredFinancialRecordList().toString());
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_ADD, actualOutput);
    }

    @Test
    public void execute_addLunch_withTimestamp() throws ParseException, CommandException {
        System.out.println(model.getFilteredFinancialRecordList().toString());
        add.parse(" d/Dinner a/10.5 t/01-01-2021").execute(model);
        System.out.println(model.getFilteredFinancialRecordList().toString());
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_ADD_TIMESTAMP, actualOutput);
    }

    @Test
    public void execute_addLunch_withCategory() throws ParseException, CommandException {
        System.out.println(model.getFilteredFinancialRecordList().toString());
        add.parse(" d/Dinner a/10.5 c/Food").execute(model);
        System.out.println(model.getFilteredFinancialRecordList().toString());
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_ADD_CATEGORY, actualOutput);
    }
}
