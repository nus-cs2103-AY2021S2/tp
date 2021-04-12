package seedu.budgetbaby.testutil;


import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_AMOUNT_CAIFAN;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_AMOUNT_EARRING;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CAIFAN;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_DESCRIPTION_EARRING;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_TAG_FOOD;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_TAG_SHOPPING;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_TIMESTAMP_CAIFAN;
import static seedu.budgetbaby.logic.commands.CommandTestUtil.VALID_TIMESTAMP_EARRING;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.budgetbaby.logic.commands.exceptions.CommandException;
import seedu.budgetbaby.logic.parser.YearMonthParser;
import seedu.budgetbaby.model.month.Month;
import seedu.budgetbaby.model.record.FinancialRecord;

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
        .withTimestamp("02-01-2021")
        .build();
    public static final FinancialRecord BUBBLETEA = new FinancialRecordBuilder()
        .withDescription("Bubble tea")
        .withAmount("3.45")
        .withTimestamp("03-01-2021")
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

    public static final Month JAN = getTypicalMonth1();
    public static final Month FEB = getTypicalMonth2();

    private TypicalFinancialRecord() {
    }

    /**
     * Returns a {@code Month} with all the typical financial records.
     */
    public static Month getTypicalMonth1() {
        Month m = new MonthBuilder().withMonth(
            YearMonthParser.getYearMonth("01-2021")
        ).build();
        try {
            for (FinancialRecord record : getTypicalFinancialRecords1()) {
                m.addFinancialRecord(record);
            }
        } catch (CommandException e) {
            return null;
        }

        return m;
    }

    public static List<FinancialRecord> getTypicalFinancialRecords1() {
        return new ArrayList<>(Arrays.asList(LUNCH, DINNER, BUBBLETEA));
    }

    public static Month getTypicalMonth2() {
        Month m = new MonthBuilder().withMonth(
            YearMonthParser.getYearMonth("02-2021")
        ).build();
        try {
            for (FinancialRecord record : getTypicalFinancialRecords2()) {
                m.addFinancialRecord(record);
            }
        } catch (CommandException e) {
            return null;
        }
        return m;
    }

    public static List<FinancialRecord> getTypicalFinancialRecords2() {
        return new ArrayList<>(Arrays.asList(CAIFAN, EARRING));
    }

}
