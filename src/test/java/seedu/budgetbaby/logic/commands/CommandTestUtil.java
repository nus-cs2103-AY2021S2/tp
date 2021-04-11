package seedu.budgetbaby.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.budgetbaby.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.budgetbaby.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.budgetbaby.commons.core.index.Index;
import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.model.BudgetBabyModel;
import seedu.budgetbaby.model.BudgetTracker;
import seedu.budgetbaby.model.record.Description;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_BREAKFAST = "Carrot Cake";
    public static final String VALID_NAME_LUNCH = "Chicken Chop";
    public static final String VALID_NAME_DINNER = "Curry Chicken Noodles";
    public static final String VALID_AMOUNT_BREAKFAST = "4.0";
    public static final String VALID_AMOUNT_LUNCH = "5.0";
    public static final String VALID_AMOUNT_DINNER = "6.5";
    public static final String VALID_TIME_BREAKFAST = "2021-04-01";
    public static final String VALID_TIME_LUNCH = "2021-04-02";
    public static final String VALID_TIME_DINNER = "2021-04-03";
    public static final String VALID_CATEGORY_BREAKFAST = "breakfast";
    public static final String VALID_CATEGORY_LUNCH = "lunch";
    public static final String VALID_CATEGORY_DINNER = "dinner";

    public static final String NAME_DESC_BREAKFAST = " " + PREFIX_DESCRIPTION + VALID_NAME_BREAKFAST;
    public static final String NAME_DESC_LUNCH = " " + PREFIX_DESCRIPTION + VALID_NAME_LUNCH;
    public static final String NAME_DESC_DINNER = " " + PREFIX_DESCRIPTION + VALID_NAME_DINNER;
    public static final String AMOUNT_DESC_BREAKFAST = " " + PREFIX_AMOUNT + VALID_AMOUNT_BREAKFAST;
    public static final String AMOUNT_DESC_LUNCH = " " + PREFIX_AMOUNT + VALID_AMOUNT_LUNCH;
    public static final String AMOUNT_DESC_BOB = " " + PREFIX_AMOUNT + VALID_AMOUNT_DINNER;
    public static final String TIME_DESC_BREAKFAST = " " + PREFIX_TIME + VALID_TIME_BREAKFAST;
    public static final String TIME_DESC_LUNCH = " " + PREFIX_TIME + VALID_TIME_LUNCH;
    public static final String TIME_DESC_DINNER = " " + PREFIX_TIME + VALID_TIME_DINNER;
    public static final String CATEGORY_DESC_BREAKFAST = " " + PREFIX_CATEGORY + VALID_CATEGORY_BREAKFAST;
    public static final String CATEGORY_DESC_LUNCH = " " + PREFIX_CATEGORY + VALID_CATEGORY_LUNCH;
    public static final String CATEGORY_DESC_DINNER = " " + PREFIX_CATEGORY + VALID_CATEGORY_DINNER;

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(BudgetBabyCommand command, BudgetBabyModel actualModel,
                                            CommandResult expectedCommandResult,
                                            BudgetBabyModel expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(BudgetBabyCommand,
     * BudgetBabyModel, CommandResult, BudgetBabyModel)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(BudgetBabyCommand command, BudgetBabyModel actualModel,
                                            String expectedMessage, BudgetBabyModel expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(BudgetBabyCommand command, BudgetBabyModel actualModel,
                                            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        BudgetTracker expectedBudgetTracker = new BudgetTracker(actualModel.getBudgetTracker());
        List<FinancialRecord> expectedFilteredList = new ArrayList<>(actualModel.getFilteredFinancialRecordList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedBudgetTracker, actualModel.getBudgetTracker());
        assertEquals(expectedFilteredList, actualModel.getFilteredFinancialRecordList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the financial record at the given {@code targetIndex} in the
     * {@code model}'s budget list.
     */
    public static void showFrAtIndex(BudgetBabyModel model, List<Index> targetIndex) {
        assertTrue(targetIndex.size() <= model.getFilteredFinancialRecordList().size());
        ArrayList<Description> frList = new ArrayList<>();

        for (Index i : targetIndex) {
            FinancialRecord fr = model.getFilteredFinancialRecordList().get(i.getZeroBased());
            frList.add(fr.getDescription());
        }

        model.updateFilteredFinancialRecordList(record -> frList.contains(record.getDescription()));

        assertEquals(targetIndex.size(), model.getFilteredFinancialRecordList().size());
    }

}



