package seedu.budgetbaby.testutil;

import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_AMOUNT_BREAKFAST;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_AMOUNT_DINNER;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_AMOUNT_LUNCH;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_CATEGORY_BREAKFAST;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_CATEGORY_DINNER;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_CATEGORY_LUNCH;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_NAME_BREAKFAST;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_NAME_DINNER;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_NAME_LUNCH;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_TIME_BREAKFAST;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_TIME_DINNER;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_TIME_LUNCH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.budgetbaby.model.BudgetTracker;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * A utility class containing a list of {@code FinancialRecord} objects to be used in tests.
 */
public class TypicalFinancialRecords {

    public static final FinancialRecord BREAKFAST = new FinancialRecordBuilder()
            .withDescription(VALID_NAME_BREAKFAST).withAmount(VALID_AMOUNT_BREAKFAST)
            .withTime(VALID_TIME_BREAKFAST).withCategories(VALID_CATEGORY_BREAKFAST)
            .build();

    public static final FinancialRecord LUNCH = new FinancialRecordBuilder()
            .withDescription(VALID_NAME_LUNCH).withAmount(VALID_AMOUNT_LUNCH)
            .withTime(VALID_TIME_LUNCH).withCategories(VALID_CATEGORY_LUNCH)
            .build();

    public static final FinancialRecord DINNER = new FinancialRecordBuilder()
            .withDescription(VALID_NAME_DINNER).withAmount(VALID_AMOUNT_DINNER)
            .withTime(VALID_TIME_DINNER).withCategories(VALID_CATEGORY_DINNER)
            .build();

    private TypicalFinancialRecords() {} // prevents instantiation

    /**
     * Returns an {@code BudgetTracker} with all the financial records.
     */
    public static BudgetTracker getTypicalAddressBook() {
        BudgetTracker bt = new BudgetTracker();
        for (FinancialRecord financialRecord : getTypicalFinancialRecords()) {
            bt.addFinancialRecord(financialRecord);
        }
        return bt;
    }

    public static List<FinancialRecord> getTypicalFinancialRecords() {
        return new ArrayList<>(Arrays.asList(BREAKFAST, LUNCH, DINNER));
    }
}
