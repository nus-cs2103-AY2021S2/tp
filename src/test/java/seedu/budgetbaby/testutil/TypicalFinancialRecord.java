package seedu.budgetbaby.testutil;

import seedu.budgetbaby.model.month.Month;
import seedu.budgetbaby.model.record.FinancialRecord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CAIFAN;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_DESCRIPTION_EARRING;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_AMOUNT_CAIFAN;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_AMOUNT_EARRING;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_TIMESTAMP_CAIFAN;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_TIMESTAMP_EARRING;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_TAG_FOOD;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_TAG_SHOPPING;


/**
 * A utility class containing a list of {@code FinancialRecord} objects to be used in tests.
 */
public class TypicalFinancialRecord {

    public static final FinancialRecord LUNCH = new FinancialRecordBuilder()
        .withDescription("Lunch")
        .withAmount("10.0")
        .withTimestamp("01-01-2021")
        .build();
    public static final FinancialRecord DINNER = new FinancialRecordBuilder()
        .withDescription("Dinner")
        .withAmount("20.0")
        .withTimestamp("02-02-2021")
        .build();
    public static final FinancialRecord BUBBLETEA = new FinancialRecordBuilder()
        .withDescription("Bubble tea")
        .withAmount("3.45")
        .withTimestamp("03-03-2021")
        .build();

    public static final FinancialRecord CAIFAN = new FinancialRecordBuilder()
        .withDescription(VALID_DESCRIPTION_CAIFAN)
        .withAmount(VALID_AMOUNT_CAIFAN)
        .withTimestamp(VALID_TIMESTAMP_CAIFAN)
        .withCategories(VALID_TAG_FOOD)
        .build();
    public static final FinancialRecord EARRING = new FinancialRecordBuilder()
        .withDescription(VALID_DESCRIPTION_EARRING)
        .withAmount(VALID_AMOUNT_EARRING)
        .withTimestamp(VALID_TIMESTAMP_EARRING)
        .withCategories(VALID_TAG_SHOPPING)
        .build();

    private TypicalFinancialRecord() {
    }

    /**
     * Returns a {@code Month} with all the typical financial records.
     */
    public static Month getTypicalMonth() {
        Month m = new MonthBuilder().build();
        for (FinancialRecord record : getTypicalFinancialRecords()) {
            m.addFinancialRecord(record);
        }
        return m;
    }

    public static List<FinancialRecord> getTypicalFinancialRecords() {
        return new ArrayList<>(Arrays.asList(LUNCH, DINNER, BUBBLETEA, CAIFAN, EARRING));
    }
}
