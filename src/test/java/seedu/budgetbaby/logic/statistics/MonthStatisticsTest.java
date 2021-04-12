package seedu.budgetbaby.logic.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import seedu.budgetbaby.logic.parser.YearMonthParser;
import seedu.budgetbaby.model.month.Month;
import seedu.budgetbaby.model.record.FinancialRecord;
import seedu.budgetbaby.model.record.FinancialRecordList;
import seedu.budgetbaby.testutil.FinancialRecordBuilder;
import seedu.budgetbaby.testutil.MonthBuilder;

public class MonthStatisticsTest {

    @Test
    public void getMonth() {
        Month monthCopy = new MonthBuilder().build();
        assertEquals(YearMonthParser.getYearMonth(new Date()), monthCopy.getMonth());
    }

    @Test
    public void getExpenses() {
        FinancialRecordList list = new FinancialRecordList();
        FinancialRecord fr = new FinancialRecordBuilder().withAmount("10").build();
        list.add(fr);
        Month month = new MonthBuilder().withRecords(list).build();
        MonthStatistics ms = new MonthStatistics(month);
        assertEquals(10, ms.getAmount());
    }
}
