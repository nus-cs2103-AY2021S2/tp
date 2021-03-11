package seedu.budgetbaby.model.budget;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BudgetTest {
    private final static double THRESHOLD = 0.0001;

    @Test
    public void getAmountTest() {
        Budget budget = new Budget(100);
        double amount = budget.getAmount();
        assertEquals(amount, 100, THRESHOLD);
    }

    @Test
    public void setAmountTest() {
        Budget budget = new Budget();
        budget.setAmount(200);
        double amount = budget.amount;
        assertEquals(amount, 200, THRESHOLD);
    }

    @Test
    public void toStringTest() {
        Budget budget = new Budget();
        assertEquals(budget.toString(), "No budget set");
        budget.setAmount(100);
        assertEquals(budget.toString(), "$100.00");
    }
}
