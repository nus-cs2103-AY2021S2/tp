package seedu.budgetbaby.logic.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.budgetbaby.model.record.Category;

public class CategoryStatisticsTest {

    @Test
    public void getAmount() {
        CategoryStatistics cs = new CategoryStatistics(new Category("food"), 10);
        assertEquals(10, cs.getAmount());
    }

    @Test
    public void getCategory() {
        CategoryStatistics cs = new CategoryStatistics(new Category("food"), 10);
        assertEquals("food", cs.getCategory().getCategory());
    }
}
