package seedu.budgetbaby.logic.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.budgetbaby.model.BudgetBabyModelManager;
import seedu.budgetbaby.model.record.Category;

public class StatisticsTest {

    Statistics statistics = new Statistics(new BudgetBabyModelManager());

    @Test
    public void fillPastMonths() {
        assertTrue(statistics.fillPastMonths());
    }

    @Test
    public void getPastMonths() {
        assertEquals(6, statistics.getPastMonths().size());
    }

    @Test
    public void getPastMonthsStatistics() {
        assertEquals(6, statistics.getPastMonthStatistics().size());
    }

    @Test
    public void allCategories() {
        assertEquals(0, statistics.allCategories().size());
    }

    @Test
    public void getAllUnsortedCategories() {
        assertEquals(0, statistics.getAllUnsortedCategories().size());
    }

    @Test
    public void getTopCategories() {
        assertEquals(0, statistics.getTopCategories().size());
    }
}
