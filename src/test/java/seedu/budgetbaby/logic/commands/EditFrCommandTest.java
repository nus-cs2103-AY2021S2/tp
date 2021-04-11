package seedu.budgetbaby.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_TYPICAL_EDIT_ALL;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_TYPICAL_EDIT_AMOUNT;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_TYPICAL_EDIT_AMOUNT_CATEGORY;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_TYPICAL_EDIT_CATEGORY;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_TYPICAL_EDIT_DESCRIPTION;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_TYPICAL_EDIT_DESCRIPTION_AMOUNT;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_TYPICAL_EDIT_DESCRIPTION_CATEGORY;
import static seedu.budgetbaby.testutil.TypicalBudgetTracker.getTypicalBudgetTracker;

import org.junit.jupiter.api.Test;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.logic.parser.EditFrCommandParser;
import seedu.budgetbaby.logic.parser.exceptions.ParseException;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.BudgetBabyModelManager;
import seedu.budgetbaby.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code EditFrCommand}.
 */
public class EditFrCommandTest {
    private BudgetBabyModel model = new BudgetBabyModelManager(getTypicalBudgetTracker(), new UserPrefs());
    private EditFrCommandParser edit = new EditFrCommandParser();

    @Test
    public void execute_editD() throws ParseException, CommandException {
        System.out.println(model.getFilteredFinancialRecordList().toString());
        edit.parse(" 2 d/Dinner").execute(model);
        System.out.println(model.getFilteredFinancialRecordList().toString());
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_EDIT_DESCRIPTION, actualOutput);
    }

    @Test
    public void execute_findA() throws ParseException, CommandException {
        edit.parse(" 2 a/10").execute(model);
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_EDIT_AMOUNT, actualOutput);
    }

    @Test
    public void execute_findC() throws ParseException, CommandException {
        edit.parse(" 2 c/Foodie").execute(model);
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_EDIT_CATEGORY, actualOutput);
    }

    @Test
    public void execute_findDA() throws ParseException, CommandException {
        edit.parse(" 2 d/Dinner a/10").execute(model);
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_EDIT_DESCRIPTION_AMOUNT, actualOutput);
    }

    @Test
    public void execute_findDC() throws ParseException, CommandException {
        edit.parse(" 2 d/Dinner c/Foodie").execute(model);
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_EDIT_DESCRIPTION_CATEGORY, actualOutput);
    }

    @Test
    public void execute_findAC() throws ParseException, CommandException {
        edit.parse(" 2 a/10 c/Foodie").execute(model);
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_EDIT_AMOUNT_CATEGORY, actualOutput);
    }

    @Test
    public void execute_editAll() throws ParseException, CommandException {
        edit.parse(" 2 d/Dinner a/10 c/Foodie").execute(model);
        String actualOutput = model.getFilteredFinancialRecordList().toString();
        assertEquals(MESSAGE_TYPICAL_EDIT_ALL, actualOutput);
    }

}
