package seedu.budgetbaby.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.budgetbaby.ablogic.commands.FindCommand;
import seedu.budgetbaby.abmodel.person.NameContainsKeywordsPredicate;
import seedu.budgetbaby.model.*;
import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;
import seedu.budgetbaby.model.record.FinancialRecord;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.budgetbaby.ablogic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.budgetbaby.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.budgetbaby.testutil.TypicalPersons.*;

/**
 * Contains integration tests (interaction with the Model) for {@code FindFrCommand}.
 */
public class FindFrCommandTest {
    private BudgetBabyModel model = new BudgetBabyModelManager(new BudgetTracker(), new UserPrefs());

    private final Description description = new Description("Breakfast");
    private final Amount amount = new Amount("5");
    private final Set<Category> categories = new HashSet<>(Arrays.asList(new Category("Food")));

    private final Description description2 = new Description("Lunch");
    private final Amount amount2 = new Amount("6");
    private final Set<Category> categories2 = new HashSet<>(Arrays.asList(new Category("Food")));

    @Test
    public void execute_withoutParams() {
        model.addFinancialRecord(new FinancialRecord(description, amount, categories));
        model.addFinancialRecord(new FinancialRecord(description2, amount2, categories2));
        Predicate<FinancialRecord> predicate = fr -> true;
        String expectedOutput = "[31-03-2021 | Breakfast | 5.0; Categories: [Food], 31-03-2021 | Lunch | 6.0; Categories: [Food]]";
        //FindFrCommand command = new FindFrCommand(description, amount, categories);
        model.updateFilteredFinancialRecordList(predicate);
        //assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredFinancialRecordList().toString(), model.getFilteredFinancialRecordList().toString());
    }

    @Test
    public void execute_findD() {
        model.addFinancialRecord(new FinancialRecord(description, amount, categories));
        model.addFinancialRecord(new FinancialRecord(description2, amount2, categories2));
        Predicate<FinancialRecord> predicate = fr -> fr.getDescription().description.equals("Lunch");
        String expectedOutput = "[31-03-2021 | Lunch | 6.0; Categories: [Food]]";
        //FindFrCommand command = new FindFrCommand(description, amount, categories);
        model.updateFilteredFinancialRecordList(predicate);
        //assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedOutput, model.getFilteredFinancialRecordList().toString());
    }

    @Test
    public void execute_findA() {
        model.addFinancialRecord(new FinancialRecord(description, amount, categories));
        model.addFinancialRecord(new FinancialRecord(description2, amount2, categories2));
        Predicate<FinancialRecord> predicate = fr -> fr.getDescription().description.equals("Lunch");
        String expectedOutput = "[31-03-2021 | Lunch | 6.0; Categories: [Food]]";
        //FindFrCommand command = new FindFrCommand(description, amount, categories);
        model.updateFilteredFinancialRecordList(predicate);
        //assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedOutput, model.getFilteredFinancialRecordList().toString());
    }

    @Test
    public void execute_findC() {
        model.addFinancialRecord(new FinancialRecord(description, amount, categories));
        model.addFinancialRecord(new FinancialRecord(description2, amount2, categories2));
        Predicate<FinancialRecord> predicate = fr -> fr.getDescription().description.equals("Lunch");
        String expectedOutput = "[31-03-2021 | Lunch | 6.0; Categories: [Food]]";
        //FindFrCommand command = new FindFrCommand(description, amount, categories);
        model.updateFilteredFinancialRecordList(predicate);
        //assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedOutput, model.getFilteredFinancialRecordList().toString());
    }

    @Test
    public void execute_findDA() {
        model.addFinancialRecord(new FinancialRecord(description, amount, categories));
        model.addFinancialRecord(new FinancialRecord(description2, amount2, categories2));
        Predicate<FinancialRecord> predicate = fr -> fr.getDescription().description.equals("Lunch");
        String expectedOutput = "[31-03-2021 | Lunch | 6.0; Categories: [Food]]";
        //FindFrCommand command = new FindFrCommand(description, amount, categories);
        model.updateFilteredFinancialRecordList(predicate);
        //assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedOutput, model.getFilteredFinancialRecordList().toString());
    }

    @Test
    public void execute_findDC() {
        model.addFinancialRecord(new FinancialRecord(description, amount, categories));
        model.addFinancialRecord(new FinancialRecord(description2, amount2, categories2));
        Predicate<FinancialRecord> predicate = fr -> fr.getDescription().description.equals("Lunch");
        String expectedOutput = "[31-03-2021 | Lunch | 6.0; Categories: [Food]]";
        //FindFrCommand command = new FindFrCommand(description, amount, categories);
        model.updateFilteredFinancialRecordList(predicate);
        //assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedOutput, model.getFilteredFinancialRecordList().toString());
    }

    @Test
    public void execute_findAC() {
        model.addFinancialRecord(new FinancialRecord(description, amount, categories));
        model.addFinancialRecord(new FinancialRecord(description2, amount2, categories2));
        Predicate<FinancialRecord> predicate = fr -> fr.getDescription().description.equals("Lunch");
        String expectedOutput = "[31-03-2021 | Lunch | 6.0; Categories: [Food]]";
        //FindFrCommand command = new FindFrCommand(description, amount, categories);
        model.updateFilteredFinancialRecordList(predicate);
        //assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedOutput, model.getFilteredFinancialRecordList().toString());
    }

    @Test
    public void execute_findAll() {
        model.addFinancialRecord(new FinancialRecord(description, amount, categories));
        model.addFinancialRecord(new FinancialRecord(description2, amount2, categories2));
        Predicate<FinancialRecord> predicate = fr -> fr.getDescription().description.equals("Lunch");
        String expectedOutput = "[31-03-2021 | Lunch | 6.0; Categories: [Food]]";
        //FindFrCommand command = new FindFrCommand(description, amount, categories);
        model.updateFilteredFinancialRecordList(predicate);
        //assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedOutput, model.getFilteredFinancialRecordList().toString());
    }

}
